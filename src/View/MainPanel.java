/*
  Author: Nora Darejati
  Id: aj7718
  Study program: Medieproduktion och processdesign (Valbar kurs)
*/

package View;

import Controller.Controller;

import javax.swing.*;

public class MainPanel extends JPanel {
    private LPanel lPanel;
    private RPanel rPanel;

    public MainPanel(int width, int height, Controller controller) {
        super(null);
        this.setSize(width, height);

        lPanel = new LPanel(width / 2 - 2, height, controller);
        add(lPanel);


        rPanel = new RPanel(width / 2 + 10, height, controller);
        add(rPanel);
    }


    public LPanel getlPanel() {
        return lPanel;
    }

    public void setlPanel(LPanel lPanel) {
        this.lPanel = lPanel;
    }

    public RPanel getrPanel() {
        return rPanel;
    }

    public void setrPanel(RPanel rPanel) {
        this.rPanel = rPanel;
    }
}
