import javax.swing.*;
import java.awt.*;

public class ClientMain extends JFrame {

    public ClientMain() {
        initComponents();
        setVisible(true) ;
        setLocationRelativeTo(null) ;
    }

    private void initComponents() {
        jPanel1 = new JPanel();
        loginButton = new JButton();
        createAccountButton = new JButton();
        exitButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setFont(new java.awt.Font("Segoe Plain", Font.BOLD, 12)); // NOI18N
        setTitle("Welcome to xSpace");
        setResizable(false) ;

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setForeground(new java.awt.Color(51, 51, 51));

        loginButton.setBackground(new java.awt.Color(51, 51, 51));
        loginButton.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        loginButton.setForeground(new java.awt.Color(102, 204, 51));
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        createAccountButton.setBackground(new java.awt.Color(51, 51, 51));
        createAccountButton.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        createAccountButton.setForeground(new java.awt.Color(173, 216, 230));
        createAccountButton.setText(" Create Account");
        createAccountButton.setAutoscrolls(true);
        createAccountButton.setRolloverEnabled(true);
        createAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createAccountButtonActionPerformed(evt);
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

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(createAccountButton)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(createAccountButton, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void createAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        CreateAccountGUI cag = new CreateAccountGUI() ;
    }

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose() ;
        LoginGUI lgui = new LoginGUI() ;
    }

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0) ;
    }

    // Variables declaration - do not modify
    private JButton createAccountButton;
    private JButton exitButton;
    private JPanel jPanel1;
    private JButton loginButton;
    // End of variables declaration
}
