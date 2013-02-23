package co.com.nuevaera.gae.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.com.nuevaera.gae.context.AppContextAware;
import co.com.nuevaera.gae.model.entity.Anuncio;
import co.com.nuevaera.gae.model.entity.AnuncioEmision;
import co.com.nuevaera.gae.model.entity.Categoria;
import co.com.nuevaera.gae.model.entity.Elemento;
import co.com.nuevaera.gae.model.entity.Emision;
import co.com.nuevaera.gae.model.entity.Empresa;
import co.com.nuevaera.gae.model.entity.Restaurante;
import co.com.nuevaera.gae.model.entity.View;
import co.com.nuevaera.gae.services.client.dto.AnuncioDto;
import co.com.nuevaera.gae.services.client.dto.AnuncioEmisionDto;
import co.com.nuevaera.gae.services.client.dto.CategoriaDto;
import co.com.nuevaera.gae.services.client.dto.ElementoDto;
import co.com.nuevaera.gae.services.client.dto.EmisionDto;
import co.com.nuevaera.gae.services.client.dto.EmpresaDto;
import co.com.nuevaera.gae.services.client.dto.RestauranteDto;
import co.com.nuevaera.gae.services.client.dto.ViewDto;

public class NuevaEraPersistCtrl {

	
	public long addEmpresa(EmpresaDto empresaDto){
		EntityManager em = EMF.get().createEntityManager();
		Empresa empresa = new Empresa();
		try{
			empresa.setNombre(empresaDto.getNombre());
			em.persist(empresa);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			em.close();
		}
		return empresa.getIdEmpresa().getId();
	}	
	
	public void addView(ViewDto viewDto){
		EntityManager em = EMF.get().createEntityManager();
		View view = new View();
		try{
			view.setClick(viewDto.isClick());
			view.setIdAnuncio(viewDto.getIdAnuncio());
			view.setIdRestaurante(viewDto.getIdRestaurante());
			view.setDuracionVisita(viewDto.getDuracionVisita());
			view.setFecha(viewDto.getFecha());			
			em.persist(view);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			em.close();
		}
	}

	public ArrayList <RestauranteDto> getRestaurantes(){
		ArrayList <RestauranteDto> restauranteDtos = new ArrayList<RestauranteDto>();
		
		RestauranteDto restauranteDto = null;
		EntityManager em = EMF.get().createEntityManager();

		try{
			Query  query = em.createQuery("select from " + Restaurante.class.getName() + " r ");
			List<Restaurante> listRestaurantes = query.getResultList();
			for (Iterator <Restaurante>iterator = listRestaurantes.iterator(); iterator
					.hasNext();) {
				Restaurante restaurante =  iterator.next();
				 restauranteDto = new RestauranteDto();
				 restauranteDto.setNombre(restaurante.getNombre());
				 restauranteDto.setBanner(restaurante.getBanner());
				 restauranteDto.setFondo(restaurante.getFondo());
				 restauranteDto.setIdRestaurante(restaurante.getIdRestaurante().getId());
				 restauranteDtos.add(restauranteDto);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			em.close();
		}
		return restauranteDtos;
	}	
	public RestauranteDto getRestaurante(Long idRestaurante, boolean lazy){
		RestauranteDto restauranteDto = null;
		EntityManager em = EMF.get().createEntityManager();

		try{
			Query  query = em.createQuery("select from " + Restaurante.class.getName() + " r where r.idRestaurante = :id");
			query.setParameter("id", idRestaurante);
			query.setMaxResults(1);
			Restaurante restaurante = (Restaurante)query.getSingleResult();
			 if(null!=restaurante){
				 restauranteDto = new RestauranteDto();
				 restauranteDto.setNombre(restaurante.getNombre());
				 restauranteDto.setBanner(restaurante.getBanner());
				 restauranteDto.setFondo(restaurante.getFondo());
				 restauranteDto.setIdRestaurante(idRestaurante);
			 }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			em.close();
		}
		return restauranteDto;
	}
	
	public long addRestaurante(RestauranteDto restauranteDto){
		EntityManager em = EMF.get().createEntityManager();
		Restaurante restaurante = new Restaurante();
		try{
			restaurante.setNombre(restauranteDto.getNombre());
			restaurante.setFondo(restauranteDto.getFondo());
			restaurante.setBanner(restauranteDto.getBanner());
			em.persist(restaurante);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			em.close();
		}
		return restaurante.getIdRestaurante().getId();
	}
	


	public long addCategoria(CategoriaDto categoriaDto){
		EntityManager em = EMF.get().createEntityManager();
		Categoria categoria = new Categoria();
		try{
			categoria.setNombre(categoriaDto.getNombre());
			categoria.setIdRestaurante(categoriaDto.getIdRestaurante());
			categoria.setFoto(categoriaDto.getFoto());
			em.persist(categoria);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			em.close();
		}
		return categoria.getIdCategoria().getId();
	}	
	
	public ArrayList<CategoriaDto> getCategorias(Long idRestaurante, boolean lazy){
		ArrayList<CategoriaDto> categorias = new ArrayList<CategoriaDto>();
		CategoriaDto categoriaDto = null;
		EntityManager em = EMF.get().createEntityManager();

		try{
			Query  query = em.createQuery("select from " + Categoria.class.getName() + " r where r.idRestaurante = :id");
			query.setParameter("id", idRestaurante);
			List<Categoria> categoriasList = query.getResultList();
			for (Iterator<Categoria> iterator = categoriasList.iterator(); iterator.hasNext();) {
				Categoria categoria = iterator.next();
				 categoriaDto = new CategoriaDto();
				 categoriaDto.setIdCategoria(categoria.getIdCategoria().getId());
				 categoriaDto.setNombre(categoria.getNombre());
				 categoriaDto.setFoto(categoria.getFoto());
				 categoriaDto.setIdRestaurante(idRestaurante);
				 categorias.add(categoriaDto);				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			em.close();
		}
		return categorias;
	}
	public CategoriaDto getCategoria(Long idCategoria){
		CategoriaDto categoriaDto = null;
		EntityManager em = EMF.get().createEntityManager();
		try{
			Query  query = em.createQuery("select from " + Categoria.class.getName() + " r where r.idCategoria = :id");
			query.setParameter("id", idCategoria);
			query.setMaxResults(1);
			Categoria categoria = (Categoria)query.getSingleResult();
			 if(null!=categoria){
				 categoriaDto = new CategoriaDto();
				 categoriaDto.setNombre(categoria.getNombre());
				 categoriaDto.setFoto(categoria.getFoto());
				 categoriaDto.setIdRestaurante(idCategoria);
			 }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			em.close();
		}
		return categoriaDto;
	}
	
	public long addElemento(ElementoDto elementoDto){
		EntityManager em = EMF.get().createEntityManager();
		Elemento elemento = new Elemento();
		try{
			elemento.setIdCategoria(elementoDto.getIdCategoria());			
			elemento.setNombre(elementoDto.getNombre());
			elemento.setDescripcionCorta(elementoDto.getDescripcionCorta());
			elemento.setDescripcionLarga(elementoDto.getDescripcionLarga());
			elemento.setDisponible(elementoDto.isDisponible());
			elemento.setFotoBig(elementoDto.getFotoBig());
			elemento.setFotoSmall(elementoDto.getFotoSmall());
			elemento.setPrecio(elementoDto.getPrecio());
			em.persist(elemento);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			em.close();
		}
		return elemento.getIdElemento().getId();
	}
	
	public ArrayList<ElementoDto> getElementos(Long idCategoria, boolean lazy){
		ArrayList<ElementoDto> elementoDtos = new ArrayList<ElementoDto>();
		ElementoDto elementoDto = null;
		EntityManager em = EMF.get().createEntityManager();
		try{
			Query  query = em.createQuery("select from " + Elemento.class.getName() + " r where r.idCategoria = :id");
			query.setParameter("id", idCategoria);
			
			List<Elemento> listElementos = query.getResultList();
			
			for (Iterator<Elemento> iterator = listElementos.iterator(); iterator
					.hasNext();) {
				Elemento elemento =  iterator.next();
				 elementoDto = new ElementoDto();
				 elementoDto.setIdElemento(elemento.getIdElemento().getId());
				 elementoDto.setNombre(elemento.getNombre());
				 elementoDto.setDescripcionCorta(elemento.getDescripcionCorta());
				 elementoDto.setDescripcionLarga(elemento.getDescripcionLarga());
				 elementoDto.setDisponible(elemento.isDisponible());
				 elementoDto.setFotoBig(elementoDto.getFotoBig());
				 elementoDto.setFotoSmall(elemento.getFotoSmall());
				 elementoDto.setIdCategoria(elemento.getIdCategoria());
				 elementoDto.setPrecio(elemento.getPrecio());
				 elementoDtos.add(elementoDto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			em.close();
		}
		return elementoDtos;
	}
	public ElementoDto getElemento(Long idElemento){
		ElementoDto elementoDto = null;
		EntityManager em = EMF.get().createEntityManager();
		try{
			Query  query = em.createQuery("select from " + Elemento.class.getName() + " r where r.idElemento = :id");
			query.setParameter("id", idElemento);
			query.setMaxResults(1);
			Elemento elemento = (Elemento)query.getSingleResult();
			 if(null!=elemento){
				 elementoDto = new ElementoDto();
				 elementoDto.setIdElemento(elemento.getIdElemento().getId());
				 elementoDto.setNombre(elemento.getNombre());
				 elementoDto.setDescripcionCorta(elemento.getDescripcionCorta());
				 elementoDto.setDescripcionLarga(elemento.getDescripcionLarga());
				 elementoDto.setDisponible(elemento.isDisponible());
				 elementoDto.setFotoBig(elementoDto.getFotoBig());
				 elementoDto.setFotoSmall(elemento.getFotoSmall());
				 elementoDto.setIdCategoria(elemento.getIdCategoria());
				 elementoDto.setPrecio(elemento.getPrecio());				 
			 }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			em.close();
		}
		return elementoDto;
	}
	
	public long addAnuncio(AnuncioDto anuncioDto){
		EntityManager em = EMF.get().createEntityManager();
		Anuncio anuncio = new Anuncio();
		try{
			anuncio.setIdEmpresa(anuncioDto.getIdEmpresa());
			anuncio.setFotoSmall(anuncioDto.getFotoSmall());
			anuncio.setFotoBig(anuncioDto.getFotoBig());
			anuncio.setDuracion(anuncioDto.getDuracion());

			em.persist(anuncio);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			em.close();
		}
		return anuncio.getIdAnuncio().getId();
	}
	
	public AnuncioDto getAnuncio(Long idAnuncio){
		AnuncioDto anuncioDto = null;
		EntityManager em = EMF.get().createEntityManager();

		try{
			Query  query = em.createQuery("select from " + Anuncio.class.getName() + " a where a.idAnuncio = :id");
			query.setParameter("id", idAnuncio);
			query.setMaxResults(1);
			Anuncio anuncio = (Anuncio)query.getSingleResult();
			 if(null!=anuncio){
				 anuncioDto = new AnuncioDto();
				 anuncioDto.setIdAnuncio(idAnuncio);
				 anuncioDto.setFotoBig(anuncio.getFotoBig());
				 anuncioDto.setFotoSmall(anuncio.getFotoSmall());
			 }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			em.close();
		}
		return anuncioDto;
	}
	
	public ArrayList<AnuncioDto> getAnuncios(AppContextAware context){
		ArrayList<AnuncioDto> anuncioDtos = new ArrayList<AnuncioDto>();

		EntityManager em = EMF.get().createEntityManager();

		try{
			Query  queryEmision;
			String queryFilter;
			
			if(null!=context.getPropiedad()){
				queryFilter = "where a.idRestaurante = :id and propiedad = :property";
				queryEmision = em.createQuery("select from " + Emision.class.getName() + " a " + queryFilter);
				queryEmision.setParameter("id", context.getIdRestaurante());
				queryEmision.setParameter("property", context.getPropiedad());
			}else{
				queryFilter = "where a.idRestaurante = :id";
				queryEmision = em.createQuery("select from " + Emision.class.getName() + " a " + queryFilter);
				queryEmision.setParameter("id", context.getIdRestaurante());				
			}
			queryEmision.setMaxResults(1);
			Emision emision = (Emision)queryEmision.getSingleResult();
			
			if(null!=emision){
				AnuncioDto anuncioDto = new AnuncioDto();
				Query queryAnuncios = em.createQuery("Select  from "+ AnuncioEmision.class.getName()+ " e  where e.idEmision = :id");
				queryAnuncios.setParameter("id", emision.getIdEmision().getId());
				List<AnuncioEmision> anuncioEmisionList = queryAnuncios.getResultList();
				
				for (Iterator<AnuncioEmision> iterator = anuncioEmisionList.iterator(); iterator
						.hasNext();) {
					AnuncioEmision anuncioEmision = iterator.next();
					Query  query = em.createQuery("select from " + Anuncio.class.getName() + " a where a.idAnuncio = :idA");
					query.setParameter("idA", anuncioEmision.getIdAnuncio());
					query.setMaxResults(1);
					Anuncio anuncio = (Anuncio)query.getSingleResult();
					 if(null!=anuncio){
						 anuncioDto = new AnuncioDto();
						 anuncioDto.setIdAnuncio(anuncio.getIdAnuncio().getId());
						 anuncioDto.setFotoBig(anuncio.getFotoBig());
						 anuncioDto.setFotoSmall(anuncio.getFotoSmall());
						 anuncioDto.setDuracion(anuncio.getDuracion());
						 anuncioDtos.add(anuncioDto);
					 }					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			em.close();
		}
		return anuncioDtos;
	}
	
	public long addEmision(EmisionDto emisionDto){
		EntityManager em = EMF.get().createEntityManager();
		Emision emision = new Emision();
		try{
			emision.setIdRestaurante(emisionDto.getIdRestaurante());
			emision.setPropiedad(emisionDto.getPropiedad());
			em.persist(emision);			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			em.close();
		}
		return emision.getIdEmision().getId();
	}
	
	public long addAnuncioEmision(AnuncioEmisionDto anuncioEmisionDto){
		EntityManager em = EMF.get().createEntityManager();
		AnuncioEmision anuncioEmision = new AnuncioEmision();
		try{
			anuncioEmision.setIdAnuncio(anuncioEmisionDto.getIdAnuncio());
			anuncioEmision.setIdEmision(anuncioEmisionDto.getIdEmision());
			em.persist(anuncioEmision);			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			em.close();
		}
		return anuncioEmision.getIdAnuncioEmision().getId();
	}	
	
}
