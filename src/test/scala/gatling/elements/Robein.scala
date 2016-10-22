package gatling.elements

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

object Robein {
  
  private val home = exec(
    http("Go to home page")
      .get("/Home")
  ).pause(1 seconds, 5 seconds)

  private val Investment = exec(
    http("Go to Investment page")
      .get("/IndexInvestment")
  ).pause(1 seconds, 5 seconds)

  private val Costs = exec(
    http("Go to Costs page")
      .get("/Costs")
  ).pause(1 seconds, 5 seconds)

  private val Products = exec(
    http("Go to Products page")
      .get("/Products")
  ).pause(1 seconds, 5 seconds)

  private val WhoAreWe = exec(
    http("Go to WhoAreWe page")
      .get("/WhoAreWe")
  ).pause(1 seconds, 5 seconds)
  
  val scnRobein = Constants.createScenario("Get two post", home, Investment, Costs, Products, WhoAreWe)
  
}