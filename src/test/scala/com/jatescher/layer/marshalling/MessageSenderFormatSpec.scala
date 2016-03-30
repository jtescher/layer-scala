package com.jatescher.layer.marshalling

import com.jatescher.layer.factories.MessageSenderFactory
import com.jatescher.layer.marshalling.v1.MessageSenderFormat.MessageSenderJsonFormat
import com.jatescher.layer.models.MessageSender
import org.scalatest.{ Matchers, WordSpec }
import spray.json._

class MessageSenderFormatSpec extends WordSpec with Matchers {
  val humanMessageSender = MessageSenderFactory.buildHumanMessageSender()

  "MessageSenderFormatt#write" should {
    "marshall message sender" in {
      val messageSender: MessageSender = humanMessageSender
      messageSender.toJson shouldBe JsObject(
        "user_id" -> JsString(humanMessageSender.userId.toString)
      )
    }
  }

  "MessageSenderFormat#read" should {
    "unmarshall message sender" in {
      JsObject("user_id" -> JsString(humanMessageSender.userId)).convertTo[MessageSender] shouldBe humanMessageSender
    }

    "raise an exception if the format is not correct" in {
      intercept[DeserializationException] {
        JsObject("user_id" -> JsNull).convertTo[MessageSender]
      }
    }
  }

}
