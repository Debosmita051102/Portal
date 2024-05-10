package soumya.megatronix.portal2023.PortalRestAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

//@CrossOrigin(origins = "https://msitparidhi.in")
@CrossOrigin(origins = "**")
@SpringBootApplication
public class PortalRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortalRestApiApplication.class, args);
		System.out.println("hi RESTAPI");
	}

}
