package com.jatescher.layer.marshalling.v1

import com.jatescher.layer.models.Conversation
import spray.json.{ DefaultJsonProtocol, RootJsonFormat }

object ConversationFormat extends DefaultJsonProtocol {

  implicit val ConversationJsonFormat: RootJsonFormat[Conversation] = jsonFormat1(Conversation)

}
