package co.com.nuevaera.gae.resources;

import java.util.ArrayList;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import co.com.nuevaera.gae.model.NuevaEraPersistCtrl;
import co.com.nuevaera.gae.services.client.dto.ElementoDto;

import com.google.gson.Gson;

public class ElementResource extends ServerResource {

	@Get("json")
	public String handleGet() {
		String rep = null;
		try {
			String idCategoria = getQuery().getValues("idCategoria");
			if (null != idCategoria) {
				NuevaEraPersistCtrl persistCtrl = new NuevaEraPersistCtrl();
				ArrayList<ElementoDto> elementos = persistCtrl.getElementos(new Long(idCategoria), false);
				Gson  gson = new Gson();
				rep = gson.toJson(elementos);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rep;
	}

}
