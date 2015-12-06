import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;

/* Breadth-first search (BFS) is an algorithm for traversing on graph data structures.
 * It starts at the tree root or some arbitrary node of a graph and explores the neighbor nodes first,
 * before moving to the next level neighbors.
 */

public class breadthFirstTraversal {

public static void main(String[] args) {

								int inf = Integer.MAX_VALUE;
								int max_row, max_col;

																								/*        A    B    C    D    E    F    G    H    I */
								int adj[][] =									{ /* A */ { inf, 8,   inf, 10,  inf, inf, 12,  inf, inf },
																               /* B */ { 8,   inf, inf, inf, 12,  18,  inf, inf, inf },
																               /* C */ { inf, inf, inf, inf, inf, 2,   inf, 10,  inf },
																               /* D */ { 10,  inf, inf, inf, inf, 8,   inf, inf, inf },
																               /* E */ { inf, 12,  inf, inf, inf, inf, 24,  inf, inf },
																               /* F */ { inf, 18,  2,   8,   inf, inf, inf, inf, inf },
																               /* G */ { 12,  inf, inf, inf, 24,  inf, inf, inf, inf },
																               /* H */ { inf, inf, 10,  inf, inf, inf, inf, inf, inf },
																               /* I */ { inf, inf, inf, inf, inf, inf, inf, 3,   inf }};

								max_row  = max_col = 9;

								Vector<Vertex> graph = new Vector<Vertex>();
								graph.add(new Vertex("A"));
								graph.add(new Vertex("B"));
								graph.add(new Vertex("C"));
								graph.add(new Vertex("D"));
								graph.add(new Vertex("E"));
								graph.add(new Vertex("F"));
								graph.add(new Vertex("G"));
								graph.add(new Vertex("H"));
								graph.add(new Vertex("I"));

								for (int i = 0; i < max_row; i++)  {
// Go through each row of the adjacency matrix collecting neighbours
																Vertex v = graph.elementAt(i);
																for (int j = 0; j < max_col; j++)  {
																								if (adj[i][j] != inf)  {
																																v.add_edge(new Edge(Vertex.get_vertex_name(i), Vertex.get_vertex_name(j), adj[i][j]));
																								}
																}
																v.order_edges();
								}

								while (true) {

																System.out.println("Enter starting vertex: (A through I - capslock only) OR enter 0 to quit");

																Scanner scan = new Scanner(System.in);
																String inputVertex = scan.next();


																if(inputVertex.equals("0")) {
																								System.out.println("exit");
																								break;
																}

																// BFS starts here

																String output = new String();

																Queue<String> queue = new LinkedList<String>();

																queue.add(inputVertex);

																output += inputVertex + ", ";

																for(int i=0; i<max_row; i++) {
																								Vertex setV = graph.elementAt(i);
																								if(setV.get_name().equals(inputVertex)) {
																																setV.visited();
																								}
																}



																String node = inputVertex;
																Vertex setNewV = null;

																while(queue.isEmpty() == false) {

																								Vertex getNewV;
																								for(int i=0; i<max_row; i++) {
																																getNewV = graph.elementAt(i);
																																if(getNewV.get_name().equals(node)) {
																																								setNewV= graph.elementAt(i);
																																}
																								}


																								while(true) {
																																String nextVertex = setNewV.getBestEdge();
																																if(nextVertex != null) {

																																								int nextVisitedValue=0;

																																								for(int i=0; i<max_row; i++) {
																																																Vertex getV = graph.elementAt(i);
																																																if(getV.get_name().equals(nextVertex)) {
																																																								nextVisitedValue=getV.get_state();
																																																}
																																								}

																																								if(nextVisitedValue!=2) {
																																																queue.add(nextVertex);
																																																output += nextVertex + ", ";

																																																for(int i=0; i<max_row; i++) {
																																																								Vertex setV = graph.elementAt(i);
																																																								if(setV.get_name()==nextVertex) {
																																																																setV.visited();
																																																								}
																																																}

																																								} else {}
																																}else{
																																								break;
																																}
																								}
																								queue.remove();
																								node = queue.peek();
																}

																System.out.println("BFS traversal is  : "+output);

																// intiatng graph for next iteration
																graph = new Vector<Vertex>();
																graph.add(new Vertex("A"));
																graph.add(new Vertex("B"));
																graph.add(new Vertex("C"));
																graph.add(new Vertex("D"));
																graph.add(new Vertex("E"));
																graph.add(new Vertex("F"));
																graph.add(new Vertex("G"));
																graph.add(new Vertex("H"));
																graph.add(new Vertex("I"));

																for (int i = 0; i < max_row; i++)  {
																								// Go through each row of the adjacency matrix collecting neighbours
																								Vertex v = graph.elementAt(i);
																								for (int j = 0; j < max_col; j++)  {
																																if (adj[i][j] != inf)  {
																																								v.add_edge(new Edge(Vertex.get_vertex_name(i), Vertex.get_vertex_name(j), adj[i][j]));
																																}
																								}
																								v.order_edges();
																}

								}

}


//--------------------------------------------------------------------
static class Vertex {
private String name;
private int id;           /* Integral id of vertex: [0, n-1] */
private int state;        /* 0: undiscovered; 1: discovered; 2: visited */
private ArrayList<Edge> edgelist;
private int totalEdge;

private static int counter = 0;

public Vertex(String name)  {
								this.name = name;
								state = 0;
								id = counter++;
								edgelist = null;
								totalEdge=0;
}

public String toString()  {

								String s = "Vertex: " + name + "\n";
								s = s + " Neighbours: \n";
								Iterator<Edge> itr = edgelist.iterator();
								while (itr.hasNext())  {
																Edge e = itr.next();
																s =  s + e;
								}

								return s;
}

public int getEdgeCount()
{
								return totalEdge;
}

public void order_edges()  {
								Collections.sort(edgelist);
}

public Edge removeEdge()
{
								Edge e = null;

								if (edgelist.size() > 0)  {
																e = edgelist.get(0);
																edgelist.remove(e);
																totalEdge--;
								}
								if ( e== null)
																totalEdge = 0;

								return e;
}

public String getBestEdge()
{
								Edge e = removeEdge();
								if(e != null)
																return e.destination();
								else
																return null;
}

public void add_edge(Edge e)  {
								if (edgelist == null)  {
																edgelist = new ArrayList<Edge>();
								}
								edgelist.add(e);
								totalEdge++;
}

public boolean match_name(String name)  {
								if (this.name.equals(name))
																return true;
								else
																return false;
}

public void visited()  {
								state = 2;
}

public void discovered()  {
								state = 1;
}

public int get_state()  {
								return state;
}

public String get_name()  {
								return name;
}

public static int get_vertex_index(String name)  {
								int v = -1;

								switch(name)  {
								case "A": v = 0; break;
								case "B": v = 1; break;
								case "C": v = 2; break;
								case "D": v = 3; break;
								case "E": v = 4; break;
								case "F": v = 5; break;
								case "G": v = 6; break;
								case "H": v = 7; break;
								case "I": v = 8; break;
								default: System.out.println("get_vertex_index: invalid name"); break;
								}
								return v;
}

public static String get_vertex_name(int index)  {
								String v = "null";
								switch(index)  {
								case 0: v = "A"; break;
								case 1: v = "B"; break;
								case 2: v = "C"; break;
								case 3: v = "D"; break;
								case 4: v = "E"; break;
								case 5: v = "F"; break;
								case 6: v = "G"; break;
								case 7: v = "H"; break;
								case 8: v = "I"; break;
								default: System.out.println("get_vertex_name: invalid index"); break;
								}
								return v;
}
}     // Class Vertex

static class Edge implements Comparable<Edge>  {
private String source;
private String destination;
private int cost;

public Edge(String s, String d, int c)  {
								source = s; destination = d; cost = c;
}

public String toString()  {
								return "  " + destination + ", cost: " + cost + "\n";
}

public int compareTo(Edge o)  {
								if (this.cost < o.cost)
																return -1;
								else if (this.cost > o.cost)
																return 1;
								else
																return 0;
}

public String destination()
{
								return destination;
}

}     // Class Edge


}
