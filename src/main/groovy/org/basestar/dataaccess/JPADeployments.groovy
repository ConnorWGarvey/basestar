package org.basestar.dataaccess

import com.google.inject.persist.Transactional
import javax.inject.Inject
import javax.persistence.EntityManager
import org.basestar.model.Deployment

/**
 * Accesses deployments stored in a SQL database
 * @since February 07, 2015
 */
class JPADeployments extends Deployments {
  @Inject EntityManager manager

  @Transactional Deployment findByName(String name) {
    manager.find(Deployment, name)
  }

  @Transactional void save(Deployment deployment) {
    manager.persist(deployment)
  }
}
