package SPLT_A4;

public class SPLT implements SPLT_Interface{
	private BST_Node root;
	private int size;

	public SPLT() {
		this.size = 0;
		this.root = null;
	} 

	public BST_Node getRoot() { //please keep this in here! I need your root node to test your tree!
		return root;
	}

	@Override
	public void insert(String s) {
		if(empty()) {
			root = new BST_Node(s);
			size++;
		} else if(!contains(s)) {
			size++;
			BST_Node inserted = root.insertNode(s);
			splay(inserted);
		}
	}

	@Override
	public void remove(String s) {
		boolean found = contains(s);
		if(found) {
			if(root.data.equals(s) && size == 1) {
				root = null;
				size--;
			} else {
				size--;
				root.removeNode(s);
			}
		}
	}

	@Override
	public String findMin() {
		if(empty()) {
			return null;
		} else {
			splay(root.findMin());
			return root.findMin().data;
		}
	}

	@Override
	public String findMax() {
		if (empty()) {
			return null;
		} else {
			splay(root.findMax());
			return root.findMax().data;
		}
	}

	@Override
	public boolean empty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean contains(String s) {
		if (empty()) {
			return false;
		}
		BST_Node lastVisited = root.containsNode(s);
		splay(lastVisited);
		if (lastVisited.data.equals(s)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int height() {
		if(empty()) {
			return -1;
		} else {
			return root.getHeight();
		}
	}
	public void splay(BST_Node node) {
//		if (node == null) return;
		while(node != root) {
			BST_Node parent = node.par;
			if(parent == null) return;
			// zig
			if (parent.equals(root)) {
				if (node.equals(parent.left)) { // single rotation left
					rotateRight(parent);
				} else if (node.equals(parent.right)) { // single rotation right
					rotateLeft(parent);
				}
			} else {
				BST_Node grandparent = parent.par;
				// zig-zig (LL)
				if (node.equals(parent.left) && parent.equals(grandparent.left)) {
					rotateRight(grandparent); // moves to right child (above parent) 
					rotateRight(parent); // moves to right child (below grandparent) 
				}
				// zag-zag (RR)
				if (node.equals(parent.right) && parent.equals(grandparent.right)) {
					rotateLeft(grandparent); // moves to left child (above parent)
					rotateLeft(parent); // moves to left child (below grandparent)
				}
				// zig-zag (RL)
				if (node.equals(parent.right) && parent.equals(grandparent.left)) {
					rotateLeft(parent); // moves parent to left child
					rotateRight(grandparent); // moves grandparent to right child
				}
				// zag-zig (LR)
				if (node.equals(parent.left)&& parent.equals(grandparent.right)) {
					rotateRight(parent); // moves parent to right child 
					rotateLeft(grandparent); // moves grandparent to left child 
				}
			}
		}
	}
	
	public void rotateLeft(BST_Node node) { //move node to left child 
		BST_Node temp = node.right;
		temp.par = node.par;
		node.right = temp.left;
		if (node.right != null) {
			node.right.par = node;
		}
		temp.left = node;
		node.par = temp;
		if(temp.par != null) {
			if (node == temp.par.left) {
				temp.par.left = temp;
			} else {
				temp.par.right = temp;
			}
		} else {
			root = temp;
		}
	}
	
	public void rotateRight(BST_Node node) { //move node to right child 
		BST_Node temp = node.left;
		temp.par = node.par;
		node.left = temp.right;
		if (node.left != null) {
			node.left.par = node;
		}
		temp.right = node;
		node.par = temp;
		if(temp.par != null) {
			if (node == temp.par.left) {
				temp.par.left = temp;
			} else {
				temp.par.right = temp;
			}
		} else {
			root = temp;
		}
	}

}
