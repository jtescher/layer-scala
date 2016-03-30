package com.jatescher.layer.models

sealed trait MessageSender

object MessageSenders {
  case class HumanMessageSender(userId: String) extends MessageSender
  case class NonHumanMessageSender(name: String) extends MessageSender
}
