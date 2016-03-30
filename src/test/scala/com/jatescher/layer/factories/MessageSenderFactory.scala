package com.jatescher.layer.factories

import com.jatescher.layer.models.MessageSenders.HumanMessageSender

object MessageSenderFactory {

  def buildHumanMessageSender(): HumanMessageSender = HumanMessageSender(userId = "user-1")

}
