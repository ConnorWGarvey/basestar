package org.basestar

import static groovyx.net.http.ContentType.*
import groovy.json.JsonSlurper
import groovyx.net.http.RESTClient
import ratpack.groovy.Groovy
import ratpack.server.RatpackServer
import spock.lang.Shared
import spock.lang.Specification

/**
 * @since February 07, 2015
 */
class FunctionalTest extends Specification {
  @Shared String host
  @Shared RatpackServer server

  def setupSpec() {
    server = RatpackServer.of(Groovy.Script.app())
    server.start()
    host = "$server.bindHost:$server.bindPort"
  }

  def cleanupSpec() {
    server.stop()
  }

  def 'can submit a deployment'() {
    def client = new RESTClient("http://$host/")
    when: def response = client.post(path:'deployment', body:[name:'name', instanceCount:1], requestContentType:JSON)
    then:
    response.status == HTTPStatus.ACCEPTED.code
    !response.data
  }

  def 'can get status'() {
    def client = new RESTClient("http://$host/")
    when:
    def response = client.get(path:'deployment/deploymentName')
    def parsed = new JsonSlurper().parse(response.data)
    then:
    response.status == HTTPStatus.OK.code
    parsed.name == 'deploymentName'
    parsed.status == 'SUCCEEDED'
  }
}
