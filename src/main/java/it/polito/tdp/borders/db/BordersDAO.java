package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public Map<Integer,Country> loadAllCountries() {
	
		String sql = "SELECT *\r\n" + 
				"FROM country c1";
		Map<Integer,Country> result = new HashMap<Integer,Country>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Country country=new Country(rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"),0); 
				result.put(country.getCodice(),country);
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
	
	public Map<Integer,Country> loadCountriesAndWeight(Integer anno,Map<Integer,Country> paesi){
		if(anno<2006) {
			String sql = "SELECT DISTINCT c1.state1no, COUNT(c1.state1no) AS num\r\n" + 
					"FROM contiguity c1 \r\n" + 
					"WHERE YEAR<=? AND c1.conttype=1\r\n" + 
					"GROUP BY c1.state1no";
			
			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, anno);
				ResultSet rs = st.executeQuery();
	
				while (rs.next()) {
				
					paesi.get(rs.getInt("c1.state1no")).setNumconfini(rs.getInt("num"));
					paesi.get(rs.getInt("c1.state1no"));
				}
				
				conn.close();
				Map<Integer,Country> result = new HashMap<Integer,Country>();

				return result;

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Errore connessione al database");
				throw new RuntimeException("Error Connection Database");
			}
		}
		else {
	
			String sql = "SELECT DISTINCT c1.state1no, COUNT(c1.state1no) AS num\r\n" + 
					"FROM contiguity2006 c1 \r\n" + 
					"WHERE YEAR=2006 AND c1.conttype=1\r\n" + 
					"GROUP BY c1.state1no";
			
			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet rs = st.executeQuery();
	
				while (rs.next()) {
					paesi.get(rs.getInt("c1.state1no")).setNumconfini(rs.getInt("num"));
					paesi.get(rs.getInt("c1.state1no"));
				}
				
				conn.close();
				return paesi;

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Errore connessione al database");
				throw new RuntimeException("Error Connection Database");
			}
		}
	}

	public List<Border> getCountryPairs(int anno, Map<Integer,Country> paesi) {
		
		if(anno<2006) {
			String sql = "SELECT DISTINCT c1.state1no, c1.state2no\r\n" + 
					"FROM contiguity c1 \r\n" + 
					"WHERE YEAR<=? AND c1.conttype=1\r\n" + 
					"GROUP BY c1.state1no, c1.state2no";
			
			List<Border> result = new ArrayList<Border>();
			
			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, anno);
				ResultSet rs = st.executeQuery();
	
				while (rs.next()) {
				
					Border border=new Border(paesi.get(rs.getInt("c1.state1no")),paesi.get(rs.getInt(("c1.state2no"))));
					result.add(border);
				}
				
				conn.close();
				return result;

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Errore connessione al database");
				throw new RuntimeException("Error Connection Database");
			}
		}
			else {
	
			String sql = "SELECT c1.state1no, c1.state2no\r\n" + 
					"FROM contiguity2006 c1 \r\n" + 
					"WHERE YEAR=2006 AND c1.conttype=1\r\n" + 
					"GROUP BY c1.state1no, c1.state2no";
			
			List<Border> result = new ArrayList<Border>();
			
			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet rs = st.executeQuery();
	
				while (rs.next()) {
					Border border=new Border(paesi.get(rs.getInt("c1.state1no")),paesi.get(rs.getInt(("c1.state2no"))));
					result.add(border);
				}
				
				conn.close();
				return result;

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Errore connessione al database");
				throw new RuntimeException("Error Connection Database");
			}
			}
	}
}
