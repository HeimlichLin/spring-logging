package idv.heimlich.spring.utils;

import idv.heimlich.spring.dao.OrgDao;
import idv.heimlich.spring.domain.OrgDo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DaoUtils {
	
	private static Logger LOGGER = LoggerFactory.getLogger("Logging Tester");
	public final String CREATE_OPERATION = "CREATE";
	public final String READ_OPERATION = "READ";
	public final String UPDATE_OPERATION = "UPDATE";
	public final String DELETE_OPERATION = "DELETE";
	public final String CLEANUP_OPERATION = "TRUNCATE";
	
	public void printOrg(List<OrgDo> orgs, String operation) {
		LOGGER.info("\n****** printing orgs after " + operation + " operation ******\n");
		for (OrgDo orgDo : orgs) {
			LOGGER.info(orgDo.toString());
		}
	}
	
	public void printOrg(OrgDo orgDo ,String operation) {
		LOGGER.info("\n****** printing orgs after invoking " + operation + " ******\n" + orgDo);
	}
	
	public void printSuccessFailure(String operation, boolean param) {
		if (param) {
			LOGGER.info("\nOperation " + operation + " successful");
		} else {
			LOGGER.info("\nOperation " + operation + " failed");
		}
	}
	
	public void createSeedDate(OrgDao dao) {
		OrgDo arg1 = new OrgDo("TEST1", 2001, "AAAAA");
		OrgDo arg2 = new OrgDo("TEST2", 2002, "BBBBB");
		
		List<OrgDo> orgs = new ArrayList<OrgDo>();
		orgs.add(arg1);
		orgs.add(arg2);
		
		int createCount = 0;
		for (OrgDo orgDo : orgs) {
			boolean isCreated = dao.create(orgDo);
			if (isCreated) {
				createCount += 1;
			}
		}
		
		LOGGER.info("Created " + createCount + " orgs");
	}
	
	public void printOrgCount(List<OrgDo> orgs, String operation) {
		LOGGER.info("\n****** currently we have " + orgs.size() + " orgs after " + operation + " operation" + " ******");
	}

}
