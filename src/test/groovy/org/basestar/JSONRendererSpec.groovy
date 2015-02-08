package org.basestar

import groovy.json.JsonSlurper
import ratpack.handling.Context
import spock.lang.Specification

/**
 * @since February 07, 2015
 */
class JSONRendererSpec extends Specification {
  Context context = Mock()
  JSONRenderer renderer = new JSONRenderer()

  def 'render a map as JSON'() {
    when: render(sample:'map')
    then: 1 * context.render({new JsonSlurper().parseText(it).sample == 'map'})
  }

  def 'render does not render null'() {
    when: render(null)
    then: 0 * context.render(_)
  }

  void render(Map data) {
    renderer.render(context, data)
  }
}
