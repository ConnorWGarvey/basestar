import static ratpack.groovy.Groovy.ratpack
import com.google.inject.persist.jpa.JpaPersistModule
import org.basestar.BasestarModule
import org.basestar.model.Deployment

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
            render([name:allPathTokens.name, status:Deployment.Status.DEPLOYED])
          }
        }
      }
    }
  }
  bindings {
    add new BasestarModule()
    add new JpaPersistModule('basestarJpaUnit')
  }
}