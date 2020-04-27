import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ItemSet
{
    public ItemSet(){
        File file = new File("ItemSet.json");
        try {
            FileWriter fr = new FileWriter(file);
            fr.write("[]");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ItemSet(ArrayList<Champion> champs)
    {
        File file = new File("ItemSet.json");
        try {
            FileWriter fr = new FileWriter(file);
            fr.write("[");

            int champno = -1;
            for(Champion champ : champs)
            {
                champno++;
                fr.write("{\"title\":\"");
                fr.write(champ.getName());
                fr.write("\",\"associatedMaps\":[],\"associatedChampions\":[");
                fr.write(Integer.toString(champ.getId()));
                fr.write("],\"blocks\":[");
                int laneno = -1;
                for(String lane : champ.lanes)
                {
                    int itemno = -1;
                    laneno++;
                    fr.write("{\"hideIfSummonerSpell\":\"\",\"items\":[");
                    for(Integer item : champ.items.get(laneno))
                    {
                        itemno++;
                        fr.write("{\"count\":1,\"id\":\"");
                        fr.write(Integer.toString(item));
                        fr.write("\"}");
                        if(champ.items.get(laneno).size() > itemno+1)
                        {
                            fr.write(",");
                        }
                    }
                    fr.write("],\"showIfSummonerSpell\":\"\",\"type\":\"");
                    fr.write(lane);
                    fr.write("\"}");
                    if(champ.lanes.size() > laneno+1)
                    {
                        fr.write(",");
                    }
                }
                fr.write("]}");
                if(champs.size() > champno + 1)
                {
                    fr.write(",");
                }
            }
            fr.write("]");
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
