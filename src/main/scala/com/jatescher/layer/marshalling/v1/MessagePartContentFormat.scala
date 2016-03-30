package com.jatescher.layer.marshalling.v1

import com.jatescher.layer.models.{ MessagePartContent, MessagePartContents }
import MessagePartContents.ImageMessagePartContent
import spray.json._

object MessagePartContentFormat extends DefaultJsonProtocol {
  import MediaTypeFormat.MediaTypeJsonFormat

  implicit val ImageMessagePartContentJsonFormat: RootJsonFormat[ImageMessagePartContent] = jsonFormat(
    ImageMessagePartContent, "id", "download_url", "expiration", "refresh_url", "size", "mime_type"
  )

  implicit val MessagePartContentJsonFormat: RootJsonFormat[MessagePartContent] = new RootJsonFormat[MessagePartContent] {
    def write(messagePartContent: MessagePartContent) = messagePartContent match {
      case messagePartContent: ImageMessagePartContent => messagePartContent.toJson
    }
    def read(value: JsValue) = value.convertTo[ImageMessagePartContent]
  }

}
