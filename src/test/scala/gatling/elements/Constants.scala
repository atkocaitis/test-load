package gatling.elements

import io.gatling.core.Predef._

import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._
import io.gatling.http.request.ExtraInfo

import scala.concurrent.duration._

object Constants {
  val numberOfUsers: Int = System.getProperty("numberOfUsers").toInt
  val duration: FiniteDuration = System.getProperty("durationMinutes").toInt.minutes
  val pause: FiniteDuration = System.getProperty("pauseBetweenRequestsMs").toInt.millisecond
  val responseTimeMs = System.getProperty("maxResponseTimeMs").toInt
  val responseSuccessPercentage = 99
  private val url: String = System.getProperty("url")
  private val repeatTimes: Int = System.getProperty("numberOfRepetitions").toInt
  private val successStatus: Int = 200

  // Define HTTP protocol to be used in simulations
  val httpProtocol = http
    .baseURL(url)
    // Check response code is 200
    .check(status.is(successStatus))
    
  /**
   * Creates a scenario by given name and executions.
   * @param name Scenario name
   * @param chains Executable that are chained together
   * @return
   */
  def createScenario(name: String, chains: ChainBuilder*): ScenarioBuilder = {
    // Do given amount of repetitions only
    if (Constants.repeatTimes > 0) {
      scenario(name).repeat(Constants.repeatTimes) {
        exec(chains).pause(Constants.pause)
      }
    } else {
      // Loop forever, it is important to put maxDuration() in Simulation setUp() method
      scenario(name).forever() {
        exec(chains).pause(Constants.pause)
      }
    }
  }
}