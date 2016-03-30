package com.jatescher.layer.factories

import akka.http.scaladsl.model.DateTime
import com.jatescher.layer.models.MessageSenders.NonHumanMessageSender
import com.jatescher.layer.models.{ Conversation, Message, MessagePart }

object MessageFactory {

  def build(messageParts: List[MessagePart] = List(MessagePartFactory.buildTextMessagePart())): Message = Message(
    id = "layer:///messages/940de862-3c96-11e4-baad-164230d1df67",
    conversation = Conversation(
      id = "layer:///conversations/f3cc7b32-3c92-11e4-baad-164230d1df67"
    ),
    parts = messageParts,
    sentAt = DateTime.fromIsoDateTimeString("2014-09-09T04:44:47").get,
    sender = NonHumanMessageSender("t-bone"),
    recipientStatus = Map(
      "777" -> "sent",
      "999" -> "sent",
      "111" -> "sent"
    )
  )

}
