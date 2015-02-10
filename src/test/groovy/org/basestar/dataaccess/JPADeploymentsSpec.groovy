package org.basestar.dataaccess

import static org.basestar.model.Deployment.Status.*
import com.google.inject.Guice
import groovy.sql.Sql
import java.sql.DriverManager
import javax.persistence.EntityManager
import org.basestar.TestBasestarModule
import org.basestar.model.Deployment
import spock.lang.Specification

/**
 * @since February 07, 2015
 */
class JPADeploymentsSpec extends Specification {
  static final String DB_URL = 'jdbc:hsqldb:mem:basestar'
  static final String DB_USER_NAME = 'sa'
  static final String DB_PASSWORD = ''
  JPADeployments deployments

  def setup() {
    def injector = Guice.createInjector(new TestBasestarModule())
    def entityManager = injector.getInstance(EntityManager)
    entityManager.createEntityGraph(Deployment)
    deployments = injector.getInstance(JPADeployments)
    DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD)
  }

  def cleanup() {
    Sql.withInstance(DB_URL, DB_USER_NAME, DB_PASSWORD) {it.execute('SHUTDOWN')}
  }

  def 'findById when record exists'() {
    deployments.save(new Deployment(name:'exists', status:DEPLOYED))
    when: def actual = deployments.findByName('exists')
    then: actual == new Deployment(name:'exists', status:DEPLOYED)
    expect: true
  }
}
