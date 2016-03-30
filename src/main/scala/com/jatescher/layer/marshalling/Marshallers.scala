package com.jatescher.layer.marshalling

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.marshalling.ToEntityMarshaller
import akka.http.scaladsl.unmarshalling.FromEntityUnmarshaller
import com.jatescher.layer.marshalling.v1._
import com.jatescher.layer.models._
import MessagePartContents.ImageMessagePartContent
import MessageParts.{ ImageMessagePart, TextMessagePart }
import MessageSenders.{ HumanMessageSender, NonHumanMessageSender }
import spray.json._

object Marshallers extends SprayJsonSupport with DefaultJsonProtocol {

  implicit val ConversationMarshaller: ToEntityMarshaller[Conversation] = ConversationFormat.ConversationJsonFormat
  implicit val ErrorResponseUnmarshaller: FromEntityUnmarshaller[ErrorResponse] = ErrorResponseFormat.ErrorResponseJsonFormat
  implicit val NonHumanMessageSenderMarshaller: ToEntityMarshaller[NonHumanMessageSender] = MessageSenderFormat.NonHumanMessageSenderJsonFormat
  implicit val HumanMessageSenderMarshaller: ToEntityMarshaller[HumanMessageSender] = MessageSenderFormat.HumanMessageSenderJsonFormat
  implicit val MessageSenderMarshaller: ToEntityMarshaller[MessageSender] = MessageSenderFormat.MessageSenderJsonFormat
  implicit val MessagePartContentMarshaller: ToEntityMarshaller[MessagePartContent] = MessagePartContentFormat.MessagePartContentJsonFormat
  implicit val ImageMessagePartContentMarshaller: ToEntityMarshaller[ImageMessagePartContent] = MessagePartContentFormat.ImageMessagePartContentJsonFormat
  implicit val TextMessagePartMarshaller: ToEntityMarshaller[TextMessagePart] = MessagePartFormat.TextMessagePartJsonFormat
  implicit val ImageMessagePartMarshaller: ToEntityMarshaller[ImageMessagePart] = MessagePartFormat.ImageMessagePartJsonFormat
  implicit val MessagePartMarshaller: ToEntityMarshaller[MessagePart] = MessagePartFormat.MessagePartJsonFormat
  implicit val MessageMarshaller: ToEntityMarshaller[Message] = MessageFormat.MessageJsonFormat
  implicit val MessageUnmarshaller: FromEntityUnmarshaller[Message] = MessageFormat.MessageJsonFormat

}
