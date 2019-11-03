import org.w3c.dom.events.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.security.Key;

public class LazyIP {
    public static void main(String[] args) throws IOException, InterruptedException {

        JButton b = new JButton("Find");
        JLabel tu = new JLabel("Type Domain name here...");
        JLabel l = new JLabel("Result");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JLabel l2 = new JLabel();
        JLabel l3 = new JLabel();
        JLabel l4 = new JLabel();
        JFrame f = new JFrame("LazyIP");
        TextField tf = new TextField();
        JLabel l5 = new JLabel();

        f.setSize(400, 400);
        tu.setBounds(50,20,250,20);
        tf.setBounds(50, 50, 250, 20);
        l.setBounds(50, 100, 350, 20);
        b.setBounds(50, 150, 95, 30);
        l2.setBounds(50, 200, 350, 20);
        l3.setBounds(50, 215, 350, 20);
        l4.setBounds(50, 230, 350, 20);
        l5.setBounds(240, 320, 150, 20);
        f.add(tu);
        f.add(tf);
        f.add(l);
        f.add(b);
        f.add(l2);
        f.add(l3);
        f.add(l4);
        f.add(panel);
        f.add(l5);

        // Press ENTER event
        tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER ){
                       b.doClick(1);
                }
            }
        });

        //INTERNET CHECK
        checkIN ci = new checkIN();
        if(ci.checkIN()==true){
            l5.setText("Internet is connected");
        }
        else {
            l5.setText("Internet is not connected");
        }

        //MAC address
        String Ma = null;
        try {
            InetAddress InetAdress = InetAddress.getLocalHost();
            NetworkInterface NetworkInterface = java.net.NetworkInterface.getByInetAddress(InetAdress);
            byte[] Mac = NetworkInterface.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < Mac.length; i++) {
                sb.append(String.format("%02X%s", Mac[i], (i < Mac.length - 1) ? "-" : ""));
            }
            Ma = sb.toString();

        } catch (Exception e) {
            System.out.println("Error Found");

        }

        l4.setText("Mac address is : " + Ma);


        // PUBLIC IP
        String systemipaddress = "";
        try {
            URL url_name = new URL("http://bot.whatismyipaddress.com/");
            BufferedReader sc = new BufferedReader(new InputStreamReader(url_name.openStream()));
            systemipaddress = sc.readLine().trim();
        } catch (Exception e) {
            System.out.println("system not working");
        }

        l3.setText("Pubic IP is: " + systemipaddress);

        // LOCAL IP
        InetAddress inetAddress = InetAddress.getLocalHost();
        l2.setText("Local IP is: " + inetAddress.getHostAddress());

        // FIND IP
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String host = tf.getText();
                    String ip = java.net.InetAddress.getByName(host).getHostAddress();

                    l.setText("Public IP of " + host + " is " + ip);

                } catch (UnknownHostException ex) {
                    l.setText("No such Host found");
                }
            }
        });

        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setResizable(false);

    }

}

