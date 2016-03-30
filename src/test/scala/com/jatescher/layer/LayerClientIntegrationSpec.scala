package com.jatescher.layer

import akka.actor.ActorSystem
import akka.http.scaladsl.model.{ HttpMethods, HttpRequest, HttpResponse, StatusCodes }
import akka.stream.ActorMaterializer
import com.typesafe.config.{ Config, ConfigFactory }
import org.scalatest.concurrent.{ IntegrationPatience, PatienceConfiguration, ScalaFutures }
import org.scalatest.{ Matchers, WordSpec }
import scala.concurrent.Future

class LayerClientIntegrationSpec extends WordSpec with Matchers with PatienceConfiguration with IntegrationPatience with ScalaFutures {
  val testConfig = ConfigFactory.load("test-application.conf")
  val router = new LayerRouter(testConfig)
  implicit val system = ActorSystem("LayerClientIntegrationSpecSystem")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

  class IntegrationLayerClient(router: LayerRouter, config: Config) extends LayerClient(router, config) {
    def testRequest(httpRequest: HttpRequest): Future[HttpResponse] = executeRequest(httpRequest)
  }

  "#executeRequest" should {
    "complete the request" in {
      val request = HttpRequest(HttpMethods.GET, uri = "https://layer.com")
      val client = new IntegrationLayerClient(router, testConfig)
      client.testRequest(request).futureValue.status shouldBe StatusCodes.OK
    }
  }

}
