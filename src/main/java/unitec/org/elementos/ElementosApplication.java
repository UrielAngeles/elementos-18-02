package unitec.org.elementos;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElementosApplication implements CommandLineRunner{
    
    @Autowired RepositorioMensajitos repoMensa;

	public static void main(String[] args) {
		SpringApplication.run(ElementosApplication.class, args);
	}

    @Override
    public void run(String... strings) throws Exception {
       //Mensajitos m=new Mensajitos("Primero", "Mi primer mensajito con hibernate");
        //repoMensa.save(m);
        /*ArrayList<Mensajitos> mensajitos =(ArrayList<Mensajitos>)repoMensa.findAll();
        for(Mensajitos mensa:mensajitos){
        
            System.out.println(mensa.getTitulo());
            System.out.println(mensa.getCuerpo());
        }
        */
    
        
        //Ahora probaremos el de buscar
        /*Mensajitos m1=repoMensa.findOne(1);
        System.out.println(m1.getTitulo());
        */
        //Actualizacion
        repoMensa.save(new Mensajitos(1, "Otro", "Malo"));
        
        ArrayList<Mensajitos> mensajitos =(ArrayList<Mensajitos>)repoMensa.findAll();
        for(Mensajitos mensa:mensajitos){
        
            System.out.println(mensa.getTitulo());
            System.out.println(mensa.getCuerpo());
        }
        
        //Borrar
        repoMensa.delete(8);
    }
}
