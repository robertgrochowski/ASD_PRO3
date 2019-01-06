import java.util.ArrayList;
import java.util.List;

public class Vertex {

    private int x;
    private int y;
    private int opened;
    private int id;
    private List<Vertex> edges;
    private static int lastId=0;

    public Vertex(int x, int y, int opened)
    {
        this.x = x;
        this.y = y;
        this.opened = opened;
        this.id = lastId++;
        edges = new ArrayList<>();
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public boolean isOpened() {
        return opened==1;
    }

    public int getOpened() {
        return opened;
    }

    public void addEdge(Vertex vertex){
        edges.add(vertex);
    }

    public List<Vertex> getNeighbours() {
        return edges;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return (((Vertex)obj).getX() == x && ((Vertex)obj).getY() == y);
    }

    @Override
    public String toString() {
        return "("+(y+1)+","+(x+1)+")";
    }
}
