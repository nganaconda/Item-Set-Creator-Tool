import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TextExtractor
{
    //Number of item names that did not correspond to an item id in-game
    static int erroritems = 0;
    //Number of champion names that did not correspond to an actual champion of the game
    static int errorchampid = 0;
    //Number of Input/Output errors
    static int ioes = 0;
    static ArrayList<Champion> champs = new ArrayList<>();
    //This string holds the name of the last champion whose set was created
    static String prevchamp = " ";
    //We have to ban 40+ champions from the procedure, because LoL atm does not support over 100 item sets
/*    static String[] banned *//*= {"sett", "ivern", "sylas", "neeko", "ornn", "kayn", "camille", "kled", "taliyah", "illaoi",
    "ekko", "reksai", "gnar", "lissandra", "zac", "singed", "nami", "elise", "khazix", "rengar", "diana", "darius", "hecarim", "lulu", "nautilus",
    "ziggs", "volibear", "fizz", "riven", "skarner", "yorick", "rumble", "leesin", "nocturne", "maokai", "renekton", "trundle", "swain",
    "anivia", "gangplank", "gragas", "janna", "karthus", "kassadin", "malzahar", "mordekaiser", "nidalee", "shaco"}*//*;*/
    static ArrayList<String> banned = new ArrayList<>();
    //Number of champion sets that were banned as a result of the banned champions, ie: for Sett, Sett Top and Sett Jungle are two champion sets banned
    static int bannedchamps = 0;
    static int goodchamps = 0;
    static double percentdone = 0.0;
    static JLabel percent;
    static String champsLink = "https://developer.riotgames.com/docs/lol#data-dragon_champions";
    static String itemsLink = "https://developer.riotgames.com/docs/lol#data-dragon_items";
    static String champsraw = "champions_raw.txt";
    static ArrayList<String> allchamps = new ArrayList<>();
    static ArrayList<ThreadDemo> threads = new ArrayList<>();
    static boolean threadsdone = false;
    private JPanel panel;
    private JLabel unfor;
    private static GUI1 mf;
    private static JFrame main;
    private static JLabel maintext;
    static String version = "Item Set Creation Tool (beta ver0.1)";


    public static void main(String[] args)
    {
        update(champsLink, itemsLink);
        countChamps(champsraw);

        welcome();

        //**************************************************************************************************************
        //TESTING IN PLACE
        /*Random rand = new Random();
        for(int i = 0; i < 48; i++)
        {
            int r = rand.nextInt(148);
            banned.add(allchamps.get(r).toLowerCase().replace(" ", "").replace("\'", "").replace(".", ""));
        }*/
        //**************************************************************************************************************

        main = new JFrame(version);
        main.setLayout(new BorderLayout());
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setLocationRelativeTo(null);
        main.setLocation(350, 350);
        main.setSize(600, 150);
        JPanel mainpane = new JPanel();

        maintext = new JLabel(" ");
        percent = new JLabel(" ");

        mainpane.setLayout(new BoxLayout(mainpane, BoxLayout.Y_AXIS));
        mainpane.add(maintext);
        mainpane.add(new JLabel(" "));
        mainpane.add(new JLabel(" "));
        mainpane.add(new JLabel(" "));
        mainpane.add(new JLabel(" "));
        mainpane.add(percent);
        main.add(mainpane);
        main.setVisible(true);

        loadAllocation(1);

        //We specify the preferred game mode, the last champion that we want to create sets up to and the last lane of the same champion, if the game mode has lanes
        //gameMode("5v5", "zyra", "support");
        //This is to fix a bug where the last champion would not get the "Helpful Items" set added to their sets
        //helpfulItems(prevchamp);
        //gameMode("aram", "zyra", " ");
        //gameMode("arurf", "zyra", " ");
        //gameMode("ofa", "zyra", " ");


        while(threadsdone == false)
        {
            threadsdone = true;
            for(ThreadDemo t : threads)
            {
                if(t.isAlive())
                {
                    threadsdone = false;
                }
            }
        }

        ItemSet set = new ItemSet(champs);

        /*for(Champion champ: champs)
        {
            int n = -1;
            System.out.println(champ.getName() + " " + champ.getId());
            for(String lane : champ.lanes)
            {
                n++;
                System.out.print(lane + " ");
                for(Integer it : champ.items.get(n))
                {
                    System.out.print(it + " ");
                }
                System.out.println();
            }
            System.out.println();
        }*/

        System.out.println("Errored Items " + erroritems);
        System.out.println("Errored Champion IDs " + errorchampid);
        System.out.println("IOEs " + ioes);
        System.out.println("Banned Champion Item Sets " + bannedchamps);
        System.out.println("Number of champion sets made is " + goodchamps);

        maintext.setText("Number of champion sets made is " + goodchamps);
        percent.setText("Item Sets created successfully. You can close the program.");
    }

    private static void welcome()
    {
        try
        {
            File file = new File("bans.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            Scanner scan = new Scanner(br);

            if(scan.hasNext())
            {
                String text = scan.nextLine().toLowerCase().replace(" ", "").replace("\'", "").replace(".", "");
                for(String name : allchamps)
                {
                    if (text.equalsIgnoreCase(name))
                    {
                        JFrame already = new JFrame(version);
                        already.setLayout(new BorderLayout());
                        already.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        already.setLocationRelativeTo(null);
                        already.setLocation(350, 350);
                        already.setSize(600, 200);

                        JPanel panalready = new JPanel();
                        panalready.setLayout(new BoxLayout(panalready, BoxLayout.Y_AXIS));

                        JPanel textpane = new JPanel();
                        textpane.setLayout(new BoxLayout(textpane, BoxLayout.Y_AXIS));

                        textpane.add(new JLabel(" "));
                        textpane.add(new JLabel("It seems you have already saved a list of bans for the Item Sets."));
                        textpane.add(new JLabel("Would you like to use the saved list? Else, you can create a new list of bans."));
                        textpane.add(new JLabel(" "));
                        textpane.add(new JLabel(" "));
                        textpane.add(new JLabel(" "));
                        textpane.add(new JLabel(" "));
                        textpane.add(new JLabel(" "));

                        panalready.add(textpane, JPanel.LEFT_ALIGNMENT);

                        JPanel buttonpane = new JPanel();
                        buttonpane.setLayout(new BoxLayout(buttonpane, BoxLayout.X_AXIS));
                        JButton ok = new JButton("OK");
                        ok.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent actionEvent) {
                                ok.setEnabled(false);
                            }
                        });
                        JButton create = new JButton("Create New");
                        create.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent actionEvent) {
                                create.setEnabled(false);
                            }
                        });
                        JButton cancel = new JButton("Cancel");
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
                        buttonpane.add(create);
                        buttonpane.add(Box.createRigidArea(new Dimension(10, 0)));
                        buttonpane.add(cancel);


                        panalready.add(buttonpane);
                        already.add(panalready, BorderLayout.WEST);
                        already.setVisible(true);

                        while(true)
                        {
                            if(!ok.isEnabled())
                            {
                                already.dispose();
                                if(text.contains("wukong"))
                                {
                                    text = "monkeyking";
                                }
                                else if(text.contains("nunu&amp;willump"))
                                {
                                    text = "nunu";
                                }
                                banned.add(text);
                                while(scan.hasNextLine())
                                {
                                    String nam = scan.nextLine();
                                    //nam = nam.toLowerCase().replace(" ", "").replace("\'", "").replace(".", "");
                                    if(nam.contains("wukong"))
                                    {
                                        nam = "monkeyking";
                                    }
                                    else if(nam.contains("nunu&amp;willump"))
                                    {
                                        nam = "nunu";
                                    }
                                    banned.add(nam);
                                }
                                return;
                            }
                            else if(!create.isEnabled())
                            {
                                already.dispose();
                                break;
                            }
                        }
                    }
                }
            }

        } catch (FileNotFoundException e) {
        }


        mf = new GUI1(allchamps.size());
        mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mf.setLocationRelativeTo(null);

        while(true)
        {
            if(!mf.isDisplayable())
            {
                break;
            }
        }

        try
        {
            File file = new File("bans.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            Scanner scan = new Scanner(br);

            while(scan.hasNext())
            {
                String name = scan.nextLine();
                //name = name.toLowerCase().replace(" ", "").replace("\'", "").replace(".", "");
                banned.add(name);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean findItems(String url, String gamemode)
    {
        boolean ioe = false;
        String name = " ";
        String lane = " ";
        ArrayList<Integer> items = new ArrayList<>();
        int id = 0;

        try {
            String source = getURLSource(url);
            //System.out.println(source);

            boolean found = false;
            boolean namelan = false;
            String item;
            try (Scanner scanner = new Scanner(source)) {
                scanner.useDelimiter("\"");
                while (scanner.hasNext() && found == false) {
                    if (scanner.next().toString().equals("og:title"))
                    {
                        scanner.next();
                        String namelane = scanner.next().toString();

                        Scanner scan = new Scanner(namelane);
                        scan.useDelimiter("\\s+");
                        for(int k = 0; k < 3; k++) {
                            scan.next();
                        }
                        String temp = scan.next().toString();
                        name = temp;
                        id = findChampID(name);
                        if(id == 0)
                        {
                            errorchampid++;
                        }

                        while(scan.hasNext() && namelan == false)
                        {
                            temp = scan.next().toString();
                            if(!temp.equals("Top") && !temp.equals("Mid") && !temp.equals("ADC") && !temp.equals("Support") && !temp.equals("Jungle") && !temp.equals("Build"))
                            {
                                name += " ";
                                name += temp;
                            }
                            else
                            {
                                if(temp.equals("Build") && !gamemode.equals("5v5"))
                                {
                                    lane = gamemode;
                                }
                                else
                                {
                                    lane = temp;
                                }
                                //System.out.println(name + " " + "-" + " " + id + " " + lane);
                                System.out.println("Fetching info on " + name + " " + lane + "...");
                                maintext.setText("Fetching info on " + name + " " + lane + "...");
                                namelan = true;
                            }
                        }
                        found = true;
                    }
                }

                scanner.useDelimiter(">");
                int j = 10;
                if(gamemode.equals("5v5"))
                {
                    j = 14;
                }

                while (scanner.hasNext()) {
                    if (scanner.next().toString().equals("Best Starting Items</h3")) {
                        scanner.useDelimiter("\"");
                        for (int i = 0; i < j; i++) {
                            while (scanner.hasNext()) {
                                if (scanner.next().toString().equals(" alt=")) {
                                    //System.out.print(scanner.next().toString() + " ");
                                    item = scanner.next().toString();
                                    //System.out.print(item + " ");
                                    int itemtoid = itemToID(item);

                                    items.add(itemtoid);
                                    if(itemtoid == 0)
                                    {
                                        if(gamemode.equals("aram"))
                                        {
                                            items.remove(new Integer(0));
                                        }
                                        else
                                        {
                                            erroritems++;
                                        }
                                    }

                                    //System.out.print(itemtoid + " ");
                                    break;
                                }
                            }
                        }
                        //System.out.println();
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("IOE");
            ioes++;
            ioe = true;
        }

        if(gamemode.equals("5v5"))
        {
            items.remove(3);
            items.remove(5);
            items.remove(5);

            boolean hasrefill = false;
            for(Integer in : items)
            {
                if(in == 2031)
                {
                    hasrefill = true;
                }
            }
            if(hasrefill == false)
            {
                items.add(4, 2031);
            }

            items.add(2055);
        }


        boolean newchamp = true;
        for(Champion champ : champs)
        {
            if(champ.getName().equals(name))
            {
                champ.setItems(lane, items);
                newchamp = false;
            }
        }
        if(newchamp == true)
        {
            Champion champion = new Champion(name, id);
            champion.setItems(lane, items);
            champs.add(champion);

            helpfulItems(prevchamp);
        }
        prevchamp = name;

        return ioe;
    }

    public static void gameMode(String mode, String lastChamp, String lastlane)
    {
        String everyurl = "https://www.metasrc.com";
        String url = "https://www.metasrc.com/" + mode + "/stats?ranks=diamond,master,grandmaster,challenger";

        boolean doneurl = false;

        try {
            String pages = getURLSource(url);

            Scanner urlsc = new Scanner(pages);
            urlsc.useDelimiter("\"");

            while(urlsc.hasNext() && doneurl == false)
            {
                String tempurl = urlsc.next();
                boolean ban = false;

                String contains = "/" + mode + "/champion/";
                if(tempurl.contains(contains))
                {
                    tempurl = everyurl + tempurl;
                    for(String st : banned)
                    {
                        if(tempurl.contains("/champion/" + st))
                        {
                            ban = true;
                        }
                    }

                    if(ban == false)
                    {
                        boolean error = findItems(tempurl, mode);
                        while (error == true) {
                            error = findItems(tempurl, mode);
                        }

                        if (mode.equals("5v5")) {
                            contains += lastChamp + "/" + lastlane;
                        } else {
                            contains += lastChamp;
                        }
                        if (tempurl.contains(contains)) {
                            doneurl = true;
                        }
                    }
                    else if(ban == true && mode.equals("5v5"))
                    {
                        bannedchamps++;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void gameMode(String mode, String name)
    {
        for(String champ : banned)
        {
            if(name.toLowerCase().replace(" ", "").replace("\'", "").replace(".", "").equalsIgnoreCase(champ))
            {
                bannedchamps++;
                return;
            }
        }

        String everyurl = "https://www.metasrc.com";

        String url = "https://www.metasrc.com/" + mode + "/stats?ranks=diamond,master,grandmaster,challenger";

        boolean found = false;

        try
        {
            String pages = getURLSource(url);

            Scanner urlsc = new Scanner(pages);
            urlsc.useDelimiter("\"");
            String tempurl = " ";

            String contains = "/" + mode + "/champion/" + name.toLowerCase().replace(" ", "").replace("\'", "").replace(".", "");
            String finishedwiththischamp = "/" + mode + "/champion/";
            boolean error = true;

            while (urlsc.hasNext() && found == false) {
                tempurl = urlsc.next();
                if (tempurl.contains(contains)) {
                    tempurl = everyurl + tempurl;

                    error = findItems(tempurl, mode);
                    while (error == true) {
                        error = findItems(tempurl, mode);
                    }

                    while (urlsc.hasNext() && found == false) {
                        tempurl = urlsc.next();
                        if (tempurl.contains(contains))
                        {
                            tempurl = everyurl + tempurl;

                            error = findItems(tempurl, mode);
                            while (error == true) {
                                error = findItems(tempurl, mode);
                            }
                        }
                        else if (tempurl.contains(finishedwiththischamp))
                        {
                            found = true;
                        }
                    }
                }
            }
            if(error == false)
            {
                goodchamps++;
                percentdone += ((double)1/(allchamps.size()-banned.size()))*100;
                int perint = (int) percentdone/2;
                percent.setText(perint + "% complete");
            }
            else
            {
                System.out.println(name + "MOYNOPANO");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public static int findChampID(String name)
    {
        int pos = -5;
        int id = 0;
        String files = "champions_raw.txt";

        try {
            FileReader fr = new FileReader(files);
            Scanner idscan = new Scanner(fr);
            idscan.useDelimiter("\"");

            while (idscan.hasNext() && id == 0) {
                pos++;
                if(idscan.next().equals("name"))
                {
                    idscan.next();
                    pos += 2;

                    if (idscan.next().contains(name))
                    {
                        fr = new FileReader(files);
                        idscan = new Scanner(fr);
                        idscan.useDelimiter("\"");

                        for (int i = 0; i < pos && idscan.hasNext(); i++) {
                            idscan.next();
                        }
                        name = idscan.next();
                        id = Integer.parseInt(name);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return id;
    }


    public static int itemToID(String item)
    {
        int pos = -5;
        int id = 0;
        String files = "items_raw.txt";
        String name;
        try {
            FileReader fr = new FileReader(files);
            Scanner idscan = new Scanner(fr);
            idscan.useDelimiter("\"");

            while (idscan.hasNext() && id == 0) {
                pos++;
                if(idscan.next().equals(item))
                {
                    fr = new FileReader(files);
                    idscan = new Scanner(fr);
                    idscan.useDelimiter("\"");

                    for(int i = 0; i < pos && idscan.hasNext(); i++){
                        idscan.next();
                    }
                    name = idscan.next();
                    try {
                        id = Integer.parseInt(name);
                    }
                    catch(NumberFormatException ne)
                    {
                        System.out.println("A string was given as itemid input. " + item);
                        id = -1;
                    }
                }
            }
            if(id == -1)
            {
                id = 0;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return id;
    }

    public static void helpfulItems(String name)
    {
        for(Champion champ : champs)
        {
            if(champ.getName().equals(name))
            {
                ArrayList<Integer> lastitems = new ArrayList<>();
                lastitems.add(3363);
                lastitems.add(3364);
                lastitems.add(2138);
                lastitems.add(2139);
                lastitems.add(2140);
                lastitems.add(2047);
                champ.setItems("Helpful Items", lastitems);
            }
        }
    }

    public static void update(String champsl, String itemsl)
    {
        System.out.println("Updating champions data...");
        try {
            String sourcec = getURLSource(champsl);
            Scanner scanner = new Scanner(sourcec);
            scanner.useDelimiter("\"");
            boolean found = false;
            String linkc = " ";

            while(scanner.hasNext() && found == false)
            {
                linkc = scanner.next();
                if(linkc.contains("/data/en_US/champion.json"))
                {
                    found = true;
                }
            }

            File champsraw = new File("champions_raw.txt");
            FileWriter fr = new FileWriter(champsraw);
            BufferedWriter br = new BufferedWriter(fr);
            br.write(getURLSource(linkc));
            br.close();
            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error updating data for Champions.");
        }

        System.out.println("Updating items data...");
        try {
            String sourcei = getURLSource(itemsl);
            Scanner scanner = new Scanner(sourcei);
            scanner.useDelimiter("\"");
            boolean found = false;
            String linki = " ";

            while(scanner.hasNext() && found == false)
            {
                linki = scanner.next();
                if(linki.contains("/data/en_US/item.json"))
                {
                    found = true;
                }
            }

            File itemsraw = new File("items_raw.txt");
            FileWriter fr = new FileWriter(itemsraw);
            BufferedWriter br = new BufferedWriter(fr);
            br.write(getURLSource(linki));
            br.close();
            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error updating data for Items.");
        }
    }

    public static void countChamps(String filename)
    {
        int sum = 0;
        try {

            File f = new File(filename);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            Scanner scanner = new Scanner(br);
            scanner.useDelimiter("\"");
            String temp = " ";

            while(scanner.hasNext())
            {
                temp = scanner.next();
                if(temp.equals("id"))
                {
                    sum++;
                    for(int i = 0; i < 9; i++)
                    {
                        scanner.next();
                    }
                    String nam = scanner.next();
                    allchamps.add(nam);
                }
            }

            System.out.println("There are currently " + sum + " champions in League of Legends.");

        } catch (FileNotFoundException e) {
            System.out.println("There was an error finding the file containing the Champion data.");
            e.printStackTrace();
        }
    }

    public static void loadAllocation(int threadno)
    {

        for(int i = 0; i < threadno; i++)
        {
            ThreadDemo tr = new ThreadDemo(i, threadno, allchamps.size(), allchamps);
            threads.add(tr);
            tr.start();
        }
    }


    public static String getURLSource(String url) throws IOException
    {
        URL urlObject = new URL(url);
        URLConnection urlConnection = urlObject.openConnection();
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

        return toString(urlConnection.getInputStream());
    }
    
    
    private static String toString(InputStream inputStream) throws IOException
    {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8")))
        {
            String inputLine;
            StringBuilder stringBuilder = new StringBuilder();
            while ((inputLine = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(inputLine);
            }

            return stringBuilder.toString();
        }
    }

}