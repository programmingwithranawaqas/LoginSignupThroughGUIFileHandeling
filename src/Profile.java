import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Profile extends JFrame implements ActionListener {
    String username;

    JLabel heading;
    JLabel lblUsername;
    JLabel lblEmail;
    JLabel lblPassword;

    JButton btnLogout;
    JButton btnChangePassword;
    JButton btnDeleteAccount;

    public Profile(String u) throws IOException {
        username = u;

        // screen size of user
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        setBounds((int)width/3,(int)height/3, 400, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Profile");
        // setContentPane(new JLabel(new ImageIcon("F:\\APC_GUI\\src\\images\\loginBackground.png")));
        setLayout(null);

        ImageIcon icon = new ImageIcon("splash.png");
        setIconImage(icon.getImage());

        initialize();
        add(heading);
        add(lblUsername);
        add(lblEmail);
        add(lblPassword);
        add(btnLogout);
        add(btnChangePassword);
        add(btnDeleteAccount);

        setVisible(true);
    }

    public void initialize() throws IOException {
        heading = new JLabel("My Profile");
        heading.setFont(new Font("Arial", Font.BOLD, 16));
        heading.setBounds(160, 30, 100, 20);
        lblUsername = new JLabel("Username : ");
        lblUsername.setBounds(100, 70, 200, 20);
        lblPassword = new JLabel("Password : ");
        lblPassword.setBounds(100, 100, 200, 20);
        lblEmail    = new JLabel("Email    : ");
        lblEmail.setBounds(100, 130, 250, 20);

        readInfoFromFile();

        btnLogout = new JButton("Logout");
        Color color = new Color(123,145,155);
        btnLogout.setBounds(300, 20, 80,40);
        btnLogout.setBackground(color);
        btnChangePassword = new JButton("Change Password");
        btnChangePassword.setBackground(color);
        btnChangePassword.setBounds(50, 170, 120, 40);
        btnDeleteAccount = new JButton("Delete Account");
        btnDeleteAccount.setBackground(color);
        btnDeleteAccount.setBounds(200, 170, 120, 40);

        Font buttonFont = new Font(Font.SANS_SERIF, Font.ITALIC, 10);

        btnDeleteAccount.setFont(buttonFont);
        btnChangePassword.setFont(buttonFont);

        btnLogout.addActionListener(this);
        btnChangePassword.addActionListener(this);
        btnDeleteAccount.addActionListener(this);


    }

    private void readInfoFromFile() throws IOException {
        FileReader fr = null;
        BufferedReader br = null;

        try{
            fr = new FileReader("Users.txt");
            br = new BufferedReader(fr);
            String line;
            while((line=br.readLine())!=null)
            {
                String[] data = line.split(",");
                if(username.equalsIgnoreCase(data[0]))
                {
                    lblUsername.setText(lblUsername.getText()+data[0]);
                    lblPassword.setText(lblPassword.getText()+data[2]);
                    lblEmail.setText(lblEmail.getText()+data[1]);
                    break;
                }
            }

        }catch (IOException ex)
        {
            ex.printStackTrace();
        }finally {
            if(br!=null)
                br.close();
            if(fr!=null)
                fr.close();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnLogout)
        {
            dispose();
            new Login();
        }
        else if(e.getSource()==btnChangePassword)
        {

        }
        else if(e.getSource()==btnDeleteAccount)
        {
            try {
                deleteAccount();
                dispose();
                new Login();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void deleteAccount() throws IOException {
        FileWriter fw = null;
        BufferedWriter bw = null;

        FileReader fr = null;
        BufferedReader br = null;
            fw = new FileWriter("temp.txt");
            bw = new BufferedWriter(fw);

            fr = new FileReader("Users.txt");
            br = new BufferedReader(fr);

            String line;
            while((line=br.readLine())!=null)
            {
                String[]data = line.split(",");
                if(username.equalsIgnoreCase(data[0]))
                {

                }
                else
                {
                    bw.write(line);
                    bw.newLine();
                }
            }

        bw.close();
        fw.close();
        br.close();
        fr.close();

            // remove Users.txt from memory
        File file = new File("Users.txt");
        boolean flag = file.delete();
        if(flag==false)
        {
            System.out.println("Not deleted");
        }

        // rename temp.txt to Users.txt
        File renameFile = new File("temp.txt");
        renameFile.renameTo(new File("Users.txt"));

    }
}
