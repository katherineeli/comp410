package LinkedList_A1;

public class LinkedListImpl implements LIST_Interface {
	Node sentinel;
	
	public LinkedListImpl() {
		sentinel = new Node(0);
	}

	@Override
	public boolean insert(double elt, int index) {
		Node inserted = new Node(elt);
		if (isEmpty() && index == 0) { // empty list
			sentinel.next = inserted; //link to inserted
			inserted.prev = sentinel; //link to beginning
			inserted.next = sentinel; // link to end
			sentinel.prev = inserted; //link end to inserted
			return true;
		} else if (isEmpty()) {
			return false;
		}
		if (index == size()) { //insert at end
			Node current = getNode(index - 1);
			current.next = inserted; //link to inserted
			inserted.prev = current; //link inserted to current
			inserted.next = sentinel; //link to end
			sentinel.prev = inserted; //link end to inserted
			return true;
		} else { //insert in middle
			Node current = getNode(index);
			if (current == null) {
				return false;
			} 
			Node previous = current.prev;
			previous.next = inserted; //link to inserted
			inserted.prev = previous; //link back
			current.prev = inserted; //link to inserted
			inserted.next = current; //link back
		}
		return true;
	}

	@Override
	public boolean remove(int index) {
		Node current = getNode(index);
		if (current == null) { //empty
			return false;
		} 
		Node previous = current.prev;
		if (index == size() - 1) { //end of list
			sentinel.prev = previous;
			previous.next = sentinel;
			return true;
		} //anywhere else in the list
		Node next = current.next;
		previous.next = next;
		next.prev = previous;
		return true;
	}

	@Override
	public double get(int index) {
		Node temp = getNode(index);
		if(temp == null) {
			return Double.NaN;
		} else {
			return temp.data;
		}
	}

	@Override
	public int size() {
		int size = 0;
		Node temp = new Node(0);
		temp = sentinel;
		if (temp.next!=null) { //not empty
			while(temp.next!=sentinel) { //while not the end
				temp = temp.next; //move on
				size++;
			}
		}
		return size;
	}

	@Override
	public boolean isEmpty() {
		if (size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void clear() {
		sentinel.next = sentinel.prev = null;
	}
	
	public Node getRoot() {
		return sentinel;
	}
	
	public Node getNode(int i) {
		if (isEmpty() || i < 0 || i >= size()) {
			return null;
		}
		Node temp = sentinel.next;
		for (int n = 0; n < i; n++) {
			temp = temp.next; //move to next one until reach given index
		}
		return temp;
	}
}
