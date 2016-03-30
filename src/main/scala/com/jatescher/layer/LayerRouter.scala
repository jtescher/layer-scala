package com.jatescher.layer

import akka.http.scaladsl.model.Uri
import com.jatescher.layer.models.Conversation
import com.typesafe.config.Config

class LayerRouter(config: Config) {
  val LAYER_HOST = config.getString("layer.host")
  val LAYER_APP_ID = config.getString("layer.app_id")

  def createMessageUrl(conversation: Conversation): Uri = {
    Uri(s"$LAYER_HOST/apps/$LAYER_APP_ID/conversations/${conversation.id}/messages")
  }

}
