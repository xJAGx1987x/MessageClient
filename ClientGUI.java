import java.awt.*;
import java.awt.event.* ;
import javax.swing.* ;
import java.util.List;

public class ClientGUI extends JFrame {

    public ClientGUI(XController xController, Commands command) {
        this.xController = xController;
        this.user = xController.getUser() ;
        this.command = command;
        this.xController.setUser(this.user);
        this.messages = command.getMyMessages();
        initComponents();
        setMyDataLabel() ;
        updateServerLabel() ;
        updateUserList(this.command.getAllUsers()) ;
        setVisible(true) ;
    }

    private void initComponents() {
        fileListModel = new DefaultListModel<>() ;
        userListModel = new DefaultListModel<>() ;
        filesList = new JList<>() ;
        userList = new JList<>() ;

        mainSplitPane = new javax.swing.JSplitPane();
        userSidePanel = new javax.swing.JPanel();
        myLabel = new javax.swing.JLabel();
        userPanel = new javax.swing.JPanel();
        userScrollPane = new javax.swing.JScrollPane();
        mainPanel = new javax.swing.JPanel();
        guidePanel = new javax.swing.JPanel();
        guideLabel = new JLabel() ;
        buttonPanel = new javax.swing.JPanel();
        messageButton = new javax.swing.JButton();
        fileButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        filesPanel = new javax.swing.JPanel();
        filesScrollPane = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(ClientGUI.this,
                        "Are you sure you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    close() ;
                }
            }
        });
        setTitle("xSpace Workspace " + command.getUser().getUsername() );
        setBackground(new java.awt.Color(51, 51, 51));
        setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        setForeground(new java.awt.Color(51, 51, 51));
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        setSize(screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        setName("xWorkSpace"); // NOI18N

        mainSplitPane.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1,
                new java.awt.Color(102, 102, 102)));
        mainSplitPane.setDividerLocation(450);
        mainSplitPane.setForeground(new java.awt.Color(51, 51, 51));

        userSidePanel.setBackground(new java.awt.Color(51, 51, 51));
        userSidePanel.setForeground(new java.awt.Color(51, 51, 51));

        myLabel.setBackground(new java.awt.Color(51, 51, 51));
        myLabel.setFont(new java.awt.Font("Segoe Print", 1, 20)); // NOI18N
        myLabel.setForeground(new java.awt.Color(248, 248, 2));

        userScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        userScrollPane.setViewportView(userList);

        userList.setBackground(new java.awt.Color(51, 51, 51));
        userList.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        userList.setForeground(new Color(173, 216, 230));

        javax.swing.GroupLayout userPanelLayout = new javax.swing.GroupLayout(userPanel);
        userPanel.setLayout(userPanelLayout);
        userPanelLayout.setHorizontalGroup(
                userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(userScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
        );
        userPanelLayout.setVerticalGroup(
                userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(userScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout userSidePanelLayout = new javax.swing.GroupLayout(userSidePanel);
        userSidePanel.setLayout(userSidePanelLayout);
        userSidePanelLayout.setHorizontalGroup(
                userSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userSidePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(userSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(userPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(myLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        userSidePanelLayout.setVerticalGroup(
                userSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(userSidePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(myLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(userPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        mainSplitPane.setLeftComponent(userSidePanel);

        mainPanel.setBackground(new java.awt.Color(51, 51, 51));
        mainPanel.setForeground(new java.awt.Color(51, 51, 51));
        guidePanel.setBackground(new java.awt.Color(51, 51, 51));
        guideLabel.setBackground(new java.awt.Color(51, 51, 51));
        guideLabel.setForeground(new Color(255, 153, 204));
        guideLabel.setFont(new Font("Segoe Print", Font.BOLD, 36)) ;
        guideLabel.setText( xController.getUser().getUsername() + "'s Files") ;

        buttonPanel.setBackground(new java.awt.Color(51, 51, 51));

        messageButton.setBackground(new java.awt.Color(51, 51, 51));
        messageButton.setFont(new java.awt.Font("Segoe Print", Font.BOLD, 18)); // NOI18N
        messageButton.setForeground(new java.awt.Color(51, 51, 255));
        messageButton.setText("Message");
        messageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                messageButtonActionPerformed(evt);
            }
        });

        fileButton.setBackground(new java.awt.Color(51, 51, 51));
        fileButton.setFont(new java.awt.Font("Segoe Print", Font.BOLD, 18)); // NOI18N
        fileButton.setForeground(new java.awt.Color(102, 255, 102));
        fileButton.setText("File");
        fileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileButtonActionPerformed(evt);
            }
        });

        deleteButton.setBackground(new java.awt.Color(51, 51, 51));
        deleteButton.setFont(new java.awt.Font("Segoe Print", Font.BOLD, 18)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(255, 51, 51));
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        exitButton.setBackground(new java.awt.Color(51, 51, 51));
        exitButton.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        exitButton.setForeground(new java.awt.Color(248, 248, 12));
        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        refreshButton.setBackground(new java.awt.Color(51, 51, 51));
        refreshButton.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        refreshButton.setForeground(new java.awt.Color(34, 239, 215));
        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
                buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(buttonPanelLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(fileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(messageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(refreshButton, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17))
        );
        buttonPanelLayout.setVerticalGroup(
                buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonPanelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(messageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(fileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20))
        );

        filesPanel.setForeground(new java.awt.Color(51, 51, 51));

        filesScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        filesList.setBackground(new java.awt.Color(51, 51, 51));
        filesList.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        filesList.setForeground(new java.awt.Color(102, 255, 102));
        filesScrollPane.setViewportView(filesList);

        GroupLayout filesPanelLayout = new javax.swing.GroupLayout(filesPanel);
        filesPanel.setLayout(filesPanelLayout);
        filesPanelLayout.setHorizontalGroup(
                filesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(filesScrollPane)
        );
        filesPanelLayout.setVerticalGroup(
                filesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(filesScrollPane, GroupLayout.PREFERRED_SIZE, 557, GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout guidePanelLayout = new javax.swing.GroupLayout(guidePanel);
        guidePanel.setLayout(guidePanelLayout);
        guidePanelLayout.setHorizontalGroup(
                guidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(guidePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(guideLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Add guideLabel here
                                .addContainerGap())
                        .addGroup(guidePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(filesPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(guidePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(buttonPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        guidePanelLayout.setVerticalGroup(
                guidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, guidePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(guideLabel) // Add guideLabel here
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(filesPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonPanel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
        );


        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(guidePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(guidePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainSplitPane.setRightComponent(mainPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(mainSplitPane) )
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mainSplitPane, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE )
        );

        pack();
    }

    private void messageButtonActionPerformed(java.awt.event.ActionEvent evt) {
        MessageGUI messageGUI = new MessageGUI(xController, this::handleMessageGUIClosed);
    }

    private void handleMessageGUIClosed() {
        refresh();
    }

    private void fileButtonActionPerformed(java.awt.event.ActionEvent evt) {
        FileGUI fileGUI = new FileGUI(xController) ;
    }

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int option = JOptionPane.showConfirmDialog(ClientGUI.this,
                "Are you sure you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION) ;
        if (option == JOptionPane.YES_OPTION) {
            close() ;
        }
    }

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int option = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this file?", "Delete File?", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            int selectedIndex = filesList.getSelectedIndex();
            if (selectedIndex != -1) {
                Message deleteMessage = messages[selectedIndex] ;

                Commands deleteFileCMD = new Commands("DELETE", xController.getUser() ) ;
                deleteFileCMD.setMessageToSend(deleteMessage) ;
                this.command = xController.handleDelete(deleteFileCMD) ;
                if("DELETED".equals(this.command.getCommand())){
                    JOptionPane.showMessageDialog(ClientGUI.this, "File deleted successfully");
                    this.messages = null ;
                    this.messages = this.command.getMyMessages() ;
                    updateServerLabel() ;
                    updateUserList(this.command.getAllUsers() ) ;
                } else if("FAILED TO DELETE".equals(this.command.getCommand())){
                    JOptionPane.showMessageDialog(ClientGUI.this, "Error deleting file");
                }
            }
        }
    }

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {
        refresh() ;
    }

    private void refresh(){
        Commands refreshCMD = new Commands("REFRESH", this.xController.getUser() ) ;
        this.command = this.xController.handleRefresh(refreshCMD) ;

        if("SUCCESS".equals(this.command.getCommand())){
            this.messages = null ;
            this.messages = this.command.getMyMessages() ;
            updateUserList(this.command.getAllUsers()) ;
            updateServerLabel() ;
        } else {
            JOptionPane.showMessageDialog(null,
                    "Error Refreshing", "Error", JOptionPane.WARNING_MESSAGE) ;
        }
    }

    private void setMyDataLabel() {
        myLabel.setText("<html><div style='text-align: top;'>Name: " + user.getUsername() + "<br>Email: "
                + user.getEmailAddress() + "<br>Date Joined: " + user.getDateJoined() + "</div></html>");
    }

    private void updateUserList(List<String> users){
        userListModel.clear() ;
        for(String string: users){
            userListModel.addElement(string) ;
        }
        userList.setModel(userListModel) ;
        userScrollPane.setViewportView(userList) ;
        setupUserMouseListener(users) ;
    }

    private void setupUserMouseListener(List<String> users) {
        userList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Double-click
                    int selectedIndex = userList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        String receiver = (String) userList.getSelectedValue();
                        int option = JOptionPane.showOptionDialog(null, "Choose action", "Action",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                                null, new String[]{"Message", "File", "Cancel"}, "Message");

                        if (option == 0) {
                            // User selected "Message"
                            MessageGUI messageGUI = new MessageGUI(xController, receiver, ClientGUI.this::handleMessageGUIClosed ) ;
                        } else if (option == 1) {
                            // User selected "File"
                            FileGUI fileGUI = new FileGUI(xController, receiver) ;
                        } else if (option == 2) {
                            // do nothing because I'm a dummy
                        }
                        userList.clearSelection() ;
                    }
                }
            }
        });
    }

    private void updateServerLabel() {
        updateMessageList();
        setupFilesMouseListener();
        filesScrollPane.setViewportView(filesList) ;
    }

    private void updateMessageList() {
        SwingUtilities.invokeLater(() -> {
            this.fileListModel.clear();
            for (Message message : this.messages) {
                if(message != null){
                    String sender = message.getSender();
                    String filename = message.getOriginalFilename();
                    int fileSize = message.getFile().length;
                    this.fileListModel.addElement(sender + " ~ " + filename + " ~ " + fileSize + " bytes");
                }
            }
            this.filesList.setModel(fileListModel) ;
        });
    }

    private void setupFilesMouseListener() {
        this.filesList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedIndex = filesList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        Message selectedMessage = messages[selectedIndex];
                        filesList.clearSelection() ;
                        handleFileDoubleClick(selectedMessage);
                    }
                }
            }
        });
    }

    private void handleFileDoubleClick(Message selectedMessage) {
        String filename = selectedMessage.getOriginalFilename();
        int fileSize = selectedMessage.getFile().length;

        FileViewer fileViewerGUI = new FileViewer(xController, selectedMessage, this::handleMessageGUIClosed );
        System.out.println("Clicked File: " + filename + ", Size: " + fileSize + " bytes");
    }

    public void close(){
        Commands closeCommand = new Commands("DISCONNECT", xController.getUser()) ;
        Commands closeSuccess = xController.close(closeCommand) ;

         if ("Disconnected".equals(closeSuccess.getCommand() ) ){
            JOptionPane.showMessageDialog(null,
                    "Logged Out Successfully", "Goodbye", JOptionPane.INFORMATION_MESSAGE) ;
            System.exit(0) ;
        } else {
            JOptionPane.showMessageDialog(null,
                    "Error Disconnecting", "Error", JOptionPane.WARNING_MESSAGE) ;
            return ;
        }
    }

    private final XController xController;
    private Message[] messages;
    private DefaultListModel<String> userListModel ;
    private JList<String> userList;
    private JList<String> filesList;
    private DefaultListModel<String> fileListModel;
    private Commands command ;
    private User user ;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton fileButton;
    private javax.swing.JPanel filesPanel;
    private javax.swing.JScrollPane filesScrollPane;
    private javax.swing.JPanel guidePanel;
    private JLabel guideLabel ;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JSplitPane mainSplitPane;
    private javax.swing.JButton messageButton;
    private javax.swing.JLabel myLabel;
    private javax.swing.JButton refreshButton;
    private javax.swing.JPanel userPanel;
    private javax.swing.JScrollPane userScrollPane;
    private javax.swing.JPanel userSidePanel;
}
