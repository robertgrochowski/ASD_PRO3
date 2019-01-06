import java.util.*;

public class Maze {

    private Vertex startVertex;
    private Vertex endVertex;
    private Vertex[][] vertices;
    private int mazeSize;

    public Maze(int[][] maze, Vertex startVertex, Vertex endVertex)
    {
        this.startVertex = startVertex;
        this.endVertex = endVertex;

        mazeSize = maze.length;
        vertices = new Vertex[mazeSize][mazeSize];

        //Collect Nodes
        for(int y = 0; y < mazeSize; y++) {
            for (int x = 0; x < mazeSize; x++) {
                vertices[y][x] = new Vertex(x, y, maze[y][x]);

                if(vertices[y][x].equals(startVertex)) vertices[y][x] = startVertex;
                if(vertices[y][x].equals(endVertex)) vertices[y][x] = endVertex;
            }
        }

        //Add edges
        for(int y = 0; y < mazeSize; y++) {
            for (int x = 0; x < mazeSize; x++) {
                //Top:
                if (isValidCord(y - 1, x) && vertices[y - 1][x].isOpened()) vertices[y][x].addEdge(vertices[y - 1][x]);
                //Bottom:
                if (isValidCord(y + 1, x) && vertices[y + 1][x].isOpened()) vertices[y][x].addEdge(vertices[y + 1][x]);
                //Left:
                if (isValidCord(y, x - 1) && vertices[y][x - 1].isOpened()) vertices[y][x].addEdge(vertices[y][x - 1]);
                //Right:
                if (isValidCord(y, x + 1) && vertices[y][x + 1].isOpened()) vertices[y][x].addEdge(vertices[y][x + 1]);
            }
        }
    }

    public void solveMaze(){
        Graph graph = new Graph(startVertex, endVertex, vertices);
        Utils.generateHTMLSoltion(vertices, getPathBFS(graph), startVertex, endVertex);
    }

    List<Vertex> getPathBFS(Graph graph)
    {
        Set<Vertex> visited = new HashSet<>();
        LinkedList<Vertex> queue = new LinkedList<>();
        Map<Vertex, Vertex> parents = new LinkedHashMap<>();

        visited.add(graph.getStartVertex());
        queue.add(graph.getStartVertex());

        while (queue.size() != 0)
        {
            Vertex vertex = queue.poll();

            for(Vertex edge : vertex.getNeighbours())
            {
                if(!visited.contains(edge))
                {
                    parents.put(edge, vertex);
                    visited.add(edge);
                    queue.add(edge);

                    if(edge == graph.getEndVertex())
                        return convertMapToPath(parents, graph.getStartVertex(), graph.getEndVertex());
                }
            }
        }

        return new LinkedList<>();
    }

    List<Vertex> convertMapToPath(Map<Vertex, Vertex> parents, Vertex startVert, Vertex endVert)
    {
        List<Vertex> path = new LinkedList<>();
        Vertex last = endVert;

        while(last != startVert)
        {
            path.add(last);
            last = parents.get(last);
        }

        path.add(startVert);
        Collections.reverse(path);
        return path;
    }

    private boolean isValidCord(int y, int x) {
        return (x >= 0 && y >=0 && x<=mazeSize-1 && y<=mazeSize-1);
    }


    //For debug only
    private static void printMaze(int[][] maze)
    {
        for(int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze[y].length; x++) {
                System.out.print(maze[y][x] + " ");
            }
            System.out.println();
        }
    }
}
