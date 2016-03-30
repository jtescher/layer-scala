package com.jatescher.layer.models

import akka.http.scaladsl.model.{ MediaType, MediaTypes }

sealed trait MessagePart {
  val mimeType: MediaType
}

object MessageParts {

  // Simple text message part
  case class TextMessagePart(body: String) extends MessagePart {
    val mimeType = MediaTypes.`text/plain`
  }

  // Custom content message part
  case class ImageMessagePart(content: MessagePartContent) extends MessagePart {
    val mimeType = content.mimeType
  }

}
