package it.polito.tdp.indovinaNumero;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;

import it.polito.tdp.indovinaNumero.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {

	private Model model;
	
	public void setModel(Model model) {
		this.model=model;
	}
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnNuovaPartita;

    @FXML
    private TextField txtNumeroTentativo;

    @FXML
    private HBox hBox;

    @FXML
    private TextField txtTentativoUtente;

    @FXML
    private Button btnProva;

    @FXML
    private TextArea txtRisposta;

    @FXML
    void doNuovaPartita(ActionEvent event) {
    		
    	// con mvc VA AGGIUNTO
    		this.model.nuovaPartita();
    		
    	 /* // gestione nuova partita VIENE SPOSTATA IN MODELLO
    			this.tentativiFatti=0;
    	    	this.inGioco= true;
    	    	this.numSegreto = (int)(Math.random()*NMAX)+1;
    	*/
    	
    	// gestione INTERFACCIA RIMANE SU CONTROLLER
    	this.txtNumeroTentativo.setText(Integer.toString(this.model.getTMAX()));
    	this.hBox.setDisable(false);
    	this.txtRisposta.setText("");
    }

    @FXML
    void doProva(ActionEvent event) {
    	int numero;
    	String s = txtTentativoUtente.getText();
    	this.txtTentativoUtente.setText("");
    	try {
    		numero = Integer.parseInt(s);
    	}
    	catch (NumberFormatException e) {
    		txtRisposta.appendText("Inserire un numero tra 1 e 100 \n");
    		return;
    	}
    	
    	// dobbiamo valutare questo numero, se è giusto ecc
    	// spostiamo questi controlli nel modello e non più nel controllore
    	
    	int result;
    	
    	// dobbiamo inserire una serie di try catch perchè il metodo che abbiamo creato genera eccezioni
    	try {
    	// passiamo il numero inserito dall'utente al modello perché lo controlli
    		
    	result= this.model.controllo(numero);
    	
    		//numero non valido o partita finita
    	} catch (IllegalStateException se) {
    		this.txtRisposta.appendText(se.getMessage());
    		this.txtNumeroTentativo.setText("0");
    		this.hBox.setDisable(true);
    		return;
    	} catch (InvalidParameterException pe) {
    		this.txtRisposta.appendText(pe.getMessage());
    		return;
    	}
    	
    	// numero valido
    	this.txtNumeroTentativo.setText(Integer.toString(this.model.getTMAX()-this.model.getTentativiFatti()));
    	
    	if(result==0) {
    		this.txtRisposta.appendText("Hai indovinato il numero segreto, "+this.model.getNumSegreto()+", con "+this.model.getTentativiFatti()+" tentativi.");
    		this.hBox.setDisable(true);
    		return;	
    	}
    	if(result==1) {
    		this.txtRisposta.appendText("Il numero inserito, "+numero+", è più grande di quello da indovinare. \n");
    	}
    	else {
    		this.txtRisposta.appendText("Il numero inserito, "+numero+", è più piccolo di quello da indovinare. \n");
    	}
    }

    @FXML
    void initialize() {
        assert btnNuovaPartita != null : "fx:id=\"btnNuovaPartita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumeroTentativo != null : "fx:id=\"txtNumeroTentativo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert hBox != null : "fx:id=\"hBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativoUtente != null : "fx:id=\"txtTentativoUtente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisposta != null : "fx:id=\"txtRisposta\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
