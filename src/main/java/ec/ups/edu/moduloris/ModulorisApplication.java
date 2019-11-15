package ec.ups.edu.moduloris;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import jade.core.Profile;
import jade.core.ProfileImpl;

@SpringBootApplication
public class ModulorisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModulorisApplication.class, args);
		
		// Inicializa los agentes
		agentload();
	}

	
	private static void agentload() {
		String host = "localhost";
		int port = -1; // puerto por defecto omite 1099
		String platform = null; // default name
		boolean main = true;	

		Runtime runtime = Runtime.instance();
		Profile profile = null;
		AgentContainer container = null;

		profile = new ProfileImpl(host, port, platform, main);

		// Crea el contenedor principal
		container = runtime.createMainContainer(profile);

		// Creacion de los agentes
		try {
			// Inicializa el agente HIS
			AgentController agentHIS = container.createNewAgent("SearchHIS", "ec.ups.edu.agent.SearchHIS", null);
			agentHIS.start();
			
		} catch (StaleProxyException e) {
			throw new RuntimeException(e);
		}
	}

}
