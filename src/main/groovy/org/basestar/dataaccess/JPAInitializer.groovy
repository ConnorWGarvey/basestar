package org.basestar.dataaccess

import com.google.inject.persist.PersistService
import javax.inject.Inject

/**
 * @since February 07, 2015
 */
class JPAInitializer {
  @Inject JPAInitializer(PersistService service) {
    service.start()
  }
}
