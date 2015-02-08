package org.basestar

import static groovyx.net.http.ContentType.*
import groovy.json.JsonSlurper
import groovyx.net.http.RESTClient
import org.apache.http.protocol.HTTP
import ratpack.groovy.Groovy
import ratpack.server.RatpackServer
import spock.lang.Shared
import spock.lang.Specification

/**
 * @since February 07, 2015
 */
class FunctionalTest extends Specification {
  @Shared RESTClient client
  @Shared String host
  @Shared RatpackServer server

  def setupSpec() {
    server = RatpackServer.of(Groovy.Script.app())
    server.start()
    host = "$server.bindHost:$server.bindPort"
    client = new RESTClient("http://$host/")
  }

  def cleanupSpec() {
    server.stop()
  }

  def 'can delete a deployment'() {
    when: def response = client.delete(path:'deployment/deploymentName')
    then:
    response.status == HTTPStatus.NO_CONTENT.code
    !response.data
  }

  def 'can get status'() {
    when:
    def response = client.get(path:'deployment/deploymentName')
    def parsed = new JsonSlurper().parse(response.data)
    then:
    response.status == HTTPStatus.OK.code
    parsed.name == 'deploymentName'
    parsed.status == 'SUCCEEDED'
  }

  def 'can submit a deployment'() {
    when: def response = client.post(path:'deployment', body:[name:'name', instanceCount:1], requestContentType:JSON)
    then:
    response.status == HTTPStatus.ACCEPTED.code
    !response.data
  }
}
