package co.com.nuevaera.gae.resources;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import co.com.nuevaera.gae.model.NuevaEraPersistCtrl;
import co.com.nuevaera.gae.services.client.dto.AnuncioDto;
import co.com.nuevaera.gae.services.client.dto.AnuncioEmisionDto;
import co.com.nuevaera.gae.services.client.dto.CategoriaDto;
import co.com.nuevaera.gae.services.client.dto.ElementoDto;
import co.com.nuevaera.gae.services.client.dto.EmisionDto;
import co.com.nuevaera.gae.services.client.dto.EmpresaDto;
import co.com.nuevaera.gae.services.client.dto.RestauranteDto;

import com.google.gson.Gson;

public class IdentityResource extends ServerResource {

	@Get("json")
	public String handleGet() {
		String resp = new String();
		try {			
			if(null!=getQuery().getValues("populate")){
				if(getQuery().getValues("populate").equalsIgnoreCase("yes")){
					populateTest();					
				}
			}
			JSONObject json = new JSONObject();
			JsonRepresentation jsonRep = new JsonRepresentation(json);
			
			String idParam = getQuery().getValues("id");
			NuevaEraPersistCtrl persistCtrl  = new NuevaEraPersistCtrl();
			
			if(null!=idParam){
				json.put("name", "value");
				
				
				Long idRestaurante = new Long(idParam); 
				
				RestauranteDto restauranteDto = persistCtrl.getRestaurante(idRestaurante, false);
				if(null!=restauranteDto){
					json.put("name", restauranteDto.getNombre());
					json.put("banner", restauranteDto.getBanner());
					json.put("fondo", restauranteDto.getFondo());					
				}
				
				resp =  jsonRep.getText();
			}else{
				ArrayList<RestauranteDto> restaurantes = persistCtrl.getRestaurantes();
				Gson gson = new Gson();
				resp = gson.toJson(restaurantes);
			}			
			
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resp;
	}
	
	private void populateTest(){

		NuevaEraPersistCtrl persistCtrl  = new NuevaEraPersistCtrl();
		
		RestauranteDto restauranteDto = new RestauranteDto();
		restauranteDto.setNombre("R2");
		restauranteDto.setBanner("http://www.ofifacil.com/docs/trabajos-diseno-grafico-graphic-design/diseno-banners-design/banner-restaurante04.jpg");
		restauranteDto.setFondo("/R2f.png");

		Long idRestaurante = persistCtrl.addRestaurante(restauranteDto);
		
		System.out.println("idR="+idRestaurante);
		
		CategoriaDto categoriaDto = new CategoriaDto();
		categoriaDto.setNombre("C1");
		categoriaDto.setFoto("http://www.canrierahotel.com/img/banner_restaurante.jpg");
		categoriaDto.setIdRestaurante(idRestaurante);
		
		long c1 = persistCtrl.addCategoria(categoriaDto);
		
		System.out.println("idc="+c1);
				
		ElementoDto elementoDto = new ElementoDto();
		elementoDto.setIdCategoria(c1);
		elementoDto.setNombre("E1");
		elementoDto.setDescripcionCorta("E1Dc");
		elementoDto.setDescripcionLarga("E1Dl");
		elementoDto.setDisponible(true);
		elementoDto.setFotoBig("http://www.puntacana.com/es/assets/Image/banner-dr-restaurants-cocoloba.jpg");
		elementoDto.setFotoSmall("E1Fs");
		elementoDto.setPrecio("Ep1");

		persistCtrl.addElemento(elementoDto);
		
		elementoDto.setIdCategoria(c1);
		elementoDto.setNombre("E2");
		elementoDto.setDescripcionCorta("E2Dc");
		elementoDto.setDescripcionLarga("E2Dl");
		elementoDto.setDisponible(true);
		elementoDto.setFotoBig("E2Fb");
		elementoDto.setFotoSmall("E2Fs");
		elementoDto.setPrecio("Ep2");		

		persistCtrl.addElemento(elementoDto);
		

		categoriaDto.setNombre("C2");
		categoriaDto.setFoto("C2f");
		categoriaDto.setIdRestaurante(idRestaurante);		
		
		long c2 = persistCtrl.addCategoria(categoriaDto);

		elementoDto.setIdCategoria(c2);
		elementoDto.setNombre("E3");
		elementoDto.setDescripcionCorta("E3Dc");
		elementoDto.setDescripcionLarga("E3Dl");
		elementoDto.setDisponible(true);
		elementoDto.setFotoBig("E3Fb");
		elementoDto.setFotoSmall("E3Fs");
		elementoDto.setPrecio("Ep3");

		persistCtrl.addElemento(elementoDto);
		
		elementoDto.setIdCategoria(c2);
		elementoDto.setNombre("E4");
		elementoDto.setDescripcionCorta("E4Dc");
		elementoDto.setDescripcionLarga("E4Dl");
		elementoDto.setDisponible(true);
		elementoDto.setFotoBig("E4Fb");
		elementoDto.setFotoSmall("E4Fs");
		elementoDto.setPrecio("Ep4");		

		persistCtrl.addElemento(elementoDto);

		categoriaDto.setNombre("C3");
		categoriaDto.setFoto("C3f");
		categoriaDto.setIdRestaurante(idRestaurante);		
		
		long c3 = persistCtrl.addCategoria(categoriaDto);

		elementoDto.setIdCategoria(c3);
		elementoDto.setNombre("E5");
		elementoDto.setDescripcionCorta("E5Dc");
		elementoDto.setDescripcionLarga("E5Dl");
		elementoDto.setDisponible(true);
		elementoDto.setFotoBig("E5Fb");
		elementoDto.setFotoSmall("E5Fs");
		elementoDto.setPrecio("Ep5");

		persistCtrl.addElemento(elementoDto);
		
		elementoDto.setIdCategoria(c3);
		elementoDto.setNombre("E6");
		elementoDto.setDescripcionCorta("E6Dc");
		elementoDto.setDescripcionLarga("E6Dl");
		elementoDto.setDisponible(true);
		elementoDto.setFotoBig("E6Fb");
		elementoDto.setFotoSmall("E6Fs");
		elementoDto.setPrecio("Ep6");		

		persistCtrl.addElemento(elementoDto);
		
		
		EmpresaDto empresa = new EmpresaDto();
		empresa.setNombre("Nueva Era");

		long e1 = persistCtrl.addEmpresa(empresa);
		
		AnuncioDto anuncioDto = new AnuncioDto();
		anuncioDto.setIdEmpresa(e1);
		anuncioDto.setFotoSmall("A1Fs");
		anuncioDto.setFotoBig("A1Fb");
		anuncioDto.setDuracion(400);
		
		long ida1 = persistCtrl.addAnuncio(anuncioDto);

		anuncioDto.setIdEmpresa(e1);
		anuncioDto.setFotoSmall("A2Fs");
		anuncioDto.setFotoBig("A2Fb");
		anuncioDto.setDuracion(400);
		
		long ida2 = persistCtrl.addAnuncio(anuncioDto);

		
		anuncioDto.setIdEmpresa(e1);
		anuncioDto.setFotoSmall("A3Fs");
		anuncioDto.setFotoBig("A3Fb");
		anuncioDto.setDuracion(400);
		
		long ida3 = persistCtrl.addAnuncio(anuncioDto);
		
		
		EmisionDto emisionDto = new EmisionDto();
		emisionDto.setIdRestaurante(idRestaurante);
		emisionDto.setPropiedad("semanasanta");
		
		long idae1 = persistCtrl.addEmision(emisionDto);
		
		AnuncioEmisionDto anuncioEmisionDto = new AnuncioEmisionDto();
		anuncioEmisionDto.setIdEmision(idae1);
		anuncioEmisionDto.setIdAnuncio(ida1);
		
		persistCtrl.addAnuncioEmision(anuncioEmisionDto);
		

		anuncioEmisionDto.setIdEmision(idae1);
		anuncioEmisionDto.setIdAnuncio(ida2);
		
		persistCtrl.addAnuncioEmision(anuncioEmisionDto);

		
		anuncioEmisionDto.setIdEmision(idae1);
		anuncioEmisionDto.setIdAnuncio(ida3);
		
		persistCtrl.addAnuncioEmision(anuncioEmisionDto);

	}
}