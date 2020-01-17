package dev.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import dev.entite.Plat;

public class PlatDaoJdbc implements IPlatDao, RowMapper<Plat> {

	private JdbcTemplate jdbcTemplate;
	
	public PlatDaoJdbc(DataSource datasource) {
		  this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	
	@Override
	public List<Plat> listerPlats() {
		RowMapper<Plat> mapper = (ResultSet rs, int rowNum) -> {
			  Plat p = new Plat();
			  p.setNom(rs.getString("nom"));
			  p.setPrixEnCentimesEuros(rs.getInt("prix"));
			  return p;
			};
		
		String sql = "SELECT * FROM PLAT";
		List<Plat> plats = jdbcTemplate.query(sql, mapper);
		return plats;
	}

	@Override
	public void ajouterPlat(String nomPlat, Integer prixPlat) {
		
		String sql = "INSERT INTO PLAT (nom,prix) VALUES(?,?)";
		jdbcTemplate.update(sql, nomPlat, prixPlat);
		
	}


	@Override
	public Plat mapRow(ResultSet rs, int rowNum) throws SQLException {
		  Plat p = new Plat();
		  p.setNom(rs.getString("nom"));
		  p.setPrixEnCentimesEuros(rs.getInt("prix"));
		  return p;
	}

}
