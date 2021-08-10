import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.sql.Time;
import java.util.Scanner;

public class GUI
{
    private JLabel label1;
    private JLabel label2 ;
    private JLabel label3;
    private JLabel label4;
    private JScrollPane scroll;
    private JCheckBox checkBox1;
    private JPanel mainpanel;
    private JPanel scorllpanel;
    private JButton cancelButton;
    private JButton OKButton;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;
    private JCheckBox checkBox4;

    public GUI()
    {
        mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.Y_AXIS));
        this.label1.setText("Good evening!");
        this.label2.setText("Unfortunately");
        this.label3.setText("Therefore");
        this.label4.setText("Please select");
        this.checkBox1.setText("Aatrox");
    }

    public static void main(String[] args)
    {
        File file = new File("src/GUI1.form");
        try
        {
            File file1 = new File("allchamps.txt");
            FileReader fr = new FileReader(file1);
            BufferedReader br = new BufferedReader(fr);

            Scanner scan = new Scanner(br);
            //scan.useDelimiter("\n");

            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            int row = 0;
            int spacerow = 0;
            String id = "idno";

            bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            bw.newLine();
            bw.write("<form xmlns=\"http://www.intellij.com/uidesigner/form/\" version=\"1\" bind-to-class=\"GUII\">");
            bw.newLine();
            bw.write("  <grid id=\"27dc6\" binding=\"mainpanel\" layout-manager=\"GridLayoutManager\" row-count=\"6\" column-count=\"1\" same-size-horizontally=\"false\" same-size-vertically=\"false\" hgap=\"-1\" vgap=\"-1\">");
            bw.newLine();
            bw.write("    <margin top=\"0\" left=\"0\" bottom=\"0\" right=\"0\"/>");
            bw.newLine();
            bw.write("    <constraints>");
            bw.newLine();
            bw.write("      <xy x=\"20\" y=\"20\" width=\"563\" height=\"524\"/>");
            bw.newLine();
            bw.write("    </constraints>");
            bw.newLine();
            bw.write("    <properties>");
            bw.newLine();
            bw.write("      <enabled value=\"false\"/>");
            bw.newLine();
            bw.write("    </properties>");
            bw.newLine();
            bw.write("    <border type=\"none\"/>");
            bw.newLine();
            bw.write("    <children>");
            bw.newLine();
            bw.write("      <component id=\"31f6f\" class=\"javax.swing.JLabel\" binding=\"label1\">");
            bw.newLine();
            bw.write("        <constraints>");
            bw.newLine();
            bw.write("          <grid row=\"0\" column=\"0\" row-span=\"1\" col-span=\"1\" vsize-policy=\"0\" hsize-policy=\"0\" anchor=\"0\" fill=\"0\" indent=\"0\" use-parent-layout=\"false\"/>");
            bw.newLine();
            bw.write("        </constraints>");
            bw.newLine();
            bw.write("        <properties>");
            bw.newLine();
            bw.write("          <text value=\"Label\"/>");
            bw.newLine();
            bw.write("        </properties>");
            bw.newLine();
            bw.write("      </component>");
            bw.newLine();
            bw.write("      <component id=\"42d8e\" class=\"javax.swing.JLabel\" binding=\"label2\">");
            bw.newLine();
            bw.write("        <constraints>");
            bw.newLine();
            bw.write("          <grid row=\"1\" column=\"0\" row-span=\"1\" col-span=\"1\" vsize-policy=\"0\" hsize-policy=\"0\" anchor=\"0\" fill=\"0\" indent=\"0\" use-parent-layout=\"false\"/>");
            bw.newLine();
            bw.write("        </constraints>");
            bw.newLine();
            bw.write("        <properties>");
            bw.newLine();
            bw.write("          <text value=\"Label\"/>");
            bw.newLine();
            bw.write("        </properties>");
            bw.newLine();
            bw.write("      </component>");
            bw.newLine();
            bw.write("      <component id=\"e2ed5\" class=\"javax.swing.JLabel\" binding=\"label3\">");
            bw.newLine();
            bw.write("        <constraints>");
            bw.newLine();
            bw.write("          <grid row=\"2\" column=\"0\" row-span=\"1\" col-span=\"1\" vsize-policy=\"0\" hsize-policy=\"0\" anchor=\"0\" fill=\"0\" indent=\"0\" use-parent-layout=\"false\"/>");
            bw.newLine();
            bw.write("        </constraints>");
            bw.newLine();
            bw.write("        <properties>");
            bw.newLine();
            bw.write("          <text value=\"Label\"/>");
            bw.newLine();
            bw.write("        </properties>");
            bw.newLine();
            bw.write("      </component>");
            bw.newLine();
            bw.write("      <component id=\"d1357\" class=\"javax.swing.JLabel\" binding=\"label4\">");
            bw.newLine();
            bw.write("        <constraints>");
            bw.newLine();
            bw.write("          <grid row=\"3\" column=\"0\" row-span=\"1\" col-span=\"1\" vsize-policy=\"0\" hsize-policy=\"0\" anchor=\"0\" fill=\"0\" indent=\"0\" use-parent-layout=\"false\"/>");
            bw.newLine();
            bw.write("        </constraints>");
            bw.newLine();
            bw.write("        <properties>");
            bw.newLine();
            bw.write("          <text value=\"Label\"/>");
            bw.newLine();
            bw.write("        </properties>");
            bw.newLine();
            bw.write("      </component>");
            bw.newLine();
            bw.write("      <scrollpane id=\"64e8c\" binding=\"scroll\">");
            bw.newLine();
            bw.write("        <constraints>");
            bw.newLine();
            bw.write("          <grid row=\"4\" column=\"0\" row-span=\"1\" col-span=\"1\" vsize-policy=\"7\" hsize-policy=\"7\" anchor=\"0\" fill=\"3\" indent=\"0\" use-parent-layout=\"false\"/>");
            bw.newLine();
            bw.write("        </constraints>");
            bw.newLine();
            bw.write("        <properties/>");
            bw.newLine();
            bw.write("        <border type=\"none\"/>");
            bw.newLine();
            bw.write("        <children>");
            bw.newLine();
            bw.write("          <grid id=\"6acf7\" binding=\"scorllpanel\" layout-manager=\"GridLayoutManager\" row-count=\"4\" column-count=\"1\" same-size-horizontally=\"false\" same-size-vertically=\"false\" hgap=\"-1\" vgap=\"-1\">");
            bw.newLine();
            bw.write("            <margin top=\"0\" left=\"0\" bottom=\"0\" right=\"0\"/>");
            bw.newLine();
            bw.write("            <constraints/>");
            bw.newLine();
            bw.write("            <properties/>");
            bw.newLine();
            bw.write("            <border type=\"none\"/>");
            bw.newLine();
            bw.write("            <children>");
            bw.newLine();
/*            bw.write("              <vspacer id=\"ba6d0\">");
            bw.newLine();
            bw.write("                <constraints>");
            bw.newLine();
            bw.write("                  <grid row=\"6\" column=\"0\" row-span=\"1\" col-span=\"1\" vsize-policy=\"6\" hsize-policy=\"1\" anchor=\"0\" fill=\"2\" indent=\"0\" use-parent-layout=\"false\"/>");
            bw.newLine();
            bw.write("                </constraints>");
            bw.newLine();
            bw.write("              </vspacer>");
            bw.newLine();*/



            int z = 0;
           while(scan.hasNext() && z < 6)
            {
                z++;
                row++;
                String name = scan.next();
                bw.write("              <component id=\"");
                bw.write(name);
                bw.write("\" class=\"javax.swing.JCheckBox\" binding=\"");
                bw.write("checkBox");
                bw.write(String.valueOf(row+1));
                bw.write("\" default-binding=\"true\">");
                bw.newLine();
                bw.write("                <constraints>");
                bw.newLine();
                bw.write("                  <grid row=\"");
                bw.write(String.valueOf(row));
                bw.write("\" column=\"0\" row-span=\"1\" col-span=\"1\" vsize-policy=\"0\" hsize-policy=\"3\" anchor=\"8\" fill=\"0\" indent=\"0\" use-parent-layout=\"false\"/>");
                bw.newLine();
                bw.write("                </constraints>");
                bw.newLine();
                bw.write("                <properties>");
                bw.newLine();
                bw.write("                  <text value=\"");
                bw.write(name);
                bw.write("\"/>");
                bw.newLine();
                bw.write("                </properties>");
                bw.newLine();
                bw.write("              </component>");
                bw.newLine();
            }

            bw.write("            </children>");
            bw.newLine();
            bw.write("          </grid>");
            bw.newLine();
            bw.write("        </children>");
            bw.newLine();
            bw.write("      </scrollpane>");
            bw.newLine();
            bw.write("      <grid id=\"9ce68\" layout-manager=\"FlowLayout\" hgap=\"5\" vgap=\"5\" flow-align=\"4\">");
            bw.newLine();
            bw.write("        <constraints>");
            bw.newLine();
            bw.write("          <grid row=\"5\" column=\"0\" row-span=\"1\" col-span=\"1\" vsize-policy=\"3\" hsize-policy=\"3\" anchor=\"2\" fill=\"1\" indent=\"0\" use-parent-layout=\"false\"/>");
            bw.newLine();
            bw.write("        </constraints>");
            bw.newLine();
            bw.write("        <properties/>");
            bw.newLine();
            bw.write("        <border type=\"none\"/>");
            bw.newLine();
            bw.write("        <children>");
            bw.newLine();
            bw.write("          <component id=\"2e276\" class=\"javax.swing.JButton\" binding=\"OKButton\" default-binding=\"true\">");
            bw.newLine();
            bw.write("            <constraints/>");
            bw.newLine();
            bw.write("            <properties>");
            bw.newLine();
            bw.write("              <enabled value=\"false\"/>");
            bw.newLine();
            bw.write("              <horizontalAlignment value=\"0\"/>");
            bw.newLine();
            bw.write("              <text value=\"OK\"/>");
            bw.newLine();
            bw.write("            </properties>");
            bw.newLine();
            bw.write("          </component>");
            bw.newLine();
            bw.write("          <component id=\"6db49\" class=\"javax.swing.JButton\" binding=\"cancelButton\" default-binding=\"true\">");
            bw.newLine();
            bw.write("            <constraints/>");
            bw.newLine();
            bw.write("            <properties>");
            bw.newLine();
            bw.write("              <text value=\"Cancel\"/>");
            bw.newLine();
            bw.write("            </properties>");
            bw.newLine();
            bw.write("          </component>");
            bw.newLine();
            bw.write("        </children>");
            bw.newLine();
            bw.write("      </grid>");
            bw.newLine();
            bw.write("    </children>");
            bw.newLine();
            bw.write("  </grid>");
            bw.newLine();
            bw.write("</form>");


            br.close();
            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }


    }

    public void check(String name)
    {

    }
}