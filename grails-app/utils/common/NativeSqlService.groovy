package common

import org.hibernate.SQLQuery
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.slf4j.LoggerFactory

import java.util.logging.Logger

class NativeSqlService {

    private Logger logger = LoggerFactory.getLogger(NativeSqlService.class)

    SessionFactory sessionFactory

    SQLQuery createSQLQuery(String sql) {
        final Session session = sessionFactory.getCurrentSession()
        final SQLQuery sqlQuery = session.createSQLQuery(sql)
        logger.info("===== create Native SQL Query ===== \n === ${sql} ===")
        return sqlQuery
    }
}
