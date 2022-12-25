public class BSTSet<K extends Comparable<K>> implements Set<K> {
   public  BSTNode1<K> root;
   public  BSTNode1<K> current;
   
   public BSTSet() {
	   root = null;
	   current = null;
   }
	@Override
	public int size() {
		BSTNode1<K> temp = root;
		return getSize(temp);
	}
	private int getSize(BSTNode1<K> n) {
		if(n==null)
			return 0;
		return 1 +  getSize(n.left) + getSize(n.right);
	}

	@Override
	public boolean full() {
		
		return false;
	}

	@Override
	public void clear() {
		root = null;

	}

	@Override
	public boolean find(K k) {
		if(root == null)
			return false;
		BSTNode1<K> p = root;
		BSTNode1<K> q = root;
		while(p!=null) {
			q= p ;
			if(p.key.equals(k)) {
				current = p ;
				return true;
			}
			else if(p.key.compareTo(k)>0)
				p= p.left;
			else
				p=p.right;
		}
		current = q;
		return false;
	}

	@Override
	public int nbKeyComp(K k) {
		if(root == null)
			return 0 ;
		
		int count=0;
		BSTNode1<K> p = root;
		while(p!=null) {
			count++;
			if(p.key.equals(k)) {
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
	public boolean insert(K k) {
		BSTNode1<K> p = current;
		BSTNode1<K> q = current;
		
		if(find(k)) {
			current = q;
			return false;
		}
		p = new BSTNode1<K>(k);
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

	@Override
	public boolean remove(K k) {
		K i = k;
        BSTNode1<K> p = root; 
        BSTNode1<K> q = null; 
        
        
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
                BSTNode1<K> s = p.right; 
                q = p; 
                
                while (s.left != null){
                    q = s; 
                    s = s.left;  
                }
                
                p.key = s.key; 
              
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
        
        
		/*Boolean removed = new Boolean(false);
		BSTNode1<K> p;
		p = remove_aux(k, root, removed); 
		current = root = p;
		return removed; */
	}
	
	
	/*private BSTNode1<K> remove_aux(K key, BSTNode1<K> p , Boolean flag) {
		BSTNode1<K> q, child = null;
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
	private BSTNode1<K> find_min(BSTNode1<K> p){
		 if(p == null)
			 return null;
		while(p.left != null)
		{ p = p.left;
		}
		return p;
		
		}  */

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
			BSTNode1<K> p = root;
			orderKey(p,temp);
			return temp;
		}
		
	}
	private void orderKey(BSTNode1<K> n, List<K> l) {
		if(n == null)
			return;
		orderKey(n.left,l);
		l.insert(n.key);
		orderKey(n.right,l);
		
	}
	
	public void display(BSTNode1<K> n) {
		if(n == null)
			return;
		display(n.left);
		System.out.println(n.key);
		display(n.right);
	}

}
