package com.jatescher.layer.models

import akka.http.scaladsl.model.DateTime

case class Message(
  id: String,
  conversation: Conversation,
  parts: List[MessagePart],
  sentAt: DateTime,
  sender: MessageSender,
  recipientStatus: Map[String, String]
)
