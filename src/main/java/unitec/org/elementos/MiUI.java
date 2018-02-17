/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitec.org.elementos;

import com.vaadin.annotations.Theme;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author UriDrack7
 */
@SpringUI
@Theme("valo")
        
public class MiUI extends UI {

    public  TextField Titulo1;
    public  TextField Cuerpo1;
    public  Integer miId;
    
        /*Aqui se prueban todas las operaciones crud*/
    @Autowired RepositorioMensajitos repoMensa; //se enlasa el repositorio con la base de datos
         	
    @Override
    protected void init(VaadinRequest request) {
        
        Titulo1 = new  TextField ();
        Cuerpo1 = new  TextField ();
        Cuerpo1 . setWidth ( " 340px " );
        //Agregaremos un layout vertical y dentro de el los componentes que quedaran en orden descendente
        final VerticalLayout layout=new VerticalLayout();
        Label e1=new Label("Bienvenido al Registro  !!!");
        e1.addStyleName(ValoTheme.LABEL_H1);
        
        Button b1=new Button("Guardar");
        b1.addStyleNames(ValoTheme.BUTTON_DANGER);

        b1.addClickListener(algo->{
        Titulo1.setValue("");
        Cuerpo1.setValue("");
        VentanaGuardar sub = new VentanaGuardar(); 
        
         UI.getCurrent().addWindow(sub);
        
        });
                     
        //Agregamos los componentes al layout
        layout.addComponent(e1);
        layout.addComponent(b1);
        
        //Solo se agrega una vez y se agrega el layout a la pagina index 
        
setContent(layout);         
        
    // Guardamos datos en la tabla pero no en la base de datos
    
    /*  List<Mensajitos> mensajes = Arrays.asList(
        new Mensajitos("Nicolaus Copernicus", "1543"),
        new Mensajitos("Galileo Galilei", "1564"),
        new Mensajitos("Johannes Kepler", "1571")); 
    */
    
   List<Mensajitos> mensajes = (List<Mensajitos>) repoMensa.findAll();
 
    // crea la laista de la base de datos
        Grid<Mensajitos> grid = new Grid<>();
        
        grid.setItems(mensajes);
        grid.addColumn(Mensajitos::getId).setCaption("Id");
        grid.addColumn(Mensajitos::getTitulo).setCaption("Titulo");
        grid.addColumn(Mensajitos::getCuerpo).setCaption("Cuerpo");
   /*     
        Button b2=new Button("Nuevo");
        b2.addStyleNames(ValoTheme.BUTTON_DANGER);
        
         b2.addClickListener(e -> {
         grid.asSingleSelect().clear();
         form.setCustomer(new Mensajitos());
    */    
    // Declaramos la seleccion
        grid.setSelectionMode(SelectionMode.SINGLE);
            
        grid.addItemClickListener(evento -> {
            
           miId = evento.getItem().getId();
           Titulo1.setValue(evento.getItem().getTitulo());
           Cuerpo1.setValue(evento.getItem().getCuerpo());
           Ventana sub =  new  Ventana ();

            UI.getCurrent().addWindow(sub);
        });
           
    //Muestra nuestro contenido en pajina/
   /* layout.addComponent(b2);*/
    
    layout.addComponents(grid);
           
    setContent(layout);
   
    }
 

class Ventana extends Window {

    public Ventana() {
        super("Actualizacion");
        setWidth("400px");
        center();
        VerticalLayout vl2 = new VerticalLayout();


        Button boton = new Button("Actualizar");
        boton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        Button botonBorrar=new Button("Borrar");
        botonBorrar.addStyleName(ValoTheme.BUTTON_DANGER);
        botonBorrar.addClickListener(evento->{
            Page.getCurrent().reload();
            repoMensa.delete(miId);
        });
        
        boton.addClickListener(evento -> {
            Page.getCurrent().reload();
        
            repoMensa.save(new Mensajitos(miId,Titulo1.getValue(),Cuerpo1.getValue()));
            close();
        });
        vl2.addComponent(Titulo1);
        vl2.addComponent(Cuerpo1);
        HorizontalLayout hl=new HorizontalLayout();
        
        hl.addComponent(boton);
        hl.addComponent(botonBorrar);
        vl2.addComponent(hl);

        setContent(vl2);
    }  
}

   public  class VentanaGuardar extends Window {

    public VentanaGuardar() {
        super("Guardar");
        setWidth("400px");
        center();
        VerticalLayout vl2 = new VerticalLayout();


        Button boton = new Button("Guardar");
        boton.addClickListener(evento -> {
            
            
            repoMensa.save(new Mensajitos(Titulo1.getValue(),Cuerpo1.getValue()));
            close();
            Page.getCurrent().reload();
        });
        vl2.addComponent(Titulo1);
        vl2.addComponent(Cuerpo1);
        vl2.addComponent(boton);

        setContent(vl2);
    }
}
}