import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class AfricaSimulation1 extends Simulation {

	val httpProtocol = http
		.baseURL("http://bringtoafrica.me")
		.inferHtmlResources()
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.5")
		.connection("keep-alive")
		.userAgentHeader("Mozilla/5.0 (X11; Linux x86_64; rv:37.0) Gecko/20100101 Firefox/37.0")


    val uri1 = "http://fonts.googleapis.com/css"
    val uri2 = "http://bringtoafrica.me"

	val scn = scenario("Websites browsen")
		.exec(http("Alle Projekte Anschauen")
			.get("/"))
		.pause(5)
		.exec(http("Erstes Projekt anschauen")
			.get("/projects/21"))
		.pause(15)
		.exec(http("Zweites Projekt anschauen")
			.get("/projects/42"))
		.pause(21)
		.exec(http("Drittes Projekt anschauen")
			.get("/projects/41"))
		.pause(6)
		.exec(http("Viertes Projekt anschauen")
			.get("/projects/43"))

	setUp(scn.inject(rampUsers(1000) over(300 seconds))).protocols(httpProtocol)
}
