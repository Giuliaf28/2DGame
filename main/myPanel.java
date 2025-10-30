package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import entity.Player;

/** 
 * Class myPanel
 * Describes the game panel where the game is drawn
 * it extends JPanel and implements Runnable to create a game loop
 * 
 * It contains:
 * 1. the settings for the game panel
 * 2. the game loop thread and th e FPS settings
 * 3. The key listener to handle user input
 * 4. The player coordinates and speed
 * 5. The constructor to set up the panel
 * 6. The method to start the game thread
 * 7. The run method to implement the game loop
 * 8. The update method to update game information
 * 9. The paintComponent method to draw the game elements
 */
public class myPanel extends JPanel implements Runnable {

    // impostazioni pannello di gioco
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48x48
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels


    // FPS
    Thread gameThread;
    int FPS=60;


    myKeyListener keyListener = new myKeyListener();

    // COORDINATE PLAYER
    int playerX = 100;
    int playerY = 100;
    float playerSpeed = 5;
    Player player = new Player(this, keyListener);

    public myPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyListener);
        this.setFocusable(true); // panel can receive focus to receive key input
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {


        double drawInterval = 1000000000 / FPS; // 60 FPS
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            // updates game infos
            update();
            // draws the screen
            repaint();

            try {
                double remainingTime=nextDrawTime-System.nanoTime();
                remainingTime=remainingTime/1000000;

                if(remainingTime<0) {
                    remainingTime=0;
                }

                Thread.sleep((long)remainingTime);

                nextDrawTime+=drawInterval;

            } catch (Exception e) {
                // TODO: handle exception
            }
        }

    }

    public void update() {
        player.update();    
    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g; // has more functions than Graphics
        player.draw(g2);
        g2.dispose();
    }

}
