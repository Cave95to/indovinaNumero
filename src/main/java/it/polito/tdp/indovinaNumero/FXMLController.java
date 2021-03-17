package it.polito.tdp.indovinaNumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {

	private final int NMAX = 100;
	private final int TMAX = 8;
	private int numSegreto;
	private int tentativiFatti;
	private boolean inGioco=false;
	
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
    	this.tentativiFatti=0;
    	this.inGioco= true;
    	this.numSegreto = (int)(Math.random()*NMAX)+1;
    	this.txtNumeroTentativo.setText(Integer.toString(TMAX));
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
    	this.tentativiFatti++;
    	this.txtNumeroTentativo.setText(Integer.toString(TMAX-this.tentativiFatti));
    	if(numero==this.numSegreto) {
    		this.txtRisposta.appendText("Hai indovinato il numero segreto, "+this.numSegreto+", con "+this.tentativiFatti+" tentativi.");
    		this.hBox.setDisable(true);
    		this.inGioco=false;
    		return;	
    	}
    	if(this.tentativiFatti==TMAX) {
    		this.txtRisposta.appendText("Hai perso, hai esaurito i tentativi a tua disposizione.\nIl numero da indovinare era "+this.numSegreto+" .");
    		this.inGioco=false;
    		this.hBox.setDisable(true);
    		return;
  
    	}
    	if(numero>this.numSegreto) {
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
