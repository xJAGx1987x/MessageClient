import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginGUI extends JFrame {

    LoginGUI() {
        initComponents() ;
        setVisible(true) ;
    }

    LoginGUI(User user) {
        initComponents() ;
        setVisible(true);
        usernameField.setText(user.getUsername()) ;
        passwordField.setText(user.getPassword()) ;
    }

    private void initComponents() {

        loginPanel = new JPanel();
        usernameLabel = new JLabel();
        usernameField = new JTextField();
        passwordLabel = new JLabel();
        passwordField = new JPasswordField();
        passwordCheckbox = new JCheckBox();
        exitButton = new JButton();
        loginButton = new JButton();
        jLabel3 = new JLabel();
        createAccountButton = new JButton();

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE ) ;
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(LoginGUI.this, "Are you sure you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    dispose();
                    ClientMain cmain = new ClientMain() ;
                }
            }
        });

        setResizable(false) ;
        setFont(new java.awt.Font("Segoe Plain", 1, 12)); // NOI18N
        setTitle("xSpace: Login") ;


        loginPanel.setBackground(new java.awt.Color(51, 51, 51));
        loginPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        usernameLabel.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(102, 204, 51));
        usernameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        usernameLabel.setText("Username: ");

        usernameField.setBackground(new java.awt.Color(51, 51, 51));
        usernameField.setFont(new java.awt.Font("Segoe Print", 1, 18));
        usernameField.setForeground(new java.awt.Color(255, 153, 204)); //

        passwordLabel.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        passwordLabel.setForeground(new java.awt.Color(102, 204, 51));
        passwordLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        passwordLabel.setText("Password: ");

        passwordField.setBackground(new java.awt.Color(51, 51, 51));
        passwordField.setFont(new java.awt.Font("Segoe Print", 1, 18));
        passwordField.setForeground(new java.awt.Color(255, 153, 204));

        passwordCheckbox.setBackground(new java.awt.Color(51, 51, 51));
        passwordCheckbox.setFont(new java.awt.Font("Segoe Print", 1, 12)); // NOI18N
        passwordCheckbox.setForeground(new java.awt.Color(102, 204, 51));
        passwordCheckbox.setText("Show Password");
        passwordCheckbox.setActionCommand("passwordCheckboxActionPerformed");
        passwordCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordCheckboxActionPerformed(evt);
            }
        });

        exitButton.setBackground(new java.awt.Color(51, 51, 51));
        exitButton.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        exitButton.setForeground(new java.awt.Color(248, 248, 12));
        exitButton.setText("Exit");
        exitButton.setActionCommand("");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        loginButton.setBackground(new java.awt.Color(51, 51, 51));
        loginButton.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        loginButton.setForeground(new java.awt.Color(102, 204, 51));
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 204, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("xSpace Login");

        createAccountButton.setBackground(new java.awt.Color(51, 51, 51));
        createAccountButton.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        createAccountButton.setForeground(new java.awt.Color(173, 216, 230));
        createAccountButton.setText("Need An Account?");
        createAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createAccountButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
                loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(loginPanelLayout.createSequentialGroup()
                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(50, 50, 50)
                                                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(61, 61, 61))
                                        .addGroup(loginPanelLayout.createSequentialGroup()
                                                .addGap(50, 50, 50)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 46, Short.MAX_VALUE))
                                        .addGroup(loginPanelLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(loginPanelLayout.createSequentialGroup()
                                                                .addGap(6, 6, 6)
                                                                .addComponent(passwordCheckbox)
                                                                .addGap(42, 42, 42)
                                                                .addComponent(createAccountButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(loginPanelLayout.createSequentialGroup()
                                                                .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(usernameField))
                                                        .addGroup(loginPanelLayout.createSequentialGroup()
                                                                .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(passwordField)))))
                                .addContainerGap())
        );
        loginPanelLayout.setVerticalGroup(
                loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(loginPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(passwordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(passwordField))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(createAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(passwordCheckbox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(loginPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(loginPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        ClientMain cmain = new ClientMain() ;
    }

    private void passwordCheckboxActionPerformed(java.awt.event.ActionEvent evt) {
        passwordField.setEchoChar(passwordCheckbox.isSelected() ? '\0' : '*') ;
    }

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        xController = new XController() ;
        String username = usernameField.getText().trim() ;
        String password = String.valueOf(passwordField.getPassword() ).trim() ;

        if(username.isEmpty() || password.isEmpty() ) {
            JOptionPane.showMessageDialog(null,
                    "INVALID USERNAME OR PASSWORD", "INVALID", JOptionPane.INFORMATION_MESSAGE) ;
            return ;
        } else {
            this.user = new User(username, password ) ;
            Commands newCommand = new Commands("LOGIN", this.user) ;
            Commands response = xController.handleLogin(newCommand) ;
            if(response.getUser().loggedIn() || "SUCCESS".equals(response.getCommand() )){
                JOptionPane.showMessageDialog(null,
                        "Successful Login", "Welcome!", JOptionPane.INFORMATION_MESSAGE) ;
                this.xController.setUser(response.getUser()) ;
                ClientGUI clientGUI = new ClientGUI( this.xController, response) ;
                dispose() ;
            } else {
                String why = response.getCommand() ;
                if("User Not Found".equals(why)){
                    usernameField.setText("");
                    passwordField.setText("") ;
                    JOptionPane.showMessageDialog(null,
                            "No User Name Found", "Did you Create an Account?", JOptionPane.INFORMATION_MESSAGE) ;
                } else if("Incorrect Password".equals(why)) {
                    passwordField.setText("") ;
                    JOptionPane.showMessageDialog(null,
                            "Incorrect Password", "IncorrectPassword", JOptionPane.INFORMATION_MESSAGE) ;
                }
            }
        }
    }

    private void createAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose() ;
        CreateAccountGUI createAccountGUI = new CreateAccountGUI() ;
    }

    private User user;
    private XController xController;
    private javax.swing.JButton createAccountButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton loginButton;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JCheckBox passwordCheckbox;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField usernameField;
    private javax.swing.JLabel usernameLabel;
}
