package org.basestar.dataaccess

import org.basestar.model.Deployment

/**
 * @since February 07, 2015
 */
abstract class Deployments {
  abstract Deployment findByName(String name)
  abstract void save(Deployment deployment)
}
