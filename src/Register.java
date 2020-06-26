import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Register extends JFrame implements ActionListener {

    JLabel lblHeading;
    JLabel lblUsername;
    JLabel lblEmail;
    JLabel lblPassword;
    JLabel lblCPassword;


    JTextField tfUsername;
    JTextField tfEmail;
    JPasswordField pfPassword;
    JPasswordField pfCPassword;

    JButton btnRegister;
    JButton btnClear;
    JButton btnCancel;

    public Register()
    {
        // screen size of user
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        setBounds((int)width/3,(int)height/3, 400, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Register your self here");
       // setContentPane(new JLabel(new ImageIcon("F:\\APC_GUI\\src\\images\\loginBackground.png")));
        setLayout(null);

        ImageIcon icon = new ImageIcon("splash.png");
        setIconImage(icon.getImage());

        initialize();
        add(lblHeading);
        add(lblUsername);
        add(lblEmail);
        add(lblPassword);
        add(lblCPassword);
        add(tfUsername);
        add(tfEmail);
        add(pfPassword);
        add(pfCPassword);
        add(btnRegister);
        add(btnClear);
        add(btnCancel);


        setVisible(true);
    }

    public void initialize()
    {
        lblHeading = new JLabel("Welcome to Advance Programming Concept");
        Font font = new Font("Arial",Font.BOLD,16);
        lblHeading.setFont(font);
        lblHeading.setBounds(20,0,400,30);

        lblUsername = new JLabel("Enter Username ");
        lblUsername.setBounds(70,50,100,30);

        lblEmail = new JLabel("Enter Email ");
        lblEmail.setBounds(70,80,100,30);

        lblPassword = new JLabel("Enter Password ");
        lblPassword.setBounds(70,110,100,30);

        lblCPassword = new JLabel("Confirm Password ");
        lblCPassword.setBounds(70,140,120,30);

        tfUsername = new JTextField();
        tfUsername.setBounds(180,55,120,20);

        tfEmail = new JTextField();
        tfEmail.setBounds(180,85,120,20);

        pfPassword = new JPasswordField();
        pfPassword.setBounds(180,115,120,20);

        pfCPassword = new JPasswordField();
        pfCPassword.setBounds(180,145,120,20);

        Font btnFont = new Font("Monospaced", Font.BOLD, 14);
        Color color = new Color(123,145,155);
        btnCancel = new JButton("Cancel");
        btnCancel.setFont(btnFont);
        btnCancel.setBackground(color);
        btnCancel.setBounds(20, 180, 100,30);

        btnClear = new JButton("Clear");
        btnClear.setFont(btnFont);
        btnClear.setBackground(color);
        btnClear.setBounds(140, 180, 100,30);

        btnRegister = new JButton("Register");
        btnRegister.setFont(btnFont);
        btnRegister.setBackground(color);
        btnRegister.setBounds(260, 180, 100,30);


        btnRegister.addActionListener(this);
        btnClear.addActionListener(this);
        btnCancel.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnClear)
        {
            clear();
        }
        else if(e.getSource() == btnRegister)
        {
            String username = tfUsername.getText().toString();
            String email = tfEmail.getText().toString();
            String password = pfPassword.getText().toString();
            String cpassword = pfCPassword.getText().toString();

            if(username.isEmpty())
            {
               JOptionPane.showMessageDialog(this,"Username is Empty");
            }
            else if(email.isEmpty())
            {
                JOptionPane.showMessageDialog(this,"Email is Empty");
            }
            else if(password.isEmpty())
            {
                JOptionPane.showMessageDialog(this,"Password is Empty");
            }
            else if (cpassword.isEmpty())
            {
                JOptionPane.showMessageDialog(this,"Confirm password is Empty");
            }
            else
            {
                //JOptionPane.showMessageDialog(this,"Everything is fine");
                if(email.contains("@"))
                {
                    if(password.equals(cpassword))
                    {
                        JOptionPane.showMessageDialog(this,"password match");
                        FileWriter fw=null;
                        BufferedWriter bw=null;
                        try
                        {
                            fw = new FileWriter("Users.txt",true);
                            bw = new BufferedWriter(fw);
                            bw.write(username+","+email+","+password);
                            bw.newLine();
                            clear();

                        }catch (IOException e1)
                        {
                            e1.printStackTrace(); // print all errors which occured
                        }
                        finally {
                            try {
                                bw.close();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                fw.close();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this,"Password Mismatched");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this,"Email incorrect");
                }

            }




        }
        else if(e.getSource() == btnCancel)
        {
            dispose();
            new Home();
        }
    }

    public void clear()
    {
        tfUsername.setText("");
        tfEmail.setText("");
        pfPassword.setText("");
        pfCPassword.setText("");
    }
}
