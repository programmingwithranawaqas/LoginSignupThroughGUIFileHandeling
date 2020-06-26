import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener {

    private JLabel heading;
    private JButton loginbtn;
    private JButton registerbtn;

    public Home()
    {
        // screen size of user
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        setBounds((int)width/3,(int)height/3, 400, 300);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setTitle("My First GUI Application");

        ImageIcon icon = new ImageIcon("splash.png");
        setIconImage(icon.getImage());

        initialize();

        add(heading);
        add(loginbtn);
        add(registerbtn);

        setVisible(true);
    }

    public void initialize()
    {
        // initializing heading for window
        heading = new JLabel();
        heading.setText("Advance Programming Concepts");
        heading.setBounds(70,30, 300,30);
        Font headingFont = new Font("Arial", Font.BOLD, 16);
        heading.setFont(headingFont);

        // initializing login button
        loginbtn = new JButton();
        loginbtn.setText("Login");
        Color color = new Color(123,145,155);
        loginbtn.setBackground(color);
        loginbtn.setBounds(50, 100, 100, 50);

        // initializing registration button
        registerbtn = new JButton();
        registerbtn.setText("Register");
        registerbtn.setBackground(color);
        registerbtn.setBounds(200, 100, 100, 50);

        Font buttonFont = new Font(Font.SANS_SERIF, Font.ITALIC, 14);
        loginbtn.setFont(buttonFont);
        registerbtn.setFont(buttonFont);

        Color buttonTextColor = new Color(255,255,255);
        loginbtn.setForeground(buttonTextColor);
        registerbtn.setForeground(buttonTextColor);

        loginbtn.addActionListener(this);
        registerbtn.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginbtn)
        {
//            JOptionPane.showMessageDialog(this,"Login Button Clicked");
            dispose(); // to close the window
            new Login();
        }
        else if(e.getSource() == registerbtn)
        {
            dispose();
            new Register(); // anonymous object
            //JOptionPane.showMessageDialog(this,"Register Button Clicked");
        }
    }
}
