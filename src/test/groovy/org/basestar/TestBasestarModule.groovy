package org.basestar

import com.google.inject.Provides
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence
import javax.persistence.spi.PersistenceProviderResolver
import javax.persistence.spi.PersistenceProviderResolverHolder
import javax.sql.DataSource
import org.apache.commons.dbcp2.ConnectionFactory
import org.apache.commons.dbcp2.DriverManagerConnectionFactory
import org.apache.commons.dbcp2.PoolableConnection
import org.apache.commons.dbcp2.PoolableConnectionFactory
import org.apache.commons.dbcp2.PoolingDataSource
import org.apache.commons.pool2.ObjectPool
import org.apache.commons.pool2.impl.GenericObjectPool
import org.hibernate.dialect.HSQLDialect
import org.hibernate.jpa.HibernatePersistenceProvider
import org.hsqldb.jdbc.JDBCDriver

/**
 * @since February 07, 2015
 */
class TestBasestarModule extends BasestarModule {
  @Provides DataSource provideDataSource() {
    ConnectionFactory connectionFactory = new DriverManagerConnectionFactory('jdbc:hsqldb:mem:basestar', 'sa', '')
    PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null)
    ObjectPool<PoolableConnection> connectionObjectPool = new GenericObjectPool<>(poolableConnectionFactory)
    poolableConnectionFactory.setPool(connectionObjectPool)
    new PoolingDataSource<PoolableConnection>(connectionObjectPool)
  }

  @Provides EntityManager provideEntityManager() {
    PersistenceProviderResolverHolder.setPersistenceProviderResolver(new PersistenceProviderResolver() {
      @Override List getPersistenceProviders() { [new HibernatePersistenceProvider()] }
      @Override void clearCachedProviders() {}
    })
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory('basestar',
        ['hibernate.hbm2ddl.auto':'update', 'hibernate.dialect':HSQLDialect.canonicalName,
            'hibernate.connection.driver_class':JDBCDriver.canonicalName, 'hibernate.connection.username':'sa',
            'hibernate.connection.password':'', 'hibernate.connection.url':'jdbc:hsqldb:mem:basestar'])
    entityManagerFactory.createEntityManager()
  }
}
