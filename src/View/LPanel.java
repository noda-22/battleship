/*
  Author: Nora Darejati
  Id: aj7718
  Study program: Medieproduktion och processdesign (Valbar kurs)
*/

package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LPanel extends JPanel {
    private JPanel buttonPanel;
    private JButton[][] grid;
    private JLabel antalSkott;

    private int width;
    private int height;
    private Controller controller;

    public LPanel(int width, int height, Controller controller) {
        this.controller = controller;
        this.setLayout(null);
        this.width = width;
        this.height = height;
        this.setSize(width, height);
        setLocation(0, 0);
        setUp();
    }

    public void setUp() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(10, 10));

        antalSkott = new JLabel("Antal skott: 0");
        antalSkott.setLocation(10, 0);
        antalSkott.setSize(100, 20);
        this.add(antalSkott);

        grid = new JButton[10][10];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                JButton btn = new JButton("");
                grid[i][j] = btn;
                btn.setFocusPainted(false);
                int currentI = i;
                int currentJ = j;
                btn.addActionListener(l -> {
                    btn.setEnabled(false);
                    controller.checkPosition(currentI, currentJ);
                    controller.checkNumberOfHits();
                });
                buttonPanel.add(btn);
            }
        }
        buttonPanel.setLocation(0, 23);
        buttonPanel.setSize(width, height - 100);
        this.add(buttonPanel);
    }

    public void reset() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j].setEnabled(true);
                grid[i][j].setText("");
            }
        }
    }


    public void updateAntalSkott(String skott) { antalSkott.setText(skott); }

    public void updateButton(int row, int col, String text) { grid[row][col].setText(text); }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
