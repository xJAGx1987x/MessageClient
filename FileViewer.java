import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.* ;
import java.awt.* ;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.border.EmptyBorder;

public class FileViewer extends javax.swing.JFrame {

    public FileViewer(XController xController, Message viewMessage, Runnable onCloseCallback) {
        this.viewMessage = viewMessage ;
        this.xController = xController ;
        this.onCloseCallback = onCloseCallback ;
        setTitle("File Viewer " + viewMessage.getOriginalFilename() );
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleClose();
            }
        });
        setLayout(new BorderLayout());

        this.fileData = viewMessage.getFile() ;

        String fileExtension = getFileExtension(viewMessage.getOriginalFilename()) ;

        if ("jpg".equalsIgnoreCase(fileExtension) || "jpeg".equalsIgnoreCase(fileExtension) ||
                "png".equalsIgnoreCase(fileExtension)) {
            displayImage(fileData);
        } else if ("txt".equalsIgnoreCase(fileExtension) || "doc".equalsIgnoreCase(fileExtension)
                || "docx".equalsIgnoreCase(fileExtension) || "dat".equalsIgnoreCase(fileExtension)
                || "java".equalsIgnoreCase(fileExtension)) {
            displayText(fileData);
        } else if (isMusic(fileData)) {
            displayMusic(fileData);
        } else {
            displayUnknown();
        }
        addButtons() ;
        setLocationRelativeTo(null);
        setVisible(true); // Set the window visible
    }

    private void handleCloseEvent() {
        dispose();
        if (onCloseCallback != null) {
            onCloseCallback.run();
        }
    }

    private void initComponents() {
        viewFilePanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        viewFilePanel.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout viewFilePanelLayout = new javax.swing.GroupLayout(viewFilePanel);
        viewFilePanel.setLayout(viewFilePanelLayout);
        viewFilePanelLayout.setHorizontalGroup(
                viewFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        viewFilePanelLayout.setVerticalGroup(
                viewFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 500, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(viewFilePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(viewFilePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0) {
            return fileName.substring(lastDotIndex + 1);
        }
        return "";
    }

    private boolean isMusic(byte[] fileData) {
        if (startsWith(fileData, "ID3") || startsWith(fileData, "RIFF")) {
            return true;
        } else if (startsWith(fileData, "OggS")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean startsWith(byte[] data, String prefix) {
        if (data.length >= prefix.length()) {
            for (int i = 0; i < prefix.length(); i++) {
                if (data[i] != (byte) prefix.charAt(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private void displayImage(byte[] fileData) {
        ImageIcon imageIcon = new ImageIcon(fileData);

        int originalWidth = imageIcon.getIconWidth();
        int originalHeight = imageIcon.getIconHeight();

        double maxScale = Math.min(400.0 / originalWidth, 400.0 / originalHeight);

        if (maxScale < 1.0) {
            Image originalImage = imageIcon.getImage();

            int scaledWidth = (int) (originalWidth * maxScale);
            int scaledHeight = (int) (originalHeight * maxScale);

            Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

            imageIcon = new ImageIcon(scaledImage);
        }

        imageLabel = new JLabel(imageIcon);
        imageLabel.setBackground(new Color(51,51,51));
        setForeground(new Color(51,51,51)); ;
        add(imageLabel, BorderLayout.CENTER);

    }

    private void displayMusic(byte[] fileData) {
        JPanel musicPanel = new JPanel(new BorderLayout());
        JPanel controlPanel = new JPanel(new FlowLayout());

        playButton = new JButton("Play");
        playButton.addActionListener(e -> play());

        stopButton = new JButton("Stop");
        stopButton.addActionListener(e -> stop());

        musicPanel.setBackground(new Color(51, 51, 51));
        controlPanel.setBackground(new Color(51, 51, 51));

        playButton.setBackground(new Color(51, 51, 51)) ;
        playButton.setForeground(new Color(102, 255, 102)) ;
        playButton.setFont(new Font("Segoe Print", Font.BOLD, 18));

        stopButton.setBackground(new Color(51, 51, 51)) ;
        stopButton.setForeground(new Color(255, 51,51) ) ;
        stopButton.setFont(new Font("Segoe Print", Font.BOLD, 18)) ;

        controlPanel.add(playButton);
        controlPanel.add(stopButton);

        musicPanel.add(controlPanel, BorderLayout.NORTH);
        add(musicPanel, BorderLayout.CENTER);
    }

    private void play() {
        stop();
        inputStream = new ByteArrayInputStream(fileData);
        try {
            AudioDevice device = FactoryRegistry.systemRegistry().createAudioDevice();
            player = new AdvancedPlayer(inputStream, device);
            new Thread(() -> {
                try {
                    setStatusMessage("Playing music...");
                    player.play();
                    setStatusMessage("Music playback finished.");
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                } finally {
                    stop();
                    setStatusMessage("Music playback stopped.");
                }
            }).start();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    private void stop() {
        if (player != null) {
            player.close();
            player = null;
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            inputStream = null;
        }
    }

    private void setStatusMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            JLabel setStatusLabel = new JLabel();
            setStatusLabel.setText(message);
        });
    }

    private void displayText(byte[] fileData) {
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        if (fileData != null) {
            try {
                String content = new String(fileData);
                textArea.setText(content);

                textArea.setFont(new Font("Segoe Print", Font.BOLD, 14));
                textArea.setBackground(new Color(51, 51, 51));
                textArea.setForeground(new Color(10,239, 10)) ;
            } catch (Exception e) {
                textArea.setText("Error: Unable to display file content.");
                e.printStackTrace();
            }
        } else {
            textArea.setText("Error: File data is null.");
        }
    }

    private void displayUnknown() {
        JLabel label = new JLabel("Unsupported file format");
        label.setBackground(new Color(51, 51, 51)) ;
        label.setForeground(new Color(248,248,12));
        label.setFont(new Font("Segoe Print", Font.BOLD, 16));
        label.setHorizontalAlignment(JLabel.CENTER);
        add(label, BorderLayout.CENTER);
        setBackground(new Color(51, 51, 51)) ;
    }

    private void addButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(51, 51, 51)) ;
        buttonPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        replyButton = new JButton("Reply");
        replyButton.setFont(new Font("Segoe Print", Font.BOLD, 18));
        replyButton.setBackground(new Color(51, 51, 51));
        replyButton.setForeground(new Color(102, 255, 102)) ;

        downloadButton = new JButton("Download");
        downloadButton.setFont(new Font("Segoe Print", Font.BOLD, 18));
        downloadButton.setBackground(new Color(51, 51, 51));
        downloadButton.setForeground(new Color(248, 248, 12) );

        deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("Segoe Print", Font.BOLD, 18));
        deleteButton.setBackground(new Color(51, 51, 51));
        deleteButton.setForeground(new Color(255,51,51)) ;

        replyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Message", "File", "Cancel"};

                int choice = JOptionPane.showOptionDialog(
                        null,
                        "Choose an option:",
                        "Option Dialog",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);

                if (choice == 0) {
                    MessageGUI messageGUI = new MessageGUI(xController, viewMessage) ;
                    dispose() ;
                } else if (choice == 1) {
                    FileGUI fileGUI = new FileGUI(xController, viewMessage) ;
                    dispose() ;
                } else if (choice == 2) {
                    return ;
                } else {
                    // This should not happen
                    System.out.println("Unexpected choice.");
                }
            }

        });


        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                downloadFile();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteFile();
            }
        });

        buttonPanel.add(replyButton) ;
        buttonPanel.add(downloadButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void downloadFile() {
        String filePath = "clientStorage/" ;
        confirmDatabase(filePath) ;

        filePath += this.viewMessage.getOriginalFilename() ;

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(this.viewMessage.getFile()) ;
            JOptionPane.showMessageDialog(this, "File downloaded successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error downloading file: " + e.getMessage());
        }
    }

    public void confirmDatabase(String path){
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public void deleteFile() {
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this file?",
                "Delete File?", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            Commands deleteCommand = new Commands("DELETE", this.xController.getUser(), viewMessage) ;
            Commands response = xController.handleDelete(deleteCommand) ;
            if ("DELETED".equals(response.getCommand())) {
                JOptionPane.showMessageDialog(this, "File deleted successfully!");
            } else if( "FAILED TO DELETE".equals(response.getCommand()) ) {
                JOptionPane.showMessageDialog(this, "FAILED TO DELETE!") ;
            }
            handleCloseEvent() ;
        }
    }

    private void handleClose() {
        stop();
        dispose();
    }

    private Runnable onCloseCallback ;
    private final Message viewMessage ;
    private ByteArrayInputStream inputStream;
    private AdvancedPlayer player;
    private JTextArea textArea;
    private JLabel imageLabel;
    private JButton replyButton ;
    private JButton downloadButton;
    private JButton deleteButton;
    private JButton playButton;
    private JButton stopButton;
    private byte[] fileData;
    private XController xController ;
    private javax.swing.JPanel viewFilePanel;
}
