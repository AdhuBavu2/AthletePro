package MainFrame.Panels;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ResultsPanel extends JPanel {
    private String dbName;
    private JLabel nameLabel;
    private JTextArea resultsArea;

    public ResultsPanel(String name) {
        setBackground(new Color(0, 120, 246));
        setLayout(null);
        dbName = name;

        nameLabel = new JLabel("Results for " + dbName);
        nameLabel.setBounds(10, 10, 400, 50);
        nameLabel.setFont(new Font("Open Sans", Font.BOLD, 24));
        add(nameLabel);

        resultsArea = new JTextArea();
        resultsArea.setEditable(false);
        resultsArea.setBounds(10, 85, 380, 340);
        add(resultsArea);

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/computer_science_ia", "root", "");

            String query = "SELECT * FROM results WHERE name = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, dbName);
            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {
                String result = rs.getString("result");
                String meet = rs.getString("meet");
                String event = rs.getString("event");
                resultsArea.append("Meet: " + meet + " \nEvent: " + event + " \nResult: " + result + "\n\n");
            }


            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
