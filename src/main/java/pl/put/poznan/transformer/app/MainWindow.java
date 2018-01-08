package pl.put.poznan.transformer.app;

import pl.put.poznan.transformer.logic.Input;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.ComboBoxUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JLabel scenarioStepsLabel;
    private JTextArea textArea1;
    private JButton ograniczButton;
    private JTextPane textPane1;
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

        podglądScenariuszaButton.addActionListener(e -> {
            textArea1.setText(input.getSteps());
        });

        pobierzZNumeracjąKrokówButton.addActionListener(e -> {
            textArea1.setText(input.getNumberedSteps());
        });

        pobierzKrokiNieZaczynająceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (String s : input.getBuggableLines())
                    textArea1.append(s);
            }
        });
        ograniczButton.addActionListener(e -> {
            if ((int)levelSelectionBox.getSelectedItem() != 0)
                textArea1.setText(input.getSubScenarios(textArea1.getText().split("\n"), (int)levelSelectionBox.getSelectedItem()));
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
        textArea1.setText(input.getSteps());
        scenarioSteps.setText(Integer.toString(input.getStepsCount()));
        scenarioCond.setText(Integer.toString(input.getConditionalDecisionCount()));
        levelSelectionBox.removeAllItems();
        System.out.println(input.getMaxDepth());
        for (int i = 0; i <= input.getMaxDepth() + 1; i++)
            levelSelectionBox.addItem(i);
    }

    private void createUIComponents() {
        Integer[] numbers = IntStream.range(0,10).boxed().toArray(Integer[]::new);
        levelSelectionBox = new JComboBox<>(numbers);
    }
}
