package weblanguages.project.client;

import javax.xml.rpc.ServiceException;
import it.cg.wl.sartoriWLC.*;
import it.cp.wl.sartoriWLC.*;
import it.gm.wl.sartoriWLC.*;
import it.sl.wl.sartoriWLC.*;

public class Connector {
	public GroupOperation go;
	public PersonOperation po;
	public SearchLabel sl;
	public GroupMetric gm;
	
	public Connector(){
		GroupOperationService cg_s = new GroupOperationServiceLocator();
		PersonOperationService  cp_s = new PersonOperationServiceLocator();
		SearchLabelService sl_s = new SearchLabelServiceLocator();
		GroupMetricService gm_s = new GroupMetricServiceLocator();

		try {
			go = cg_s.getSartoriWLC_CRUDGroup();
			po = cp_s.getSartoriWLC_CRUDPerson();
			sl = sl_s.getSartoriWLC_SearchLabel();
			gm = gm_s.getSartoriWLC_GroupMetrics();

		} catch (ServiceException e) {
			e.printStackTrace();
		}		
	}
}
