package MainFrame.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class SignUpPanel extends JPanel implements ActionListener {
    private JLabel signupTitle;
    private JLabel nameTitle;
    private JTextField nameField;

    private JLabel optionTitle;
    private JComboBox<String> regularMeetComboBox;
    private ArrayList<String> eventsList = new ArrayList<>();
    private JComboBox eventsCombobox;
    private JLabel eventsLabel;
    private JButton submit;

    public SignUpPanel(String name) {
        setBackground(new Color(20, 100, 246));
        setLayout(null);

        signupTitle = new JLabel("Sign Up");
        signupTitle.setBounds(525, 0, 350, 100);
        signupTitle.setFont(new Font("Open Sans", Font.BOLD, 32));
        signupTitle.setForeground(Color.BLACK);
        add(signupTitle);

        nameTitle = new JLabel("Full Name");
        nameTitle.setBounds(525, 70, 350, 100);
        nameTitle.setFont(new Font("Open Sans", Font.BOLD, 16));
        nameTitle.setForeground(Color.BLACK);
        add(nameTitle);

        nameField = new JTextField();
        nameField.setBounds(525, 147, 225, 33);
        add(nameField);

        optionTitle = new JLabel("Select Meet");
        optionTitle.setBounds(525, 170, 350, 100);
        optionTitle.setFont(new Font("Open Sans", Font.BOLD, 16));
        optionTitle.setForeground(Color.BLACK);
        add(optionTitle);

        regularMeetComboBox = new JComboBox<String>();
        regularMeetComboBox.setBounds(525, 245, 225, 33);
        add(regularMeetComboBox);

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/computer_science_ia", "root", "");
            PreparedStatement statement = connection.prepareStatement("SELECT * from track_meets WHERE meet_type = 'regular'");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                regularMeetComboBox.addItem(resultSet.getString("name"));
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error in connection");
        }

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

        eventsLabel = new JLabel("Select Event");
        eventsLabel.setBounds(525, 275, 350, 100);
        eventsLabel.setFont(new Font("Open Sans", Font.BOLD, 16));
        eventsLabel.setForeground(Color.BLACK);
        add(eventsLabel);

        eventsCombobox = new JComboBox(eventsList.toArray(new String[0]));
        eventsCombobox.setBounds(525, 345, 225, 33);
        add(eventsCombobox);

        submit = new JButton("Sign Up");
        submit.addActionListener(this);
        submit.setBounds(525, 400, 225, 33);
        submit.setFocusable(false);
        submit.setBackground(new Color(246, 254, 219));
        submit.setOpaque(true);
        add(submit);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit) {

            String athleteName = nameField.getText();
            String meetName = (String) regularMeetComboBox.getSelectedItem();
            String eventName = (String) eventsCombobox.getSelectedItem();

            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/computer_science_ia", "root", "");
                PreparedStatement statement = connection.prepareStatement("INSERT INTO signups (athlete_name, meet_name, event) VALUES (?, ?, ?)");
                statement.setString(1, athleteName);
                statement.setString(2, meetName);
                statement.setString(3, eventName);

                statement.executeUpdate();

                statement.close();
                connection.close();

                JOptionPane.showMessageDialog(null, "Signup successful!");

            } catch (SQLException sqlException) {
                JOptionPane.showMessageDialog(null, "Error in connection");
            }
        }
    }

}
