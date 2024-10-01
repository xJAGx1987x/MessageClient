import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;


public class FileGUI {

    FileGUI(XController xController){
        this.xController = xController ;
        this.user = xController.getUser() ;
        initializeFrame() ;
    }

    FileGUI(XController xController, String receiver ){
        this.xController = xController ;
        this.user = xController.getUser() ;
        initializeFrame() ;
        this.userField.setText(receiver) ;
    }

    FileGUI(XController xController, Message replyMessage){
        this.xController = xController ;
        this.user = this.xController.getUser() ;
        initializeFrame() ;
        this.userField.setText(replyMessage.getSender() ) ;
        previewMessage(replyMessage) ;
    }

    private void initializeFrame(){
        initializeComponents() ;
        fileFrame.getContentPane().add(filePanel) ;
        fileFrame.setSize(400,400) ;
        fileFrame.setFont(new Font("Times New Roman", Font.BOLD, 12)) ;
        fileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE ) ;
        fileFrame.setLocationRelativeTo(null) ;
        fileFrame.setVisible(true) ;
    }

    private void initializeComponents(){
        initFileButtons();
        fileFrame = new JFrame("SELECT A FILE");

        filePanel = new JPanel();
        filePanel.setLayout(new BorderLayout(5, 5));
        filePanel.setBorder(new EmptyBorder(10,10,10,10));
        filePanel.setBackground(new Color(51, 51,51));

        fileLabel = new JLabel("FILE:");
        fileLabel.setFont(new Font("Segoe Print", Font.BOLD, 16));
        fileLabel.setPreferredSize(new Dimension(50,16));
        fileLabel.setBackground(new Color(51, 51,51));
        fileLabel.setForeground(new Color(204, 127, 98));

        fileField = new JTextField(25);
        fileField.setFont(new Font("Segoe Print", Font.BOLD, 16));
        fileField.setBackground(new Color(51,51,51));
        fileField.setForeground(new Color(204, 127, 98)) ;

        fileSubPanel = new JPanel();
        fileSubPanel.setLayout(new BorderLayout(5, 5));
        fileSubPanel.setPreferredSize(new Dimension(400,25));
        fileSubPanel.setBackground(new Color(51,51,51));

        fileSubPanel.add(fileLabel, BorderLayout.WEST);
        fileSubPanel.add(fileField, BorderLayout.CENTER);

        userLabel = new JLabel("USER");
        userLabel.setFont(new Font("Segoe Print", Font.BOLD, 16));
        userLabel.setPreferredSize(new Dimension(50,16));
        userLabel.setBackground(new Color(51, 51, 51)) ;
        userLabel.setForeground(new Color(204, 127, 98) ) ;

        userField = new JTextField(25);
        userField.setFont(new Font("Segoe Print", Font.BOLD, 16));
        userField.setBackground(new Color(51, 51, 51)) ;
        userField.setForeground(new Color(204, 127, 98) ) ;

        userPanel = new JPanel();
        userPanel.setLayout(new BorderLayout(5, 5));
        userPanel.setPreferredSize(new Dimension(400,25));
        userPanel.setBackground(new Color(51, 51, 51)) ;

        userPanel.add(userLabel, BorderLayout.WEST);
        userPanel.add(userField, BorderLayout.CENTER);

        previewPanel = new JPanel();
        previewPanel.setBackground(new Color(51, 51, 51)) ;
        filePreviewField = new JTextArea(25, 25);
        filePreviewField.setFont(new Font("Segoe Print", Font.BOLD, 16));
        filePreviewField.setBackground(new Color(51,51,51));
        filePreviewField.setForeground(new Color(204, 127, 98)) ;

        filePreviewScrollPane = new JScrollPane(filePreviewField) ;
        previewPanel.add(filePreviewScrollPane);

        textFieldsPanel = new JPanel();
        textFieldsPanel.setLayout(new GridLayout(2, 1));
        textFieldsPanel.setBackground(new Color(51, 51, 51)) ;
        textFieldsPanel.add(userPanel);
        textFieldsPanel.add(fileSubPanel);

        filePanel.add(textFieldsPanel, BorderLayout.NORTH);
        filePanel.add(previewPanel, BorderLayout.CENTER);
        filePanel.add(fileButtonPanel, BorderLayout.SOUTH);
    }

    private void initFileButtons(){
        fileButtonPanel = new JPanel();
        fileButtonPanel.setBackground(new Color(51,51,51)) ;
        fileButtonPanel.setBorder(new EmptyBorder(5,5,5,5));
        fileButtonPanel.setLayout(new GridLayout(1,4));

        sendButton = new JButton("SEND");
        sendButton.setPreferredSize(new Dimension(75, 30));
        sendButton.setHorizontalAlignment(SwingConstants.CENTER);
        sendButton.setFont(new Font("Segoe Print", Font.BOLD, 14));
        sendButton.setBackground(new Color(51, 51, 51));
        sendButton.setForeground(new Color(248, 248, 2));
        sendButton.addActionListener(e -> handleSendFile());

        selectFileButton = new JButton("SELECT");
        selectFileButton.setPreferredSize(new Dimension(75, 30));
        selectFileButton.setHorizontalAlignment(SwingConstants.CENTER);
        selectFileButton.setFont(new Font("Segoe Print", Font.BOLD, 14));
        selectFileButton.setBackground(new Color(51, 51, 51));
        selectFileButton.setForeground(new Color(34, 239, 215));
        selectFileButton.addActionListener(e -> handleSelectFile());

        fileButtonPanel.add(sendButton);
        fileButtonPanel.add(selectFileButton);
    }

    private void handleSelectFile() {
        if (fileChooser == null) {
            fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select A File");
            fileChooser.setFont(new Font("Times New Roman", Font.BOLD, 12));
            fileChooser.setMultiSelectionEnabled(false);
            File currentDirectory = new File("C:/");
            fileChooser.setCurrentDirectory(currentDirectory);
        }

        int returnValue = fileChooser.showOpenDialog(fileFrame);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            this.selectedFile = fileChooser.getSelectedFile();
            fileField.setText(selectedFile.getName());
            previewFile();
        }
    }

    private void previewFile(){
        if (
                this.selectedFile.getName().toLowerCase().endsWith(".txt") ||
                this.selectedFile.getName().toLowerCase().endsWith("doc") ||
                this.selectedFile.getName().toLowerCase().endsWith("docx") ||
                this.selectedFile.getName().toLowerCase().endsWith(".dat") ||
                this.selectedFile.getName().toLowerCase().endsWith(".java")
        ) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                String line;
                StringBuilder content = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                reader.close();
                filePreviewField.setText(content.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error reading the file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (this.selectedFile.getName().toLowerCase().endsWith(".jpg") ||
                this.selectedFile.getName().toLowerCase().endsWith(".jpeg") ||
                this.selectedFile.getName().toLowerCase().endsWith(".png")) {
            try {
                BufferedImage originalImg = ImageIO.read(selectedFile);

                double scale = 0.25;

                int scaledWidth = (int) (originalImg.getWidth() * scale);
                int scaledHeight = (int) (originalImg.getHeight() * scale);

                Image scaledImg = originalImg.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(scaledImg);

                imageLabel = new JLabel(icon);
                imageScrollPane = new JScrollPane(imageLabel);
                imageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED ) ;
                previewPanel.removeAll();
                previewPanel.add(imageScrollPane);
                previewPanel.revalidate();
                previewPanel.repaint();
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error loading the image", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            filePreviewField.setText("Preview not available for this file type.");
        }
    }

    private void previewMessage(Message message) {
        byte[] fileData = message.getFile();
        String fileName = message.getOriginalFilename();
        String fileExtension = getFileExtension(fileName);

        if (
                fileExtension != null && (("txt".equalsIgnoreCase(fileExtension)
                || "doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)
                || "dat".equalsIgnoreCase(fileExtension)) || "java".equalsIgnoreCase(fileExtension) )
        ) {
            String content = new String (fileData) ;
            filePreviewField.setText(content);
        } else if (fileExtension != null && (fileExtension.toLowerCase().endsWith("jpg") ||
                fileExtension.toLowerCase().endsWith("jpeg") ||
                fileExtension.toLowerCase().endsWith("png"))) {
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(fileData);
                BufferedImage originalImg = ImageIO.read(bis);
                bis.close();

                double scale = 0.25;

                int scaledWidth = (int) (originalImg.getWidth() * scale);
                int scaledHeight = (int) (originalImg.getHeight() * scale);

                Image scaledImg = originalImg.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(scaledImg);

                imageLabel = new JLabel(icon);
                imageScrollPane = new JScrollPane(imageLabel);
                imageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                previewPanel.removeAll();
                previewPanel.add(imageScrollPane);
                previewPanel.revalidate();
                previewPanel.repaint();
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error loading the image", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            filePreviewField.setText("Preview not available for this file type.");
        }
    }

    private void handleSendFile() {
        String filename = fileField.getText();
        String receiver = userField.getText();

        // Validate user input
        if (receiver.isEmpty() || "Enter User Here".equals(receiver) ) {
            JOptionPane.showMessageDialog(null, "Enter a User to send a File to", "INVALID USER", JOptionPane.INFORMATION_MESSAGE);
            userField.setText("Enter User Here");
            return;
        }

        if (this.selectedFile == null || filename.isEmpty()) {
            handleSelectFile();
            return ;
        }

        byte[] fileData;
        try {
            fileData = Files.readAllBytes(this.selectedFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            // Handle file reading error
            return;
        }
        Message sendMessage = new Message( xController.getUser().getUsername(), receiver, fileData, filename) ;
        Commands sendCommand = new Commands("SEND MESSAGE", this.user, sendMessage) ;
        Commands response = xController.handleSendFile(sendCommand) ;
        if ("INVALID USER".equals(response.getCommand())) {
            JOptionPane.showMessageDialog(null, "User Not Found",
                    "INVALID USER", JOptionPane.INFORMATION_MESSAGE);
            userField.setText("");
        } else if ("FAILED TO SEND".equals(response.getCommand())) {
            JOptionPane.showMessageDialog(null, "FAILED TO SEND: TRY AGAIN",
                    "FAILED TO SEND", JOptionPane.INFORMATION_MESSAGE);
        } else if ("SUCCESS".equals(response.getCommand())) {
            JOptionPane.showMessageDialog(null, "MESSAGE SUCCESSFULLY SENT",
                    "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            fileFrame.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "UNKNOWN COMMAND", "FAILURE", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0) {
            return fileName.substring(lastDotIndex + 1);
        }
        return "";
    }

    private User user ;
    private File selectedFile ;
    private JFileChooser fileChooser ;
    private JFrame fileFrame ;
    private JPanel filePanel ;
    private JPanel fileButtonPanel ;
    private JPanel userPanel ;
    private JPanel fileSubPanel ;
    private JLabel fileLabel ;
    private JLabel userLabel ;
    private JTextField fileField ;
    private JTextField userField ;
    private JButton sendButton ;
    private JButton selectFileButton ;
    private JScrollPane filePreviewScrollPane ;
    private JPanel textFieldsPanel ;
    private JPanel previewPanel ;
    private JTextArea filePreviewField ;
    private JScrollPane imageScrollPane ;
    private JLabel imageLabel ;
    private final XController xController ;
}
