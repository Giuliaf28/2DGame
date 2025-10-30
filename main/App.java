package main;

import javax.swing.JFrame;

public class App {
    public static void main(String[] args) {
        
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("2D Game");


        myPanel gamePanel = new myPanel();
        frame.add(gamePanel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true); 


        gamePanel.startGameThread();
    }
}