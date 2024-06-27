import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CaesarCipher {

    private static JFrame f = new JFrame();
    private static JButton b1 = new JButton("Encrypt");
    private static JButton b2 = new JButton("Decrypt");
    private static JButton b3 = new JButton("Submit");
    private static JTextField tf1 = new JTextField(25);
    private static JTextField tf2 = new JTextField(10);
    private static JLabel l1 = new JLabel("Enter Text");
    private static JLabel l11 = new JLabel("Enter Key");
    private static JLabel l2 = new JLabel("Plaintext :");
    private static JLabel l3 = new JLabel("Cipher text :");
    private static JLabel l4 , l5;

public static String pythonencrypt(String text,String key){
    String result=" ";
    try{
    ProcessBuilder pb = new ProcessBuilder("Python3","CaesarCipher.py","encrypt",text,key);
    pb.redirectErrorStream(true);
    Process p = pb.start();
    BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
    String line;
    while((line=b.readLine())!=null)
    result+=line;
    p.waitFor();
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    return result;
}

public static String pythondecrypt(String text,String key){
    String result="";
    try{
    ProcessBuilder pb = new ProcessBuilder("Python3","CaesarCipher.py","decrypt",text,key);
    pb.redirectErrorStream(true);
    Process p = pb.start();
    BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
    String line;
    while((line=b.readLine())!=null)
    result+=line;
    p.waitFor();
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    return result;
}

public static void encrypt()
{
    b1.setVisible(false); b2.setVisible(false);
    f.add(l1); f.add(tf1); f.add(l11);
    f.add(tf2); f.add(b3); f.revalidate();
    b3.addActionListener(e->{
    String s = tf1.getText();
    String t=tf2.getText();
    if(!t.isEmpty() && !s.isEmpty()){
    String r = pythonencrypt(s,t);
    l1.setVisible(false); l11.setVisible(false); tf1.setVisible(false); tf2.setVisible(false); 
    b3.setVisible(false);
    l4 = new JLabel(s);
    l5 = new JLabel(r);
    l4.setForeground(Color.WHITE);
    l5.setForeground(Color.WHITE);
    f.add(l2); f.add(l4);
    f.add(l3); f.add(l5);
    tf1.setText(""); tf2.setText("");
    }
    else JOptionPane.showMessageDialog(null,"Both text and key fields must be filled");
    });
}

public static void decrypt()
{
    f.remove(b1); f.remove(b2); f.revalidate();
    f.add(l1); f.add(tf1); f.add(l11);
    f.add(tf2); f.add(b3); f.revalidate();
    b3.addActionListener(e->{
    String s = tf1.getText();
    String t=tf2.getText();
    if(!t.isEmpty() && !s.isEmpty()){
    String r = pythondecrypt(s,t);
    l1.setVisible(false); l11.setVisible(false); tf1.setVisible(false); tf2.setVisible(false); 
    b3.setVisible(false);
    l4 = new JLabel(s);
    l5 = new JLabel(r);
    l4.setForeground(Color.WHITE);
    l5.setForeground(Color.WHITE);
    f.add(l3); f.add(l4); 
    f.add(l2); f.add(l5); 
    tf1.setText(""); tf2.setText("");
    }
    else JOptionPane.showMessageDialog(null,"Both text and key fields must be filled");
    });
}

    public static void main(String args[])
    {
        f.setTitle("Caesar Cipher");
        f.setSize(400,400);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setBackground(Color.BLACK);
        f.setLayout(new FlowLayout(FlowLayout.CENTER,30,30));
        f.add(b1);
        f.add(b2);
        b1.addActionListener(e-> encrypt());
        b2.addActionListener(e-> decrypt());
        f.setVisible(true);
        l1.setForeground(Color.WHITE);
        l11.setForeground(Color.WHITE);
        l2.setForeground(Color.WHITE);
        l3.setForeground(Color.WHITE);
    }
}
