package org.basestar

import com.google.inject.AbstractModule
import com.google.inject.Provides
import ratpack.render.Renderer

/**
 * @since February 07, 2015
 */
class BasestarModule extends AbstractModule {
  @Override protected void configure() {}

  @Provides Renderer<Map> provideRenderer() {
    new JSONRenderer()
  }
}
