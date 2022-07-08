
package banking;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;


public class GamePanel extends JPanel implements Runnable
{
    
    Thread gameThread;
    
    KeyHandler keyH = new KeyHandler(this);
    
    public boolean check, check2, check3;
    
    Image bankaccount = new ImageIcon("src\\images\bankaccount.png").getImage();
    Image download = new ImageIcon("src\\images\\download.png").getImage();
    Image transaction = new ImageIcon("src\\images\\transaction.png").getImage();
    Image transfer = new ImageIcon("src\\images\\transfer.png").getImage();
    Image withdraw = new ImageIcon("src\\images\\withdraw.png").getImage();
    
    Rectangle rect = new Rectangle(45, 80, 200, 300);
    Rectangle rect2 = new Rectangle(52, 100, 188, 250);
    Rectangle line = new Rectangle(30, 78, 180, 5);
    
    public int pIndex = 0;
    
    public int mainState;
    
    public final int mainMenuState = 1, withdrawState = 2, depositState = 3, transferState = 4, tranState = 5;
    
    public int commandNum = 1;
    
    int fps = 60;
    public double balance;
    
    public String balancer ="";
    
    public String bals;
    
    public String accs;
    
    public String[] transactionBalance = new String[11];
    
    public int[] yVals = {100, 130, 160, 190, 220, 250, 280, 310, 340, 370, 400};
    
    public ArrayList<String> type = new ArrayList();
    
    public ArrayList<String> sign = new ArrayList();
    
    public GamePanel()
    {
        this.setBounds(0,0,600,800);
        this.addKeyListener(keyH);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        getString();
        menuState();
    }
    
    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    
    
    public void menuState()
    {
        mainState = mainMenuState;
        
    }
    
    public String getString()
    {
          bals = keyH.accBalance(0.0, balancer);
         return bals;
    }
        
    
    @Override
    public void run()
    {
       double drawInterval = 1000000000/fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        int timer = 0;
        int drawCount = 0;
        
        while(gameThread != null)
        {
            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            
            lastTime = currentTime;
            
            if(delta >= 1)
            {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000)
            {
                drawCount = 0;
                timer = 0;
            }
        }

    }
    
    public void update()
    {
        if(commandNum > 4)
        {
            commandNum = 1;
        }
        if(commandNum < 1)
        {
            commandNum = 4;
        }
        
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        
        if(mainState == mainMenuState)
        {
            if(commandNum == 1)
        {
            g.setColor(Color.black);
            g.setFont(new Font("Arial",65, 65));
            g.drawString(">", 325, 200);
            
        }
        if(commandNum == 2)
        {
            g.setColor(Color.black);
            g.setFont(new Font("Arial",65, 65));
            g.drawString(">", 325, 325);
        }
         if(commandNum == 3)
        {
            g.setColor(Color.black);
            g.setFont(new Font("Arial",65, 65));
            g.drawString(">", 325, 425);
        }
         if(commandNum == 4)
        {
            g.setColor(Color.black);
            g.setFont(new Font("Arial",65, 65));
            g.drawString(">", 325, 575);
        }
        
        
        g.setColor(Color.black);
        g.fillRect(rect.x, rect.y, rect.width, rect.height);
        
        g.setColor(Color.white);
        g.fillRect(rect2.x, rect2.y, rect2.width, rect2.height);
        
        g.setColor(Color.black);
        g.setFont(new Font("Arial",8,20));
        g.drawString("Account Details:",50,120);
        g.drawString("Account ID:",50, 150);
        g.drawString(accs,52,170);
        g.drawString("Account Balance:",52, 200);
        g.drawString( bals,52,230);
        g.drawString("Account Type:",52, 260);
        g.drawString("Savings Account",52, 290);
        g.drawString("Withdraw:", 300, 125);
        g.drawString("Deposit:",300, 255);
        g.drawString("Transfer:",300, 385);
        g.drawString("Transaction History:", 225, 520);
        
        g2.drawImage(withdraw, 400, 100, null);
        g2.drawImage(download, 400, 225, null);
        g2.drawImage(transfer, 400, 350, null);
        g2.drawImage(transaction, 400, 475, null);
        
        if(keyH.withdraw)
        {
            g2.drawString("Withdraw Ammount:", 420, 220);
            check = true;
            check2 = false;
            check3 = false;
        }
        if(keyH.deposit)
        {
            g2.drawString("Deposit Ammount:", 430, 220);
            check2 = true;
            check = false;
            check3 = false;
        }
        if(keyH.transfer)
        {
             g2.drawString("Transfer Ammount:", 430, 220);
             check3 = true;
             check = false;
             check2 = false;
        }
        }
 
        if(mainState == tranState)
        {
            g.setColor(Color.black);
            g.setFont(new Font("Arial",20,20));
            g.drawString("Transaction History", 30, 75);
            
            g.fillRect(line.x, line.y, line.width, line.height);
            
            
            for(int i = 0; i < pIndex; i++)
            {
                  g.setColor(Color.black);
                  g.setFont(new Font("Arial",20,20));
                  g.drawString(type.get(i), 30, yVals[i]);
                  g.drawString(transactionBalance[i], 160, yVals[i]);
                  
                  if(sign.get(i).equals("-"))
                  {
                      g.setColor(Color.red);
                      g.setFont(new Font("Arial",20,20));
                      g.drawString(sign.get(i), 150, yVals[i]);
                  }
                  if(sign.get(i).equals("+"))
                  {
                      g.setColor(Color.green);
                      g.setFont(new Font("Arial",20,20));
                      g.drawString(sign.get(i), 150, yVals[i]);
                  }
            }
        }
        
        g.dispose();
        
    }
}
