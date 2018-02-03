/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitec.org.elementos;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author UriDrack7
 */
@SpringUI
@Theme("valo")
public class MiUI extends UI {

    @Autowired RepositorioMensajitos repoMensa;
    @Override
    protected void init(VaadinRequest request) {
        //Agregaremos un layout vertical y dentro de el los componentes que quedaran en orden descendente
        VerticalLayout layout=new VerticalLayout();
        Label e1=new Label("UriDrack7!!!");
        e1.addStyleName(ValoTheme.LABEL_H1);
        
        Button b1=new Button("Guardar");
        b1.addStyleNames(ValoTheme.BUTTON_DANGER);
        
        //Vamos a programar el evento usando programacion funconal
        
        b1.addClickListener(algo->{
            //Aqui ponemos el evento
            e1.setValue("Mi primer Programación Funcional");
        });
        
        //Agregamos los componentes al layout
        layout.addComponent(e1);
        layout.addComponent(b1);
        
        //Solo se agrega una vez y se agrega el layout a la pagina index 
        
    
        // Have some data
        List<Mensajitos> mensajitos = (List<Mensajitos>) repoMensa.findAll();
           

    // Create a grid bound to the list
        Grid<Mensajitos> grid = new Grid<>();
        grid.setItems(mensajitos);
        grid.addColumn(Mensajitos::getTitulo).setCaption("Titulo");
        grid.addColumn(Mensajitos::getCuerpo).setCaption("Cuerpo");


   // switch to multiselect mode
        grid.setSelectionMode(SelectionMode.MULTI);

        grid.addSelectionListener(event -> {
            Set<Mensajitos> selected = event.getAllSelectedItems();
            Notification.show(selected.size() + " registros seleccionados");
        });

    layout.addComponent(grid);
        setContent(layout);
    }
    
}