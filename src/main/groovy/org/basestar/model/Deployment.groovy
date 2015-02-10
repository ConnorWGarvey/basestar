package org.basestar.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 * @since February 07, 2015
 */
@Entity @Table(name='deployments') @EqualsAndHashCode @ToString class Deployment {
  @Id String name
  String status

  static class Status {
    static final String DEPLOYING = 'DEPLOYING'
    static final String DEPLOYED = 'DEPLOYED'
    static final String FAILED = 'FAILED'
  }
}
