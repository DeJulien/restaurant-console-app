package dev.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dev.entite.Plat;



@Repository
public class PlatDaoJpa implements IPlatDao {
	
	@PersistenceContext private EntityManager em;
	

	
	public List<Plat> listerPlats() {
		String sql = "Select p From Plat p";
		
		TypedQuery<Plat> query1 = em.createQuery(sql,Plat.class);
		List<Plat> plat=query1.getResultList();
		
		return plat;
	}
	
	
	@Transactional
	public void ajouterPlat(String nomPlat, Integer prixPlat) {
		Plat plat = new Plat(nomPlat, prixPlat);
        em.persist(plat);
		
	}

}
