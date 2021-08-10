import java.util.ArrayList;

public class Champion
{
    private String name;
    private int id;
    public ArrayList<ArrayList<Integer>> items = new ArrayList<>();
    public ArrayList<String> lanes = new ArrayList<>();

    public Champion(String nam, int idno)
    {
        name = nam;
        id = idno;
    }

    public void setItems(String lane, ArrayList<Integer> item)
    {
        lanes.add(lane);
        items.add(item);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
