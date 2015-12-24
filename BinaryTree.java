import java.util.*;

/* 
 * 
 */

public class BinaryTree<E>  {

   private int index;        /* This is the index of the next node to insert
                                in the tree. */
   private TreeNode<E> root; /* The absolute root of the tree. */

   /*
    * Default constructor. */
   public BinaryTree()  {
      index = 0;
      root = null;
   }

   /* ------------------------------------------------------------------
    * Recursively calculates the size of the tree; i.e., the number of
    * elements in the binary tree. */
   public int size() { return size_p(root); }
   
   private int size_p(TreeNode<E> t)  {
      if(t==null)
    	  return 0;
      else return(1+ size_p(t.get_left()) + size_p(t.get_right()));          
   }

   /* -------------------------------------------------------------------
    * Recursively does an inorder traversal of the tree
    */
   public void inorder() { inorder_p(root); }

   private void inorder_p(TreeNode<E> t)  {
      
	   if (t != null) {
		   inorder_p(t.get_left());
		   System.out.print(t.get_info()+",");
		   inorder_p(t.get_right());
		   }
		   
   }
   
   /*
    *inorder traversal using iteration */
   
   public void inorder_itr() { 
	   
	   Stack<TreeNode<E>> store = new Stack<TreeNode<E>>();
	   
	   TreeNode<E> current;
	   current=root;
	   boolean done=true;
	   
	   while(done){
		   if(current!=null){
			   store.push(current);	   
			   current=current.left;
		   } else {
			   	if(!store.isEmpty()){
			   		current=store.pop();
			   		System.out.print(current.info+",");
			   		current=current.right;
			   	} else{
			   		done=false;
			   	}
		   }	
	  }
	   
   }
   
   /*
    *size using iteration */
   
public int size_itr() { 
	   
	   Stack<TreeNode<E>> store = new Stack<TreeNode<E>>();
	   
	   TreeNode<E> current;
	   current=root;
	   boolean done=true;
	   int size=0;
	   
	   while(done){
		   if(current!=null){
			   store.push(current);	   
			   current=current.left;
		   } else {
			   	if(!store.isEmpty()){
			   		current=store.pop();
			   		size++;
			   		current=current.right;
			   	} else{
			   		done=false;
			   	}
		   }	
	  }
	   return size;
	   
   }

   /* --------------------------------------------------------------------
    * Recursively does an preorder traversal of the tree
    */
   public void preorder() { preorder_p(root); }

   private void preorder_p(TreeNode<E> t)  {
	   
	   if (t != null) {
		   System.out.print(t.get_info()+",");
		   preorder_p(t.get_left());
		   preorder_p(t.get_right());
		   }
		   
   }

   /* -------------------------------------------------------------------
    * Recursively does an postorder traversal of the tree
    */
   public void postorder() { postorder_p(root); }
   
   private void postorder_p(TreeNode<E> t)  {
	   
	   if (t != null) {
		   postorder_p(t.get_left());
		   postorder_p(t.get_right());
		   System.out.print(t.get_info()+",");
		   }
		   
   }

   /*
    * add method without recursion - till index 7
    */
   
   public void add(E info)
   {

      TreeNode<E> node = new TreeNode<E>(info);

       if (index == 0)  {
           root = node;
       }
       else if (index == 1)  {
          root.left = node;
       }
       else if (index == 2)  {
          root.right = node;
       }
       else if (index == 3)  {
          root.left.left = node;
       }
       else if (index == 4)  {
          root.left.right=node;
       }
       else if (index == 5)  {
         root.right.left=node;
       } 
       else if (index == 6)  {
           root.right.right=node;
       }
       index++;
   }

   /* -------------------------------------------------------------------
    * Each node in the tree is an object of this type. */
   protected static class TreeNode<E>  {
      private TreeNode<E> left,
                          right;
      private E info;

      public TreeNode(E info) { left = right = null; this.info = info; }
      public TreeNode<E> get_left()  { return left; }
      public TreeNode<E> get_right() { return right;}
      public E get_info() { return info;}
   }

   
   public static void main(String[] args)  {
      BinaryTree<Integer> bt = new BinaryTree<Integer>();

      bt.add(0);  
      bt.add(1);  
      bt.add(2);  
      bt.add(3);   
      bt.add(4);   
      bt.add(5);
      bt.add(6); 
      
      /*
       * The above tree will look like:
       *                            0 
       *                           /  \
       *                          /    \
       *                        1       2
       *                       / \      / \
       *                      /   \    /   \
       *                     3     4   5    6
       */
      System.out.println("The tree has " + bt.size() + " nodes.");
      System.out.println("Inorder using rec");
      bt.inorder();
      System.out.println("");
      System.out.println("Postorder using rec");
      bt.postorder();
      System.out.println("");
      System.out.println("Preorder using rec");
      bt.preorder();
      System.out.println("");
      System.out.println("Inorder using itr");
      bt.inorder_itr();
      System.out.println("");
      System.out.println("The tree size using itr has " + bt.size_itr() + " nodes.");
   }
}