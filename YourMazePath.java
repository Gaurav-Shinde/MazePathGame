import java.util.*;
import java.awt.Point;
import java.util.LinkedList;

/*

This program creates a mazepath using
Depth First Search algorithm rather than
Breadth First Search. Depth First Search is based
on a spanning tree and here does not use queue or parent 
arrays due to it being recursive. Its big-O is generalized
at O(n+e).

//Write more info about DFS and BFS comparisons:

DFS uses back tracing instead of parent array storage of nodes in the mazepath
DFS is not shortest length path, but BFS is.
PQT can be modified to be BFS or DFS

what is BFS vs DFS used for?
  BFS is used to find the shortest path in a maze (djikstra)
  DFS finds A mazepath. BFS can be turned into DFS by switching
  its Queue (FIFO) with a Stack (FILO).

when is BFS better? DFS?
  BFS is better in finding the shortest path w/ least length.
  It's also analogous to level traversal or sequential representation
  
  DFS can have pre-order, in-order, and post-order traversal.
  DFS creates a recursive calling depth-first tree

What is big-O of BFS vs DFS?
    BFS
        Queue operations take O(n) time
        adjacency list is traversed in O(e) time
        Total time complexity is O(n+e) //or Djikstra O(n^2)
    DFS 
        O(n+e) as well.


General algorithm
  BFS:
    for all vertex u in set V and u!=s,
        set d[u] = infinity,v[u] = false, p[u] = null
    set d[s] = 0, p[s] = null, v[s] = true
    set Q queue to empty and enqueue Q(s)
    while(Q!=empty)
        u = dequeue Q
        for each vertex v adjacent to u
            if(v[v]!=1)
                set v[v] to true, p[v] = u, d[v] = d[u]+1
                enqueue Q(v)

    DFS:
    For each vertex u in V
        set v[u] = false, p[u] = null, d[u] = infinity
    DFS(G,u)
        set v[u] = true
        for each vertex v adjacent to u
            set p[v] = u, d[v] = d[u]+1 
            DFS(G,v)


*/
public class YourMazePath
{   
	//InMaze object
    private InputGraphicMaze maze;
    //#rows and #cols
    private int R, C; 
    //visited rxc 2D array
    private int V[][];
    //Used for path mapping
    boolean done = false;

    //main
    public static void main(String[] args)
    {   
        new YourMazePath();
    }
    
    //constructor
    public YourMazePath() {       
       maze = new InputGraphicMaze();
       R=maze.Rows(); C=maze.Cols();  
       V = new int[R+1][C+1];
       
       //initialize all points to not visited
       for (int i=1; i<=R; i++)
           for (int j=1; j<=C; j++) V[i][j]=0;
       
       //Path Linked list
       LinkedList<Point> Path = new LinkedList<Point>();

       //start DFS to find path
       CreatePath(maze, 1, 1, R, C, Path);

       //Display points of path
       ListIterator<Point> A = Path.listIterator();
       System.out.println("Points path from start to finish");
        while(A.hasNext()){
            System.out.println(A.next());
        }
       
       //show Path in green
       maze.showPath(Path);
    }

    /*
    The CreatePath method is a recuursive function based on DFS
    to find a path from srow x scol to erow x ecol. It recieves
    params of maze, srow, scol, erow, ecol, and Linked List for Path 
    named L. It first checks whether the new first point u is already visited,
    if not, then it is visited and if it's not the end point, then it chooses
    to go Up, Right, Down, or Left based on openings. Once it finds the point
    it wants to go to next, that points row and col info is inserted into the
    recursive method to find another adjacent point and so on till end point is found.
    There is an important mechanism for displaying the actual path however, and it is 
    the use of done. Once the end point is found, the recursive methods may decide to
    further randomly try to find the end point from another adjacent point, but this is
    inhibited by the done boolean since when the end point is found, parent method calls
    are unable to chose any more adjacent nodes and are simply back traced and put into the 
    Linked List from the last point being last and first point being first. 
    */
    public void CreatePath(InputGraphicMaze maze,      
      int srow, int scol, int erow, int ecol, LinkedList<Point> L)
    { 
        int R = erow; int C = ecol;
        Point u = new Point(srow,scol);
        int r=(int) u.getX(), c=(int) u.getY();

        if (V[r][c]!=1){                                                          
            
            V[r][c] = 1;
            
            if ((r==R)&&(c==C)) {done=true;}
            else{
                
            	 if ((!done) && (r>1)){              
            		if((V[r-1][c]!=1)&&(maze.can_go(r, c,'U'))){
                         CreatePath(maze,r-1,c,R,C,L);
                    }                    
                 }
            	 if ((!done) && (c<C)&&(V[r][c+1]!=1)&&(maze.can_go(r, c,'R'))){ 
                       CreatePath(maze,r,c+1,R,C,L);
                }
            	 if ((!done) && (r<R)&&(V[r+1][c]!=1)&&(maze.can_go(r, c, 'D'))){ 
                     CreatePath(maze,r+1,c,R,C,L);
                 }              
            	 if ((!done) && (c>1)&&(V[r][c-1]!=1)&&(maze.can_go(r, c, 'L'))){ 
                     CreatePath(maze,r,c-1,R,C,L);
                 }
            }
        }
        
        if(done){
            L.addFirst(u);
        }
        
    }

}
