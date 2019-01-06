import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph {

    private List<Vertex> vertices = new LinkedList<>();
    private Vertex startVertex;
    private Vertex endVertex;

    public Graph(Vertex startVertex, Vertex endVertex, Vertex[][] vertices)
    {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        for(int i = 0; i < vertices.length; i++)
        {
            for (int x = 0; x < vertices[i].length; x++) {
                this.vertices.add(vertices[i][x]);
            }
        }
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public Vertex getEndVertex() {
        return endVertex;
    }
}
