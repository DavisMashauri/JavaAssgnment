# JavaAssgnment
package pastpaperreview;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;

public class PastPaperReview {
    private static ArrayList<PastPaper> pastPapers = new ArrayList<>();

    public static void main(String[] args) {

        JFrame frame = new JFrame("Student Past Paper Review System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main Menu
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(3, 1));

        JButton uploadButton = new JButton("Upload Past Paper");
        JButton viewButton = new JButton("View Past Papers");
        JButton searchButton = new JButton("Search Past Papers");

        menuPanel.add(uploadButton);
        menuPanel.add(viewButton);

        menuPanel.add(searchButton);

        frame.add(menuPanel);
        frame.setVisible(true);

        // Upload Past Paper
        uploadButton.addActionListener(e -> uploadPastPaper(frame));

        // View Past Papers
        viewButton.addActionListener(e -> viewPastPapers(frame));

        // Search Past Papers
        searchButton.addActionListener(e -> searchPastPapers(frame));
    }

    // Upload Past Paper
    private static void uploadPastPaper(JFrame frame) {

        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            // Get metadata
            String courseName = JOptionPane.showInputDialog("Enter Course Name:");
            String year = JOptionPane.showInputDialog("Enter Year:");

            pastPapers.add(new PastPaper(selectedFile.getName(), selectedFile.getAbsolutePath(), courseName, year));

            JOptionPane.showMessageDialog(frame, "Past paper uploaded successfully!");
        }
    }

    // View Past Papers
    private static void viewPastPapers(JFrame frame) {
        JFrame viewFrame = new JFrame("View Past Papers");
        viewFrame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (PastPaper paper : pastPapers) {
            JButton button = new JButton(paper.getFileName() + " - " + paper.getCourseName() + " (" + 

paper.getYear() + ")");
            button.addActionListener(e -> openFile(paper.getFilePath()));
            panel.add(button);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        viewFrame.add(scrollPane);
        viewFrame.setVisible(true);
    }

    // Search Past Papers
    private static void searchPastPapers(JFrame frame) {
        String searchTerm = JOptionPane.showInputDialog("Enter Course Name or Year:");

        JFrame searchFrame = new JFrame("Search Results");

        searchFrame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (PastPaper paper : pastPapers) {
            if (paper.getCourseName().equalsIgnoreCase(searchTerm) || paper.getYear().equalsIgnoreCase(searchTerm)) {
                JButton button = new JButton(paper.getFileName() + " - " + paper.getCourseName() + " (" + paper.getYear() + ")");
                button.addActionListener(e -> openFile(paper.getFilePath()));
                panel.add(button);
            }
        }


        JScrollPane scrollPane = new JScrollPane(panel);
        searchFrame.add(scrollPane);
        searchFrame.setVisible(true);
    }

    // Open File
    private static void openFile(String filePath) {
        try {
            Desktop.getDesktop().open(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// PastPaper Class to Store Metadata
class PastPaper {

    private String fileName;
    private String filePath;
    private String courseName;
    private String year;

    public PastPaper(String fileName, String filePath, String courseName, String year) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.courseName = courseName;
        this.year = year;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getYear() {
        return year;
    }
}
