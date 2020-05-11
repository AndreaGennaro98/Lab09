package it.polito.tdp.borders;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	Model m=new Model();
	
	public void setM(Model model) {
		this.m=model;
		ObservableList<Country> list=FXCollections.observableArrayList(m.countries().values());
		txtStato.setItems(list);
	}


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtAnno;
    
    @FXML
    private ChoiceBox<Country> txtStato;

    @FXML
    private Button btnVicini;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	
    	int anno=0;
    	
    	try {
			anno=Integer.parseInt(txtAnno.getText());
		} catch (NumberFormatException e) {
			txtResult.appendText("Non è stato inserito un numero ma una stringa");
		}
    	
    	Graph<Country, DefaultEdge> grafo= new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);
    	Map<Integer, Country> stati=m.countries();
    	Map<Integer,Country>statiConPeso=m.getStati(anno,stati);
    	List<Border> confini=m.getBorder(anno,stati);
    	Graphs.addAllVertices(grafo, stati.values());
    	for(Border b:confini) {
    		grafo.addEdge(b.getC1(), b.getC2());
    	}
    	txtResult.appendText("Grafo con "+grafo.vertexSet().size()+" vertici e "+grafo.edgeSet().size()+"archi \n");
    	for(Country c:statiConPeso.values())
    		txtResult.appendText("Lo stato "+c.getNome()+" ha "+c.getNumconfini()+ " paesi confinanti via terra\n");
    	ObservableList<Country> list=FXCollections.observableArrayList(m.countries().values());
		txtStato.setItems(list);
    	

    }

    @FXML
    void trovaTuttiIVicini(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnVicini != null : "fx:id=\"btnVicini\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }

}

