package com.jatescher.layer

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.headers.{ Accept, Authorization, OAuth2BearerToken }
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.Materializer
import com.jatescher.layer.marshalling.Marshallers._
import com.jatescher.layer.marshalling.Marshallers.ErrorResponseUnmarshaller
import com.jatescher.layer.models.{ ErrorResponse, Message }
import com.jatescher.layer.http.MediaRanges.LayerJsonMediaRange
import com.typesafe.config.Config
import scala.concurrent.{ ExecutionContext, Future }

class LayerClient(router: LayerRouter, config: Config)(implicit system: ActorSystem, materializer: Materializer, ec: ExecutionContext) {
  val LAYER_TOKEN = config.getString("layer.token")

  def sendMessage(message: Message): Future[Either[ErrorResponse, Message]] = {
    for {
      messageRequest <- sendMessageRequest(message)
      response <- executeRequest(messageRequest)
      messageOrErrorResponse <- unmarshallResponse(response)
    } yield messageOrErrorResponse
  }

  private def sendMessageRequest(message: Message): Future[HttpRequest] = {
    Marshal(message).to[RequestEntity].map { entity =>
      HttpRequest(
        method = HttpMethods.POST,
        uri = router.createMessageUrl(message.conversation),
        entity = entity,
        headers = List(
          Authorization(OAuth2BearerToken(LAYER_TOKEN)),
          Accept(LayerJsonMediaRange)
        )
      )
    }
  }

  protected def executeRequest(httpRequest: HttpRequest): Future[HttpResponse] = {
    Http().singleRequest(httpRequest)
  }

  private def unmarshallResponse(response: HttpResponse): Future[Either[ErrorResponse, Message]] = {
    val unmarshalledResponse = Unmarshal(response.entity)

    if (response.status == StatusCodes.Created) {
      unmarshalledResponse.to[Message].map(Right(_))
    } else {
      unmarshalledResponse.to[ErrorResponse].map(Left(_))
    }
  }

}
