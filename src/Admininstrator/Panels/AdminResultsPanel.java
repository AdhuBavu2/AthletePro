package Admininstrator.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class AdminResultsPanel extends JPanel implements ActionListener {

    private JLabel resultsTitle;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel eventLabel;
    private JComboBox eventComboBox;
    private ArrayList<String> eventsList = new ArrayList<String>();
    private JLabel meetLabel;
    private JComboBox meetComboBox;
    private JLabel resultLabel;
    private JTextField resultField;
    private JButton submit;
    public AdminResultsPanel() {
        setBackground(new Color(0, 100, 246));
        setLayout(null);

        eventsList.add("100 meter dash");
        eventsList.add("200 meter dash");
        eventsList.add("400 meter dash");
        eventsList.add("110 meter Hurdles");
        eventsList.add("300 meter Hurdles");
        eventsList.add("800 meter run");
        eventsList.add("1600 meter run");
        eventsList.add("3200 meter run");
        eventsList.add("Discus");
        eventsList.add("Shot put");
        eventsList.add("Javelin");
        eventsList.add("Long Jump");
        eventsList.add("High Jump");

        resultsTitle = new JLabel("Results");
        resultsTitle.setBounds(100, 10, 350, 100);
        resultsTitle.setFont(new Font("Open Sans", Font.BOLD, 24));
        resultsTitle.setForeground(Color.BLACK);
        add(resultsTitle);

        nameLabel = new JLabel("Full Name");
        nameLabel.setBounds(100, 80, 350, 100);
        nameLabel.setFont(new Font("Open Sans", Font.BOLD, 16));
        nameLabel.setForeground(Color.BLACK);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100, 150, 250, 33);
        add(nameField);

        eventLabel = new JLabel("Select Event");
        eventLabel.setBounds(100, 170, 350, 100);
        eventLabel.setFont(new Font("Open Sans", Font.BOLD, 16));
        eventLabel.setForeground(Color.BLACK);
        add(eventLabel);

        eventComboBox = new JComboBox(eventsList.toArray(new String[0]));
        eventComboBox.setBounds(100, 240, 250, 33);
        add(eventComboBox);

        meetLabel = new JLabel("Select Meet");
        meetLabel.setBounds(100, 260, 350, 100);
        meetLabel.setFont(new Font("Open Sans", Font.BOLD, 16));
        meetLabel.setForeground(Color.BLACK);
        add(meetLabel);

        meetComboBox = new JComboBox<String>();
        meetComboBox.setBounds(100, 330, 250, 33);
        add(meetComboBox);

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/computer_science_ia", "root", "");
            PreparedStatement statement = connection.prepareStatement("SELECT name FROM track_meets");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                meetComboBox.addItem(resultSet.getString("name"));
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error in connection");
        }

        resultLabel = new JLabel("Result");
        resultLabel.setBounds(500, 150, 350, 100);
        resultLabel.setFont(new Font("Open Sans", Font.BOLD, 16));
        resultLabel.setForeground(Color.BLACK);
        add(resultLabel);

        resultField = new JTextField();
        resultField.setBounds(500, 220, 250, 33);
        add(resultField);

        submit = new JButton("Submit Results");
        submit.addActionListener(this);
        submit.setBounds(500, 300, 225, 50);
        submit.setFocusable(false);
        submit.setBackground(new Color(246, 254, 219));
        submit.setOpaque(true);
        add(submit);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit) {
            String name = nameField.getText();
            String event = (String) eventComboBox.getSelectedItem();
            String meet = (String) meetComboBox.getSelectedItem();
            String result = resultField.getText();

            try {
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/computer_science_ia", "root", "");
                Statement statement = connection.createStatement();

                statement.executeUpdate("INSERT INTO results (name, event, meet, result) VALUES ('"+ name +"', '"+ event +"', '"+ meet +"', '"+ result +"')");

                JOptionPane.showMessageDialog(null, "Added Result");

                nameField.setText("");
                resultField.setText("");


            } catch (SQLException sqlException) {
                JOptionPane.showMessageDialog(null, "Error in Connection");
            }
        }
    }
}
