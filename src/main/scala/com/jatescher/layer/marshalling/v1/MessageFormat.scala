package com.jatescher.layer.marshalling.v1

import com.jatescher.layer.models.Message
import spray.json.{ DefaultJsonProtocol, RootJsonFormat }

object MessageFormat extends DefaultJsonProtocol {
  import ConversationFormat.ConversationJsonFormat
  import MessagePartFormat.MessagePartJsonFormat
  import DateTimeFormat.DateTimeJsonFormat
  import MessageSenderFormat.MessageSenderJsonFormat

  implicit val MessageJsonFormat: RootJsonFormat[Message] = jsonFormat(
    Message, "id", "conversation", "parts", "sent_at", "sender", "recipient_status"
  )

}
