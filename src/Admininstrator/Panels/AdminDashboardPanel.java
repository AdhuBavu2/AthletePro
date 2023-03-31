package Admininstrator.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminDashboardPanel extends JPanel implements ActionListener {
    private JLabel announcementTitle;
    private JLabel dateLabel;
    private JTextField dateField;
    private JLabel announcementLabel;
    private JTextArea textArea;
    private JButton submit;


    public AdminDashboardPanel() {
        setBackground(new Color(0, 100, 246));
        setLayout(null);

        announcementTitle = new JLabel("Send Announcements");
        announcementTitle.setBounds(25, 30, 350, 100);
        announcementTitle.setFont(new Font("Open Sans", Font.BOLD, 24));
        announcementTitle.setForeground(Color.BLACK);
        add(announcementTitle);

        dateLabel = new JLabel("Select a date (YYYY-MM-DD)");
        dateLabel.setBounds(25, 100, 250, 30);
        dateLabel.setFont(new Font("Open Sans", Font.PLAIN, 14));
        dateLabel.setForeground(Color.BLACK);
        add(dateLabel);

        dateField = new JTextField();
        dateField.setBounds(25, 150, 225, 33);
        add(dateField);

        announcementLabel = new JLabel("Message");
        announcementLabel.setBounds(25, 210, 250, 30);
        announcementLabel.setFont(new Font("Open Sans", Font.PLAIN, 14));
        announcementLabel.setForeground(Color.BLACK);
        add(announcementLabel);

        textArea = new JTextArea();
        textArea.setBounds(25, 255, 300, 120);
        add(textArea);

        submit = new JButton("Send Announcement");
        submit.addActionListener(this);
        submit.setBounds(25, 395, 225, 33);
        submit.setFocusable(false);
        submit.setBackground(new Color(246, 254, 219));
        submit.setOpaque(true);
        add(submit);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit) {
            String date = dateField.getText();
            String message = textArea.getText();

            try {
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/computer_science_ia", "root", "");
                Statement statement = connection.createStatement();

                statement.executeUpdate("INSERT INTO announcements (text, date) VALUES ('"+ message +"', '"+ date +"')");

                JOptionPane.showMessageDialog(null, "Added Announcement!");

                dateField.setText("");
                textArea.setText("");


            } catch (SQLException sqlException) {
                JOptionPane.showMessageDialog(null, "Error in Connection");
            }
        }
    }
}
