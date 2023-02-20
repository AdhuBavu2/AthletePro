package LoginSystem;

import MainFrame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreenStudent implements ActionListener {
    private JFrame frame;

    private JPanel background;
    private JLabel studentCenterTitle;
    private JLabel adminCenterTitle;

    private JLabel userID;
    private JLabel password;
    private JTextField userIDField;
    private JPasswordField passwordField;
    private JButton studentLoginButton;
    private JButton studentForgotPassword;
    private JLabel messageLabel;

    private JLabel adminUserID;
    private JLabel adminPassword;
    private JTextField adminIDField;
    private JPasswordField adminPasswordField;
    private JButton adminLoginButton;

    private JPanel panel;
    private ImageIcon logoImage;
    private JLabel title;
    private JButton createAccount;


    public LoginScreenStudent() {
        // Background image
        background = new JPanel();
        background.setLayout(null);
        background.setBounds(300, 0, 500, 600);
        background.setBackground(new Color(246, 254, 219));
        background.setSize(500, 600);


        // Panel
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new java.awt.Color(20, 100, 246));
        panel.setBounds(0, 0, 300, 600);
        panel.setSize(300, 600);

        // Logo Image
        logoImage = new ImageIcon("Images/App logo.png");
        title = new JLabel(logoImage);
        title.setBounds(0, 150, 300, 200);
        panel.add(title);


        // Main student login title
        studentCenterTitle = new JLabel("Student Login");
        studentCenterTitle.setBounds(90, 100, 350, 100);
        studentCenterTitle.setFont(new Font("Open Sans", Font.BOLD, 32));
        studentCenterTitle.setForeground(Color.BLACK);
        background.add(studentCenterTitle);

        // Student UserID text
        userID = new JLabel("User ID");
        userID.setBounds(90, 200, 100, 50);
        userID.setFont(new Font("Open Sans", Font.BOLD, 16));
        background.add(userID);

        // Student password text
        password = new JLabel("Password");
        password.setBounds(90, 300, 100, 50);
        password.setFont(new Font("Tahoma", Font.BOLD, 16));
        background.add(password);

        // Student UserID Field
        userIDField = new JTextField();
        userIDField.setBounds(90, 240, 225, 33);
        background.add(userIDField);

        // Student Password Field
        passwordField = new JPasswordField();
        passwordField.setBounds(90, 340, 225, 33);
        background.add(passwordField);

        // Student Login Button
        studentLoginButton = new JButton("Log In");
        studentLoginButton.addActionListener(this);
        studentLoginButton.setBounds(90, 440, 225, 33);
        studentLoginButton.setFocusable(false);
        studentLoginButton.setBackground(new Color(0, 120, 74));
        studentLoginButton.setOpaque(true);
        background.add(studentLoginButton);

        studentForgotPassword = new JButton("Change Password");
        studentForgotPassword.addActionListener(this);
        studentForgotPassword.setBounds(90, 400, 225, 33);
        studentForgotPassword.setFocusable(false);
        studentForgotPassword.setBackground(Color.RED);
        studentForgotPassword.setOpaque(true);
        background.add(studentForgotPassword);

        // Frame
        frame = new JFrame("Login Screen");
        frame.add(background);
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        LoginScreenStudent loginScreenStudent = new LoginScreenStudent();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == studentLoginButton) {
            MainFrame mainFrame = new MainFrame();
        }
    }
}