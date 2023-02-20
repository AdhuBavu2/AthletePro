package LoginSystem;

import MainFrame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

public class LoginScreenStudent implements ActionListener, MouseListener {
    private JFrame frame;

    private JPanel background;
    private JLabel studentCenterTitle;
    private JLabel userID;
    private JLabel password;
    private JTextField userIDField;
    private JPasswordField passwordField;
    private JButton studentLoginButton;
    private JLabel studentForgotPassword;
    private JLabel studentCreateAccount;
    private JLabel adminLabel;
    private JPanel panel;
    private ImageIcon logoImage;
    private JLabel title;



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
        studentLoginButton.setBounds(90, 420, 225, 33);
        studentLoginButton.setFocusable(false);
        studentLoginButton.setBackground(new Color(0, 100, 246));
        studentLoginButton.setOpaque(true);
        background.add(studentLoginButton);

        // Forgot Password Label
        studentForgotPassword = new JLabel("Forgot Password?");
        studentForgotPassword.addMouseListener(this);
        studentForgotPassword.setBounds(90, 375, 110, 25);
        studentForgotPassword.setBackground(new Color(246, 254, 219));
        background.add(studentForgotPassword);

        //
        studentCreateAccount = new JLabel("Don't have an account?");
        studentCreateAccount.addMouseListener(this);
        studentCreateAccount.setBounds(90, 453, 140, 25);
        background.add(studentCreateAccount);

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
        if (e.getSource() == studentLoginButton) {
            String studentUserID = userIDField.getText();
            String studentPassword = String.valueOf(passwordField.getPassword());

            try {
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/iadb", "root", "FBLA2023");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from students");

                while(resultSet.next()) {
                    if(resultSet.getString("userName").equals(studentUserID) && resultSet.getString("password").equals(studentPassword)) {
                        JOptionPane.showMessageDialog(null, "Login Successful");
                        frame.dispose();
                        MainFrame mainFrame = new MainFrame(resultSet.getString("name"));
                    } else {
                        JOptionPane.showMessageDialog(null, "Wrong Username or Password");
                    }
                }

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == studentForgotPassword) {
            frame.dispose();
            ForgotPasswordStudent forgotPasswordStudent = new ForgotPasswordStudent();
        }

        else if(e.getSource() == studentCreateAccount) {
            frame.dispose();
            CreateAccountStudent createAccountStudent = new CreateAccountStudent();
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() == studentForgotPassword) {
            studentForgotPassword.setForeground(Color.RED);
        }

        else if(e.getSource() == studentCreateAccount) {
            studentCreateAccount.setForeground(Color.GREEN);
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == studentForgotPassword) {
            studentForgotPassword.setForeground(Color.BLACK);
        }

        else if(e.getSource() == studentCreateAccount) {
            studentCreateAccount.setForeground(Color.BLACK);
        }

    }
}