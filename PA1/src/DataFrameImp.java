import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class DataFrameImp implements DataFrame {

	public Map<String,Pair<Integer,Class<?>>> dataFrameCol; //type
	public Map<String,Pair<Integer,Array<?>>> dataFrameData;
	private int nbCol;
	public Set<String> columnName;  // my_column  only name
    public	Array<String> arrColumnName;
	
	public DataFrameImp() {
		dataFrameCol = new BSTMap<String , Pair<Integer,Class<?>>>();
		dataFrameData = new BSTMap<String,Pair<Integer,Array<?>>>();
		nbCol = -1;
		columnName = new BSTSet<String>();
		arrColumnName = new DynamicArray<String>();
	}
	
	@Override
	public int getNbCols() {
		
		return dataFrameCol.size();
	}

	@Override
	public boolean addCol(String colName, Class<?> colType) {
		boolean temp = false;
		Pair<Integer,Array<?>> data = new Pair<Integer,Array<?>>(nbCol+1 , new DynamicArray<Object>());
		Pair<Integer,Class<?>> type = new Pair<Integer,Class<?>>(nbCol+1 , colType);
		
		
		temp = dataFrameCol.insert(colName, type);
		if(temp) {
			nbCol++;
			dataFrameData.insert(colName, data);
			columnName.insert(colName);
			arrColumnName.add(colName);
			
			
		}
		return temp;
	}

	@Override
	public boolean addCol(String colName, Class<?> colType, Array<Object> col) {
		if(dataFrameCol.size()>0) {
			if(col.size()!= getNbRows())
				return false;	
		}
		
	   //  Array<Integer> aa = new DynamicArray<Integer>() ;
	   // aa.add((Integer)col.get(0)); 
	//	Array<Integer> a = Arrays.copyOf(col, col.size(), Integer[].class);
		boolean temp = false;
		Pair<Integer,Array<?>> data = new Pair<Integer,Array<?>>(nbCol+1 , col);
		Pair<Integer,Class<?>> type = new Pair<Integer,Class<?>>(nbCol+1 , colType);
		
		
		temp = dataFrameCol.insert(colName, type);
		if(temp) {
			nbCol++;
			
			dataFrameData.insert(colName, data);
			
			
			columnName.insert(colName);
			arrColumnName.add(colName);
			
		}
		return temp;
	}

	@Override
	public boolean isCol(String colName) {
		boolean temp = columnName.find(colName);
		return temp;
	}

	@Override
	public int getColInd(String colName)throws IllegalArgumentException {
	//	Pair<Boolean , Pair<Integer,Class<?>>> temp = dataFrameCol.retrieve(colName);
		if(!dataFrameCol.retrieve(colName).first)
			throw new IllegalArgumentException();
		else
		return dataFrameCol.retrieve(colName).second.first;
	}

	@Override
	public String getColName(int j) throws ArrayIndexOutOfBoundsException {
		String temp =  arrColumnName.get(j);
		return temp;
	}

	@Override
	public Map<String, Pair<Integer, Class<?>>> getColInfo() {
		// TODO Auto-generated method stub
		return dataFrameCol;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Array<Object> getCol(int j) throws ArrayIndexOutOfBoundsException  {
		//String temp = getColName(j);
		//Pair<Boolean , Pair<Integer,Array<?>>> temp1 = dataFrameData.retrieve(getColName(j));
		if(!dataFrameData.retrieve(getColName(j)).first)		
		return new DynamicArray<Object>();
		else
			return (Array<Object>)dataFrameData.retrieve(getColName(j)).second.second;
			
	}

	@SuppressWarnings("unchecked")
	@Override
	public Array<Object> getCol(String colName) throws IllegalArgumentException {
		if(!dataFrameData.retrieve(colName).first)
			throw new IllegalArgumentException();
		else
		return (Array<Object>)dataFrameData.retrieve(colName).second.second;
	}

	@Override
	public int getNbRows() {
		if(dataFrameCol.size()==0)
			return 0;
		else
		return getCol(0).size();
	}

	@Override
	public void addRow(Array<Object> row) throws IllegalArgumentException  {
		if (row.size() == 0)
			throw new IllegalArgumentException();
		
		if(getNbCols()!=0)
			if(getNbCols() != row.size()) {
				//System.out.println(getNbCols() +"," + row.size());
				throw new IllegalArgumentException();
			}
		for(int i = 0 ; i<row.size();i++) {
			if(getType(i).isInstance(row.get(i)) == false) 
				throw new IllegalArgumentException();
			
			getCol(i).add(row.get(i));
		} 
	/*	if(getNbCols() !=0 && row.size() != getNbCols())
			throw new IllegalArgumentException();
		for(int i = 0 ; i<row.size(); i++) {
			if(!getType(i).isInstance(row.get(i)))
				throw new IllegalArgumentException();
			getCol(i).add(row.get(i));
		}  */

	}
	private Class<?> getType(int i){
		
		
		return dataFrameCol.retrieve(getColName(i)).second.second;
	}
       private Class<?> getType(String colName){
		
		
		return dataFrameCol.retrieve(colName).second.second;
	}

	@Override
	public Array<Object> getRow(int i) throws ArrayIndexOutOfBoundsException{
		Array<Object> temp = new DynamicArray<Object>();
		for(int j = 0 ; j<getNbCols() ; j++) {
			temp.add(getCol(j).get(i));
		}
		return temp;
	}
	
	private void emptyDF() {
		dataFrameCol.clear();
		dataFrameData.clear();
		nbCol = -1;
		columnName.clear();
		arrColumnName = new DynamicArray<String>();
		
	}

	@Override
	public boolean loadCSV(String fileName) {
		Array<Object> r = new DynamicArray<Object>();
		try {
			emptyDF();
			File f = new File(fileName);
			Scanner sc = new Scanner(f);
			if(sc.hasNextLine() == false) {   // if empty
				
				return false;}
			
			String line = sc.nextLine(); //x = line
			int nbLine = 1 ;
			String arrName[] = line.split(",");
			
			if(sc.hasNextLine()==false) {   // one line only and it will be the columns 
				for(int i = 0 ; i<arrName.length ; i++) // and if there is more than on line it wont add column name
					addCol(arrName[i] , String.class);
				
				return true;
			}
			int nbCol = arrName.length;
			
			while(sc.hasNextLine()) {    // read row by row 
		     line = sc.nextLine();
		     r = new DynamicArray<Object>(); // every raw has its data so we will need it for every raw 
		     nbLine++;
		     String rawTemp[] = line.split(",");
		     if(rawTemp.length != nbCol) {  // num of raw != num of column 
		    	 emptyDF();
		    	 
		    	 return false;
		     }
		     for(int i = 0 ; i<rawTemp.length ; i++) {  // processing one line a[0] = italy , a[1] = 1/1/2021
		    	int parse = 0 ; // if changed make it 1 to identify later 
		    	try {
		    		int temp1 = Integer.parseInt(rawTemp[i]);  // if failed it will throw exception 
		    		
		    		parse = 1 ;
		    		if(nbLine==2) {
		    			Class<?> colType = Integer.class;   // know its type
		    			Array<Object> data = new DynamicArray<Object>(); // store first data in raw in order to add col later
		    			data.add(temp1);
		    			addCol(arrName[i] , colType , data);// no column was adder before 
		    		}
		    		else 
		    			r.add(temp1); // not line 2 so we already know the type and if we collect the whole raw in the 
		    		                // array we will add the whole raw in the data frame
		    	}
		    	catch(Exception e) {  // catch of int
		    	//	System.out.println("not integer");
		    		try {
			    		double temp2 = Double.parseDouble(rawTemp[i]);
			    		
			    		parse = 1;
			    		if(nbLine==2) {
			    			Class<?> colType = Class.forName("java.lang.Double");   
			    			Array<Object> data = new DynamicArray<Object>();  
			    			data.add(temp2);
			    			addCol(arrName[i] , colType , data);
			    			
			    		}
			    		else
			    			r.add(temp2);
			    		
			    		
			    	}
			    	catch(Exception e1) {
			    	//	System.out.println("not double");
			    	}
		    		
		    	}
		    	
		    	
		    	
		    	try {
		    		Date temp3 = new SimpleDateFormat("yyyy-MM-dd").parse(rawTemp[i]);
		    		
		    		parse = 1;
		    		if(nbLine == 2) {
		    			Class<?> colType = Date.class;   
		    			Array<Object> data = new DynamicArray<Object>();  
		    			data.add(temp3);
		    			addCol(arrName[i] , colType , data);	
		    			
		    		}
		    		else
		    			r.add(temp3);
		    		
		    	}
		    	catch(Exception e) {
		    		
		    	}
		    	
		    	try {
		    		
		    		if(parse==0) {
		    			if(nbLine==2) {
		    			Class<?> colType = Class.forName("java.lang.String");   
		    			Array<Object> data = new DynamicArray<Object>();  
		    			data.add(rawTemp[i]);
		    			addCol(arrName[i] , colType , data); }
		    			
		    		
		    		else
		    			r.add(rawTemp[i]);
		    		}
		    	}
		    	catch(Exception e) {
		    		
		    	}
		     }  // end of for 
		     
		    	if(nbLine> 2 && r.size() != getNbCols()) {
		    		
		    		emptyDF();
		    		return false;
		    	}
		    	
		    	if(compatable(r) == false) {
		    		emptyDF();
		    		
		    		return false;
		    	}
		    	
		    	if(r.size() != 0) {  // maybe empty row 
		    		addRow(r);
		    	}
		  //   }  // end of for 
				
			}  // end of while
			
		}  // the end of first try 
		catch(FileNotFoundException e) {
			 
			System.out.println("not found");
			return false;
		}
		return true; 
	
	}
	
	
	
	private boolean compatable(Array<Object>r) {
		for(int i = 0 ; i<r.size(); i++) {
			if(getType(i).isInstance(r.get(i)) == false)
				return false;
		}
		return true;
		
	}
	@Override
	public DataFrame filterCols(Array<String> colNames) throws IllegalArgumentException {
		DataFrame d = new DataFrameImp();
		int size = colNames.size();
		for(int i = 0 ; i<size;i++)  // if colNames is empty it will not go through the loop 
		{
		 String name = colNames.get(i); // return the element which in this case the string name
		 Class<?> type = getType(name); // return the type of the specefied name 
		 Array<Object> arr = getCol(name); // get data of the specefied column 
		 d.addCol(name, type, arr);
			
		}
		return d;
	}
     
	
	@Override
	public DataFrame filterRows(Condition cond) {
		DataFrame d = new DataFrameImp();
		for(int i = 0 ; i<getNbCols(); i++) {
			String name = getColName(i);
			Class<?> type = getType(name);
			d.addCol(name, type);  // add the column first in order to add the raws later
		}
		for(int i = 0 ; i<getNbRows() ; i++)  // number of raws of our data frame
		{
			if(cond.test(getRow(i))) 
				d.addRow(getRow(i));
		}
		return d;
	}
	/*class Cond implements Condition{
		String country;
		public Cond(String c)
		{this.country = c;}	
		public boolean test(Array<Object> raw) {
			for(int i = 0 ; i<raw.size(); i++) {
				if(raw.get(i).equals(country))
					return true;
			}
			return false;
		}
	} */

	@Override
	public double mean(String colName) {
		if(isCol(colName) == false)
		throw new IllegalArgumentException();
		Class<?> type = getType(colName);
		if(Double.class.equals(type) || Integer.class.equals(type)) {
			Array<?> arrCol = getCol(colName); // get the specefied column that has data(array)
			double sum = 0;
			for(int i = 0 ; i<arrCol.size(); i++) {
				if(Double.class.equals(type))
				   sum = sum + (Double)arrCol.get(i);
				else
					sum = sum + (Integer)arrCol.get(i);
			}
			int num = arrCol.size();
			if(num == 0)
				return 0.0;
			return sum/num;
		}
		else
			throw new IllegalArgumentException(" its not a number ");	
		
	}

}

