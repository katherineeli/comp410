package BST_A2;

public class BST implements BST_Interface {
	  public BST_Node root;
	  int size;
	  
	  public BST(){ size=0; root=null; }
	  
	  public boolean insert(String s) {
		  if(size == 0) {
			  root = new BST_Node(s);
			  size++;
			  return true;
		  }
		  if (this.contains(s)) {
			  return false;
		  } else if (root.insertNode(s)) {
			  size++;
			  return true;
		  }
		  return false;
	  }
	  public boolean remove(String s) {
		  if(size == 0) {
			  return false;
		  }
		  if(this.empty() || !this.contains(s)) {
			  return false;
		  } else if (root.getData().compareTo(s) == 0) {
			  if(root.left == null && root.right == null) {
				  root = null;
			  }
			  else if(root.left == null && root.right != null) { //1 child right
				  root = root.right;
			  }
			  else if(root.left != null && root.right == null) { //1 child left
				  root = root.left;
			  }
			  else if(root.left != null && root.right != null) { //2 children
				  BST_Node temp = root.right.findMin(); //change which one is next to move
				  root.removeNode(temp.data);
				  root.data = temp.data; //copy to current
			  }
			  size--;
			  return true;
		  } else {
			  size--;
			  return root.removeNode(s);
		  }
	  }
	  public String findMin() {
		  if(size == 0) {
			  return null;
		  }
		  return root.findMin().getData();
	  }
	  public String findMax() {
		  if(size == 0) {
			  return null;
		  }
		  return root.findMax().getData();
	  }
	  public boolean empty() {
		  if(size == 0) {
			  return true;
		  }
		  return false;
	  }
	  public boolean contains(String s) {
		  if (size == 0) {
			  return false;
		  }
		  return root.containsNode(s);
	  }
	  public int size() {
		  return size;
	  }
	  public int height() {
		  if (empty()) {
			  return -1;
		  }
		  return root.getHeight();
	  }
	  @Override
	  //used for testing, please leave as is
	  public BST_Node getRoot(){ return root; }

	}

