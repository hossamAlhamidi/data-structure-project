
public class BSTNode<K extends Comparable<K>,T >  {
	public T data;
	public K key;
	public BSTNode<K,T> left;
	public BSTNode<K,T> right;
	
	public BSTNode(K k, T val) 
	{ key = k; data = val; left = right = null;
	}
	public BSTNode(K k, T val, BSTNode<K,T> l, BSTNode<K,T> r)
{ key = k; data = val; left = l; right = r;
	}
	

}
