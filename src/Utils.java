import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class Utils {

    public static int[][] generateRandomMaze(int size) {
        int[][] maze = new int[size][size];
        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            for (int y = 0; y < size; y++) {
                maze[i][y] = rand.nextBoolean() ? 1 : 0;
            }
        }

        return maze;
    }

    public static Vertex[] generateRandomPoints(int size){
        Random random = new Random();

        Vertex[] vertices = new Vertex[2];

        vertices[0] = new Vertex(random.nextInt(size), random.nextInt(size), 1);
        vertices[1] = new Vertex(random.nextInt(size), random.nextInt(size), 1);

        while(vertices[1].equals(vertices[0]))
            vertices[1] = new Vertex(random.nextInt(size), random.nextInt(size), 1);

        return vertices;
    }

    public static void generateHTMLSoltion(Vertex[][] vertices, List<Vertex> path, Vertex startVertex, Vertex endVertex) {
        StringBuilder builder = new StringBuilder();

        builder.append("<html><body>");

        for(int y = 0; y < vertices.length; y++)
        {
            for(int x = 0; x < vertices[y].length; x++)
            {
                if(startVertex == vertices[y][x]) {
                    builder.append("<b><font color='lime'>").append('S').append("</font></b>");
                }
                else if(endVertex == vertices[y][x]){
                    builder.append("<b><font color='red'>").append('k').append("</font></b>");
                }
                else if(path.contains(vertices[y][x])) {
                    builder.append("<b><font color='lime'>").append('1').append("</font></b>");
                }
                else builder.append(vertices[y][x].getOpened());

                builder.append(' ');
            }
            builder.append("<br>");
        }

        builder.append("<br>Start: ").append(startVertex).append(", Koniec: ").append(endVertex).append("<br>");

        if(path.size() < 1)
            builder.append("<b>Brak drogi!</br>");
        else
        {
            builder.append("Istnieje droga: ").append(path).append("<br>");
            builder.append("Długosc drogi: ").append(path.size());
        }

        builder.append("</body></html>");

        try {
            FileWriter fileWriter = new FileWriter(new File("solution.html"));
            fileWriter.write(builder.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
