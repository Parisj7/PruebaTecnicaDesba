package Project.PruebaTecnicaFrancoParis;

import Project.PruebaTecnicaFrancoParis.Model.GraphicInterface;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PruebaTecnicaFrancoParisApplication {			// Metodo principal donde desde la interfaz trabajaremos

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(PruebaTecnicaFrancoParisApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);

		GraphicInterface graphicInterface = new GraphicInterface();
		graphicInterface.setVisible(true);
	}
}