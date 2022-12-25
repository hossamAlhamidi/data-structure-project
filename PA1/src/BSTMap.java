
public class BSTMap<K extends Comparable<K>, T> implements Map<K, T> {
    public  BSTNode<K,T> root ;
    public  BSTNode<K,T> current ;
    
    public BSTMap() {
    	root = null;
    	current = null;
    }
	@Override
	public int size() {
		BSTNode<K,T> temp = root;
		return getSize(temp);
	}
	private int getSize(BSTNode<K,T> n) {
		if(n==null)
			return 0;
		return 1 +  getSize(n.left) + getSize(n.right);
	}

	@Override
	public boolean full() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		root = null;

	}

	@Override
	public boolean update(K k, T e) {
		if(findKey(k)) {
			remove(current.key);
			boolean temp = insert(k,e);
			return temp;
		}
		else {
			return false;
		}
		
		/*BSTNode<K,T> temp = current;
		remove(current.key);
		if(insert(k,e))
		return true;
		else
		{
			insert(temp.key,temp.data);
			return false;
		}  */
	}

	@Override
	public Pair<Boolean, T> retrieve(K k) {
		BSTNode<K,T> p = current;
		if(findKey(k)) {
			Pair<Boolean,T> tmp = new Pair<Boolean,T>(true,current.data);
			current = p;
			return tmp;
		}
		else {
			current = p;
			Pair<Boolean,T> tmp = new Pair<Boolean,T>(false,null);
			return tmp;
		}
			
		
		
	}
	

	@Override
	public int nbKeyComp(K k) {
		if(root == null)
			return 0 ;
		
		int count=0;
		BSTNode<K,T> p = root;
		while(p!=null) {
			count++;
			if(p.key.equals(k)) {
				//break;
				return count;
				}
		   if(k.compareTo(p.key) < 0)
			   p=p.left;
		   else
			   p=p.right;
				
		}
		return count;
	}

	
	
	@Override
	public boolean insert(K k, T e) {
		BSTNode<K,T> p = current;
		BSTNode<K,T> q = current;
		
		if(findKey(k)) {
			current = q;
			return false;
		}
		p = new BSTNode<K,T>(k,e);
		if(root == null) {
			current = root = p;
			return true;}
		else {
			if(k.compareTo(current.key)>0)
				current.right = p;
			else
				current.left = p;
			current = p;
			return true;
		}
		
	}
	/*public boolean remove(K k) {
    
    K i = k;
    BSTNode<K,T> p = root; 
    BSTNode<K,T> q = null; 
    
    
    while (p!= null) { 
        if (i.compareTo(p.key)<0){
            q = p; 
            p = p.left; 
        }
        else 
            if (i.compareTo(p.key)>0){
                q = p; 
                p = p.right; 
            }
            else {
        
        if ((p.right!=null) && (p.left!=null)){
            BSTNode<K,T> s = p.right; 
            q = p; 
            
            while (s.left != null){
                q = s; 
                s = s.left;  
            }
            
            p.key = s.key; 
            p.data = s.data; 
            i = s.key; 
            p = s;   
        }  
        
        if (p.left!= null){
            p = p.left; 
        } 
        else {
            p = p.right; 
        }
        
        if (q ==null){
            root = q; 
        }
        
        else { 
            if (i.compareTo(q.key)<0){
                q.left = p ; 
                
            }else { 
                q.right = p; 
                
           
            }
            
        }
        
        current = root; 
        return true; 
            
    }
}
    return false; 
} */

	@Override

	  public boolean remove(K k) {
		K i = k;
        BSTNode<K,T> p = root; 
        BSTNode<K,T> q = null; 
        
        
        while (p!= null) { 
            if (i.compareTo(p.key)<0){
                q = p; 
                p = p.left; 
            }
            else 
                if (i.compareTo(p.key)>0){
                    q = p; 
                    p = p.right; 
                }
                else {
            
            if ((p.right!=null) && (p.left!=null)){
                BSTNode<K,T> s = p.right; 
                q = p; 
                
                while (s.left != null){
                    q = s; 
                    s = s.left;  
                }
                
                p.key = s.key; 
                p.data = s.data; 
                i = s.key; 
                p = s;   
            }  
            
            if (p.left!= null){
                p = p.left; 
            } 
            else {
                p = p.right; 
            }
            
            if (q ==null){
                root = q; 
            }
            
            else { 
                if (i.compareTo(q.key)<0){
                    q.left = p ; 
                    
                }else { 
                    q.right = p; 
                    
               
                }
                
            }
            
            current = root; 
            return true; 
                
        }
   }
        return false; 
	/*	Boolean removed = new Boolean(false);
		BSTNode<K,T> p;
		p = remove_aux(k, root, removed); 
		current = root = p;
		return removed;
	}
	
	private BSTNode<K, T> remove_aux(K key, BSTNode<K, T> p , Boolean flag) {
		BSTNode<K, T> q, child = null;
		if(p == null)
			return null;
		if(key.compareTo(p.key) < 0)
			p.left = remove_aux(key, p.left,flag); 
		else if(key.compareTo(p.key) > 0)
			p.right = remove_aux(key, p.right,flag); 
		else {
			 flag = true;
			if (p.left != null && p.right != null){ //two children
				q = find_min(p.right);
				p.key = q.key;
				p.data = q.data;
				p.right = remove_aux(q.key, p.right,flag);
			}
			else {
				if (p.right == null) //one child
					child = p.left;
				else if (p.left == null) //one child
					child = p.right;
				return child;
			}
		}
		return p;
	}
	private BSTNode<K,T> find_min(BSTNode<K,T> p){
	 if(p == null)
		 return null;
	while(p.left != null)
	{ p = p.left;
	}
	return p; */
	
	} 

	@Override
	public List<K> getKeys() {
		LinkedList<K> temp = new LinkedList<K>();
		if(root == null)
			return temp;
		if(root.left == null && root.right == null) {
			temp.insert(root.key);
			return temp;
		}
		else {
			BSTNode<K,T> p = root;
			orderKey(p,temp);
			return temp;
		}
		
		
	}
	private void orderKey(BSTNode<K,T> n, List<K> l) {
		if(n == null)
			return;
		orderKey(n.left,l);
		l.insert(n.key);
		orderKey(n.right,l);
		
	}
/*	public void display(BSTNode<K,T> n) {
		if(n == null)
			return;
		display(n.left);
		System.out.println(n.data);
		display(n.right);
	} */
	
 private boolean findKey(K tkey) {
		
		if(root == null)
			return false;
		BSTNode<K,T> p = root;
		BSTNode<K,T> q = root;
		while(p!=null) {
			q= p ;
			if(p.key.equals(tkey)) {
				current = p ;
				return true;
			}
			else if(p.key.compareTo(tkey)>0)
				p= p.left;
			else
				p=p.right;
		}
		current = q;
		return false;
			
	}

}
