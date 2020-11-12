package com.rd.scm.dao;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.rd.scm.model.ServiceDomain;


public interface ServiceDomainDaoJpa extends CrudRepository<ServiceDomain, String> {

    
    Collection<ServiceDomain> findByStatusCd(String statusCd);

    /**
     * Returns a list of value objects representing the available service domains.
     *
     * @return list of value objects representing the available service domains.
     *
     * @throws EJBException
     */
    /*
    public List<ServiceDomain> getActiveServiceDomainsJDBC() throws EJBException {
        ArrayList<ServiceDomain> serviceDomainList = new ArrayList<ServiceDomain>();

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{

            con = statResourceBase.getConnection();
            pstmt = con.prepareStatement("SELECT * FROM SERVICE_DOMAIN WHERE STATUS_CD = ?");
            pstmt.setString(1,"A");
            rs = pstmt.executeQuery();

            while( rs.next() ) {
                ServiceDomain vo = new ServiceDomain( rs.getString("SD_CD"),
                                                      rs.getString("DESCR"),
                                                      rs.getLong("CSR_LAST_NUMBER"),
                                                      rs.getString("UNIT"),
                                                      rs.getString("USE_PS_SWAT"),
                                                      rs.getString("USE_PS_LOCKING"),
                                                      rs.getString("USE_FILE_SWAT"),
                                                      rs.getTimestamp("UPDATE_DT"),
                                                      rs.getString("UPDATE_USERID"),
                                                      rs.getString("STATUS_CD"),
                                                      rs.getString("CSR_APPL_ENV"),
                                                      rs.getString("CUST_PRIORITY_REQ"),
                                                      rs.getString("CSR_TAB_TASK")
                                                     );
                serviceDomainList.add(vo);
            }
        }
        catch(Exception e) {
            if(isFatalEnabled) {
                log.fatal("Error getting service domains", e);
                throw new EJBException(e.getMessage());
            }
        }
        finally {
            statResourceBase.releaseConnection(rs, pstmt, con);
        }

        return serviceDomainList;
    }*/
}
