public class COVID19VaccinationImp implements COVID19Vaccination {
     
	class Cond implements Condition{
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
	}
	
	public DataFrame df;
	
	public COVID19VaccinationImp() {
		df = new DataFrameImp();
	}
	
	@Override
	public void setData(DataFrame df) {
		this.df = df;
		
	}

	@Override
	public DataFrame getPeopleVaccinated(String countryName) {
		Array<String> cName = new DynamicArray<String>();  // in order to add column name you want 
		cName.add("Date");
		cName.add("People Vaccinated");
		
		Condition c = new Cond(countryName);
		DataFrame temp = df.filterRows(c);  // filter row by country 
		
		
		//DataFrame d = temp.filterCols(cName);
		
		return temp.filterCols(cName);   // fitler column the filtered data frame 
	}

	@Override
	public DataFrame getPeopleFullyVaccinated(String countryName) {
		Array<String> cName = new DynamicArray<String>();  // in order to add column name you want 
		cName.add("Date");
		cName.add("People Fully Vaccinated");
		
		Condition c = new Cond(countryName);
		DataFrame temp = df.filterRows(c);  // filter row by country 
		
		
		//DataFrame d = temp.filterCols(cName);
		
		return temp.filterCols(cName);   // fitler column the filtered data frame 
	}

	@Override
	public DataFrame getPeopleVaccinatedPerHundred(String countryName) {
		
		
		Condition c = new Cond(countryName);
		DataFrame temp = df.filterRows(c);  // filter row by country 
		
		Array<String> cName = new DynamicArray<String>();  // in order to add column name you want 
		cName.add("Date");
		cName.add("Percentage of People Vaccinated");
		//DataFrame d = temp.filterCols(cName);
		
		return temp.filterCols(cName);   // fitler column the filtered data frame 
	}

	@Override
	public DataFrame getPercentageOfPeopleFullyVaccinated(String countryName) {
		Condition c = new Cond(countryName);
		DataFrame temp = df.filterRows(c);  // filter row by country 
		
		Array<String> cName = new DynamicArray<String>();  // in order to add column name you want 
		cName.add("Date");
		cName.add("Percentage of People Fully Vaccinated");
		//DataFrame d = temp.filterCols(cName);
		
		return temp.filterCols(cName);   // fitler column the filtered data frame 
	}

	@Override
	public Set<String> getVaccines(String countryName) {
		Set<String> nameOfVac = new BSTSet<String>(); // to return the names later
		Condition c = new Cond(countryName);
		DataFrame temp = df.filterRows(c);  // filter row by country
		
		Array<String> cName = new DynamicArray<String>();  
		cName.add("Vaccines");
		DataFrame filteredCol = temp.filterCols(cName);
		
		Array<Object> arrVac = filteredCol.getCol("Vaccines"); // to deal with it in for
		
		for(int i = 0 ; i<arrVac.size(); i++) {
			String current = (String )arrVac.get(i); // the whole Vaccines without split   
			String [] split = current.split("-");   // split it 
			for(int j = 0 ; j< split.length ; j++) {
				String temp1 = split[j];
				nameOfVac.insert(temp1);
			}
			
		}
		return nameOfVac;
	}

	@Override
	public double getAvgVaccinatedPerDay(String countryName) {
		Condition c = new Cond(countryName);
		DataFrame temp = df.filterRows(c);  // filter row by country 
		
	//	Array<String> cName = new DynamicArray<String>();  // in order to add column name you want 
	//	cName.add("People Vaccinated");
		//DataFrame d = temp.filterCols(cName);
		
		return temp.mean("People Vaccinated");
	}

	@Override
	public double getAvgFullyVaccinatedPerDay(String countryName) {
		Condition c = new Cond(countryName);
		DataFrame temp = df.filterRows(c);  // filter row by country 
		return temp.mean("People Fully Vaccinated");
	}

}
