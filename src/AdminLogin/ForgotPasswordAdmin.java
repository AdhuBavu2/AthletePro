package AdminLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ForgotPasswordAdmin implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private ImageIcon logoImage;
    private JLabel logo;
    private JPanel panel1;
    private JLabel title;
    private JLabel userID;
    private JLabel securityQuestion;
    private JLabel answer;
    private JLabel newPassword;
    private JTextField userIDField;
    private JTextField securityQuestionField;
    private JTextField answerField;
    private JPasswordField newPasswordField;
    private JButton confirmButton;
    private JButton search;
    private ImageIcon backImage;
    private JButton back;

    public ForgotPasswordAdmin() {
        // Panel
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(0, 100, 246));
        panel.setBounds(0, 0, 300, 600);
        panel.setSize(300, 600);

        // Logo Image
        logoImage = new ImageIcon("Application Icons and Images/App logo.png");
        logo = new JLabel(logoImage);
        logo.setBounds(0, 150, 300, 200);
        panel.add(logo);

        // Panel1
        panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBackground(new Color(246, 254, 219));
        panel1.setBounds(300, 0, 500, 600);
        panel1.setSize(500, 600);

        // Title
        title = new JLabel("Change Password");
        title.setBounds(100, 20, 350, 100);
        title.setFont(new Font("Open Sans", Font.BOLD, 32));
        title.setForeground(Color.BLACK);
        panel1.add(title);

        // Username text
        userID = new JLabel("Username");
        userID.setBounds(100, 125, 180, 50);
        userID.setFont(new Font("Open Sans", Font.BOLD, 16));
        panel1.add(userID);

        // Security Question text
        securityQuestion = new JLabel("Security Question");
        securityQuestion.setBounds(100, 205, 180, 50);
        securityQuestion.setFont(new Font("Open Sans", Font.BOLD, 16));
        panel1.add(securityQuestion);

        // Answer text
        answer = new JLabel("Answer");
        answer.setBounds(100, 285, 180, 50);
        answer.setFont(new Font("Open Sans", Font.BOLD, 16));
        panel1.add(answer);

        // New password text
        newPassword = new JLabel("New Password");
        newPassword.setBounds(100, 365, 180, 50);
        newPassword.setFont(new Font("Open Sans", Font.BOLD, 16));
        panel1.add(newPassword);

        // UserID Field
        userIDField = new JTextField();
        userIDField.setBounds(100, 170, 225, 33);
        panel1.add(userIDField);

        // Security Question Field
        securityQuestionField = new JTextField();
        securityQuestionField.setBounds(100, 250, 225, 33);
        panel1.add(securityQuestionField);

        // Answer Field
        answerField = new JTextField();
        answerField.setBounds(100, 330, 225, 33);
        panel1.add(answerField);

        // New Password Field
        newPasswordField = new JPasswordField();
        newPasswordField.setBounds(100, 410, 225, 33);
        panel1.add(newPasswordField);

        // Search Button
        search = new JButton("Search");
        search.addActionListener(this);
        search.setBounds(345, 170, 100, 33);
        search.setFocusable(false);
        search.setBackground(new Color(0, 100, 246));
        search.setOpaque(true);
        panel1.add(search);

        // Confirm Button
        confirmButton = new JButton("Change Password");
        confirmButton.addActionListener(this);
        confirmButton.setBounds(100, 490, 225, 33);
        confirmButton.setFocusable(false);
        confirmButton.setBackground(new Color(0, 100, 246));
        confirmButton.setOpaque(true);
        panel1.add(confirmButton);

        backImage = new ImageIcon("Application Icons and Images/Back Button Transparent.png");
        back = new JButton(backImage);
        back.addActionListener(this);
        back.setBounds(5, 15, 40, 38);
        back.setFocusable(false);
        back.setBackground(new Color(246, 254, 219));
        back.setOpaque(true);
        panel1.add(back);

        frame = new JFrame("Change Password");
        frame.add(panel1);
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == search) {
            String userName = userIDField.getText();

            try {
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/computer_science_ia", "root", "");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select securityQuestion from administrators where userName='"+userName+"'");

                if(resultSet.next()) {
                    securityQuestionField.setText(resultSet.getString(1));
                } else {
                    JOptionPane.showMessageDialog(null, "Username not found");
                }
                connection.close();
                resultSet.close();

            } catch(SQLException sqlException) {
                JOptionPane.showMessageDialog(null, "Error in Connection");
            }
        }

        if(e.getSource() == confirmButton) {
            String userName = userIDField.getText();
            String newPassword = String.valueOf(newPasswordField.getPassword());
            String securityQuestion = securityQuestionField.getText();
            String answer1 = answerField.getText();

            try {
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/computer_science_ia", "root", "");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select answer from administrators where userName='"+userName+"'");

                resultSet.next();
                if(resultSet.getString("answer").equals(answer1)) {
                    statement.executeUpdate("update administrators set password='"+newPassword+"' where userName='"+userName+"' and answer='"+answer1+"'");
                    JOptionPane.showMessageDialog(null, "Your password has been updated");
                    frame.dispose();
                    LoginScreenAdmin loginScreenStudent = new LoginScreenAdmin();
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter correct username or answer");
                }

            } catch(SQLException sqlException) {
                JOptionPane.showMessageDialog(null, "Error in connection");
            }
        }

    }
}
