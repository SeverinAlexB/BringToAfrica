
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class AfricaSimulationProjektErstellen extends Simulation {

	val httpProtocol = http
		.baseURL("http://bringtoafrica.me")
		.inferHtmlResources()
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.5")
		.connection("keep-alive")
		.contentTypeHeader("application/x-www-form-urlencoded")
		.userAgentHeader("Mozilla/5.0 (X11; Linux x86_64; rv:37.0) Gecko/20100101 Firefox/37.0")

	val headers_1 = Map("Accept" -> "image/png,image/*;q=0.8,*/*;q=0.5")

	val headers_5 = Map(
		"Accept" -> "application/font-woff;q=0.9,*/*;q=0.8",
		"Accept-Encoding" -> "identity")

    val uri1 = "http://fonts.googleapis.com/css"
    val uri2 = "http://bringtoafrica.me"

	val scn = scenario("AfricaSimulationProjektErstellen")
		.exec(http("request_0")
			.get("/projects")
			.resources(http("request_1")
			.get(uri2 + "/assets/img/breadcrumb-bg.jpg")
			.headers(headers_1)
			.check(status.is(304))))
		.pause(1)
		.exec(http("request_2")
			.get("/projects/21"))
		.pause(2)
		.exec(http("request_3")
			.get("/projects/new"))
		.pause(24)
		.exec(http("request_4")
			.post("/projects/data")
			.formParam("title", "Mis supper projekt")
			.formParam("description", "asfdsadflkjsdksad  asdfölkjk asfd ölkjsdaf  ksadfjkölasdfj  dasflöjlk asdf")
			.formParam("startsAt", "1.2.15")
			.formParam("endsAt", "1.3.14")
			.resources(http("request_5")
			.get(uri2 + "/assets/fonts/glyphicons-halflings-regular.woff")
			.headers(headers_5))
			.check(status.is(400)))
		.pause(11)
		.exec(http("request_6")
			.post("/projects/data")
			.formParam("title", "Mis supper projekt")
			.formParam("description", "")
			.formParam("startsAt", "01.02.2015")
			.formParam("endsAt", "01.03.2015")
			.check(status.is(400)))
		.pause(2)
		.exec(http("request_7")
			.post("/projects/data")
			.formParam("title", "Mis supper projekt")
			.formParam("description", "afsdasdf")
			.formParam("startsAt", "01.02.2015")
			.formParam("endsAt", "01.03.2015")
			.check(status.is(400)))
		.pause(19)
		.exec(http("request_10")
			.post("/projects/data")
			.formParam("title", "Mis supper projekt")
			.formParam("description", "asdfasdfsfsadfds")
			.formParam("startsAt", "2015-04-17")
			.formParam("endsAt", "2015-05-17"))
		.pause(12)
		.exec(http("request_11")
			.post("/projects/ware")
			.formParam("amount", "4")
			.formParam("donation", "asdfasdf")
			.formParam("amount", "2")
			.formParam("donation", "safddsaf"))
		.pause(6)
		.exec(http("request_12")
			.post("/projects/save"))
		.pause(3)
		.exec(http("request_13")
			.get("/projects/41"))

	setUp(scn.inject(rampUsers(20) over (300 seconds))).protocols(httpProtocol)
}
