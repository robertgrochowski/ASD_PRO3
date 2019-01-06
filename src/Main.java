public class Main
{

    public static void main(String[] args)
    {
        int mazeSize = 10;

        try {
            if(args.length == 1)
                mazeSize = Integer.parseInt(args[0]);
            else throw new IllegalArgumentException();

            if(mazeSize > 50)
            {
                System.err.println("Maximum maze length exceeded (50x50)");
                mazeSize = 10;
            }
        }
        catch(Exception e) {
            System.err.println("Invalid program argument(s) - assuming default maze size");
        }

        //Sample maze
       /* int[][] mazeSchema =
            {{1,1,1,0,1,0,1,0,1,1},
            {1,0,0,1,1,0,0,1,0,1},
            {1,1,1,1,0,0,1,0,0,0},
            {0,0,1,0,0,1,0,0,1,0},
            {1,0,1,0,0,1,0,0,0,1},
            {0,1,1,1,1,1,0,0,0,0},
            {0,0,0,1,0,1,0,1,0,1},
            {1,1,1,0,0,1,1,0,1,1},
            {0,1,0,0,0,0,1,1,1,1},
            {0,0,0,0,1,1,0,1,1,1}};*/

        //Generate random maze and Start, End points
        int[][] mazeSchema = Utils.generateRandomMaze(mazeSize);
        Vertex[] startEndVerts = Utils.generateRandomPoints(mazeSize);

        //Make Start and End open ways
        mazeSchema[startEndVerts[0].getY()][startEndVerts[0].getX()] = 1;
        mazeSchema[startEndVerts[1].getY()][startEndVerts[1].getX()] = 1;

        //sample maze
        //Maze maze = new Maze(mazeSchema, new Vertex(2,2, 1), new Vertex(8,9,1));
        Maze maze = new Maze(mazeSchema, startEndVerts[0], startEndVerts[1]);
        maze.solveMaze();
    }
}
