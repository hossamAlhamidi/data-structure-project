public class DynamicArray<T> implements Array<T> {
	private T[] arr;
	private int nb;
	private int maxSize;
    
	
	public DynamicArray() {
		
		arr = (T[]) new Object[0];
		nb = 0;
		maxSize = 0;
	}
		
	public void display() {
		System.out.println("element is ");
		for(int i = 0 ; i<nb ; i++) {
			System.out.print(arr[i] +"," );
		}
	}
	@Override
	public int size() {
	
		return nb;
	}

	@Override
	public int capacity() {
		// TODO Auto-generated method stub
		return maxSize;
	}

	@Override
	public T get(int i) throws ArrayIndexOutOfBoundsException {
		if(i>=nb)
				throw new ArrayIndexOutOfBoundsException();
		return arr[i];
	}

	@Override
	public void set(int i, T e) throws ArrayIndexOutOfBoundsException {
		if(i>=nb)
			throw new ArrayIndexOutOfBoundsException();
		arr[i] = e;

	}

	@Override
	public void add(T e) {
		if(maxSize==0) {
			arr = (T[]) new Object[1];
		    arr[nb++]=e;
		    maxSize++;
		}
		else if(nb<maxSize) {
			
			arr[nb++] = e;
		}
		else {
			T[] temp = (T[]) new Object[maxSize];
			for(int i = 0 ; i<nb ; i++) {
				temp[i] = arr[i];
			}
			arr = (T[]) new Object[maxSize*2];
			maxSize = maxSize*2;
			for(int i = 0 ; i<nb;i++) {
				arr[i] = temp[i];
			}
			arr[nb++] = e;
			
		}
		}
	
	

}
