package pl.put.poznan.transformer.app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Kuba on 28.12.2017.
 */
public class MainWindow {
    private static JFileChooser fc;
    private JPanel mainPanel;
    private JButton aleksandraButton;
    private JButton analizaButton;
    private JPanel bottomPanel;
    private JPanel topPanel;
    private JList list1;
    //private JFileChooser fc;
    private File file;

    public MainWindow() {
        aleksandraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int returnVal = fc.showDialog(mainPanel, "Wybierz plik");
                if (returnVal == JFileChooser.APPROVE_OPTION){
                    file = fc.getSelectedFile();
                    System.out.println("Otwieranie pliku");
                }
                else
                {
                    System.out.println("Anulowano otwieranie pliku");
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainWindow");
        frame.setContentPane(new MainWindow().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        fc = new JFileChooser();
    }
}
