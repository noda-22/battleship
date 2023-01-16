/*
  Author: Nora Darejati
  Id: aj7718
  Study program: Medieproduktion och processdesign (Valbar kurs)
*/

package View;

import Controller.Controller;

import javax.swing.*;

public class RPanel extends JPanel {
    private JList<Object> highscore;
    private JButton newGame;
    private JLabel title;
//    private JLabel price;

    private int width;
    private int height;

    private Controller controller;

    public RPanel(int width, int height, Controller controller) {
        this.controller = controller;
        this.setLayout(null);
        this.width = width;
        this.height = height;
        this.setSize(width, height);
        setLocation(width, 0);
        setUp();
    }

    public void setUp() {
        title = new JLabel("Highscore");
        title.setLocation(0, 0);
        title.setSize(width / 2, 20);
        this.add(title);

        highscore = new JList<>();
        highscore.setLocation(0, 23);
        highscore.setSize(width, height - 100);
        this.add(highscore);


        newGame = new JButton("Ny spelomgång");
        newGame.setEnabled(true);
        newGame.setSize(width / 2, 30);
        newGame.setLocation(width / 2 - 30, height - 75);
        newGame.addActionListener(l -> controller.reset());
        this.add(newGame);

//        createOrder = new JButton("Visa highscore");
//        createOrder.setEnabled(true);
//        createOrder.setSize(width / 2 - 30, 30);
//        createOrder.setLocation(0, height - 75);
////        createOrder.addActionListener(l -> controller.placeOrder());
//        this.add(createOrder);
    }


    public void updateHighscore(String[] stringList)
    {
        highscore.setListData(stringList);
    }

//    public JList<Object> getHighscore() {
//        return highscore;
//    }
//
//    public void setHighscore(JList<Object> highscore) {
//        this.highscore = highscore;
//    }

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
