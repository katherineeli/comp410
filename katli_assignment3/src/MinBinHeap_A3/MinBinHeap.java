package MinBinHeap_A3;

public class MinBinHeap implements Heap_Interface {
	private EntryPair[] array; //load this array
	private int size;
	private static final int arraySize = 10000; //Everything in the array will initially be null. This is ok! Just build out from array[1]

	public MinBinHeap() {
		this.array = new EntryPair[arraySize];
		array[0] = new EntryPair(null, -100000); //0th will be unused for simplicity of child/parent computations...the book/animation page both do this.
//		size = size();
	}

	@Override
	public void delMin() {
		array[1] = array[size()];
		array[size()] = null;
		bubbleDown(1); //reorder through array 
//		size--;
	}

	@Override
	public int size() {
		size = 0;
		int i = 1; //index 0 unused
		while (array[i] != null) {
			size++;
			i++;
		}
		return size;
	}

	@Override
	public void insert(EntryPair entry) {
		if(size() == 0) {
			this.array[1] = entry;
//			size++;
		} else {
			int index = size() + 1;
			array[index] = entry; //append to end
			//check priority of the new entry and reorder 
			while(index/2 > 0) {
				if (array[index].getPriority() < array[index/2].getPriority()) { //if parent is bigger, swap
					EntryPair temp = array[index/2];
					array[index/2] = array[index];
					array[index] = temp;
					index = index/2;
				} else {
					break;
				}
			}
		}
	}

	@Override
	public EntryPair getMin() {
		if(size() == 0) {
			return null;
		} else {
			return array[1];
		}
	}

	@Override
	public void build(EntryPair[] entries) {
		//put array into entries
		for (int i = 0; i < entries.length; i++) {
			array[i+1] = entries[i];
		}
		int index = size() / 2;
		while(index > 0) {
			bubbleDown(index);
			index--;
		}
	}
	
	public void bubbleDown(int index) { //bubble down: check heap order
		while (index * 2 <= size()) {
			int min = getMinChild(index); //find which child is smaller 
			if (array[index].getPriority() > array[min].getPriority()) { // if parent is bigger, swap
				EntryPair temp = array[index];
				array[index] = array[min];
				array[min] = temp;
				index = min;
			} else { //end if order is correct
				break;
			}
		}
	}
	
	public int getMinChild(int index) {
		if ((index * 2 + 1) > size()) { //last child
			return index * 2;
		} else if (array[index * 2].getPriority() < array[index * 2 + 1].getPriority()) { //left child smaller
			return index * 2;
		} else { //right child smaller
			return index * 2 + 1;
		}
	}
	
	//Please do not remove or modify this method! Used to test your entire Heap.
	@Override
	public EntryPair[] getHeap() { 
		return this.array;
	}
}
