public class LinkedList<T> implements List<T> {
      private Node<T> head;
      private Node<T> current;
      
      public LinkedList() {
    	  head = null;
    	  current = null;
      }
	@Override
	public boolean empty() {
		// TODO Auto-generated method stub
		return head==null;
	}

	@Override
	public boolean full() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		int size = 0;
		Node<T> p = head;
		while(p!=null) {
			size++;
			p=p.next;
		}
		return size;
	}

	@Override
	public void findFirst() {
		// TODO Auto-generated method stub
		current = head;

	}

	@Override
	public void findNext() {
		// TODO Auto-generated method stub
		current = current.next;

	}

	@Override
	public boolean last() {
		// TODO Auto-generated method stub
		return current.next == null;
	}

	@Override
	public T retrieve() {
		// TODO Auto-generated method stub
		return current.data;
	}

	@Override
	public void update(T e) {
		// TODO Auto-generated method stub
        current.data = e;
	}

	@Override
	public void insert(T e) {
		Node<T> tmp; 
		if (empty())
		 current = head = new Node<T>(e);
		
		else {
			tmp = current.next;
			current.next = new Node<T>(e);
			current = current.next; 
			current.next = tmp;
		}

	}

	@Override
	public void remove() {
		if (current == head)
		 head = head.next;
		else {
			Node<T> tmp = head;
			while (tmp.next != current) 
				tmp = tmp.next;
			tmp.next = current.next;
			}
		if (current.next == null) 
			current = head;
		else
			current = current.next;

	}

}
