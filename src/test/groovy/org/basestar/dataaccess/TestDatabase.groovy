package org.basestar.dataaccess

import groovy.sql.Sql
import javax.inject.Inject
import javax.persistence.EntityManager
import org.basestar.model.Deployment

/**
 * @since February 10, 2015
 */
class TestDatabase {
  @Inject EntityManager entityManager
  @Inject Sql sql

  void initialize() {
    entityManager.createEntityGraph(Deployment)
  }

  void shutdown() {
    sql.execute('SHUTDOWN')
  }
}
