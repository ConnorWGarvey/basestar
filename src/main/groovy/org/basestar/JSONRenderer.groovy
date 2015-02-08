package org.basestar

import groovy.json.JsonBuilder
import ratpack.handling.Context
import ratpack.render.RendererSupport

/**
 * @since February 07, 2015
 */
class JSONRenderer extends RendererSupport<Map> {
  @Override void render(Context context, Map object) {
    if (object) context.render(new JsonBuilder(object).toString())
  }
}
