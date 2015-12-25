import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/* import Chord_node.java & ComparableSorts.java to run this program
 * 
 * Chord is a protocol and algorithm for a peer-to-peer distributed hash table. 
 * A distributed hash table stores key-value pairs by assigning keys to different computers (known as "nodes"); 
 * a node will store the values for all the keys for which it is responsible.
 * Chord specifies how keys are assigned to nodes, and how a node can discover the value for a given key by 
 * first locating the node responsible for that key.
 * 
 * Below network forms 8 bit chord network and has 25 IP nodes
 */

public class chordNetwork {
	
	public static void main(String args[]){
		
		String[] input_IPs = {"10.0.0.1",
				"12.0.5.1",
				"198.165.98.11",
				"198.165.98.201",
				"0.0.0.1",
				"0.0.0.10",          
				"216.47.143.249",    
				"216.47.152.222",    
				"98.138.253.109",    
				"98.139.183.24",     
				"98.138.252.30",     
				"75.75.7.101",       
				"85.75.7.101",       
				"151.207.17.241",    
				"10.48.71.91",       
				"15.89.38.55",       
				"215.147.210.28",    
				"10.0.251.128",      
				"69.89.31.5",        
				"9.8.31.5",          
				"64.188.59.23",      
				"74.6.50.24",        
				"23.45.174.184",     
				"23.75.217.84",      
				"128.239.155.101"};
		
			Chord_node[] nodeArray = new Chord_node[input_IPs.length];
		
			// setting up node name,index and ip 
			
			for (int i=0;i<input_IPs.length;i++){
				Chord_node n = new Chord_node();
				int a=(int)h(input_IPs[i]);
				String p ="N";
				p=p.concat(Integer.toString(a));
				n.node_name=p;
				n.node_index=a;
				n.node_ip=input_IPs[i];
				nodeArray[i]=n;
		}
		
		ComparableSorts.insertionSort(nodeArray);	 // sort on basis of node index for chord
		
		nodeArray = buildNodes(input_IPs, nodeArray); // setting successor, predecessor and finger table
		
		// view chord info including finger table value
		// uncomment below part to view finger table for all nodes and all other info 
		
		/*for(int i = 0 ; i< input_IPs.length ; i++){
			System.out.println("Node Index: " +nodeArray[i].node_index);
			System.out.println("Node Name: "+nodeArray[i].node_name);
			System.out.println("Node IP: "+nodeArray[i].node_ip);
			System.out.println("Node Predecessor: " +nodeArray[i].predecessor.node_name);
			System.out.println("Node Successor: " +nodeArray[i].successor.node_name);
			
			System.out.println("Finger table value: ");
			for(int f = 0 ; f< nodeArray[i].finger_table.length ; f++){
				System.out.println("N"+nodeArray[i].finger_table[f].node_index);
			}
			System.out.print("\n");
		} */
		
		double totalredirection=0;
		double loop=0;
		
		while(true){
		System.out.print("\n");
		System.out.println("Enter Document ID (numbers or string) (or enter 000 to exit): " );
		Scanner scan1 = new Scanner(System.in);
	    String docID = scan1.next();
	    
	    if(docID.equals("000")){
	    	break;
	    }
	    
	    int b=(int)h(docID);
	    String p ="K";
		p=p.concat(Integer.toString(b));
		System.out.println("Hash of document ID is: " +p );
		System.out.print("\n");
		System.out.println("Enter starting node (like N0 or N25...):" );
		Scanner scan2 = new Scanner(System.in);
	    String snode = scan2.next();
	    int n= Integer.parseInt(snode.substring(1));
	    
	    if(n>b){
	    	System.out.println("Node value cannot be greater than key hash value" );
	    	System.out.println("Please provide proper Start Node value - exiting" );
	    	System.exit(2);
	    }
	    
	    int redirection=getredirection(n,nodeArray,b); // get redirection path 
	    totalredirection+=redirection;
	    loop++;	
	    
	    
		}
		
		if(loop>0) {
		double red=totalredirection/loop; // get avg of total redirection
		System.out.print("\n");
		System.out.println("Average number of redirections: "+red);
		}
		
}
	
	// hash method
	public static int h(String s)  {
		long h = 0;
		int len = s.length();

		for (int i = 0; i < len; i++)  {
			h = 31 * h + s.charAt(i);
		}

		return (int) Math.abs(h % 256);  // In case of overflow and negative numbers
	}
	
	
	private static Chord_node[] buildNodes(String[] input_IPs, Chord_node[] nodeArray) 
	{
		
		// setting predecessor and successor nodes
		for(int i = 0 ; i< input_IPs.length ; i++){
			if(i != 0)
				nodeArray[i].predecessor = nodeArray[i-1];
			if(i != input_IPs.length -1)
				nodeArray[i].successor = nodeArray[i+1];
		}
		
		nodeArray[0].predecessor = nodeArray[input_IPs.length -1];
		nodeArray[input_IPs.length -1].successor = nodeArray[0];
		
		
		for(int i = 0 ; i< input_IPs.length ; i++){
			nodeArray[i].finger_table = new Chord_node[nodeArray[i].finger_table.length];
			int count = i;
			for(int k = 0 ; k< nodeArray[i].finger_table.length ; k++){
				nodeArray[i].finger_table[k] = new Chord_node();
				while((nodeArray[i].node_index + (int)Math.pow(2, k)) 
						> nodeArray[count].successor.node_index 
						&& count != input_IPs.length-1){
					count++;
				}
				
				if((nodeArray[i].node_index + (int)Math.pow(2, k))<=nodeArray[count].successor.node_index)
				{
					nodeArray[i].finger_table[k] = nodeArray[count].successor;
				}
				
				// if value greater than max node value set finger table value as last/max node 
				if((nodeArray[i].node_index + (int)Math.pow(2, k)) >=247 )
				{
					nodeArray[i].finger_table[k] = nodeArray[input_IPs.length-1];
				}
				
				// if value greater than 2^m i.e 256 setting nodes as per modulo formula in finger table
				if((nodeArray[i].node_index + (int)Math.pow(2, k)) >=257 )
				{
					//nodeArray[i].finger_table[k] = nodeArray[0];
					int v = (nodeArray[i].node_index + (int)Math.pow(2, k));
					int m= v%256;
					ArrayList<Integer> aux = new ArrayList<Integer>();
					ArrayList<Integer> taux = new ArrayList<Integer>();
					for(int d=0; d<nodeArray.length;d++){
						aux.add(nodeArray[d].node_index);
						taux.add(nodeArray[d].node_index);
					}
					
					for(int d=0; d<aux.size();d++){
						if(aux.get(d)<m){
							taux.remove(aux.get(d));
						}
					}
					Collections.sort(taux); // Sort the arraylist 
					int z=taux.get(0); //gets the first item, i.e lowest value 
					for(int d=0; d<nodeArray.length;d++){
						if(nodeArray[d].node_index==z){
							nodeArray[i].finger_table[k] = nodeArray[d];  
						}
					}	
				}			
			}
		}
		return nodeArray;
	}
	
	
	public static int getredirection(int n ,Chord_node[] nodeArr ,int key )
	{
		ArrayList<Integer> aux = new ArrayList<Integer>();
		aux.add(n);	 // add starting node
			
		int loop=0;
		
		Chord_node startn = new Chord_node();
		
		// get the starting node value
		for(int i=0;i<nodeArr.length;i++){
			if(nodeArr[i].node_index==n){
				startn=nodeArr[i];
			}	
		}
		
		// go through loop until found key>node
		while(key>loop){
			int nextNode=getNextNodeInfo(startn,key);
			aux.add(nextNode); // add next node into list
			
			// if reached to max node in network break from loop and considering edge conditions
			if(nextNode==247 && key>247 && key<256){
				aux.add(0);
				break;
			} else if (nextNode==247){
				break;
			}
			
			for(int i=0;i<nodeArr.length;i++){
				if(nodeArr[i].node_index==nextNode){
					startn=nodeArr[i]; // set next node for next iteration
				}	
			}
			loop=nextNode;
		}
		
		// view redirections using list
		for(int z=0;z<aux.size();z++){
			if(z+1<aux.size()){
			System.out.println( "N"+aux.get(z)+" redirected to N"+aux.get(z+1));
			} else if(z+1==aux.size()) {
				System.out.println( "N"+aux.get(z)+" has K"+key);
			}
		}
		
		System.out.println( "Number of redirections: "+ (aux.size()-1));
		
		return (aux.size()-1);
	}
	
	public static int getNextNodeInfo(Chord_node startn ,int key)
	{		
		
		int node=0;
		ArrayList<Integer> aux = new ArrayList<Integer>();
		
		// get the best node from the finger table for incoming node
		for(int x=0;x<startn.finger_table.length;x++){
			if(startn.finger_table[x].node_index<key ){
				if(startn.finger_table[0].node_index<key){				
					aux.add(startn.finger_table[x].node_index);
				}
			}
		}
		
		// if no node found set first value of finger table as last node
		if(aux.size()==0){
			aux.add(startn.finger_table[0].node_index);
		}
		
		Collections.sort(aux); // Sort the arraylist  
		node=aux.get(aux.size() - 1); //gets the last item, i.e highest value 
		return node;
		}	
}	
