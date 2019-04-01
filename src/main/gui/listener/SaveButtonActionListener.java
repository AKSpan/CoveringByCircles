package main.gui.listener;

import main.gui.GuiBuilder;
import main.gui.GuiBuilderV2;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveButtonActionListener implements ActionListener {

    public SaveButtonActionListener(GuiBuilderV2 guiBuilder) {
        this.guiBuilder = guiBuilder;
    }

    private GuiBuilderV2 guiBuilder;

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("Выберите место для сохранения...");
            chooser.setFileFilter(new FileNameExtensionFilter("Текстовые файлы .txt", ".txt"));
            chooser.setSelectedFile(new File("coordinates.txt"));
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                String fileName = selectedFile.getPath();
                File f = new File(fileName);
                FileWriter fw = new FileWriter(f);
                BufferedWriter bf = new BufferedWriter(fw);
                bf.write(guiBuilder.getCoordinateSystem().fullDataString());
                bf.close();
                JOptionPane.showConfirmDialog(null, "Файл сохранен в " + f.getPath(), "Сохранение", JOptionPane.DEFAULT_OPTION);
            }
        } catch (IOException e1) {
            JOptionPane.showConfirmDialog(null, e1.getMessage(), "Ошибка сохранения", JOptionPane.DEFAULT_OPTION);
        }
    }

}
