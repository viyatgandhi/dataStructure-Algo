import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Vector;

/* prim's algorithm to find minimum spanning tree 
 * 
 */

public class prims {

public static void main(String[] args) {


								int inf = Integer.MAX_VALUE;
								int max_row, max_col;

								// graph presented in matrix form


																			  /*        A    B    C    D    E    F */
								int adj[][] = 								{ /* A */ { inf, 6,   5,   1,  inf, inf  },
																                /* B */ { 6,  inf,  5,  inf,  3,  inf  },
																                /* C */ { 5,  inf, inf,  5,  inf,  2   },
																                /* D */ { 1,  5,    5, inf,    6,  4   },
																                /* E */ { inf, 3,  inf, 6,   inf,  5   },
																                /* F */ { inf, inf,  2, 4,   5,    inf },};

								max_row  = max_col = 6;

								Vector<Vertex> graph = new Vector<Vertex>();

								graph.add(new Vertex("A"));
								graph.add(new Vertex("B"));
								graph.add(new Vertex("C"));
								graph.add(new Vertex("D"));
								graph.add(new Vertex("E"));
								graph.add(new Vertex("F"));

								for (int i = 0; i < max_row; i++)  {
																Vertex v = graph.elementAt(i);
																for (int j = 0; j < max_col; j++)  {
																								if (adj[i][j] != inf)  {
																																Vertex x = graph.elementAt(j);
																																v.add_edge(new Edge(x, adj[i][j]));
																								}
																}

								}

								// minimum spanning tree by Prim's

								ArrayList<String> allVertex = new ArrayList<String>();
								allVertex.add("A");
								allVertex.add("B");
								allVertex.add("C");
								allVertex.add("D");
								allVertex.add("E");
								allVertex.add("F");


								while (!allVertex.isEmpty()) {

																String inputVertex = allVertex.get(0);

																Vertex evalVertex = null;

																Vertex getNewV;
																for(int i=0; i<max_row; i++) {
																								getNewV = graph.elementAt(i);
																								if(getNewV.get_name().equals(inputVertex)) {
																																evalVertex= graph.elementAt(i);
																								}
																}

																System.out.println("Start vertex:" + evalVertex.get_name());

																PriorityQueue<Edge> pq = new PriorityQueue<>();
																ArrayList<Vertex> adjVertexs = new ArrayList<>();
																evalVertex.setConnected(true);
																adjVertexs.add(evalVertex);

																ArrayList<Edge> adjEdges = evalVertex.get_Edges();
																for (Edge edge : adjEdges) {
																								pq.add(edge);
																}

																int totalCost = 0;
																while (!adjVertexs.isEmpty()) {

																								if (pq.isEmpty()) {
																																break;
																								}

																								Edge edge = pq.remove();
																								if (edge.getSource().isConnected() && !edge.getDestination().isConnected()) {

																																System.out.println("  Add edge <" + edge.getSource().get_name() + ", " + edge.getDestination().get_name() + ", " + edge.getCost() + ">");

																																totalCost += edge.getCost();

																																edge.getDestination().setConnected(true);

																																adjVertexs.add(edge.getDestination());

																																adjEdges = edge.getDestination().get_Edges();

																																for (Edge adEdge : adjEdges) {
																																								if (edge.getDestination() != edge.getSource()) {
																																																pq.add(adEdge);
																																								}
																																}
																								}
																}

																System.out.println("Total cost: " + totalCost);

																// setting all the vertices back to false for next iteration
																for(int i=0; i<max_row; i++) {
																								getNewV = graph.elementAt(i);
																								getNewV.setConnected(false);
																}
																allVertex.remove(0);
								}

}


//--------------------------------------------------------------------
static class Vertex {
private String name;
private int id;           /* Integral id of vertex: [0, n-1] */
private int state;        /* 0: undiscovered; 1: discovered; 2: visited */
private ArrayList<Edge> edgelist;
private int totalEdge;
private boolean connected;

private static int counter = 0;

public Vertex(String name)  {
								this.name = name;
								state = 0;
								id = counter++;
								edgelist = null;
								totalEdge=0;
								connected=false;
}

public boolean isConnected() {
								return connected;
}

public void setConnected(boolean connected) {
								this.connected = connected;
}


public int getEdgeCount()
{
								return totalEdge;
}

public void order_edges()  {
								Collections.sort(edgelist);
}

public void add_edge(Edge e)  {
								if (edgelist == null)  {
																edgelist = new ArrayList<Edge>();
								}
								edgelist.add(e);
								e.setSource(this);
								totalEdge++;
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

public ArrayList<Edge> get_Edges() {
								return edgelist;
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


private Vertex source;
private Vertex destination;
private int cost;

public Edge(Vertex v, int cost) {
								this.destination = v;
								this.cost = cost;
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

public Vertex getSource() {
								return source;
}

public void setSource(Vertex source) {
								this.source = source;
}

public Vertex getDestination() {
								return destination;
}

public void setDestination(Vertex destination) {
								this.destination = destination;
}

public int getCost() {
								return cost;
}

public void setCost(int cost) {
								this.cost = cost;
}

}     // Class Edge
}
