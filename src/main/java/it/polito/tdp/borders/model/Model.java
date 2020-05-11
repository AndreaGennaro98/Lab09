package it.polito.tdp.borders.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	BordersDAO dao;
	
	public Model() {
		dao=new BordersDAO();
	}
		
		public Map<Integer, Country> getStati(int anno,Map<Integer, Country> statiMap){
			return dao.loadCountriesAndWeight(anno, statiMap);
		}
		
		public Map<Integer,Country> countries(){
			return dao.loadAllCountries();
		}
		
		public List<Border> getBorder(int anno,Map<Integer,Country> paesi) {
			return dao.getCountryPairs(anno,paesi);
		}

	

}
