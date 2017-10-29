package pm.sboot.sdrest.ws;

import java.security.Principal;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
	private static final Logger log = LoggerFactory.getLogger(ProductController.class);
	
	
	@Autowired
	private KeycloakSecurityContext securityContext;
	@Autowired
	private AccessToken accessToken;

	@GetMapping(path = "/products")
	public String getProducts(Model model, Principal principal) {
		//TODO How to avoid keycloak dependency here? spring aop? How?
		log.error("AccessToken: " + securityContext.getTokenString());
		log.error("User: {} / {}", accessToken.getPreferredUsername(), accessToken.getName());
		log.error("Principal: {}", principal.getName());
		
		model.addAttribute("products", Arrays.asList("iPad", "iPhone", "iPod"));
		return "products";
	}

	@GetMapping(path = "/logout")
	public String logout(HttpServletRequest request) throws ServletException {
		request.logout();
		return "redirect:/";
	}
}