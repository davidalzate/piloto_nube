package co.com.nuevaera.gae.services.server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import co.com.nuevaera.gae.resources.AdResource;
import co.com.nuevaera.gae.resources.CategoryResource;
import co.com.nuevaera.gae.resources.ElementResource;
import co.com.nuevaera.gae.resources.IdentityResource;


public class InitRestService extends Application {
	  @Override
	public Restlet createInboundRoot() {
			// Create a router Restlet that routes each call to a new Resource
			Router router = new Router(getContext());

			router.attach("/nuevaera/identityresource",IdentityResource.class);
			router.attach("/nuevaera/categoryresource",CategoryResource.class);
			router.attach("/nuevaera/elementresource",ElementResource.class);
			router.attach("/nuevaera/adresource",AdResource.class);
			return router;
			
	}
	 
	

}
