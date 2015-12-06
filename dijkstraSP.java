import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;


public class dijkstraSP {

private int distances[];           		// simple array of cumulative distance from the source vertex
private Set<Integer> settled;     		// set of settled vertices
private Set<Integer> unsettled;     	// set of unsettled vertices
private int totalVertices;            // total vertices
private int adjacencyMatrix[][];      // matrix of the graph
private int predecessorVertex[];      // simple array that holds the predecessor node for the vertex


// Constructor
public dijkstraSP(int totalVertices)
{
								this.totalVertices = totalVertices;
								distances = new int[totalVertices + 1];
								settled = new HashSet<Integer>();
								unsettled = new HashSet<Integer>();
								adjacencyMatrix = new int[totalVertices + 1][totalVertices + 1];
								predecessorVertex = new int[totalVertices + 1];
}


public void run (int adjacency_matrix[][], int source){

								int currentVertex;
								for (int i = 1; i <= totalVertices; i++)
																for (int j = 1; j <= totalVertices; j++)
																								adjacencyMatrix[i][j] = adjacency_matrix[i][j]; // add values to matrix of run method

								for (int i = 1; i <= totalVertices; i++) {
																distances[i] = Integer.MAX_VALUE; // set distance to infinity to all
																predecessorVertex[i] = source; // set predecessor vertex as source for all
								}

								unsettled.add(source);  // add to unsettled
								distances[source] = 0; // update source destination to 0
								while (!unsettled.isEmpty()) {
																currentVertex = getLowestCostVertex(); // get lowest cost vertex  for unsettled
																unsettled.remove(currentVertex); // remove from unsettled
																settled.add(currentVertex); // and add to settled
																evalVertexs(currentVertex);
																//evaluate the adjacent vertices , update the new distance and start with next best unsettled vertex
								}

}

private int getLowestCostVertex() {
								int min;
								int node = 0;

								Iterator<Integer> iterator = unsettled.iterator();
								node = iterator.next();
								min = distances[node];
								for (int i = 1; i <= distances.length; i++) {
																if (unsettled.contains(i)) {
																								if (distances[i] <= min) {
																																min = distances[i];
																																node = i;
																								}
																}
								}
								return node;
}

private void evalVertexs(int currentVertex){
								int edgeDistance = -1;
								int newDistance = -1;

								for (int destVertex = 1; destVertex <= totalVertices; destVertex++) {

																if (!settled.contains(destVertex)) {

																								if (adjacencyMatrix[currentVertex][destVertex] != Integer.MAX_VALUE) {
																																edgeDistance = adjacencyMatrix[currentVertex][destVertex];
																																newDistance = distances[currentVertex] + edgeDistance; // add the distance

																																if (newDistance < distances[destVertex]) {
																																								distances[destVertex] = newDistance; // update new distance
																																								predecessorVertex[destVertex] = currentVertex; // update predecessor vertex
																																}
																																unsettled.add(destVertex); // add to unsettled and run through loop
																								}
																}
								}
}


public static void main(String args[]) {

								// used to print the output in pretty way
								String[] printArray = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

								int adjacency_matrix[][];
								int totalVertices;
								int source = 0;
								Scanner input = new Scanner(System.in);
								Scanner inputFile;

								//put the graph in graph.txt in matrix from and provide proper location below

								try{

																File file = new File("C://Users//viyat//workspace//dataStructures&Algo//graph.txt");
																inputFile = new Scanner(file);

																System.out.println("Enter the total number of vertices");
																totalVertices = input.nextInt();
																adjacency_matrix = new int[totalVertices + 1][totalVertices + 1];

																System.out.println("Weighted Matrix for the input graph is:");
																System.out.println();

																for (int i = 1; i <= totalVertices; i++) {

																								for (int j = 1; j <= totalVertices; j++) {

																																adjacency_matrix[i][j] = inputFile.nextInt();

																																if (i == j) {
																																								adjacency_matrix[i][j] = 0;
																																								System.out.print(adjacency_matrix[i][j] + " ");
																																								continue;
																																} else if (adjacency_matrix[i][j] == 0) {
																																								adjacency_matrix[i][j] = Integer.MAX_VALUE;
																																								System.out.print("0 ");
																																} else {
																																								System.out.print(adjacency_matrix[i][j] + " ");
																																}
																								}
																								System.out.println();
																}

																System.out.println();
																System.out.println("Please enter a start vertex ( 1 through "+totalVertices+"):");
																source = input.nextInt();

																dijkstraSP dijkstrasAlgorithm = new dijkstraSP(totalVertices);
																dijkstrasAlgorithm.run(adjacency_matrix, source);

																System.out.println("Shorted path and cost to all other vertices from your input vertex");
																System.out.println();

																int j=0;

																for (int i = 1; i <= dijkstrasAlgorithm.distances.length - 1; i++) {
																								j=i;
																								ArrayList<String> path = new ArrayList<String>();
																								path.add(printArray[j-1]);
																								while (dijkstrasAlgorithm.predecessorVertex[j] != source) { // add to path until reached to source
																																path.add(printArray[(dijkstrasAlgorithm.predecessorVertex[j]-1)]);
																																j = dijkstrasAlgorithm.predecessorVertex[j];
																								}
																								path.add(printArray[(source-1)]); // add source at the end
																								Collections.reverse(path); // reverse it

																								System.out.println(printArray[(source-1)] + " --> " + printArray[(i-1)] + " : Cost is  "+ dijkstrasAlgorithm.distances[i]
																																											+" Path is: " + path );
																}


								} catch (Exception e) {
																e.printStackTrace();
								}

}

}
