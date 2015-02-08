import static ratpack.groovy.Groovy.ratpack
import org.basestar.BasestarModule

ratpack {
  handlers {
    prefix('deployment') {
      post {
        response.status(202)
        response.send()
      }
      // Example of many verbs for the same URI:
      // https://github.com/ratpack/ratpack/blob/master/ratpack-core/src/test/groovy/ratpack/path/PathAndMethodRoutingSpec.groovy
      get(':name') {
        render([name:allPathTokens.name, status:'SUCCEEDED'])
      }
    }
  }
  bindings {
    add new BasestarModule()
  }
}