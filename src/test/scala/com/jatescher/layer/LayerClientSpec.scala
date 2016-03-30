package com.jatescher.layer

import akka.actor.ActorSystem
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import akka.util.ByteString
import com.jatescher.layer.factories.MessageFactory
import com.jatescher.layer.models.ErrorResponse
import com.typesafe.config.{ Config, ConfigFactory }
import org.scalatest.concurrent.{ IntegrationPatience, ScalaFutures }
import org.scalatest.{ Matchers, WordSpecLike }
import scala.concurrent.Future

class LayerClientSpec extends WordSpecLike with Matchers with ScalaFutures with IntegrationPatience {

  val testConfig = ConfigFactory.load("test-application.conf")
  val router = new LayerRouter(testConfig)
  implicit val system = ActorSystem("LayerClientSpecSystem")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

  val successResponseJson = """{
    |  "id": "layer:///messages/940de862-3c96-11e4-baad-164230d1df67",
    |  "url": "https://api.layer.com/apps/24f43c32-4d95-11e4-b3a2-0fd00000020d/messages/940de862-3c96-11e4-baad-164230d1df67",
    |  "conversation": {
    |    "id": "layer:///conversations/f3cc7b32-3c92-11e4-baad-164230d1df67",
    |    "url": "https://api.layer.com/apps/24f43c32-4d95-11e4-b3a2-0fd00000020d/conversations/f3cc7b32-3c92-11e4-baad-164230d1df67"
    |  },
    |  "parts": [
    |    {
    |      "body": "Hello, World!",
    |      "mime_type": "text/plain"
    |    }
    |  ],
    |  "sent_at": "2014-09-09T04:44:47+00:00",
    |  "sender": {
    |    "name": "t-bone",
    |    "user_id": null
    |  },
    |  "recipient_status": {
    |    "777": "sent",
    |    "999": "sent",
    |    "111": "sent"
    |  }
    |}""".stripMargin

  val failureResponseJson = """{
    |  "id":"invalid_header",
    |  "code":107,
    |  "message":"Accept header missing; must be one of: application/vnd.layer+json; version=1.0, application/vnd.layer+json; version=1.1",
    |  "url":"https://developer.layer.com/docs/platform"
    |}""".stripMargin

  class SuccessfulLayerClient(router: LayerRouter, config: Config) extends LayerClient(router, config) {
    override protected def executeRequest(httpRequest: HttpRequest): Future[HttpResponse] = {
      Future.successful(HttpResponse(StatusCodes.Created, entity = HttpEntity.Strict(ContentTypes.`application/json`, ByteString(successResponseJson))))
    }
  }

  class UnSuccessfulLayerClient(router: LayerRouter, config: Config) extends LayerClient(router, config) {
    override protected def executeRequest(httpRequest: HttpRequest): Future[HttpResponse] = {
      Future.successful(HttpResponse(StatusCodes.Unauthorized, entity = HttpEntity.Strict(ContentTypes.`application/json`, ByteString(failureResponseJson))))
    }
  }

  "#sendMessage" must {
    "Return a message if successful" in {
      val message = MessageFactory.build()
      val layerClient = new SuccessfulLayerClient(router, testConfig)
      layerClient.sendMessage(message).futureValue shouldBe Right(message)
    }

    "Return an exception if unsuccessful" in {
      val message = MessageFactory.build()
      val layerClient = new UnSuccessfulLayerClient(router, testConfig)
      layerClient.sendMessage(message).futureValue shouldBe Left(ErrorResponse(
        id = "invalid_header",
        code = 107,
        message = "Accept header missing; must be one of: application/vnd.layer+json; version=1.0, application/vnd.layer+json; version=1.1",
        url = "https://developer.layer.com/docs/platform"
      ))
    }
  }

}
