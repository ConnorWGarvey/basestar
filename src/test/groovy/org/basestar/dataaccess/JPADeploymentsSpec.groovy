package org.basestar.dataaccess

import static org.basestar.model.Deployment.Status.*
import com.google.inject.Guice
import groovy.sql.Sql
import java.sql.DriverManager
import javax.inject.Inject
import javax.persistence.EntityManager
import org.basestar.TestBasestarModule
import org.basestar.model.Deployment
import spock.guice.UseModules
import spock.lang.Specification

/**
 * @since February 07, 2015
 */
@UseModules(TestBasestarModule) class JPADeploymentsSpec extends Specification {
  @Inject TestDatabase testDatabase
  @Inject JPADeployments deployments

  def setup() {
    testDatabase.initialize()
  }

  def cleanup() {
    testDatabase.shutdown()
  }

  def 'findById when record exists'() {
    deployments.save(new Deployment(name:'exists', status:DEPLOYED))
    when: def actual = deployments.findByName('exists')
    then: actual == new Deployment(name:'exists', status:DEPLOYED)
  }
}
