/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 *
 * @author a18franciscorm
 */
public class ControladorVentana implements Initializable{
    String vacio="";
    
    @FXML
    private TextField texId;
    
    @FXML
    private TextField texName;
    
    @FXML
    private DatePicker fechaAlta;
    
    @FXML
    private void darAlta(ActionEvent event ){
        
        System.out.println("Click en alta, datos: ");
        if(!texId.getText().isEmpty()){
            System.out.println(texId.getText());
            texId.setText(vacio);}
        else
            System.out.println("No tiene id");
        if(!texName.getText().isEmpty()){
            System.out.println(texName.getText());
            texName.setText(vacio);
        }
        else
            System.out.println("No tiene nombre");
        try{
        if(fechaAlta.getValue().toString()=="")
            System.out.println("Sin fecha");
        else
            System.out.println(fechaAlta.getValue().toString());
        }
        catch(RuntimeException rte1){
            System.out.println(rte1.getCause());
            System.out.println("Non se introduxo Data");
        }

    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
