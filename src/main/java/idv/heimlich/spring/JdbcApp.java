package idv.heimlich.spring;

import idv.heimlich.spring.dao.OrgDao;
import idv.heimlich.spring.domain.OrgDo;
import idv.heimlich.spring.utils.DaoUtils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class JdbcApp {
	
	@Autowired
	private OrgDao dao;
	
	@Autowired
	private DaoUtils daoUtils;
	
	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("bean-cp.xml");
		JdbcApp app = ctx.getBean(JdbcApp.class);
		app.actionMethod();
		
//		OrgDao dao = (OrgDao) ctx.getBean("orgDao");
		
//		DaoUtils.createSeedDate(dao);
//		
//		List<OrgDo> orgs = dao.getAllOrgs();
//		DaoUtils.printOrg(orgs, DaoUtils.READ_OPERATION);
//		
//		OrgDo orgDo = new OrgDo("TEST3", 2003, "CCCCC"); 
//		boolean isCreated = dao.create(orgDo);
//		DaoUtils.printSuccessFailure(DaoUtils.CREATE_OPERATION, isCreated);
//		DaoUtils.printOrg(dao.getAllOrgs(), DaoUtils.READ_OPERATION);
//		
//		OrgDo orgDo2 = dao.getOrg(1);
//		DaoUtils.printOrg(orgDo2, "getOrg");
//		
//		OrgDo orgDo3 = dao.getOrg(2);
//		orgDo3.setCode("ZZZZZ");
//		boolean isUpdated = dao.update(orgDo3);
//		DaoUtils.printSuccessFailure(DaoUtils.UPDATE_OPERATION, isUpdated);
//		DaoUtils.printOrg(dao.getOrg(2), DaoUtils.UPDATE_OPERATION);
//		
//		boolean isDeleted = dao.delete(dao.getOrg(3));
//		DaoUtils.printSuccessFailure(DaoUtils.DELETE_OPERATION, isDeleted);
//		DaoUtils.printOrg(dao.getAllOrgs(), DaoUtils.DELETE_OPERATION);
//		
//		dao.cleanup();
//		DaoUtils.printOrgCount(dao.getAllOrgs(), DaoUtils.CLEANUP_OPERATION);
//		
////		for (OrgDo orgDo : orgs) {
////			System.out.println(orgDo);
////		}
		
		((ClassPathXmlApplicationContext) ctx).close();		
	}
	
	public void actionMethod() {
		
		this.daoUtils.createSeedDate(this.dao);
		
		List<OrgDo> orgs = this.dao.getAllOrgs();
		this.daoUtils.printOrg(orgs, this.daoUtils.READ_OPERATION);
		
		OrgDo orgDo = new OrgDo("TEST3", 2003, "CCCCC"); 
		boolean isCreated = this.dao.create(orgDo);
		this.daoUtils.printSuccessFailure(this.daoUtils.CREATE_OPERATION, isCreated);
		this.daoUtils.printOrg(this.dao.getAllOrgs(), this.daoUtils.READ_OPERATION);
		
		OrgDo orgDo2 = this.dao.getOrg(1);
		this.daoUtils.printOrg(orgDo2, "getOrg");
		
		OrgDo orgDo3 = this.dao.getOrg(2);
		orgDo3.setCode("ZZZZZ");
		boolean isUpdated = this.dao.update(orgDo3);
		this.daoUtils.printSuccessFailure(this.daoUtils.UPDATE_OPERATION, isUpdated);
		this.daoUtils.printOrg(this.dao.getOrg(2), this.daoUtils.UPDATE_OPERATION);
		
		boolean isDeleted = this.dao.delete(this.dao.getOrg(3));
		this.daoUtils.printSuccessFailure(this.daoUtils.DELETE_OPERATION, isDeleted);
		this.daoUtils.printOrg(this.dao.getAllOrgs(), this.daoUtils.DELETE_OPERATION);
		
		this.dao.cleanup();
		this.daoUtils.printOrgCount(this.dao.getAllOrgs(), this.daoUtils.CLEANUP_OPERATION);
		
//		for (OrgDo orgDo : orgs) {
//			System.out.println(orgDo);
//		}		
	}

}
