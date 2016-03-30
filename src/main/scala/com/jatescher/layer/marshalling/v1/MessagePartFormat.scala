package com.jatescher.layer.marshalling.v1

import com.jatescher.layer.models.MessageParts.{ ImageMessagePart, TextMessagePart }
import com.jatescher.layer.models.{ MessagePart, MessagePartContent, MessageParts }
import spray.json._

object MessagePartFormat extends DefaultJsonProtocol {
  import MessagePartContentFormat.MessagePartContentJsonFormat

  implicit val TextMessagePartJsonFormat: RootJsonFormat[TextMessagePart] = new RootJsonFormat[TextMessagePart] {
    def write(textMessagePart: TextMessagePart) = JsObject(
      "body" -> JsString(textMessagePart.body),
      "mime_type" -> JsString(textMessagePart.mimeType.toString)
    )

    def read(value: JsValue) = value.asJsObject.fields("body") match {
      case JsString(body) => TextMessagePart(body)
      case _ => deserializationError("Body cannot be blank")
    }
  }

  implicit val ImageMessagePartJsonFormat: RootJsonFormat[ImageMessagePart] = new RootJsonFormat[ImageMessagePart] {
    def write(imageMessagePart: ImageMessagePart) = JsObject(
      "content" -> imageMessagePart.content.toJson,
      "mime_type" -> JsString(imageMessagePart.mimeType.toString)
    )
    def read(value: JsValue) = value.asJsObject.getFields("content", "mime_type") match {
      case Seq(contentJson: JsObject, JsString(mimeTypeString)) => ImageMessagePart(contentJson.convertTo[MessagePartContent])
      case _ => deserializationError("Image message part is not valid")
    }
  }

  implicit val MessagePartJsonFormat: RootJsonFormat[MessagePart] = new RootJsonFormat[MessagePart] {
    def write(messagePart: MessagePart) = messagePart match {
      case textMessagePart: TextMessagePart => textMessagePart.toJson
      case imageMessagePart: ImageMessagePart => imageMessagePart.toJson
    }
    def read(value: JsValue) = value.asJsObject.getFields("body") match {
      case Seq(JsString(_)) => value.convertTo[TextMessagePart]
      case _ => value.convertTo[ImageMessagePart]
    }
  }

}
