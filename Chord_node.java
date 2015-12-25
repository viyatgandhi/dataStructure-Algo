public class Chord_node  implements Comparable<Chord_node> {

		   private static final int m = 8; // 8 bit 

		   Chord_node successor;    // Node's successor
		   Chord_node predecessor;  // Node's predecessor
		   Chord_node[] finger_table = new Chord_node[m];  // Finger table of at most m entries
		   int node_index;     // Integer index of this node [0 - 255]
		   String node_name;   // Name of node, e.g., N20 or N190
		   String node_ip;   // Name of node, e.g., N20 or N190
		   
		   public int compareTo (Chord_node o ){
		   if (this.node_index < o.node_index)
	              return -1;
	          else if (this.node_index > o.node_index)
	              return 1;
	          else
	              return 0;
	      }
		   
}