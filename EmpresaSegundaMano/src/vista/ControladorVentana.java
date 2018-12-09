package vista;
import empresasegundamano.HibernateUtil;
import empresasegundamano.NewHibernateUtil;
import java.net.URL;
import java.time.Instant;
import pojos.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
public class ControladorVentana implements Initializable{
    Cliente clienteSeleccionado= null;
    Proveedor proveedorSeleccionado= null;
    String vaciadorString="";
    LocalDate vaciadorAlta=null;
    //Atributos Proveedor:
    @FXML
    private TextField texNombreProveedor;
    @FXML
    private DatePicker fechaAltaProveedor;
    @FXML
    private TableView<Proveedor> tablaProveedores;
    @FXML
    private TableColumn<Proveedor,Integer> idProveedor;
    @FXML
    private TableColumn<Proveedor,String> nombreProveedor;
    @FXML
    private TableColumn<Proveedor,Date> fechaProveedor;
    private ObservableList <Proveedor>listaProveedores = FXCollections.observableArrayList();
    
    //Atributos Cliente
    @FXML
    private TableView<Cliente> tablaClientes;
    @FXML
    private TextField texdniCliente;
    @FXML
    private TextField texnombreCliente;
    @FXML
    private TextField texapel1Cliente;
    @FXML
    private TextField texapel2Cliente;
    @FXML
    private TextField texmovilCliente;
    @FXML
    private TextField texemailCliente;
    @FXML
    private DatePicker entfechaCliente;
    private ObservableList <Cliente>listaClientes = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Cliente,String>dniCliente;
    @FXML
    private TableColumn<Cliente,String>nombreCliente;
    @FXML
    private TableColumn<Cliente,String>apellido1Cliente;
    @FXML
    private TableColumn<Cliente,String>apellido2Cliente;
    @FXML
    private TableColumn<Cliente,String>movilCliente;
    @FXML
    private TableColumn<Cliente,String>emailCliente;
    @FXML
    private TableColumn<Cliente,Date>fechaCliente;
    private Session sesion;
    
    
    //Atributos Taller 
    private ObservableList <Taller>listaTalleres = FXCollections.observableArrayList();
    @FXML
    private  TableView<Taller>tablaTaller;
    @FXML
    private  TableColumn<Taller,Integer>idTaller;
    @FXML
    private  TableColumn<Taller,String>nombreTaller;
    @FXML
    private  TextField texNombreTaller;
    private Taller tallerSeleccionado;
    
    
    //Atributos Exposicion
    private ObservableList <Exposicion>listaExpos = FXCollections.observableArrayList();
    @FXML
    private TableView<Exposicion> tablaExpo;
    @FXML 
    private TableColumn<Exposicion,Integer> idExpo;
    @FXML 
    private TableColumn<Exposicion,String> nombreExpo;
    @FXML 
    private TableColumn<Exposicion,String> lugarExpo;
    @FXML 
    private TextField texNombreExpo;
    @FXML 
    private TextField texLugarExpo;
    private Exposicion exposicionSeleccionada;
    
    
    //Atrubtos Vendedor
    private Vendedor vendedorSeleccionado;
    private ObservableList <Vendedor>listaVendedores = FXCollections.observableArrayList();
    private ObservableList opcionesVendedores = FXCollections.observableArrayList();
    @FXML
    private TableView<Vendedor>tablaVendedor;
    @FXML
    private TableColumn<Vendedor,String>dniVendedor;
    @FXML
    private TableColumn<Vendedor,String>nombreVendedor;
    @FXML
    private TableColumn<Vendedor,String>apellido1Vendedor;
    @FXML
    private TableColumn<Vendedor,String>apellido2Vendedor;
    @FXML
    private TableColumn<Vendedor,String>movilVendedor;
    @FXML
    private TableColumn<Vendedor,String>emailVendedor;
    @FXML
    private TableColumn<Vendedor,String>salarioVendedor;
    @FXML
    private TableColumn<Vendedor,String>comisionVendedor;
    @FXML
    private TableColumn<Vendedor,LocalDate>fechaVendedor;
    @FXML 
    private TextField texDniVendedor;
    @FXML 
    private TextField texNombreVendedor;
    @FXML 
    private TextField texApel1Vendedor;
    @FXML 
    private TextField texApel2Vendedor;
    @FXML 
    private TextField texMovilVendedor;
    @FXML 
    private TextField texEmailVendedor;
    @FXML
    private ComboBox texCondicionVendedor;
    @FXML 
    private TextField texCantidadVendedor;
    @FXML 
    private DatePicker altaVendedor;
    
    
    //Metodos Proveedor
    @FXML
    private void darAltaProveedor(ActionEvent event ){
        String auxname=null;
        Date auxfecha=null;
        try{
            System.out.println(fechaAltaProveedor.getValue().toString());
            int dia=fechaAltaProveedor.getValue().getDayOfMonth();
            int mes=fechaAltaProveedor.getValue().getMonthValue()-1; //se resta 1 mes para cuadrar la fecha
            int año=(fechaAltaProveedor.getValue().getYear()-1900); //se resta 1900 años para cuadrar la fecha
            auxfecha= new Date(año,mes,dia);
            fechaAltaProveedor.setValue(vaciadorAlta);
            if(!texNombreProveedor.getText().isEmpty()){
            System.out.println(texNombreProveedor.getText());
            auxname=texNombreProveedor.getText();
            texNombreProveedor.setText(vaciadorString);
            Proveedor p=new Proveedor(auxname,auxfecha);
            listaProveedores.add(p);
            refrescarProveedores();
            //guardarModificar(p);
        }
        else{
            System.out.println("No tiene nombre");
        }
        }
        catch(RuntimeException rte1){
            System.out.println("Non se introdujo fecha");
        } 
    }
    @FXML
    private void darBajaProveedor(ActionEvent event){
        Proveedor p=(Proveedor) tablaProveedores.getSelectionModel().getSelectedItem();
        listaProveedores.remove(p);
        refrescarProveedores();
        //eliminar(p);
    }
    
    @FXML
    private void ModificarProveedor (ActionEvent event ){
        
        String auxname=null;
        Date auxfecha=null;
        try{
            Proveedor p=(Proveedor)tablaProveedores.getSelectionModel().getSelectedItem();
            int aux =tablaProveedores.getSelectionModel().getSelectedIndex();
            int dia=fechaAltaProveedor.getValue().getDayOfMonth();
            int mes=fechaAltaProveedor.getValue().getMonthValue(); //se resta 1 mes para cuadrar la fecha
            int año=(fechaAltaProveedor.getValue().getYear()); //se resta 1900 años para cuadrar la fecha
            auxfecha= new Date(año,mes,dia);
            fechaAltaProveedor.setValue(vaciadorAlta);
            if(!texNombreProveedor.getText().isEmpty()){
            System.out.println(texNombreProveedor.getText());
            auxname=texNombreProveedor.getText();
            texNombreProveedor.setText(vaciadorString);
            p=new Proveedor(auxname,auxfecha);
            listaProveedores.remove(aux);
            listaProveedores.add(aux, p);
            refrescarProveedores();
            fechaAltaProveedor.setValue(vaciadorAlta);
            //guardarModificar(p);
        }
        else{
            System.out.println("No tiene nombre");
        }
        }
        catch(RuntimeException rte1){
            System.out.println("Non se introdujo fecha");
        }
    }
    
    
    //Metodos Cliente
    @FXML
    public void darAltaCliente(ActionEvent event){
         Date auxfecha= null;
        if(texdniCliente.getText().isEmpty() || texnombreCliente.getText().isEmpty() || texapel1Cliente.getText().isEmpty() || texapel2Cliente.getText().isEmpty() || texmovilCliente.getText().isEmpty() || texmovilCliente.getText().isEmpty()){
            System.out.println("Faltan datos, insercion cancelada");
        }else{
            try{
                int dia=entfechaCliente.getValue().getDayOfMonth();
                int mes=entfechaCliente.getValue().getMonthValue()-1; //se resta 1 mes para cuadrar la fecha
                int año=(entfechaCliente.getValue().getYear()-1900); //se resta 1900 años para cuadrar la fecha
                auxfecha= new Date(año,mes,dia);
            }catch(RuntimeException rte1){
                System.out.println("Non se introdujo fecha");
            }
            Cliente c;
            c = new Cliente(texdniCliente.getText(),texnombreCliente.getText(),texapel1Cliente.getText(),texapel2Cliente.getText(),texmovilCliente.getText(),texemailCliente.getText(),auxfecha);
            listaClientes.add(c);
            refrescarClientes();
            texdniCliente.setText(vaciadorString);
            texnombreCliente.setText(vaciadorString);
            texapel1Cliente.setText(vaciadorString);
            texapel2Cliente.setText(vaciadorString);
            texmovilCliente.setText(vaciadorString);
            texemailCliente.setText(vaciadorString);
            entfechaCliente.setValue(vaciadorAlta);
        }
    }
    @FXML
    private void darBajaCliente(ActionEvent event){
        Cliente e=(Cliente)tablaClientes.getSelectionModel().getSelectedItem();
        listaClientes.remove(e);
        System.out.println("Eliminado cliente "+e.getDni());
        texdniCliente.setText(vaciadorString);
        texnombreCliente.setText(vaciadorString);
        texapel1Cliente.setText(vaciadorString);
        texapel2Cliente.setText(vaciadorString);
        texmovilCliente.setText(vaciadorString);
        texemailCliente.setText(vaciadorString);
        entfechaCliente.setValue(vaciadorAlta);
        
        //eliminar(c);
    }
    
    @FXML
    private void ModificarCliente (ActionEvent event ){
        Date auxfecha= null;
        Cliente e=(Cliente)tablaClientes.getSelectionModel().getSelectedItem();
        int aux=tablaClientes.getSelectionModel().getSelectedIndex();
        if(texdniCliente.getText().isEmpty() || texnombreCliente.getText().isEmpty() || texapel1Cliente.getText().isEmpty() || texapel2Cliente.getText().isEmpty() || texmovilCliente.getText().isEmpty() || texmovilCliente.getText().isEmpty()){
            System.out.println("Faltan datos, insercion cancelada");
        }else{
            try{
                int dia=entfechaCliente.getValue().getDayOfMonth();
                int mes=entfechaCliente.getValue().getMonthValue(); //se resta 1 mes para cuadrar la fecha
                int año=(entfechaCliente.getValue().getYear()); //se resta 1900 años para cuadrar la fecha
            auxfecha= new Date(año,mes,dia);
            }catch(RuntimeException rte1){
            System.out.println("Non se introdujo fecha");}
        
            e = new Cliente(texdniCliente.getText(),texnombreCliente.getText(),texapel1Cliente.getText(),texapel2Cliente.getText(),texmovilCliente.getText(),texemailCliente.getText(),auxfecha);
            listaClientes.remove(aux);
            listaClientes.add(aux, e);
            refrescarClientes();
            System.out.println("Editado cliente "+e.getDni());
            texdniCliente.setText(vaciadorString);
            texnombreCliente.setText(vaciadorString);
            texapel1Cliente.setText(vaciadorString);
            texapel2Cliente.setText(vaciadorString);
            texmovilCliente.setText(vaciadorString);
            texemailCliente.setText(vaciadorString);
            entfechaCliente.setValue(vaciadorAlta);
        }
    }
    
    
    //Metodos Taller
    @FXML
    private void altaTaller(ActionEvent event){
        if(texNombreTaller.getText().isEmpty()){
            System.out.println("Falta el nombre.");
        }else{
            Taller t=new Taller(texNombreTaller.getText());
            listaTalleres.add(t);
            refrescarTalleres();
            texNombreTaller.setText(vaciadorString);
        }
    }
    @FXML
    private void bajaTaller(ActionEvent event){
        Taller t= (Taller)tablaTaller.getSelectionModel().getSelectedItem();
        listaTalleres.remove(t);
        refrescarTalleres();
        texNombreTaller.setText(vaciadorString);
    }
    @FXML
    private void modificacionTaller(ActionEvent event){
        Taller t= (Taller)tablaTaller.getSelectionModel().getSelectedItem();
        int aux = tablaTaller.getSelectionModel().getSelectedIndex();
        if(texNombreTaller.getText().isEmpty()){
            System.out.println("Falta nombre");
        }else{
            t.setNombre(texNombreTaller.getText());
            listaTalleres.set(aux, t);
            refrescarTalleres();
            texNombreTaller.setText(vaciadorString);
        }

    }
    
    
    //Metodos Exposicion
    @FXML
    private void altaExpo (ActionEvent event){
        if(texNombreExpo.getText().isEmpty() || texLugarExpo.getText().isEmpty()){
            System.out.println("Faltan datos");
        }else{
            Exposicion e= new Exposicion();
            e.setNombre(texNombreExpo.getText());
            e.setLugar(texLugarExpo.getText());
            listaExpos.add(e);
            texNombreExpo.setText(vaciadorString);
            texLugarExpo.setText(vaciadorString);
            refresarExposiciones();
        }
    }
    @FXML
    private void baixaExpo (ActionEvent event){
        Exposicion e= (Exposicion)tablaExpo.getSelectionModel().getSelectedItem();
        listaExpos.remove(e);
        texNombreExpo.setText(vaciadorString);
        texLugarExpo.setText(vaciadorString);
        refresarExposiciones();
    }
    @FXML
    private void modificacionExpo (ActionEvent event){
        Exposicion e= (Exposicion)tablaExpo.getSelectionModel().getSelectedItem();
        int aux=tablaExpo.getSelectionModel().getSelectedIndex();
        if(texNombreExpo.getText().isEmpty() || texLugarExpo.getText().isEmpty()){
            System.out.println("Faltan datos");
        }else{
            e.setNombre(texNombreExpo.getText());
            e.setLugar(texLugarExpo.getText());
            listaExpos.set(aux, e);
            texNombreExpo.setText(vaciadorString);
            texLugarExpo.setText(vaciadorString);
            refresarExposiciones();
        }
    }
    
    
    //Metodos Vendedor
    @FXML
    private void altaVendedor(ActionEvent event){
        Date auxfecha;
        try{
            int dia=altaVendedor.getValue().getDayOfMonth();
            int mes=altaVendedor.getValue().getMonthValue()-1; //se resta 1 mes para cuadrar la fecha
            int año=(altaVendedor.getValue().getYear()-1900); //se resta 1900 años para cuadrar la fecha
            auxfecha= new Date(año,mes,dia);
            if(texDniVendedor.getText().isEmpty() || texNombreVendedor.getText().isEmpty() || texApel1Vendedor.getText().isEmpty() || texApel2Vendedor.getText().isEmpty() || texMovilVendedor.getText().isEmpty() || texEmailVendedor.getText().isEmpty() || texCondicionVendedor.getSelectionModel().isEmpty() || texCantidadVendedor.getText().isEmpty()){
                System.out.println("Faltan datos, asegurese de llenar todos los campos.");
            }else{
                Vendedor v;
                if(texCondicionVendedor.getSelectionModel().getSelectedItem().equals("Asalariado")){
                    v =new VendedorAsalariado(Double.parseDouble(texCantidadVendedor.getText()),texDniVendedor.getText(),texNombreVendedor.getText(),texApel1Vendedor.getText(),texApel2Vendedor.getText(),texMovilVendedor.getText(),texEmailVendedor.getText(),auxfecha);
                }else{
                    v =new VendedorComision(Double.parseDouble(texCantidadVendedor.getText()),texDniVendedor.getText(),texNombreVendedor.getText(),texApel1Vendedor.getText(),texApel2Vendedor.getText(),texMovilVendedor.getText(),texEmailVendedor.getText(),auxfecha);
                }
                listaVendedores.add(v);
                refrescarVendedores();
                texDniVendedor.setText(vaciadorString);
                texNombreVendedor.setText(vaciadorString);
                texApel1Vendedor.setText(vaciadorString);
                texApel2Vendedor.setText(vaciadorString);
                texMovilVendedor.setText(vaciadorString);
                texCondicionVendedor.getSelectionModel().select(null);
                texCantidadVendedor.setText(vaciadorString);
                altaVendedor.setValue(vaciadorAlta);
            }
            }catch(RuntimeException rte1){
                System.out.println("Non se introdujo fecha");
            }
        
    }
    @FXML
    private void bajaVendedor(ActionEvent event){
        Vendedor v=(Vendedor)tablaVendedor.getSelectionModel().getSelectedItem();
        listaVendedores.remove(v);
        refrescarVendedores();
        texDniVendedor.setText(vaciadorString);
        texNombreVendedor.setText(vaciadorString);
        texApel1Vendedor.setText(vaciadorString);
        texApel2Vendedor.setText(vaciadorString);
        texMovilVendedor.setText(vaciadorString);
        texCondicionVendedor.getSelectionModel().select(null);
        texCantidadVendedor.setText(vaciadorString);
        altaVendedor.setValue(vaciadorAlta);
    }
    @FXML
    private void modificacionVendedor (ActionEvent event){
        Vendedor v=(Vendedor)tablaVendedor.getSelectionModel().getSelectedItem();
        int aux=tablaVendedor.getSelectionModel().getSelectedIndex();
        Date auxfecha;
        try{
            int dia=altaVendedor.getValue().getDayOfMonth();
            int mes=altaVendedor.getValue().getMonthValue(); //se resta 1 mes para cuadrar la fecha
            int año=(altaVendedor.getValue().getYear()); //se resta 1900 años para cuadrar la fecha
            auxfecha= new Date(año,mes,dia);
            if(texDniVendedor.getText().isEmpty() || texNombreVendedor.getText().isEmpty() || texApel1Vendedor.getText().isEmpty() || texApel2Vendedor.getText().isEmpty() || texMovilVendedor.getText().isEmpty() || texEmailVendedor.getText().isEmpty() || texCondicionVendedor.getSelectionModel().isEmpty() || texCantidadVendedor.getText().isEmpty()){
                System.out.println("Faltan datos, asegurese de llenar todos los campos.");
            }else{
                
                if(texCondicionVendedor.getSelectionModel().getSelectedItem().equals("Asalariado")){
                    v =new VendedorAsalariado(Double.parseDouble(texCantidadVendedor.getText()),texDniVendedor.getText(),texNombreVendedor.getText(),texApel1Vendedor.getText(),texApel2Vendedor.getText(),texMovilVendedor.getText(),texEmailVendedor.getText(),auxfecha);
                }else{
                    v =new VendedorComision(Double.parseDouble(texCantidadVendedor.getText()),texDniVendedor.getText(),texNombreVendedor.getText(),texApel1Vendedor.getText(),texApel2Vendedor.getText(),texMovilVendedor.getText(),texEmailVendedor.getText(),auxfecha);
                }
                listaVendedores.set(aux, v);
                refrescarVendedores();
                texDniVendedor.setText(vaciadorString);
                texNombreVendedor.setText(vaciadorString);
                texApel1Vendedor.setText(vaciadorString);
                texApel2Vendedor.setText(vaciadorString);
                texMovilVendedor.setText(vaciadorString);
                texCondicionVendedor.getSelectionModel().select(null);
                texCantidadVendedor.setText(vaciadorString);
                altaVendedor.setValue(vaciadorAlta);
            }
            }catch(RuntimeException rte1){
                System.out.println("Non se introdujo fecha");
            }
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        sesion=NewHibernateUtil.getSession();
        
        System.out.println("Buenas, me he iniciado.");
        idProveedor.setCellValueFactory(new PropertyValueFactory("idProveedor"));
        nombreProveedor.setCellValueFactory(new PropertyValueFactory("nombre"));
        fechaProveedor.setCellValueFactory(new PropertyValueFactory<Proveedor,Date>("fechaAlta"));
        
        dniCliente.setCellValueFactory(new PropertyValueFactory("dni"));
        nombreCliente.setCellValueFactory(new PropertyValueFactory("nombre"));
        apellido1Cliente.setCellValueFactory(new PropertyValueFactory("apellido1"));
        apellido2Cliente.setCellValueFactory(new PropertyValueFactory("apellido2"));
        movilCliente.setCellValueFactory(new PropertyValueFactory("movil"));
        emailCliente.setCellValueFactory(new PropertyValueFactory("email"));
        fechaCliente.setCellValueFactory(new PropertyValueFactory("fechaAlta"));
        
        idTaller.setCellValueFactory(new PropertyValueFactory("idTaller"));
        nombreTaller.setCellValueFactory(new PropertyValueFactory("nombre"));
        
        nombreExpo.setCellValueFactory(new PropertyValueFactory("nombre"));
        lugarExpo.setCellValueFactory(new PropertyValueFactory("lugar"));
        idExpo.setCellValueFactory(new PropertyValueFactory("idExposicion"));
        
        dniVendedor.setCellValueFactory(new PropertyValueFactory("dni"));
        nombreVendedor.setCellValueFactory(new PropertyValueFactory("nombre"));
        apellido1Vendedor.setCellValueFactory(new PropertyValueFactory("apellido1"));
        apellido2Vendedor.setCellValueFactory(new PropertyValueFactory("apellido2"));
        movilVendedor.setCellValueFactory(new PropertyValueFactory("movil"));
        emailVendedor.setCellValueFactory(new PropertyValueFactory("email"));
        fechaVendedor.setCellValueFactory(new PropertyValueFactory("fechaAlta"));
        salarioVendedor.setCellValueFactory(new PropertyValueFactory("salario"));
        comisionVendedor.setCellValueFactory(new PropertyValueFactory("comision"));
        
        tablaClientes.getSelectionModel().selectedItemProperty().addListener(
        (ObservableValue<? extends Cliente> observable, Cliente oldValue, Cliente newValue) -> {
            clienteSeleccionado= null;
            clienteSeleccionado= newValue;
            texdniCliente.setText(clienteSeleccionado.getDni());
            texnombreCliente.setText(clienteSeleccionado.getNombre());
            texapel1Cliente.setText(clienteSeleccionado.getApellido1());
            texapel2Cliente.setText(clienteSeleccionado.getApellido2());
            texmovilCliente.setText(clienteSeleccionado.getMovil());
            texemailCliente.setText(clienteSeleccionado.getEmail());
            entfechaCliente.setValue(vaciadorAlta);
        });
        tablaProveedores.getSelectionModel().selectedItemProperty().addListener(
        (ObservableValue<? extends Proveedor> observable, Proveedor oldValue, Proveedor newValue) ->{
            proveedorSeleccionado=null;
            proveedorSeleccionado=newValue;
            texNombreProveedor.setText(proveedorSeleccionado.getNombre());
        });
        tablaTaller.getSelectionModel().selectedItemProperty().addListener(
        (ObservableValue<? extends Taller> observable, Taller oldValue, Taller newValue) ->{
           tallerSeleccionado=null;
            tallerSeleccionado=newValue;
            texNombreTaller.setText(tallerSeleccionado.getNombre());
        });
        
        tablaExpo.getSelectionModel().selectedItemProperty().addListener(
        (ObservableValue<? extends Exposicion> observable, Exposicion oldValue, Exposicion newValue) ->{
           exposicionSeleccionada=null;
            exposicionSeleccionada=newValue;
            texNombreExpo.setText(exposicionSeleccionada.getNombre());
            texLugarExpo.setText(exposicionSeleccionada.getLugar());
        });
        
        opcionesVendedores.add("Asalariado");
        opcionesVendedores.add("Comisionista");
        texCondicionVendedor.setItems(opcionesVendedores);
        
        tablaVendedor.getSelectionModel().selectedItemProperty().addListener(
        (ObservableValue<? extends Object> observable, Object oldValue, Object newValue) ->{
            vendedorSeleccionado=null;
            if(newValue.getClass().getCanonicalName().equalsIgnoreCase("pojos.VendedorAsalariado")){
                VendedorAsalariado v =(VendedorAsalariado)newValue;
                vendedorSeleccionado=v;
                texDniVendedor.setText(v.getDni());
                texNombreVendedor.setText(v.getNombre());
                texApel1Vendedor.setText(v.getApellido1());
                texApel2Vendedor.setText(v.getApellido2());
                texMovilVendedor.setText(v.getMovil());
                texEmailVendedor.setText(v.getEmail());
                LocalDate ld=LocalDate.of(v.getFechaAlta().getYear(), v.getFechaAlta().getMonth(), v.getFechaAlta().getDate());
                altaVendedor.setValue(ld);
                texCantidadVendedor.setText(String.valueOf(v.getSalario()));
                texCondicionVendedor.getSelectionModel().select("Asalariado");
            }else{
                VendedorComision v =(VendedorComision)newValue;
                vendedorSeleccionado=v;
                texDniVendedor.setText(v.getDni());
                texNombreVendedor.setText(v.getNombre());
                texApel1Vendedor.setText(v.getApellido1());
                texApel2Vendedor.setText(v.getApellido2());
                texMovilVendedor.setText(v.getMovil());
                texEmailVendedor.setText(v.getEmail());
                LocalDate ld=LocalDate.of(v.getFechaAlta().getYear(), v.getFechaAlta().getMonth(), v.getFechaAlta().getDate());
                altaVendedor.setValue(ld);
                texCantidadVendedor.setText(String.valueOf(v.getComision()));
                texCondicionVendedor.getSelectionModel().select("Comisionista");
            }
        });
    }
    
    public void refrescarProveedores(){
        tablaProveedores.setItems(listaProveedores);
    }
    
    public void refrescarClientes(){
        tablaClientes.setItems(listaClientes);
    }
    
    public void refrescarTalleres(){
        tablaTaller.setItems(listaTalleres);
    }
    
    public void refresarExposiciones(){
        tablaExpo.setItems(listaExpos);
    }
    
    public void refrescarVendedores(){
        tablaVendedor.setItems(listaVendedores);
    }
    
    private void guardarModificar(Object objeto){
        sesion.beginTransaction();
        sesion.saveOrUpdate(objeto);
        sesion.getTransaction().commit();
        sesion.clear();
    }
    
    private void eliminar(Object objeto){
        sesion.beginTransaction();
        sesion.delete(objeto);
        sesion.getTransaction().commit();
        sesion.clear();
    }
    
    
    private void cargarDatos(){
        
    }
}
