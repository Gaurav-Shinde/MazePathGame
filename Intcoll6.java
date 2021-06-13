
import java.util.*;
import java.io.*;
public class Intcoll6{

	private int howmany;
    private btNode c;
    
    
    
  //Inner class btNode for Binary tree creation
  	private static class btNode{

  		private int info;
  		private btNode left, right;
  		
  		/*
  		Constructor 1
  		@I none
  		@O none
  		@D creates an object of the btNode class with initialized fields
  		*/
  		public btNode()  {  info=0; left=right=null;  }
  		
  		/*
  		Constructor
  		@I int i, bt Node lt, btNode rt
  		@O none
  		@D Creates an object of btNode class with parameter initialized fields
  		*/
  		public btNode(int i, btNode lt, btNode rt)  {
  				   info=i; left=lt; right=rt;
  		}
  	}
    
  	/*
	CONSTRUCTOR 1
	@I none
	@O none
	@D Creates object of Intcoll6 class and initializes its fields
	*/
  	public Intcoll6() {
        c = null;
        howmany = 0;
        
    }
    
  	/*
	CONSTRUCTOR 2
	@I int i (placeholder)
	@O none
	@D Creates object of Intcoll6 class and initializes its fields
	*/
  	public Intcoll6(int i){
        c = null;
        howmany = 0;
    }
    
  	/*
	insert
	@D: The insert method iterates through the BST to search for i. The traversal
		occurs through a while loop and contains a prev btNode and p btNode.
		The prev is set to null and p to c. As i is checked for, prev holds the position
		of the previous btNode to p. If i is not found, and p is null, then p is set
		to new btNode with info as i and set to the appropriate subtree of prev.
	@I int i
	@O none
	@D i is inserted in BT if absent
	*/
    public void insert(int i){
        if(i > 0){
            btNode pred = null;
            btNode p = c;
            while ((p != null) && (p.info != i)){ 
                pred = p;
            if (p.info > i) p = p.left;
                else { p =p.right;
                }
            }
            if (p == null){
                howmany++;
                p = new btNode(i, null, null);
                if (pred != null)
                {
                    if (pred.info > i){ pred.left = p;}
                    else{ pred.right = p;}
                
                }
                else { c = p;}
                }
            }            
            
        }    

    /*
	Omit 
	@S: The omit method has a general check for null BST and 3 scenarios.
		In the first scenario, it is checked whether the target is a leaf p or root p
		in a simple tree. Use inorder traversal to find the target, and if a leaf p, meaning 
		both its subtrees are null, then just set its predecessor subtree to null. In the second scenario,
		our target has one subtree. Here we want to traverse its subtrees to find element that is closest to that value,
		which should also be a leaf p. Once it is found, we will set the predecessor of our "closest value"'s respective
		subtree to null, and set that value to our target, which is to be replaced. In scenario 3, our target has 2 subtrees,
		and the process is similar to that of scenario 2. However for scenario 3, you will have to get the "closest value" 
		from both sides and compute the smallest difference between the target and either closest value. Then you will replace
		the target with the appropriate p value.

    //TA Qs
    1. 
	@I int i
	@O none
	@D Omits i from BT if present
	*/
    public void omit(int i){
        btNode p = c;
        btNode pred = null;
        
       if(i>0){
        //find target node
          while ((p != null) && (p.info != i)) {
              pred = p;
              if (i > p.info) {
                  p = p.right;
              } else {
                  p = p.left;
              }
          }
          if (p != null) {
            
            howmany--;
              
              //left subtree, but no right //ask TA
              if ((p.left != null) && (p.right == null)) {
                  /*btNode A = p.left;
                  btNode B = p;
                  while (A.right != null) {
                      B = A;
                      A = A.right;
                  }
                  p.info = A.info;
                  B.right = A.left;*/

                  //root
                  if(pred==null){
                        p = p.left;
                  }else{
                        if(pred.info > p.info){pred.left = p.left;}else{pred.right = p.left;}
                  }
              } 
              //right subtree, but no left
              else if ((p.left == null) && (p.right != null)) {
                  /*btNode A = p.right;
                  btNode B = p;
                  while (A.left != null) {
                      B = A;
                      A = A.left;
                  }
                  p.info = A.info;
                  B.left = A.right;*/


                  if(pred==null){
                        p = p.right;
                  }else{
                        if(pred.info > p.info){pred.left = p.right;}else{pred.right = p.right;}
                  }
              } 
              //no subtrees
              else if ((p.left == null) && (p.right == null)) {
                  if (pred == null) {
                      c = null;
                  } else if (p.info < pred.info) {
                      pred.left = null;
                  } else {
                      pred.right = null;
                  }
              } 
              //left and right subtree
              else if ((p.left != null) && (p.right != null)) {
                  btNode A = p.right;
                  btNode B = p;
                  btNode C = p.left;
                  btNode D = p;
                    /*
                    Just finding best element in right subtree can make
                    tree uneven after prolonged omitting, instead, we need to
                    check compare best elements for each subtree tree and chose
                    one with least deviation from target value.
                    */
                    while (A.left != null) {
                        B = A;
                        A = A.left;
                    }
                    while (C.right != null) {
                        D = C;
                        C = C.right;
                    }

                    int diffC = p.info - C.info;
                    int diffA = A.info - p.info;
                    if(diffA <= diffC){
                        //when the p.right has no left nodes
                      if (B == p) {
                          p.info = A.info;
                          B.right = A.right;
                      } 
                      //when p.right has 1 or more left nodes
                      else {
                          p.info = A.info;
                          B.left = A.right;
                      }
                    }else{
                        if(D == p){
                            p.info = C.info;
                            D.left = C.left;
                        }else{
                            p.info = C.info;
                            D.right = C.left;
                        }
                    }
                }
          }  
        }
    }
    
    /*
	belongs 
	//preorder traverse non-recursive
	@I int i
	@O Boolean value
	@D Returns true if i belongs to BT, else false.
	*/
   public boolean belongs(int i)
   {
      btNode p=c;
      while ((p!=null)&&(p.info!=i)) 
      {
         if (p.info>i) p=p.left;
         else p=p.right;
      }
      return (p!=null); 
   }
   
   /*
	copyTree
	@S The copyTree method uses preorder recursive traversal to print elements
		of btNode.
	@I btNode t
	@O btNode A
	@D Returns a copy of btNode t as btNode A where A doesn't refer to same pointer as t.
	*/
   private static btNode copytree(btNode t){
       btNode root = null;
       if (t != null){
           root = new btNode();
           root.info = t.info;
           root.left = copytree(t.left);
           root.right = copytree(t.right);
       }
       return root;
   }
   
   /*
	copy
	@S Uses copyTree method to create a new tree copy of obj.c and assigns it to this.c.
		As well as assigning this.howmany to obj.howmany.
	@I Intcoll6 obj
	@O none
	@D Copies all fields of obj into this
	*/
   public void copy(Intcoll6 obj){
       if (this != obj){
           howmany = obj.howmany;
           c = copytree(obj.c);
       }
   }

   /*
	toArray
	@S The toArray method uses inorder recursion to initialize elements of btNode into an array.
		For each time a leaf p is met, info is stored and index increases by 1 to capture next element...
		and so on.
	@I btNode t, int[] array, int index
	@O int numNodes
	@D Returns number of elements in array, but more importantly, stores BT contents in sorted order
		from smallest to greatest in an array. Later used for equals method.
	*/
   private static int toarray(btNode t, int[] a, int i)
   {
      int num_nodes=0;
      if (t!=null)
      {
         num_nodes=toarray(t.left, a, i);
         a[num_nodes+i]=t.info;   
         num_nodes=num_nodes+1+toarray(t.right, a, num_nodes+i+1);
      }
      return num_nodes;
   } 
   
   /*
	equals
	@S equals method uses toArray method to create two arrays containing elements of 
		different btNode and then each individual element is checked for equality.
	@I Intcoll6 obj
	@O Boolean result
	@D Returns true if elements of this.c are present in obj.c and vice versa. Else returns false.
	*/
   public boolean equals(Intcoll6 obj)
   {
      int j = 0; boolean result  = (howmany==obj.howmany);
      if (result)
      { 
         int[] a=new int[howmany]; 
         int[] b=new int[howmany];
         toarray(c, a, 0); toarray(obj.c, b, 0);

         j=0;
         while ((result) && (j<howmany))
         {
            result=(a[j]==b[j]); j++;
         }
      }
      return result;
   }

   /*
	printTree
	@S Uses inorder recursive traversal to print elements of btNode t in sorted
		order from least to greatest.
	@I btNode t
	@O none
	@D Prints elements of btNode t
	*/
   private static void printtree(btNode t)
   {
      if (t!=null)
      {
          printtree(t.left);
          System.out.println(t.info);
          printtree(t.right);
      }
   }

   /*
	print
	@I none
	@O none
	@D Uses printTree to print elements c
	*/
   public void print()
   {
      printtree(c);
   }
   
   /*
	gethowmany
	@I none
	@O int howmany
	@D Returns # of elements in c
	*/
   public int get_howmany() {return howmany;}

}
