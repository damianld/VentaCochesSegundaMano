/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import empresasegundamano.HibernateUtil;
import empresasegundamano.NewHibernateUtil;
import java.net.URL;
import pojos.Cliente;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import mapeos.Proveedor;


/**
 *
 * @author a18franciscorm
 */
public class ControladorVentana implements Initializable{
    String vaciadorString="";
    LocalDate vaciadorAlta=null;
    
    @FXML
    private TextField texId;
    
    @FXML
    private TextField texName;
    
    @FXML
    private DatePicker fechaAlta;
    
    @FXML
    private TableView tablaProveedores;
    
    @FXML
    private TableColumn id;
    
    @FXML
    private TableColumn nombre;
    
    @FXML
    private TableColumn fecha;
//    private ObservableList <ClienteP>listaproveedores = FXCollections.observableArrayList();
    private Session sesion;
    
    @FXML
    private void darAlta(ActionEvent event ){
        String auxname=null;
        Date auxfecha=null;
        System.out.println("Click en alta, datos: ");
        if(!texName.getText().isEmpty()){
            System.out.println(texName.getText());
            auxname=texName.getText();
            texName.setText(vaciadorString);
        }
        else{
            System.out.println("No tiene nombre");
        }
        try{
            System.out.println(fechaAlta.getValue().toString());
            int dia=fechaAlta.getValue().getDayOfMonth();
            int mes=fechaAlta.getValue().getMonthValue();
            int año=fechaAlta.getValue().getYear();
            auxfecha= new Date(año,mes,dia);
            fechaAlta.setValue(vaciadorAlta);
        }
        catch(RuntimeException rte1){
            System.out.println("Non se introdujo fecha");
        }
        
        Proveedor p=new Proveedor(1,auxname,auxfecha);
        
        sesion.beginTransaction();
        sesion.saveOrUpdate(p);
        sesion.getTransaction().commit();
//        listaproveedores.add(e);
//        refrescar(listaproveedores);
    }
    
    @FXML
    private void darBaja(ActionEvent event ){
        
        System.out.println("Click en Baja");
        
    }
    
    @FXML
    private void Modificar (ActionEvent event ){
//        ClienteP e=(ClienteP)tablaProveedores.getSelectionModel().getSelectedItem();
//        System.out.println(e.id);
        //Buscar e modificar no arrayList
        System.out.println("Click en modificar");
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Buenas, me he iniciado.");
        id.setCellValueFactory(new PropertyValueFactory("id"));
        nombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        fecha.setCellValueFactory(new PropertyValueFactory("fecha"));
        sesion=NewHibernateUtil.getSession();
    }
    public void refrescar(ObservableList ol){
        tablaProveedores.setItems(ol);
    }
    
}
