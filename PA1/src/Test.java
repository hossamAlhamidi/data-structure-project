
public class Test {
    
	public static void main(String[] args) {
		
		
	
		
		
		// TODO Auto-generated method stub
		 
		  DataFrameImp df = new DataFrameImp();
		System.out.println(  df.loadCSV("Vaccination.csv"));
		
		System.out.println("col: " + df.getNbCols());
		System.out.println("row: "+ df.getNbRows());   
		
		COVID19Vaccination c = new COVID19VaccinationImp();
		c.getPeopleFullyVaccinated("Italy");
		
		
		
	//	System.out.println(  df.loadCSV("only.txt"));
		//  df.loadCSV("ss.txt");
			/*	BSTMap<Integer,Integer> b = new BSTMap<Integer,Integer>();
               
               b.insert(10, 1);
               b.insert(20, 2);
               b.insert(30, 3);
               b.insert(40, 4);
               System.out.println(b.retrieve(30).second);
              b.update(30, 5);
              
            
              System.out.println(b.retrieve(30).second);
         //     System.out.println(b.root.data);
          // System.out.println(  b.nbKeyComp(40));
               
               LinkedList<Integer> l = (LinkedList<Integer>)b.getKeys();
          l.findFirst();
           //  System.out.println(  l.retrieve());
               while(!l.last()) {
            	   System.out.println(l.retrieve());
            	   l.findNext();
               }
               System.out.println(l.retrieve());
         //   while(!b.getKeys().last()) {
          //  	System.out.println(b.getKeys().retrieve());
           // 	b.getKeys().findNext();
           // }
             // System.out.println( b.size());
              // System.out.println(b.findKey(40));
            //   b.display(b.root); */
		
	/*	 DynamicArray<String> d = new  DynamicArray<String>();
		 System.out.println("size:"+d.size() +"  capacity "+d.capacity());
		 
		 
		 d.add("A");
		 System.out.println("size:"+d.size() +"  capacity "+d.capacity());
		 d.add("B");
		 System.out.println("size:"+d.size() +"  capacity "+d.capacity());
		 d.add("C");
		 System.out.println("size:"+d.size() +"  capacity "+d.capacity());
		 d.add("D");
		 System.out.println("size:"+d.size() +"  capacity "+d.capacity());
		 d.add("E");
		 System.out.println("size:"+d.size() +"  capacity "+d.capacity());
		 d.add("F");
		 System.out.println("size:"+d.size() +"  capacity "+d.capacity());
		 d.display(); // only to make sure   */
		 
		// d.set(0, "Z");
		 
	//	 Class c = Class.
		 
	}

}
