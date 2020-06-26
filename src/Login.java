import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Login extends JFrame implements ActionListener {

    JLabel heading;
    JLabel labelUsername;
    JLabel labelPassword;

    JTextField inputUsername;
    JPasswordField inputPassword;

    JButton btnLogin;
    JButton btnCancel;

    public Login()
    {
        // screen size of user
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        setBounds((int)width/3,(int)height/3, 400, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login Form");
        setContentPane(new JLabel(new ImageIcon("F:\\APC_GUI\\src\\images\\loginBackground.png")));
        setLayout(null);

        ImageIcon icon = new ImageIcon("splash.png");
        setIconImage(icon.getImage());

        initialize();
        add(heading);
        add(labelUsername);
        add(labelPassword);
        add(inputUsername);
        add(inputPassword);
        add(btnLogin);
        add(btnCancel);

        setVisible(true);
    }

    public void initialize()
    {
        heading = new JLabel();
        Font headingFont = new Font("Arial", Font.BOLD, 18);
        heading.setText("LOGIN HERE");
        heading.setFont(headingFont);
        heading.setBounds(130, 40, 150, 50);

        // initializing labels
        labelUsername = new JLabel("Enter your username : ");
        labelUsername.setBounds(20, 120, 250, 30);
       // labelUsername.setForeground(new Color(255,255,0));
        labelUsername.setFont(new Font("Arial", Font.BOLD, 14));

        labelPassword = new JLabel("Enter your password : ");
        labelPassword.setBounds(20, 160, 250, 30);
        //labelPassword.setForeground(new Color(255,255,0));
        labelPassword.setFont(new Font("Arial", Font.BOLD, 14));

        // initializing input fields
        inputUsername = new JTextField();
        inputUsername.setBounds(230,120, 130, 30);

        inputPassword = new JPasswordField();
        inputPassword.setBounds(230,160, 130, 30);

        inputUsername.setForeground(Color.GRAY);
        inputUsername.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (inputUsername.getText().equals("Enter Username")) {
                    inputUsername.setText("");
                    inputUsername.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (inputUsername.getText().isEmpty()) {
                    inputUsername.setForeground(Color.GRAY);
                    inputUsername.setText("Enter Username");
                }
            }
        });

        btnLogin = new JButton("Login");
        btnLogin.setBounds(250, 200, 80, 40);
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(100, 200, 80, 40);
        Color color = new Color(123,145,155);
        btnLogin.setBackground(color);
        btnCancel.setBackground(color);
        btnLogin.addActionListener(this);
        btnCancel.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnLogin)
        {
            String username = inputUsername.getText().trim();
            String password = inputPassword.getText().trim();

            boolean flag = false;
            try {
                flag = validateUser(username, password);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if(flag==true)
            {
               // JOptionPane.showMessageDialog(this, "Login Successfully");
               dispose();
                try {
                    new Profile(username);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Invalid Username or password. Please Register yourself");
            }

        }
        else if(e.getSource() == btnCancel)
        {
            dispose();
            new Home();
        }
    }

    private boolean validateUser(String username, String password) throws IOException {
        FileReader fr = null;
        BufferedReader br = null;

        try{
            fr = new FileReader("Users.txt");
            br = new BufferedReader(fr);
            String line;
            while((line=br.readLine())!=null)
            {
                String[] data = line.split(",");
                if(username.equalsIgnoreCase(data[0]) && password.equals(data[2]))
                    return true;
            }

        }catch (IOException ex)
        {
            ex.printStackTrace();
        }
        finally {
            if(br!=null)
                br.close();
            if(fr!=null)
                fr.close();
        }

        return false;
    }
}