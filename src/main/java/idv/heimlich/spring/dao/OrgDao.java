package idv.heimlich.spring.dao;

import idv.heimlich.spring.domain.OrgDo;

import java.util.List;

import javax.sql.DataSource;

public interface OrgDao {
	
	public void setDataSource(DataSource ds);
	
	public boolean create(OrgDo orgDo);
	
	public OrgDo getOrg(Integer id);
	
	public List<OrgDo> getAllOrgs();
	
	public boolean delete(OrgDo orgDo);
	
	public boolean update(OrgDo orgDo);
	
	public void cleanup();

}
