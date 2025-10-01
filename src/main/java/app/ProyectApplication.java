package app;

import app.infrastructure.gui.ClinicConsoleApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//es la interfaz que permite ejecutar código apenas arranca la aplicación.
public class ProyectApplication implements CommandLineRunner {
    
    //sirve para inyectar dependencias automáticamente 
    //Spring crea un objeto y lo pone aquí sin necesidad de new
    @Autowired
    private ClinicConsoleApp consoleApp;
    
    public static void main(String[] args) {
        SpringApplication.run(ProyectApplication.class, args);
    }
    
    
    //El @Override está para indicar que estamos sobrescribiendo el método run de la interfaz CommandLineRunner
    @Override
    //cuando la aplicación corre, inmediatamente se abre la interfaz de consola
    //donde se puede interactuar con la clínica
    public void run(String... args) throws Exception {
        consoleApp.start();
    }
}



/*Anotación	Propósito principal
@Override	Sobrescribir métodos de la clase padre o interfaz.
@Autowired	Inyección automática de dependencias.
@Component	Marca una clase como bean de Spring.
@Service	Bean especializado en lógica de negocio.
@Repository	Bean especializado en acceso a datos.
@Controller	Bean que maneja solicitudes HTTP.
@Entity	Marca una clase como entidad para base de datos.
@Value	Inyecta valores de configuración en propiedades de clase.


@Component “registrar al empleado en la empresa”.
@Autowired “darle automáticamente las herramientas que necesita”.
@Service “empleado que hace tareas importantes (lógica)”.
@Repository “empleado que guarda y busca datos en el almacén (DB)”.
@Controller “empleado que atiende clientes (HTTP)”.*/