import static ratpack.groovy.Groovy.ratpack
import org.basestar.BasestarModule

ratpack {
  handlers {
    prefix('deployment') {
      post {
        response.status(202)
        response.send()
      }
      handler(':name') {
        byMethod {
          delete {
            response.status(204)
            response.send()
          }
          get {
            render([name:allPathTokens.name, status:'SUCCEEDED'])
          }
        }
      }
    }
  }
  bindings {
    add new BasestarModule()
  }
}