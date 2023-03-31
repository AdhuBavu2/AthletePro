package Admininstrator.Panels;

import javax.swing.*;
import java.awt.*;

public class AdminDashboardPanel extends JPanel {
    private JLabel announcementLabel;
    private JLabel dateLabel;

    public AdminDashboardPanel() {
        setBackground(new Color(0, 100, 246));
        setLayout(null);

        announcementLabel = new JLabel("Send Announcements");
        announcementLabel.setBounds(25, 30, 350, 100);
        announcementLabel.setFont(new Font("Open Sans", Font.BOLD, 24));
        announcementLabel.setForeground(Color.BLACK);
        add(announcementLabel);

        dateLabel = new JLabel("Select a date:");
        dateLabel.setBounds(25, 130, 150, 30);
        dateLabel.setFont(new Font("Open Sans", Font.PLAIN, 14));
        dateLabel.setForeground(Color.BLACK);
        add(dateLabel);
    }
}
