package main.gui.listener;

import main.core.objects.Circle;
import main.core.objects.Point;
import main.gui.GuiBuilderV2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class LoadButtonActionListener implements ActionListener {
    private GuiBuilderV2 guiBuilder;

    public LoadButtonActionListener(GuiBuilderV2 guiBuilder) {
        this.guiBuilder = guiBuilder;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int ret = fileChooser.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File loadedFile = fileChooser.getSelectedFile();
            if (loadedFile.canRead()) {
                try (BufferedReader br = new BufferedReader(new FileReader(loadedFile))) {
                    String sCurrentLine;
                    Set<Point> points = new HashSet<>();
                    Circle circle = null;
                    boolean isCirclePart = false;
                    LineNumberReader count = new LineNumberReader(new FileReader(loadedFile));
                    int progressBarStep = count.getLineNumber() / 100;

                    while ((sCurrentLine = br.readLine()) != null) {
//                        this.guiBuilder.getProgressBar().setValue(this.guiBuilder.getProgressBar().getValue() + progressBarStep);
                        if (sCurrentLine.equals("POINTS:"))
                            continue;
                        if (sCurrentLine.equals("CIRCLE:")) {
                            isCirclePart = true;
                            continue;
                        }
                        String[] coors = sCurrentLine.split(",");
                        if (isCirclePart) {
                            circle = new Circle(new Point(Double.valueOf(coors[0]), Double.valueOf(coors[1])), Double.valueOf(coors[2]));
                            break;
                        } else {
                            points.add(new Point(Double.valueOf(coors[0]), Double.valueOf(coors[1])));

                        }
                    }
                    guiBuilder.getLogicWrapper().getCoordinateSystem().setCircle(circle);
                    guiBuilder.getLogicWrapper().getCoordinateSystem().addPoints(points);
                    guiBuilder.getUiComponentsHolder().getDrawPanel().addPoints(points, circle);
                    guiBuilder.getUiComponentsHolder().setLabelText("Точек " + points.size());
                } catch (IOException ex) {
                    JOptionPane.showConfirmDialog(null, ex.getMessage(), "Не удалось прочитать файл", JOptionPane.DEFAULT_OPTION);
                }
            }
        }
    }//2380

}
