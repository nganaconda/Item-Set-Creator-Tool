import java.util.ArrayList;

public class ThreadDemo extends Thread
{
    int id;
    int sum;
    int load;
    ArrayList<String> allchamps = new ArrayList<>();

    ThreadDemo(int id, int sum, int load, ArrayList<String> allchamps)
    {
        this.id = id;
        this.sum = sum;
        this.load = load;
        this.allchamps = allchamps;
    }

    public void run()
    {
        int pointerfirst = (load/sum)*id;
        int pointerlast = pointerfirst + (load/sum)-1;
        if(pointerlast >= load)
        {
            pointerlast = load-1;
        }
        if(pointerlast < load-1 && id == sum - 1)
        {
            pointerlast = load-1;
        }

        String pointerf = allchamps.get(pointerfirst);
        String pointerl = allchamps.get(pointerlast);

        for(int i = pointerfirst; i <= pointerlast; i++)
        {
            String name = allchamps.get(i);

            if(name.equalsIgnoreCase("wukong"))
            {
                name = "monkeyking";
            }
            else if(name.equalsIgnoreCase("Nunu & Willump"))
            {
                name = "nunu";
            }


            TextExtractor.gameMode("5v5", name);
            TextExtractor.gameMode("aram", name);
            //TextExtractor.gameMode("ofa", name);
            //TextExtractor.gameMode("urf", name);
            //System.out.println(name);
        }

    }
}
