import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class GUI1 extends JFrame {
    private JPanel panel = new JPanel();
    private JPanel titlepanel = new JPanel();
    JScrollPane scroll = new JScrollPane();
    private int championsno;
    private int banned = 100;
    private int sum = 0;
    private JButton ok;
    private ArrayList<String> bans = new ArrayList<>();

    public GUI1(int no) {
        championsno = no;
        createUI();
        setTitle("Item Set Creation Tool (Beta Version 0.2");
        setSize(550, 400);
        setVisible(true);
    }

    private void createUI() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        titlepanel.setLayout(new BoxLayout(titlepanel, BoxLayout.Y_AXIS));

        LocalTime now = LocalTime.now();
        int hour = now.getHour();
        String message = " ";
        if(hour < 12)
        {
            message = "Good Morning!";
        }
        else if (hour < 18)
        {
            message = "Good Afternoon!";
        }
        else
        {
            message = "Good Evening!";
        }

        titlepanel.add(new JLabel(message));
        titlepanel.add(new JLabel("Unfortunately, League Of Legends currently supports only up to 100 Item Sets."));
        titlepanel.add(new JLabel("Therefore, you will have to ban " + (championsno - banned) + " Champions from the making of the Item Sets."));
        titlepanel.add(new JLabel("Please select the champions you are not likely to play in the near future from"));
        JLabel select = new JLabel("the list below: (" + sum + " selected)");
        titlepanel.add(select);
        titlepanel.add(new JLabel(" "));

        try
        {
            File file = new File("allchamps.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            Scanner scan = new Scanner(br);

            while(scan.hasNext())
            {
                String name = scan.nextLine();
                JCheckBox check = new JCheckBox(name);
                check.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        if(check.isSelected()) {
                            bans.add(check.getText());
                            sum++;
                            select.setText("the list below: (" + sum + " selected)");
                            if (sum >= championsno - banned) {
                                ok.setEnabled(true);
                            } else {
                                ok.setEnabled(false);
                            }
                        }
                        if(!check.isSelected())
                        {
                            bans.remove(check.getText());
                            sum--;
                            select.setText("the list below: (" + sum + " selected)");
                            if (sum >= championsno - banned) {
                                ok.setEnabled(true);
                            } else {
                                ok.setEnabled(false);
                            }
                        }
                    }
                });
                panel.add(check);
            }

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        scroll.setViewportView(panel);
        titlepanel.add(scroll);

        JPanel buttonpane = new JPanel();
        buttonpane.setLayout(new BoxLayout(buttonpane, BoxLayout.X_AXIS));

        ok = new JButton("OK");
        ok.setEnabled(false);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try
                {
                    File file = new File("bans.txt");
                    FileWriter fw = new FileWriter(file);
                    BufferedWriter bw = new BufferedWriter(fw);

                    for(String name : bans)
                    {
                        if(name.equals("Nunu &amp; Willump"))
                        {
                            name = "nunu";
                        }
                        if(name.equals("Wukong"))
                        {
                            name = "monkeyking";
                        }

                        name = name.toLowerCase().replace(" ", "").replace("\'", "").replace(".", "");
                        bw.write(name);
                        bw.newLine();
                    }
                    bw.close();
                    GUI1.super.dispose();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        });


        JButton cancel = new JButton("cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        buttonpane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonpane.add(Box.createHorizontalGlue());
        buttonpane.add(ok);
        buttonpane.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonpane.add(cancel);

        titlepanel.add(buttonpane);
        add(titlepanel);
    }

    /*public static void main(String[] args) {
        GUI1 mf = new GUI1(148);
        mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mf.setLocationRelativeTo(null);
    }*/
}
