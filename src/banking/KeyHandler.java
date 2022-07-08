
package banking;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class KeyHandler implements KeyListener
{
    GamePanel gp;
    
    public KeyHandler(GamePanel gp)
    {
        this.gp = gp;
    }
    public boolean upPressed, downPressed;
    
    public boolean withdraw, deposit, transfer, transaction;
    
    public String accBalance(double balance, String bals)
    {
        Random rand = new Random();
        
        int num = rand.nextInt(7);
        
        switch(num)
        {
            case 1: balance = 12352.89;
            break;
            case 2: balance = 52.28;
            break;
            case 3: balance = 270021.12;
            break;
            case 4: balance = 12875.23;
            break;
            case 5: balance = 192818.14;
            break;
            case 6: balance = 1932.85;
            break;
            case 7: balance = 0.01;
            break;
            default: balance = 13832.12;
            break;
        }
        bals = String.format("%.2f",balance);
         return bals;
    }
    
    @Override
    public void keyPressed(KeyEvent e)
    {
       if(gp.mainState == gp.mainMenuState)
       {

           if(e.getKeyCode()== KeyEvent.VK_S)
            {
                gp.commandNum++;
                downPressed = true;
            }
            if(e.getKeyCode() == KeyEvent.VK_W)
             {
                gp.commandNum--;
                upPressed = true;
             }
            
            if(gp.commandNum == 1)
            {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    withdraw = true;
                    deposit = false;
                    transfer = false;
                    transaction = false;
                    
                }
         
            }
            if(gp.commandNum == 2)
            {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    deposit = true;
                    transfer = false;
                    transaction = false;
                    withdraw = false;
                }
                
            }
            if(gp.commandNum == 3)
            {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    transfer = true;
                    transaction = false;
                    withdraw = false;
                    deposit = false;
                }
                
            }
            if(gp.commandNum == 4)
            {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    transaction = true;
                    transfer = false;
                    withdraw = false;
                    deposit = false;
                    
                    gp.mainState = gp.tranState;
                }
            }
            if(gp.mainState == gp.tranState)
            {
                 if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
                    {
                        gp.mainState = gp.mainMenuState;
                    }
            }
       }
        
    }
    @Override
    public void keyReleased(KeyEvent e)
    {
        if(e.getKeyCode()== KeyEvent.VK_S)
        {
            downPressed = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_W)
        {
           upPressed = false;
        }
    }
    @Override
    public void keyTyped(KeyEvent e)
    {
        
    }
}
