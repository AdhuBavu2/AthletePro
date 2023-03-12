package MainFrame.Panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HomePanel extends JPanel implements ActionListener{
    private JLabel slideshowLabel;
    private BufferedImage[] allImages;
    private JLabel[] imageLabels;
    private ImageIcon icon;
    private JButton nextButton;
    private JButton previousButton;
    private int x;
    private int y;



    public HomePanel(String name) {
        setBackground(new Color(0, 100, 246));
        File folder = new File("Slideshow Images");
        File[] files = folder.listFiles();

        int width = 400;
        int height = 225;


        allImages = new BufferedImage[files.length];
        imageLabels = new JLabel[files.length];

        for(int i = 0; i < files.length; i++) {
            try {
                allImages[i] = ImageIO.read(files[i]);
                // resize the image to the desired dimensions
                Image scaledImage = allImages[i].getScaledInstance(width, height, Image.SCALE_SMOOTH);
                allImages[i] = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                allImages[i].getGraphics().drawImage(scaledImage, 0, 0, null);
                // create a new JLabel with the resized image
                imageLabels[i] = new JLabel();
                icon = new ImageIcon(allImages[i]);
                imageLabels[i].setIcon(icon);
                imageLabels[i].setBorder(BorderFactory.createEmptyBorder(50, 20, 0, 0)); // move image down by 10 pixels and right by 20 pixels
                add(imageLabels[i]);
                imageLabels[i].setVisible(false); // hide all images initially


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        setLayout(new FlowLayout(FlowLayout.LEFT));


        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            int currentImageIndex = 0;

            public void actionPerformed(ActionEvent e) {
                // hide the current image
                imageLabels[currentImageIndex].setVisible(false);
                // show the next image
                currentImageIndex++;
                if (currentImageIndex == imageLabels.length) {
                    currentImageIndex = 0;
                }
                imageLabels[currentImageIndex].setVisible(true);
            }
        });

        previousButton = new JButton("Previous");
        previousButton.addActionListener(new ActionListener() {
            int currentImageIndex = 0;

            public void actionPerformed(ActionEvent e) {
                // hide the current image
                imageLabels[currentImageIndex].setVisible(false);
                // show the previous image
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


    }






    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
