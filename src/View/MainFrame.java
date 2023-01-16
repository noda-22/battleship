/*
  Author: Nora Darejati
  Id: aj7718
  Study program: Medieproduktion och processdesign (Valbar kurs)
*/

package View;

import Controller.Controller;

import javax.swing.*;


public class MainFrame extends JFrame {
    private MainPanel mainPanel;
    private Controller controller;

    public MainFrame(int width, int height, Controller controller) {
        super("Sänka skepp");
        this.controller = controller;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //avsluta när fönstret stängs
        this.setResizable(false);
        this.setSize(width, height);
        this.mainPanel = new MainPanel(width, height, controller);
        this.setContentPane(mainPanel);
        this.setVisible(true);

    }


    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public String inputDialog(String message) {
        return JOptionPane.showInputDialog(message);
    }
    public void showMessage (String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public void updateHighscore(String[] stringList) {
        mainPanel.getrPanel().updateHighscore(stringList);
    }

    public void updateAntalSkott(String skott) { mainPanel.getlPanel().updateAntalSkott(skott); }

    public void updateButton(int row, int col, String text) {
        mainPanel.getlPanel().updateButton(row, col, text);
    }

    public void reset() {
        mainPanel.getlPanel().reset();
    }
}
