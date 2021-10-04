package idv.heimlich.spring.dao.impl;

import idv.heimlich.spring.dao.OrgDao;
import idv.heimlich.spring.domain.OrgDo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

//@Repository("orgDao")
@Repository
public class OrgDaoImpl implements OrgDao {
	
//	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	protected static RowMapper<OrgDo> CONVERTER = new RowMapper<OrgDo>() {

		public OrgDo mapRow(ResultSet rs, int rowNum) throws SQLException {
			OrgDo orgDo = new OrgDo();
			orgDo.setId(rs.getInt("id"));
			orgDo.setName(rs.getString("name"));
			orgDo.setYear(rs.getInt("year"));
			orgDo.setCode(rs.getString("code"));
			return orgDo;
		}
		
	};
	
	public RowMapper<OrgDo> getConverter() {
		return CONVERTER;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
//		this.jdbcTemplate = new JdbcTemplate(dataSource);		
	}

	public boolean create(OrgDo orgDo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(orgDo);
		String sql = "INSERT INTO org (id, name, year, code) "
				+ " VALUES (nvl((select max(id) from org), 0) + 1, :name, :year, :code) ";
		return this.namedParameterJdbcTemplate.update(sql, params) == 1;
		
//		String sql = "INSERT INTO org (id, name, year, code) "
//				+ " VALUES (nvl((select max(ID) from org), 0) + 1, ?, ?, ?) ";
//		Object[] args = new Object[] {orgDo.getName(), orgDo.getYear(), orgDo.getCode()};
//		return this.jdbcTemplate.update(sql, args) == 1;
	}

	public OrgDo getOrg(Integer id) {
		SqlParameterSource params = new MapSqlParameterSource("id", id);
		String sql = "SELECT id, name, year, code FROM org WHERE id = :id ";
		return this.namedParameterJdbcTemplate.queryForObject(sql, params, CONVERTER);
		
//		String sql = "SELECT id, name, year, code FROM org WHERE id = ? ";
//		Object[] args = new Object[] {id};
//		OrgDo orgDo = this.jdbcTemplate.queryForObject(sql, args, CONVERTER);
//		return orgDo;
	}

	public List<OrgDo> getAllOrgs() {
		String sql = "SELECT * FROM org ";
		List<OrgDo> orgList = this.namedParameterJdbcTemplate.query(sql, CONVERTER);
		return orgList;
		
//		String sql = "SELECT * FROM org ";
////		List<OrgDo> orgList = jdbcTemplate.query(sqlQuery, new OrgRowMapper());
//		List<OrgDo> orgList = this.jdbcTemplate.query(sql, CONVERTER);
//		return orgList;
	}

	public boolean delete(OrgDo orgDo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(orgDo);
		String sql = "DELETE FROM org WHERE id = :id ";		
		return this.namedParameterJdbcTemplate.update(sql, params) == 1;
		
//		String sql = "DELETE FROM org WHERE id = ? ";
//		Object[] args = new Object[] {orgDo.getId()};
//		return this.jdbcTemplate.update(sql, args) == 1;
	}

	public boolean update(OrgDo orgDo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(orgDo);
		String sql = "UPDATE org SET code = :code WHERE id = :id ";
		return this.namedParameterJdbcTemplate.update(sql, params) == 1;
		
//		String sql = "UPDATE org SET code = ? WHERE id = ? ";
//		Object[] args = new Object[] {orgDo.getCode(), orgDo.getId()};
//		return this.jdbcTemplate.update(sql, args) == 1;
	}

	/**
	 * Delete 逐筆刪
	 * Truncate table 全刪，TABLE會留
	 * Drop table 全刪，TABLE不留
	 */
	public void cleanup() {
		String sql = "TRUNCATE TABLE org ";
		this.namedParameterJdbcTemplate.getJdbcOperations().execute(sql);
//		this.jdbcTemplate.execute(sql);
	}

}
