package AdminLogin;

import LoginSystem.EmailValidator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateAccountAdmin implements ActionListener {
    private JFrame frame;

    private JPanel panel;
    private JPanel panel1;

    private JLabel title;
    private JLabel userName;
    private JLabel name;
    private JLabel phone;
    private JLabel password;
    private JLabel email;
    private JLabel securityQuestion;
    private JLabel answer;
    private JButton createButton;

    private JTextField userNameField;
    private JTextField nameField;
    private JTextField phoneField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JComboBox securityQuestionBox;
    private JTextField answerField;
    private JButton back;
    private String[] choices = {"What is your nick name?", "What is your mothers name?", "What is the name of your first pet?"};

    public CreateAccountAdmin() {
        // Panel
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(0, 100, 246));
        panel.setBounds(0, 0, 300, 600);
        panel.setSize(300, 600);

        // Panel 1
        panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBackground(new java.awt.Color(246, 254, 219));
        panel1.setBounds(300, 0, 600, 600);
        panel1.setSize(600, 600);

        // Title
        title = new JLabel("User Registration");
        title.setBounds(170, 35, 400, 75);
        title.setFont(new Font("Segoe Print", Font.BOLD, 32));
        title.setForeground(Color.BLACK);
        panel1.add(title);

        // User Name
        userName = new JLabel("User Name");
        userName.setBounds(80, 140, 100, 50);
        userName.setFont(new Font("Segoe Print", Font.BOLD, 16));
        panel1.add(userName);

        // Name
        name = new JLabel("Name");
        name.setBounds(80, 210, 100, 50);
        name.setFont(new Font("Segoe Print", Font.BOLD, 16));
        panel1.add(name);

        // Phone
        phone = new JLabel("Phone Number");
        phone.setBounds(80, 280, 180, 50);
        phone.setFont(new Font("Segoe Print", Font.BOLD, 16));
        panel1.add(phone);

        // Password
        password = new JLabel("Password");
        password.setBounds(80, 350, 180, 50);
        password.setFont(new Font("Segoe Print", Font.BOLD, 16));
        panel1.add(password);

        // Email id
        email = new JLabel("Email Address");
        email.setBounds(350, 175, 180, 50);
        email.setFont(new Font("Segoe Print", Font.BOLD, 16));
        panel1.add(email);

        // Security Label
        securityQuestion = new JLabel("Security Question");
        securityQuestion.setBounds(350, 250, 180, 50);
        securityQuestion.setFont(new Font("Segoe Print", Font.BOLD, 16));
        panel1.add(securityQuestion);

        // Answer
        answer = new JLabel("Answer");
        answer.setBounds(350, 345, 170, 50);
        answer.setFont(new Font("Segoe Print", Font.BOLD, 16));
        panel1.add(answer);

        // Username field
        userNameField = new JTextField();
        userNameField.setBounds(80, 180, 200, 33);
        panel1.add(userNameField);

        // Name field
        nameField = new JTextField();
        nameField.setBounds(80, 250, 200, 33);
        panel1.add(nameField);

        // Name field
        phoneField = new JTextField();
        phoneField.setBounds(80, 320, 200, 33);
        panel1.add(phoneField);

        //Password field
        passwordField = new JPasswordField();
        passwordField.setBounds(80, 390, 200, 33);
        panel1.add(passwordField);

        // Email field
        emailField = new JTextField();
        emailField.setBounds(350, 215, 200, 33);
        panel1.add(emailField);

        // Security question Field
        securityQuestionBox = new JComboBox(choices);
        securityQuestionBox.setBounds(350, 300, 200, 33);
        panel1.add(securityQuestionBox);

        // Answer Field
        answerField = new JTextField();
        answerField.setBounds(350, 380, 200, 33);
        panel1.add(answerField);

        // Create Button
        createButton = new JButton("Create Account");
        createButton.addActionListener(this);
        createButton.setBounds(140, 460, 300, 33);
        createButton.setFocusable(false);
        createButton.setBackground(new java.awt.Color(0, 100, 246));
        createButton.setOpaque(true);
        panel1.add(createButton);
        //back button
        back = new JButton("Back");
        back.addActionListener(this);
        back.setBounds(0, 20, 140, 50);
        panel1.add(back);
        // Frame
        frame = new JFrame("Change Password");
        frame.add(panel);
        frame.add(panel1);
        frame.setSize(900, 600);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            frame.dispose();
            LoginScreenAdmin back = new LoginScreenAdmin();
        }
        EmailValidator emailValidator = new EmailValidator();

        if (e.getSource() == createButton) {
            String userName = userNameField.getText();
            String name = nameField.getText();
            String phone = phoneField.getText();
            String password = String.valueOf(passwordField.getPassword());
            String email = emailField.getText();
            String securityQuestion = (String) securityQuestionBox.getSelectedItem();
            String answer = answerField.getText();

            if (password.equals("")) {
                JOptionPane.showMessageDialog(null, "All fields need to be completed properly");
            } else if (!emailValidator.validate(email.trim())) {
                JOptionPane.showMessageDialog(null, "All fields need to be completed properly");
            } else if (answer.equals("")) {
                JOptionPane.showMessageDialog(null, "All fields need to be completed properly");
            } else if (userName.equals("")) {
                JOptionPane.showMessageDialog(null, "All fields need to be completed properly");
            } else if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "All fields need to be completed properly");
            } else if (phone.equals("")) {
                JOptionPane.showMessageDialog(null, "All fields need to be completed properly");
            } else {
                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/computer_science_ia", "root", "");
                    Statement statement = connection.createStatement();

                    statement.executeUpdate("insert into administrators (userName, name, phone, password, email, securityQuestion, answer) values('" + userName + "', '" + name + "', '" + phone + "', '" + password + "', '" + email + "', '" + securityQuestion + "', '" + answer + "')");
                    JOptionPane.showMessageDialog(null, "Thanks for registration");
                    frame.dispose();
                    LoginScreenAdmin loginScreen = new LoginScreenAdmin();

                }
                catch (SQLException sqlException) {
                    JOptionPane.showMessageDialog(null, "Something went wrong, please try again later.");
                }

            }

        }
    }
}
