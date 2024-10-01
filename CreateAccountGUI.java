import javax.swing.* ;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CreateAccountGUI extends JFrame {

    public CreateAccountGUI() {
        initComponents() ;
        setVisible(true) ;
    }

    private void initComponents() {

        loginPanel = new javax.swing.JPanel();
        usernameLabel = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        passwordCheckbox = new javax.swing.JCheckBox();
        exitButton = new javax.swing.JButton();
        createAccountButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        goLoginButton = new javax.swing.JButton();

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE) ;
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(CreateAccountGUI.this, "Are you sure you want to exit?",
                        "Confirm Exit", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    dispose();
                    ClientMain cmain = new ClientMain() ;
                }
            }
        });
        setResizable(false) ;
        setFont(new java.awt.Font("Segoe Plain", 1, 12)); // NOI18N
        setTitle("xSpace: Create Account") ;

        loginPanel.setBackground(new java.awt.Color(51, 51, 51));
        loginPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        loginPanel.setPreferredSize(new java.awt.Dimension(400, 300));

        usernameLabel.setBackground(new java.awt.Color(51, 51, 51));
        usernameLabel.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(173, 216, 230));
        usernameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        usernameLabel.setText("Username : ");
        usernameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        usernameField.setBackground(new java.awt.Color(51, 51, 51));
        usernameField.setFont(new java.awt.Font("Segoe Print", 1, 18));
        usernameField.setForeground(new java.awt.Color(255, 153, 204)) ;

        passwordLabel.setBackground(new java.awt.Color(51, 51, 51));
        passwordLabel.setFont(new java.awt.Font("Segoe Print", 1, 18));
        passwordLabel.setForeground(new java.awt.Color(173, 216, 230));
        passwordLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        passwordLabel.setText("Password : ");
        passwordLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        passwordField.setBackground(new java.awt.Color(51, 51, 51));
        passwordField.setFont(new java.awt.Font("Segoe Print", 1, 18));
        passwordField.setForeground(new java.awt.Color(255, 153, 204));

        passwordCheckbox.setBackground(new java.awt.Color(51, 51, 51));
        passwordCheckbox.setFont(new java.awt.Font("Segoe Print", 1, 12));
        passwordCheckbox.setForeground(new java.awt.Color(173, 216, 230));
        passwordCheckbox.setText("Show Password");
        passwordCheckbox.setActionCommand("passwordCheckboxActionPerformed");
        passwordCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordCheckboxActionPerformed(evt);
            }
        });

        exitButton.setBackground(new java.awt.Color(51, 51, 51));
        exitButton.setFont(new java.awt.Font("Segoe Print", 1, 18));
        exitButton.setForeground(new java.awt.Color(248, 248, 12));
        exitButton.setText("Exit");
        exitButton.setActionCommand("");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        createAccountButton.setBackground(new java.awt.Color(51, 51, 51));
        createAccountButton.setFont(new java.awt.Font("Segoe Print", 1, 18));
        createAccountButton.setForeground(new java.awt.Color(173, 216, 230));
        createAccountButton.setText("Create");
        createAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createAccountButtonActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setFont(new java.awt.Font("Segoe Print", 1, 18));
        jLabel3.setForeground(new java.awt.Color(173, 216, 230));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Create An Account");

        emailField.setBackground(new java.awt.Color(51, 51, 51));
        emailField.setFont(new java.awt.Font("Segoe Print", 1, 18));
        emailField.setForeground(new java.awt.Color(255, 153, 204)) ;

        emailLabel.setBackground(new java.awt.Color(51, 51, 51));
        emailLabel.setFont(new java.awt.Font("Segoe Print", 1, 18));
        emailLabel.setForeground(new java.awt.Color(173, 216, 230));
        emailLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        emailLabel.setText("Email : ");
        emailLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        goLoginButton.setBackground(new java.awt.Color(51, 51, 51));
        goLoginButton.setFont(new java.awt.Font("Segoe Print", 1, 18));
        goLoginButton.setForeground(new java.awt.Color(102, 204, 51));
        goLoginButton.setText("Go To Login");
        goLoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goLoginButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
                loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(loginPanelLayout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(createAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(loginPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(loginPanelLayout.createSequentialGroup()
                                                .addGap(44, 44, 44)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(loginPanelLayout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addComponent(passwordCheckbox)
                                                .addGap(41, 41, 41)
                                                .addComponent(goLoginButton, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
                                        .addGroup(loginPanelLayout.createSequentialGroup()
                                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(emailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(passwordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                                                        .addComponent(usernameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(loginPanelLayout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(usernameField)
                                                                        .addComponent(passwordField)))
                                                        .addGroup(loginPanelLayout.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addComponent(emailField)))))
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
                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(loginPanelLayout.createSequentialGroup()
                                                .addComponent(passwordField)
                                                .addGap(3, 3, 3))
                                        .addGroup(loginPanelLayout.createSequentialGroup()
                                                .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(emailField, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(passwordCheckbox, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                                        .addComponent(goLoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(createAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(loginPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(loginPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null) ;
    }

    private void passwordCheckboxActionPerformed(java.awt.event.ActionEvent evt) {
        passwordField.setEchoChar(passwordCheckbox.isSelected() ? '\0' : '*') ;
    }

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        ClientMain cmail = new ClientMain() ;
    }

    private void createAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String username = usernameField.getText() ;
        String password = String.valueOf(passwordField.getPassword()) ;
        String email = emailField.getText() ;

        if(username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return ;
        }
        User newUser = new User(username, password, email, null) ;

        Commands createAccount = new Commands("CREATE ACCOUNT", newUser) ;

        XController xController = new XController() ;
        Commands accountCreated = xController.handleCreate(createAccount) ;

        if("ACCOUNT_CREATED".equals(accountCreated.getCommand())){
            JOptionPane.showMessageDialog(this, "Account Created.",
                    "Success", JOptionPane.INFORMATION_MESSAGE) ;
            usernameField.setText("") ;
            passwordField.setText("") ;
            emailField.setText("") ;
            newUser = accountCreated.getUser() ;
            dispose() ;
            LoginGUI lgui = new LoginGUI(newUser) ;
        } else if("USER NAME TAKEN".equals(accountCreated.getCommand() ) ){
            JOptionPane.showMessageDialog(this, "User name is taken",
                    "Warning", JOptionPane.WARNING_MESSAGE) ;
            usernameField.setText("") ;
            passwordField.setText("") ;
            emailField.setText("") ;
            newUser = null ;
        }

    }

    private void goLoginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose() ;
        LoginGUI lgui = new LoginGUI() ;
    }

    // Variables declaration - do not modify
    private javax.swing.JButton createAccountButton;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton goLoginButton;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JCheckBox passwordCheckbox;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField usernameField;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration
}
