package MainFrame.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.nio.file.Files;
import java.sql.*;

public class DocumentPanel extends JPanel {
    private JButton uploadButton;

    public DocumentPanel() {
        setBackground(new Color(0, 120, 246));
        setLayout(null);

        // Create and add the upload button
        uploadButton = new JButton("Upload File");
        uploadButton.setBounds(10, 10, 100, 30);
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(DocumentPanel.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                    // Insert the file into the database
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/computer_science_ia", "root", "");
                        String name = selectedFile.getName();
                        byte[] content = Files.readAllBytes(selectedFile.toPath());
                        String sql = "INSERT INTO uploaded_documents (name, content) VALUES (?, ?)";
                        try (PreparedStatement statement = connection.prepareStatement(sql)) {
                            statement.setString(1, name);
                            statement.setBytes(2, content);
                            statement.executeUpdate();
                        }
                        JOptionPane.showMessageDialog(DocumentPanel.this, "File uploaded successfully!");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(DocumentPanel.this, "Error uploading file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        add(uploadButton);
    }
}
