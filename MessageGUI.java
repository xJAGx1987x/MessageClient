import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MessageGUI {

    MessageGUI(XController xController, Runnable onCloseCallback) {
        this.xController = xController;
        this.user = xController.getUser();
        this.onCloseCallback = onCloseCallback;
        initializeFrame();
    }

    MessageGUI(XController xController, String receiver, Runnable onCloseCallback ){
        this.xController = xController ;
        this.user = xController.getUser() ;
        this.onCloseCallback = onCloseCallback;
        initializeFrame() ;
        userField.setText(receiver) ;
    }

    MessageGUI(XController xController, Message message ) {
        this.xController = xController;
        this.user = xController.getUser() ;
        this.messageSender = message.getSender() ;
        this.file = message.getFile() ;
        initializeFrame();
        userField.setText(messageSender) ;

        String filename = message.getOriginalFilename();
        String fileExtension = getFileExtension(filename);

        if (!("txt".equalsIgnoreCase(fileExtension) || "doc".equalsIgnoreCase(fileExtension)
                || "docx".equalsIgnoreCase(fileExtension) || "dat".equalsIgnoreCase(fileExtension))) {
            filename = removeFileExtension(filename) + ".txt";
            messagePreviewField.setText("");
        } else {
            if (file != null) {
                try {
                    String content = new String(file);
                    messagePreviewField.setText("\n\n" + content);
                } catch (Exception e) {
                    messagePreviewField.setText("Error: Unable to display file content.");
                    e.printStackTrace();
                }
            } else {
                messagePreviewField.setText("Error: File data is null.");
            }
        }
        messageField.setText("RE--" + filename);
    }

    private void handleCloseEvent() {
        messageFrame.dispose();

        if (onCloseCallback != null) {
            onCloseCallback.run();
        }
    }

    private void initializeFrame() {
        initializeComponents();
        messageFrame.getContentPane().add(messagePanel);
        messageFrame.setSize(400, 400);
        messageFrame.setFont(new Font("Segoe Print", Font.BOLD, 12));
        messageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        messageFrame.setLocationRelativeTo(null);
        messageFrame.setVisible(true) ;
    }

    private void initializeComponents() {
        initMessageButtons();
        messageFrame = new JFrame("SEND MESSAGE");
        messagePanel = new JPanel();
        messagePanel.setLayout(new BorderLayout(5, 5));
        messagePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        messagePanel.setBackground(new Color(51, 51, 51));

        messageLabel = new JLabel("MESSAGE:");
        messageLabel.setFont(new Font("Segoe Print", 1, 16));
        messageLabel.setPreferredSize(new Dimension(100, 16));
        messageLabel.setForeground(new Color(255, 255, 51));

        messageField = new JTextField(50);
        messageField.setFont(new Font("Segoe Print", 1, 18));
        messageField.setForeground(new Color(255, 255, 51)) ;
        messageField.setBackground(new Color(51, 51, 51)) ;

        messageSubPanel = new JPanel();
        messageSubPanel.setLayout(new BorderLayout(5, 5));
        messageSubPanel.setPreferredSize(new Dimension(400, 25));
        messageSubPanel.setBackground(new Color(51, 51, 51));

        messageSubPanel.add(messageLabel, BorderLayout.WEST);
        messageSubPanel.add(messageField, BorderLayout.CENTER);

        userLabel = new JLabel("USER");
        userLabel.setFont(new Font("Segoe Print", Font.BOLD, 16));
        userLabel.setPreferredSize(new Dimension(100, 16));
        userLabel.setForeground(new Color(255, 255, 51));

        userField = new JTextField(25);
        userField.setFont(new Font("Segoe Print", Font.BOLD, 18));
        userField.setForeground(new Color(255, 255, 51));
        userField.setBackground(new Color(51, 51, 51));

        userPanel = new JPanel();
        userPanel.setLayout(new BorderLayout(5, 5));
        userPanel.setPreferredSize(new Dimension(400, 25));
        userPanel.setBackground(new Color(51, 51, 51));

        userPanel.add(userLabel, BorderLayout.WEST);
        userPanel.add(userField, BorderLayout.CENTER);

        previewPanel = new JPanel();
        previewPanel.setBackground(new Color(51, 51, 51));

        messagePreviewField = new JTextArea(100, 25);
        messagePreviewField.setFont(new Font("Segoe Print", Font.BOLD, 18)) ;
        messagePreviewField.setForeground(new Color(255, 255, 51)) ;
        messagePreviewField.setBackground(new Color(51, 51, 51) ) ;

        messageScrollPane = new JScrollPane(messagePreviewField);
        previewPanel.add(messageScrollPane);

        textFieldsPanel = new JPanel();
        textFieldsPanel.setLayout(new GridLayout(2, 1));
        textFieldsPanel.setBackground(new Color(51, 51, 51));

        textFieldsPanel.add(userPanel);
        textFieldsPanel.add(messageSubPanel);
        JScrollPane messageScrollPane = new JScrollPane(previewPanel);
        messagePanel.add(textFieldsPanel, BorderLayout.NORTH);
        messagePanel.add(messageScrollPane, BorderLayout.CENTER);
        messagePanel.add(messageButtonPanel, BorderLayout.SOUTH);
    }

    private void initMessageButtons() {
        messageButtonPanel = new JPanel();
        messageButtonPanel.setBackground(new Color (51,51,51)) ;
        messageButtonPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        messageButtonPanel.setLayout(new FlowLayout());

        sendButton = new JButton("SEND");
        sendButton.setPreferredSize(new Dimension(100, 30));
        sendButton.setFont(new Font("Segoe Print", Font.BOLD, 16));
        sendButton.setBackground(new Color(51,51,51)) ;
        sendButton.setForeground(new Color(255, 153, 204));
        sendButton.addActionListener(e -> handleSendMessage());

        messageButtonPanel.add(sendButton);
    }

    private void handleSendMessage() {
        String filename = messageField.getText();
        String receiver = userField.getText();

        if (receiver.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter a User to send a Message to",
                    "INVALID USER", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (filename.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter a Message to send",
                    "INVALID MESSAGE", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String fileExtension = getFileExtension(filename) ;
        if(!("txt".equalsIgnoreCase(fileExtension) || "doc".equalsIgnoreCase(fileExtension)
                || "docx".equalsIgnoreCase(fileExtension) || "dat".equalsIgnoreCase(fileExtension))) {
            filename = removeFileExtension(filename);
            filename += ".txt";
        }

        String message = messagePreviewField.getText();
        byte[] byteMessage = message.getBytes();

        Message sendMessage = new Message(this.user.getUsername(), receiver, byteMessage, filename) ;

        Commands sendCommand = new Commands("SEND MESSAGE", xController.getUser() );
        sendCommand.setMessageToSend(sendMessage) ;
        Commands response = xController.handleSendFile(sendCommand);

        if ("INVALID USER".equals(response.getCommand())) {
            JOptionPane.showMessageDialog(null, "User Not Found",
                    "INVALID USER", JOptionPane.INFORMATION_MESSAGE);
            userField.setText("");
        } else if ("FAILED TO SEND".equals(response.getCommand())) {
            JOptionPane.showMessageDialog(null, "FAILED TO SEND: TRY AGAIN",
                    "FAILED TO SEND", JOptionPane.INFORMATION_MESSAGE);
        } else if ("SUCCESS".equals(response.getCommand())) {
            handleCloseEvent() ;
            this.messageFrame.dispose();
            JOptionPane.showMessageDialog(null, "MESSAGE SUCCESSFULLY SENT",
                    "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "UNKNOWN COMMAND",
                    "FAILURE", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0) {
            return fileName.substring(lastDotIndex + 1);
        }
        return "";
    }

    private String removeFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0) {
            return fileName.substring(0, lastDotIndex);
        }
        return fileName;
    }

    private User user ;
    private JFrame messageFrame;
    private JPanel messagePanel;
    private JPanel messageButtonPanel;
    private JPanel userPanel;
    private JPanel messageSubPanel;
    private JLabel messageLabel;
    private JLabel userLabel;
    private JTextField messageField;
    private JTextField userField;
    private JButton sendButton;
    private JScrollPane messageScrollPane;
    private JPanel textFieldsPanel;
    private JPanel previewPanel;
    private JTextArea messagePreviewField;
    private final XController xController;
    private String messageSender ;
    private byte[] file ;
    private Runnable onCloseCallback ;
}
