package pl.put.poznan.transformer.app;

import pl.put.poznan.transformer.logic.Input;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ScenarioGUI {
    private JButton wczytajButton;
    private JButton numerowanyButton;
    private JButton wyświetlButton;
    private JTextArea textArea1;
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private JPanel leftPanel;
    private JLabel conditionalsLabel;
    private JLabel conditionalsCount;
    private JLabel stepsLabel;
    private JLabel stepsCount;
    private JLabel limitLabel;
    private JComboBox<Integer> levelSelector;
    private JButton bledneKrokiButton;
    private JButton ograniczButton;
    private JLabel titleLabel;

    Input input;
    JFileChooser fc;
    File file;
    private boolean numbered = false;

    public ScenarioGUI() {
        wczytajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fc = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Plik tekstowy (*.txt)", "txt");
                fc.setAcceptAllFileFilterUsed(false);
                fc.setFileFilter(filter);
                int returnVal = fc.showDialog(mainPanel, "Wybierz plik");
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    file = fc.getSelectedFile();
                    System.out.println("Otwieranie pliku " + file);
                    ScenarioGUI.this.loadFile(file);
                } else {
                    System.out.println("Anulowano otwieranie pliku");
                }
            }
        });
        numerowanyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText(input.getNumberedSteps());
                numbered = true;
            }
        });
        wyświetlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText(input.getSteps());
                numbered = false;
            }
        });
        bledneKrokiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText("");
                for (String s : input.getBuggableLines())
                    textArea1.append(s + "\n");
                numbered = false;
            }
        });
        ograniczButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText(input.getSubScenarios(numbered ? input.getNumberedSteps().split("\n")
                        : input.getSteps().split("\n"), (int) levelSelector.getSelectedItem()));
            }
        });
    }

    private void loadFile(File file) {
        input = new Input(file.toString());
        titleLabel.setText(input.getTitle());
        textArea1.setText(input.getSteps());
        stepsCount.setText(Integer.toString(input.getStepsCount()));
        conditionalsCount.setText(Integer.toString(input.getConditionalDecisionCount()));
        levelSelector.removeAllItems();
        System.out.println(input.getMaxDepth());
        for (int i = 0; i <= input.getMaxDepth() + 1; i++)
            levelSelector.addItem(i);
        levelSelector.setEnabled(true);
        numbered = false;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ScenarioGUI");
        frame.setContentPane(new ScenarioGUI().mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        final JPanel spacer1 = new JPanel();
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(spacer1, gbc);
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(leftPanel, gbc);
        scrollPane = new JScrollPane();
        scrollPane.setMinimumSize(new Dimension(300, 200));
        scrollPane.setPreferredSize(new Dimension(600, 400));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        leftPanel.add(scrollPane, gbc);
        textArea1 = new JTextArea();
        textArea1.setEditable(false);
        textArea1.setMinimumSize(new Dimension(300, 200));
        scrollPane.setViewportView(textArea1);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(spacer2, gbc);
        numerowanyButton = new JButton();
        numerowanyButton.setText("Wyświetl numerowany scenariusz");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(numerowanyButton, gbc);
        wyświetlButton = new JButton();
        wyświetlButton.setText("Wyświetl załadowany scenariusz");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(wyświetlButton, gbc);
        bledneKrokiButton = new JButton();
        bledneKrokiButton.setText("Wyświetl błędne kroki");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(bledneKrokiButton, gbc);
        wczytajButton = new JButton();
        wczytajButton.setText("Wczytaj scenariusz");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        leftPanel.add(wczytajButton, gbc);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(panel1, gbc);
        conditionalsLabel = new JLabel();
        conditionalsLabel.setText("Liczba instrukcji warunkowych:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(conditionalsLabel, gbc);
        stepsLabel = new JLabel();
        stepsLabel.setText("Liczba kroków:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(stepsLabel, gbc);
        stepsCount = new JLabel();
        stepsCount.setText("0cxc");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(stepsCount, gbc);
        conditionalsCount = new JLabel();
        conditionalsCount.setText("0");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(conditionalsCount, gbc);
        limitLabel = new JLabel();
        limitLabel.setText("Stopień scenariusza:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(limitLabel, gbc);
        levelSelector = new JComboBox();
        levelSelector.setEnabled(false);
        levelSelector.setPreferredSize(new Dimension(60, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(levelSelector, gbc);
        ograniczButton = new JButton();
        ograniczButton.setText("Ogranicz");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(ograniczButton, gbc);
        titleLabel = new JLabel();
        titleLabel.setText("Scenariusz");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
