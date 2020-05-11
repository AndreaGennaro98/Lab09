package it.polito.tdp.borders.db;

import java.util.List;
import java.util.Map;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class TestDAO {

	public static void main(String[] args) {

		BordersDAO dao = new BordersDAO();

		System.out.println("Lista di tutte le nazioni: \n");
		Map<Integer,Country> countries = dao.loadAllCountries();
		countries=dao.loadCountriesAndWeight(2003, countries);
			System.out.println(countries.values()+"\n\n");
			
		List<Border> borders=dao.getCountryPairs(2003, countries);
		for(Border b:borders)
			System.out.println(b+"\n");
		
	}
}
