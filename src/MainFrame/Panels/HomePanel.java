package MainFrame.Panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class HomePanel extends JPanel implements ActionListener{
    private JLabel slideshowLabel;
    private BufferedImage[] allImages;
    private JLabel[] imageLabels;
    private ImageIcon icon;
    private JButton nextButton;
    private JButton previousButton;
    private JLabel announcementLabel;
    private JTextArea announcementArea;


    public HomePanel(String name) {
        setBackground(new Color(0, 100, 246));
        setLayout(null);

        File folder = new File("Slideshow Images");
        File[] files = folder.listFiles();

        slideshowLabel = new JLabel("Recent Photos");
        slideshowLabel.setBounds(50, 0, 350, 100);
        slideshowLabel.setFont(new Font("Open Sans", Font.BOLD, 24));
        slideshowLabel.setForeground(Color.BLACK);
        add(slideshowLabel);

        int width = 400;
        int height = 225;

        allImages = new BufferedImage[files.length];
        imageLabels = new JLabel[files.length];

        int x = 50;
        int y = 80;
        int gap = 10;

        for(int i = 0; i < files.length; i++) {
            try {
                allImages[i] = ImageIO.read(files[i]);
                Image scaledImage = allImages[i].getScaledInstance(width, height, Image.SCALE_SMOOTH);
                allImages[i] = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                allImages[i].getGraphics().drawImage(scaledImage, 0, 0, null);
                imageLabels[i] = new JLabel();
                icon = new ImageIcon(allImages[i]);
                imageLabels[i].setIcon(icon);
                imageLabels[i].setBounds(x, y, width, height);
                add(imageLabels[i]);
                imageLabels[i].setVisible(false);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int buttonWidth = 30;
        int buttonHeight = 30;
        int buttonX = 200;
        int buttonY = 330;

        nextButton = new JButton(new ImageIcon("Application Icons and Images/Right Arrow Transparent.png"));
        nextButton.setBackground(new Color(246, 254, 219));
        nextButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        nextButton.addActionListener(new ActionListener() {
            int currentImageIndex = 0;

            public void actionPerformed(ActionEvent e) {
                imageLabels[currentImageIndex].setVisible(false);
                currentImageIndex++;
                if (currentImageIndex == imageLabels.length) {
                    currentImageIndex = 0;
                }
                imageLabels[currentImageIndex].setVisible(true);
            }
        });


        buttonX += buttonWidth + gap;

        previousButton = new JButton(new ImageIcon("Application Icons and Images/Left Arrow Transparent.png"));
        previousButton.setBackground(new Color(246, 254, 219));
        previousButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        previousButton.addActionListener(new ActionListener() {
            int currentImageIndex = 0;

            public void actionPerformed(ActionEvent e) {

                imageLabels[currentImageIndex].setVisible(false);
                currentImageIndex--;
                if (currentImageIndex < 0) {
                    currentImageIndex = imageLabels.length - 1;
                }
                imageLabels[currentImageIndex].setVisible(true);
            }
        });

        imageLabels[0].setVisible(true);

        add(nextButton);
        add(previousButton);

        announcementLabel = new JLabel("Announcements");
        announcementLabel.setBounds(530, 0, 350, 100);
        announcementLabel.setFont(new Font("Open Sans", Font.BOLD, 24));
        announcementLabel.setForeground(Color.BLACK);
        add(announcementLabel);


        announcementArea = new JTextArea();
        announcementArea.setBounds(530, 80, 400, 340);
        announcementArea.setLineWrap(true);
        announcementArea.setWrapStyleWord(true);
        announcementArea.setEditable(false);
        add(announcementArea);

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/computer_science_ia", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM announcements");

            while(resultSet.next()) {
                String date = resultSet.getString("date");
                String text = resultSet.getString("text");
                announcementArea.append(date + " - " + text + "\n\n");
            }

        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error in Connection");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
