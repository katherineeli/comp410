package SPLT_A4;

//import BST_A2.BST_Node;

public class BST_Node {
	String data;
	BST_Node left;
	BST_Node right;
	BST_Node par; //parent...not necessarily required, but can be useful in splay tree
	boolean justMade; //could be helpful if you change some of the return types on your BST_Node insert.
	//I personally use it to indicate to my SPLT insert whether or not we increment size.

	BST_Node(String data){ 
		this.data=data;
		this.justMade=true;
	}

	BST_Node(String data, BST_Node left,BST_Node right,BST_Node par){ //feel free to modify this constructor to suit your needs
		this.data=data;
		this.left=left;
		this.right=right;
		this.par=par;
		this.justMade=true;
	}

	// --- used for testing  ----------------------------------------------
	//
	// leave these 3 methods in, as is (meaning also make sure they do in fact return data,left,right respectively)

	public String getData(){ return data; }
	public BST_Node getLeft(){ return left; }
	public BST_Node getRight(){ return right; }

	// --- end used for testing -------------------------------------------
	public BST_Node containsNode(String s){ 
		if(data.compareTo(s) < 0) { //big one goes to the right
			if(right != null) {
				return right.containsNode(s);
			} return this;
		} else if(data.compareTo(s) > 0) { //small one goes to the left
			if(left != null) {
				return left.containsNode(s);
			} return this;
		} else { 
			return this;
		}
	}
	public BST_Node insertNode(String s){ 
		if(data.compareTo(s) < 0) {
			if(right == null) {
				right = new BST_Node(s, null, null, this); //use new constructor
				return right;
			}
			return right.insertNode(s);
		} else if(data.compareTo(s) > 0) {
			if(left == null) {
				left = new BST_Node(s, null, null, this); 
				return left;
			}
			return left.insertNode(s);
		}
		return null;
	}

	public boolean removeNode(String s){ 
		if (data == null) return false;
		if (data.contentEquals(s)) {
			if(left != null) {
				data = left.findMax().data;
				left.removeNode(data);
				if (left.data == null) {
					left = null;
				}
			} else if(right != null) {
				data = right.findMin().data;
				right.removeNode(data);
				if (right.data == null) {
					right = null;
				}
			} else {
				data = null;
				return true;
			}
		}
		while(data != s) {
			if(data.compareTo(s) < 0) {
				if (right == null) return false;
				if (!right.removeNode(s)) return false;
				if (right.data == null) {
					right = null;
					return true;
				}
			} else if(data.compareTo(s) > 0) {
				if (left == null) return false;
				if (!left.removeNode(s)) return false;
				if (left.data == null) {
					left = null;
					return true;
				}
			}
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


	// --- Some example methods that could be helpful ------------------------------------------
	//
	// add the meat of correct implementation logic to them if you wish

	// you MAY change the signatures if you wish...names too (we will not grade on delegation for this assignment)
	// make them take more or different parameters
	// have them return different types
	//
	// you may use recursive or iterative implementations

	/*
	  public BST_Node containsNode(String s){ return false; } //note: I personally find it easiest to make this return a Node,(that being the node splayed to root), you are however free to do what you wish.
	  public BST_Node insertNode(String s){ return false; } //Really same logic as above note
	  public boolean removeNode(String s){ return false; } //I personal do not use the removeNode internal method in my impl since it is rather easily done in SPLT, feel free to try to delegate this out, however we do not "remove" like we do in BST
	  public BST_Node findMin(){ return left; } 
	  public BST_Node findMax(){ return right; }
	  public int getHeight(){ return 0; }

	  private void splay(BST_Node toSplay) { return false; } //you could have this return or take in whatever you want..so long as it will do the job internally. As a caller of SPLT functions, I should really have no idea if you are "splaying or not"
	                        //I of course, will be checking with tests and by eye to make sure you are indeed splaying
	                        //Pro tip: Making individual methods for rotateLeft and rotateRight might be a good idea!
	 */

	// --- end example methods --------------------------------------




	// --------------------------------------------------------------------
	// you may add any other methods you want to get the job done
	// --------------------------------------------------------------------


}
