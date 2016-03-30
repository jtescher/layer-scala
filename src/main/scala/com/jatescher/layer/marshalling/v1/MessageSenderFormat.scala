package com.jatescher.layer.marshalling.v1

import com.jatescher.layer.models.{ MessageSender, MessageSenders }
import MessageSenders.{ HumanMessageSender, NonHumanMessageSender }
import spray.json._

object MessageSenderFormat extends DefaultJsonProtocol {

  implicit val HumanMessageSenderJsonFormat: RootJsonFormat[HumanMessageSender] = jsonFormat(HumanMessageSender, "user_id")

  implicit val NonHumanMessageSenderJsonFormat: RootJsonFormat[NonHumanMessageSender] = jsonFormat(NonHumanMessageSender, "name")

  implicit val MessageSenderJsonFormat: RootJsonFormat[MessageSender] = new RootJsonFormat[MessageSender] {
    def write(messageSender: MessageSender) = messageSender match {
      case humanMessageSender: HumanMessageSender => humanMessageSender.toJson
      case nonHumanMessageSender: NonHumanMessageSender => nonHumanMessageSender.toJson
    }
    def read(value: JsValue) = value.asJsObject.fields("user_id") match {
      case JsString(_) => value.convertTo[HumanMessageSender]
      case _ => value.convertTo[NonHumanMessageSender]
    }
  }

}
