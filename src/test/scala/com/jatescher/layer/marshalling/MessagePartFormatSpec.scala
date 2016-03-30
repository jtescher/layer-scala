package com.jatescher.layer.marshalling

import com.jatescher.layer.factories.MessagePartFactory
import com.jatescher.layer.marshalling.v1.MessagePartContentFormat.MessagePartContentJsonFormat
import com.jatescher.layer.marshalling.v1.MessagePartFormat.{ ImageMessagePartJsonFormat, MessagePartJsonFormat, TextMessagePartJsonFormat }
import com.jatescher.layer.models.MessagePart
import com.jatescher.layer.models.MessageParts.{ ImageMessagePart, TextMessagePart }
import org.scalatest.{ Matchers, WordSpec }
import spray.json._

class MessagePartFormatSpec extends WordSpec with Matchers {
  val textMessagePart = MessagePartFactory.buildTextMessagePart()
  val imageMessagePart = MessagePartFactory.buildImageMessagePart()

  "TextMessagePartJsonFormat#write" should {
    "marshall text message parts" in {
      textMessagePart.toJson shouldBe JsObject(
        "mime_type" -> JsString(textMessagePart.mimeType.toString),
        "body" -> JsString(textMessagePart.body)
      )
    }
  }

  "TextMessagePartJsonFormat#read" should {
    "unmarshall text message parts" in {
      JsObject(
        "mime_type" -> JsString(textMessagePart.mimeType.toString),
        "body" -> JsString(textMessagePart.body)
      ).convertTo[TextMessagePart] shouldBe textMessagePart
    }

    "raise an exception if the format is not correct" in {
      intercept[DeserializationException] {
        JsObject(
          "mime_type" -> JsString(textMessagePart.mimeType.toString),
          "body" -> JsNull
        ).convertTo[TextMessagePart]
      }
    }
  }

  "ImageMessagePartJsonFormat#write" should {
    "marshall image message parts" in {
      imageMessagePart.toJson shouldBe JsObject(
        "mime_type" -> JsString(imageMessagePart.mimeType.toString),
        "content" -> imageMessagePart.content.toJson
      )
    }
  }

  "ImageMessagePartJsonFormat#read" should {
    "unmarshall image message parts" in {
      JsObject(
        "mime_type" -> JsString(imageMessagePart.mimeType.toString),
        "content" -> imageMessagePart.content.toJson
      ).convertTo[ImageMessagePart] shouldBe imageMessagePart
    }

    "raise an exception if the format is not correct" in {
      intercept[DeserializationException] {
        JsObject(
          "mime_type" -> JsString(imageMessagePart.mimeType.toString),
          "content" -> JsNull
        ).convertTo[ImageMessagePart]
      }
    }
  }

  "MessagePartJsonFormat#write" should {
    "marshall image message parts" in {
      val messagePart: MessagePart = imageMessagePart
      messagePart.toJson shouldBe JsObject(
        "mime_type" -> JsString(imageMessagePart.mimeType.toString),
        "content" -> imageMessagePart.content.toJson
      )
    }
  }

  "MessagePartJsonFormat#read" should {
    "unmarshall image message parts" in {
      JsObject(
        "mime_type" -> JsString(imageMessagePart.mimeType.toString),
        "content" -> imageMessagePart.content.toJson
      ).convertTo[MessagePart] shouldBe imageMessagePart
    }

    "raise an exception if the format is not correct" in {
      intercept[DeserializationException] {
        JsObject(
          "mime_type" -> JsString(imageMessagePart.mimeType.toString),
          "content" -> JsNull
        ).convertTo[MessagePart]
      }
    }
  }

}
