package BST_A2;

public class BST_Node {
	  String data;
	  BST_Node left;
	  BST_Node right;
	  
	  BST_Node(String data){ this.data=data; }

	  // --- used for testing  ----------------------------------------------
	  //
	  // leave these 3 methods in, as is

	  public String getData(){ return data; }
	  public BST_Node getLeft(){ return left; }
	  public BST_Node getRight(){ return right; }

	  // --- end used for testing -------------------------------------------

	  
	  // --- fill in these methods ------------------------------------------
	  //
	  // at the moment, they are stubs returning false 
	  // or some appropriate "fake" value
	  //
	  // you make them work properly
	  // add the meat of correct implementation logic to them

	  // you MAY change the signatures if you wish...
	  // make the take more or different parameters
	  // have them return different types
	  //
	  // you may use recursive or iterative implementations

	  public boolean containsNode(String s){ 
		 if(data.compareTo(s) < 0) { //big one goes to the right
			  if(right != null) {
				  return right.containsNode(s);
			  } return false;
		  } else if(data.compareTo(s) > 0) { //small one goes to the left
			  if(left != null) {
				  return left.containsNode(s);
			  } return false;
		  } else { 
			  return true;
		  	}
		  }
	  
	  public boolean insertNode(String s){ 
		  if(data.compareTo(s) < 0) {
			  if(right == null) {
				  right = new BST_Node(s);
				  return true;
			  }
			  return right.insertNode(s);
		  } if(data.compareTo(s) > 0) {
			  if(left == null) {
				  left = new BST_Node(s);
				  return true;
			  }
			  return left.insertNode(s);
		  }
		  return false;
		  }
	  
	  public boolean removeNode(String s){
		  BST_Node toRemove = this;
		  BST_Node parent = this;
		  boolean isRight = false;
//		  if(!this.containsNode(s)) {
//			  return false;
//		  }
		  while(toRemove.data != s) {
			 if(toRemove.data.compareTo(s) < 0) {
				 isRight = true;
	  		  if (toRemove.right != null) {
	  			  parent = toRemove;
	  			  toRemove = toRemove.right;
	  		  }
		  } else if(toRemove.data.compareTo(s) > 0) {
			  isRight = false;
			  if(toRemove.left != null) {
				  parent = toRemove;
				  toRemove = toRemove.left;
			  }
		  	}
		  }
		  while(toRemove != null) {
			  if (toRemove.left == null && toRemove.right == null) { //leaf
				  if(isRight) {
					  parent.right = null;
				  } else {
					  parent.left = null;
				  }
			  } else if (toRemove.left == null && toRemove.right != null) { //one child right
				  if(isRight) {
					  parent.right = toRemove.right;
				  } else {
					  parent.left = toRemove.right;
				  }
			  } else if (toRemove.left != null && toRemove.right == null) { //one child left
				  if(isRight) {
					  parent.right = toRemove.left;
				  } else {
					  parent.left = toRemove.left;
				  }
			  } else if(toRemove.left != null && toRemove.right != null) { //two children
				  BST_Node nextNode = toRemove.right.findMin(); //change which one is next to move
				  String removeData = nextNode.data;
				  removeNode(nextNode.data);
				  toRemove.data = removeData;
			  }
		  		return true;
			}
		  	return false; 
		  }
	  
	  public BST_Node findMin(){ 
		  if(left != null) {
			  return left.findMin();
		  }
		  return this; 
		  }
	  
	  public BST_Node findMax(){
		  if(right != null) {
			  return right.findMax();
		  }
		  return this; 
		  }
	  
	  public int getHeight(){
		  if(left == null && right == null) {
			  return 0; 
		  } else if (left != null && right == null) {
			  return left.getHeight() + 1;
		  } else if (left == null && right != null) {
			  return right.getHeight() + 1;
		  } else if (left != null && right != null) {
			  if(left.getHeight() > right.getHeight()) {
				  return left.getHeight() + 1;
			  } 
			  return right.getHeight() + 1;
		  }
		  return 0;
		  }


	  // --- end fill in these methods --------------------------------------
//	  public BST_Node getParent(BST_Node node, String s) {
//		  if(node == null) {
//			  return null;
//		  }
//		  if(node.containsNode(s)) {
//			  return null;
//		  }
//		  BST_Node parent = null;
//		  while (node != null) {
//			  if(s.compareTo(node.data) > 0) {
//				  parent = node;
//				  node = parent.left;
//			  } else if(s.compareTo(node.data) < 0) {
//				  parent = node;
//				  node = parent.right;
//			  } else {
//				  parent = node;
//				  break;
//			  }
//		  }
//		  return parent;
//	  }

	  // --------------------------------------------------------------------
	  // you may add any other methods you want to get the job done
	  // --------------------------------------------------------------------
	  
	  public String toString(){
	    return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")
	            +",Right: "+((this.right!=null)?right.data:"null");
	  }
	}
