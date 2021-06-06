package mx.edu.itlapiedad.dao.importes;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import mx.edu.itlapiedad.models.Importes;


@Repository
public class ImportesJDBC implements ImportesDAO {

	@Autowired
	JdbcTemplate conexion;
	

	@Override
	public List<Importes> consultarImportes(int id, Timestamp fecha_horai, Timestamp fecha_horaf) {
		String sql_query = "SELECT  c.id, c.nombre, SUM(importe) AS total_importe FROM ticket_renglones tr JOIN tickets t ON tr.TICKET_id = t.id INNER JOIN cajeros c ON c.id=t.CAJERO_id WHERE  c.id=? AND t.fecha_hora BETWEEN ? AND ?";
		return conexion.query(sql_query, new RowMapper<Importes>() {

			@Override
			public Importes mapRow(ResultSet rs, int rowNum) throws SQLException {
				Importes importes = new Importes();
				importes.setCajero_id(rs.getInt("c.id"));
				importes.setCajero_nombre(rs.getString("c.nombre"));
				importes.setImporte(rs.getFloat("total_importe"));
				return importes;	
			}
			
		}, id, fecha_horai, fecha_horaf);
		
}
}
