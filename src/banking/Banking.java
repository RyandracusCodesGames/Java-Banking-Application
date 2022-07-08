
package banking;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JTextArea;


public class Banking implements ActionListener{
    
    public static Banking banking;
    
    public JFrame jframe, frame, window = new JFrame();
    
    public  JTextField text, text2, text3 = new JTextField(15);
    
    public JButton button, butt, userSupport, back, sent;
    
    public String account;
    
    public JLabel label4, message = new JLabel();
    
    public int x = 1000;
    
    public GamePanel gamePanel = new GamePanel();
    
    public KeyHandler key = new KeyHandler(gamePanel);
    
    public JRadioButton radio;
    
    public double gameBalance = Double.parseDouble(gamePanel.bals);
    
    int i = 0;
    
    public Banking()
    {
        Image icon = new ImageIcon("src\\images\\withdraw.png").getImage();
        
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        
        
        text = new JTextField(15);
        text2 = new JTextField(15);

        
        panel.setBounds(290, 300, 180, 30);
        panel2.setBounds(290, 350, 180, 30);
        
        jframe = new JFrame();
        jframe.setSize(800,800);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(false);
        jframe.setTitle("Community Banking Hub of America");
        jframe.setLayout(null);
        jframe.setIconImage(icon);
        
        JLabel label = new JLabel();
        label.setText("Welcome to the Community Banking Hub of America!");
        label.setFont(new Font("Arial",Font.BOLD,25));
        label.setForeground(Color.white);
        label.setBounds(120, 75, 800, 250);
        
        JLabel label2 = new JLabel();
        label2.setText("Account Name:");
        label2.setFont(new Font("Arial",Font.BOLD,15));
        label2.setForeground(Color.white);
        label2.setBounds(180,211,200,200);
        
        JLabel label3 = new JLabel();
        label3.setText("Password:");
        label3.setFont(new Font("Arial",Font.BOLD,15));
        label3.setForeground(Color.white);
        label3.setBounds(208,261,200,200); 
        
        label4 = new JLabel();
        label4.setIcon(new ImageIcon("src\\images\\bankaccount1.png"));
        label4.setBounds(300, 325, 600, 600);
        
        JLabel label5 = new JLabel();
        label5.setIcon(new ImageIcon("src\\images\\bankaccount1.png"));
        label5.setBounds(300, -200, 600, 600);
        
        button = new JButton("Log In");
        button.setBounds(240, 400, 300, 75);
        button.addActionListener(this);
        
        JLabel background = new JLabel();
        background.setIcon(new ImageIcon("src\\images\\background.jpg"));
        background.setBounds(0,0,800,800);
        
        radio = new JRadioButton();
        radio.setBounds(240,500,20,12);
        
        JLabel rem = new JLabel();
        rem.setText("Remember Me");
        rem.setBounds(265, 500, 200, 12);
        rem.setForeground(Color.white);
        rem.setFont(new Font("Arial",Font.BOLD,16));
        
        
        jframe.add(button);
        jframe.add(label);
        jframe.add(label2);
        jframe.add(label3);
        jframe.add(label4);
        jframe.add(label5);
        jframe.add(panel);
        jframe.add(panel2);
        jframe.add(radio);
        jframe.add(rem);
        jframe.add(background);
        
        panel.add(text);
        panel2.add(text2);
        
        
        jframe.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)throws IllegalArgumentException
    {
        if(e.getSource() == button)
        {
            String password = "java"; 
            
            gamePanel.accs = text.getText();

            if(text2.getText().equals(password))
            {
                jframe.dispose();
                
                butt = new JButton("Submit");
                butt.setBounds(600,250,200,100);
                butt.addActionListener(this);
                
                userSupport = new JButton("User Support");
                userSupport.setBounds(600, 550, 200, 100);
                userSupport.addActionListener(this);
                
                JPanel pan = new JPanel();
                pan.setBounds(590,200,200,50);
                pan.setBackground(Color.red);
                
                pan.add(text3);
           
                frame = new JFrame();
                frame.setSize(800,800);
                frame.setResizable(false);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setTitle("Welcome to the Community Banking Hub of America");
                frame.add(gamePanel);
                frame.setLayout(null);
                frame.add(pan);
                frame.add(butt);
                frame.add(userSupport);
               
                frame.setIconImage(gamePanel.withdraw);
                frame.setVisible(true);
                
                   
                gamePanel.startGameThread();
            }

        }
        if(gamePanel.check || gamePanel.check3)
        {

            if(e.getSource() == butt)
           {
              String w = text3.getText();
              double balance = Double.parseDouble(w);
              
              gameBalance -= balance;
              
              gamePanel.requestFocus();
              
              if(gameBalance <= 0)
               {
                 throw new IllegalArgumentException("Withdrawl cannot exceed amount in account!");
               }
              else
              {
                  JPanel labe = new JPanel();
                  labe.setBounds(590, 420, 200, 200);
                  
                  JLabel elf = new JLabel();
                  elf.setText(String.format("Account Balance:%.2f", gameBalance));
                  
                  labe.add(elf);       
                  
                  frame.add(labe);
                  frame.setVisible(true);
              }
              gamePanel.transactionBalance[i] = String.format("%.2f",balance);
              
              i++;
              
              gamePanel.pIndex++;
              
              if(gamePanel.check)
              {
                  gamePanel.type.add("Withdrawed : ");
                  gamePanel.sign.add("-");
              }

              if(gamePanel.check3)
              {
                  gamePanel.type.add("Transfered : ");
                  gamePanel.sign.add("-");
              }

              System.out.println(gameBalance);
           }
           
           
        }
           
        if(gamePanel.check2)
        {
            if(e.getSource() == butt)
           {
              String x = text3.getText();
              double balance2 = Double.parseDouble(x);
              
              gamePanel.requestFocus();
              
              gameBalance += balance2;
              
               JPanel labe = new JPanel();
               labe.setBounds(590, 420, 200, 200);
                  
               JLabel elf = new JLabel();
               elf.setText(String.format("Account Balance:%.2f", gameBalance));
                  
               labe.add(elf);
                          
                  
               frame.add(labe);
               frame.setVisible(true);
               
               gamePanel.transactionBalance[i] = String.format("%.2f", balance2);
               i++;
               gamePanel.pIndex++;
               
               gamePanel.type.add("Deposited : ");
               gamePanel.sign.add("+");
              
              System.out.println(gameBalance);
           }
        }
        gamePanel.bals = String.valueOf(gameBalance);
        
        if(e.getSource() == userSupport)
        {
            frame.setVisible(false);

            window.setSize(800,800);
            window.setResizable(false);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setTitle("Welcome to the Community Banking Hub of America");
            window.setIconImage(gamePanel.withdraw);
            
            back = new JButton("Return");
            back.setBounds(50, 50, 100, 20);
            back.addActionListener(this);
            
            JLabel news = new JLabel();
            news.setText("Contact User Support via Text");
            news.setBounds(35, 60, 300, 200);
            news.setFont(new Font("Arial",Font.BOLD,15));
            
            JLabel qna = new JLabel();
            qna.setText("Most Common QNA");
            qna.setBounds(260, 60, 300, 200);
            qna.setFont(new Font("Arial",Font.BOLD,15));
            
            JLabel tra = new JLabel();
            tra.setText("Q:Why aren't transactions working?");
            tra.setBounds(260, 100, 500, 200);
            tra.setFont(new Font("Arial",Font.BOLD,15));
            
            JLabel tras = new JLabel();
            tras.setText("A: Must press Enter to select transaction! Scroll through options using W and S!");
            tras.setBounds(260, 120, 1000, 200);
            tras.setFont(new Font("Arial",Font.BOLD,13));
            
            JTextArea support = new JTextArea();
            support.setBounds(35, 200, 200, 300);
            
            sent = new JButton("Send");
            sent.setBounds(60, 550, 150, 75);
            sent.addActionListener(this);
            
            window.add(back);
            window.add(support);
            window.add(news);
            window.add(qna);
            window.add(tra);
            window.add(tras);
            window.add(sent);
            window.setLayout(null);
            window.setVisible(true);
            
        }
        
        if(e.getSource() == back)
        {
            frame.setVisible(true);
            window.dispose();
            
            gamePanel.requestFocus();
        }
        
        if(e.getSource() == sent)
        {
            message.setText("Message Sent!");
            message.setBounds(80, 550, 300, 200);
            message.setFont(new Font("Arial",Font.BOLD,15));
            
            window.add(message);
            window.setLayout(null);
            window.validate();
            window.setVisible(true);
        }
    }
  
    public String getString(String acc)
    {
        account = text.getText();
        acc = account;
        return acc;
    }
    public static void main(String[] args) 
    {
        banking = new Banking();
    }
    
}
