package pl.put.poznan.transformer.app;

import pl.put.poznan.transformer.logic.Input;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.ComboBoxUI;
import java.io.File;
import java.io.FileFilter;
import java.util.stream.IntStream;

/**
 * Created by Kuba on 28.12.2017.
 */
public class MainWindow {
    private JFileChooser fc;
    private JPanel mainPanel;
    private Input input;
    private JButton wczytajButton;
    private JButton podglądScenariuszaButton;
    private JPanel bottomPanel;
    private JPanel topPanel;
    private JList<String> list1;
    private JLabel scenarioTitle;
    private JScrollPane scrollPane;
    private JLabel scenarioLevelsLabel;
    private JLabel scenarioSteps;
    private JLabel levelSelectionLabel;
    private JComboBox levelSelectionBox;
    private JPanel rightPanel;
    private JButton pobierzKrokiNieZaczynająceButton;
    private JButton pobierzZNumeracjąKrokówButton;
    private JLabel scenarioCond;
    private File file;

    public MainWindow() {
        wczytajButton.addActionListener(actionEvent -> {
            fc = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Plik tekstowy (*.txt)", "txt");
            fc.setAcceptAllFileFilterUsed(false);
            fc.setFileFilter(filter);
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
    }

    private void loadFile(File file){
        input = new Input(file.toString());
        scenarioTitle.setText(input.getTitle());
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String s : input.getSteps().split("\n"))
            model.addElement(s);
        list1.setModel(model);
        scenarioSteps.setText(new Integer(input.getStepsCount()).toString());
        scenarioCond.setText(new Integer(input.getConditionalDecisionCount()).toString());
    }

    private void createUIComponents() {
        Integer[] numbers = IntStream.range(0,10).boxed().toArray(Integer[]::new);
        levelSelectionBox = new JComboBox<>(numbers);
    }
}
