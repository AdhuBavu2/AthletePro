package Admininstrator;

import Admininstrator.Panels.AdminDashboardPanel;
import Admininstrator.Panels.AdminResultsPanel;
import Admininstrator.Panels.AdminSignUpPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMainFrame implements ActionListener {
    private JFrame frame;
    private JPanel buttonPanel;
    private JPanel logoutPanel;
    private JButton logoutButton;
    private JLabel nameLabel;
    private String dbName;

    public AdminMainFrame(String name) {

        dbName = name;

        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(new Color(0, 100, 246));
        buttonPanel.setBounds(0, 0, 1000, 600);
        buttonPanel.setSize(1000, 600);
        buttonPanel.setBorder(new EmptyBorder(15, 0, 10, 0));

        frame = new JFrame("Main Screen");
        frame.add(buttonPanel);
        frame.setSize(1018, 600);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        nameLabel = new JLabel("Signed in as " + dbName);
        nameLabel.setBounds(30, 20, 350, 50);
        nameLabel.setFont(new Font("Open Sans", Font.BOLD, 20));
        buttonPanel.add(nameLabel);

        var tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Open Sans", Font.PLAIN, 16));
        tabbedPane.addTab("<html><body leftmargin=10 topmargin=8 marginwidth=10 marginheight=5 style='font-family:Tahoma;color:black'>Dashboard</body></html>", new AdminDashboardPanel());
        tabbedPane.addTab("<html><body leftmargin=10 topmargin=8 marginwidth=10 marginheight=5 style='font-family:Tahoma;color:black'>Sign Up</body></html>", new AdminSignUpPanel());
        tabbedPane.addTab("<html><body leftmargin=10 topmargin=8 marginwidth=10 marginheight=5 style='font-family:Tahoma;color:black'>Results</body></html>", new AdminResultsPanel());
        tabbedPane.setBounds(25, 70, 960, 500);
        tabbedPane.setBackground(new Color(246, 254, 219));
        tabbedPane.setForeground(Color.white);
        buttonPanel.add(tabbedPane);

        logoutPanel = new JPanel();
        logoutPanel.setLayout(null);
        logoutPanel.setBackground(new Color(0, 100, 246));
        logoutPanel.setBounds(860, 10, 100, 40);
        logoutPanel.setSize(100, 40);
        buttonPanel.add(logoutPanel);

        logoutButton = new JButton(new ImageIcon("Application Icons and Images/logout.png"));
        logoutButton.addActionListener(this);
        logoutButton.setBounds(0, 0, 100, 40);
        logoutButton.setSize(100, 40);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground(new Color(0, 100, 246));
        logoutPanel.add(logoutButton);

        UIManager.put("TabbedPane.borderHightlightColor", new Color(0, 0, 0));
        UIManager.put("TabbedPane.darkShadow", new Color(0, 0, 0));
        UIManager.put("TabbedPane.light", new Color(0, 0, 0));
        UIManager.put("TabbedPane.selectHighlight", new Color(0, 0, 0));
        UIManager.put("TabbedPane.darkShadow", new Color(0, 0, 0));
        UIManager.put("TabbedPane.focus", new Color(0, 0, 0));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}