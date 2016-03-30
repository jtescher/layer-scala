package com.jatescher.layer.marshalling.v1

import com.jatescher.layer.models.Message
import spray.json.{ DefaultJsonProtocol, RootJsonFormat }

object MessageFormat extends DefaultJsonProtocol {
  import ConversationFormat.ConversationJsonFormat
  import DateTimeFormat.DateTimeJsonFormat
  import MessagePartFormat.MessagePartJsonFormat
  import MessageSenderFormat.MessageSenderJsonFormat

  implicit val MessageJsonFormat: RootJsonFormat[Message] = jsonFormat(
    Message, "id", "conversation", "parts", "sent_at", "sender", "recipient_status"
  )

}
