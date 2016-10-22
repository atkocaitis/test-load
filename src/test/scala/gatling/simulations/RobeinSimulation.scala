package gatling.simulations

import gatling.elements.{Constants, Robein}
import io.gatling.core.Predef._
import scala.concurrent.duration._

class RobeinSimulation extends Simulation {
  
  setUp(
    Robein.scnRobein.inject(rampUsers(Constants.numberOfUsers) over (3 seconds))
  )
  .protocols(Constants.httpProtocol)
  .pauses(constantPauses)
  .maxDuration(Constants.duration)
  .assertions(
    global.responseTime.max.lessThan(Constants.responseTimeMs),
    global.successfulRequests.percent.greaterThan(Constants.responseSuccessPercentage)
  )
 
}