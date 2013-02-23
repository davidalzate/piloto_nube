package co.com.nuevaera.gae.resources;

import java.util.ArrayList;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import co.com.nuevaera.gae.context.AppContextAware;
import co.com.nuevaera.gae.model.NuevaEraPersistCtrl;
import co.com.nuevaera.gae.services.client.dto.AnuncioDto;

import com.google.gson.Gson;

public class AdResource extends ServerResource {

	@Get("json")
	public String handleGet() {
		String rep = null;
		try {
			String idRestaurante = getQuery().getValues("idRestaurante");

			if (null != idRestaurante) {

				String propiedad = getQuery().getValues("propiedad");
				
				AppContextAware appContextAware = new AppContextAware();
				appContextAware.setIdRestaurante(new Long(idRestaurante));
				appContextAware.setPropiedad(propiedad);
				
				NuevaEraPersistCtrl persistCtrl = new NuevaEraPersistCtrl();
				ArrayList<AnuncioDto> elementos = persistCtrl.getAnuncios(appContextAware);
				Gson gson = new Gson();
				rep  = gson.toJson(elementos);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rep;
	}

}
