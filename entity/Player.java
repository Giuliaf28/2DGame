package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.myKeyListener;
import main.myPanel;

public class Player extends Entity {

    myKeyListener kl;
    myPanel mp;

    public Player(myPanel mp, myKeyListener kl) {
        this.mp = mp;
        this.kl = kl;

        setDefaultValues();
    }

    public void setDefaultValues() {

        x = 100;
        y = 100;
        speed = 5;
    }

    public void update() {


        if (kl.upPressed == true) 
            y -= speed;
            if(y>mp.screenHeight+mp.tileSize)
                y=0;
            
        else if (kl.downPressed == true)
            y += speed;
            if(y<0-mp.tileSize)
                y=mp.screenHeight;

        
        else if (kl.leftPressed == true)
            x -= speed;
            if(x>mp.screenWidth+mp.tileSize)
                x=0;

        else if (kl.rightPressed == true)
            x += speed;
            if(x<0)
                x=mp.screenWidth;

    }

public void draw(Graphics2D g2) {

        g2.setColor(Color.white);
        g2.fillRect(x, y, mp.tileSize, mp.tileSize);
    }
}



