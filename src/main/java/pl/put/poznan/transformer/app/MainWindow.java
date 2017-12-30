package pl.put.poznan.transformer.app;

import pl.put.poznan.transformer.logic.Input;

import javax.swing.*;
import java.io.File;

/**
 * Created by Kuba on 28.12.2017.
 */
public class MainWindow {
    private static JFileChooser fc;
    private JPanel mainPanel;
    private JButton wczytajButton;
    private JButton analizaButton;
    private JPanel bottomPanel;
    private JPanel topPanel;
    private JList<String> list1;
    private JLabel scenarioTitle;
    private JScrollPane scrollPane;
    private File file;

    public MainWindow() {
        wczytajButton.addActionListener(actionEvent -> {
            int returnVal = fc.showDialog(mainPanel, "Wybierz plik");
            if (returnVal == JFileChooser.APPROVE_OPTION){
                file = fc.getSelectedFile();
                System.out.println("Otwieranie pliku " + file);
                loadFile(file);
            }
            else
            {
                System.out.println("Anulowano otwieranie pliku");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Analizator Scenariuszy");
        frame.setContentPane(new MainWindow().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        fc = new JFileChooser();
    }

    private void loadFile(File file){
        Input input = new Input(file.toString());
        scenarioTitle.setText(input.getTitle());
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String s : input.getSteps().split("\n"))
            model.addElement(s);
        list1.setModel(model);
    }
}
