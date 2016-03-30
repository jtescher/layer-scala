package com.jatescher.layer.factories

import akka.http.scaladsl.model.MediaTypes
import com.jatescher.layer.models.MessagePartContents.ImageMessagePartContent
import com.jatescher.layer.models.MessageParts.{ ImageMessagePart, TextMessagePart }

object MessagePartFactory {

  def buildTextMessagePart(): TextMessagePart = TextMessagePart("Hello, World!")

  def buildImageMessagePart(): ImageMessagePart = ImageMessagePart(ImageMessagePartContent(
    id = "layer:///content/940de862-3c96-11e4-baad-164230d1df60",
    downloadUrl = "http://google-testbucket.storage.googleapis.com/some/download/path",
    expiration = "2014-09-09T04:44:47+00:00",
    refreshUrl = "https://api.layer.com/apps/082d4684-0992-11e5-a6c0-1697f925ec7b/content/7a0aefb8-3c97-11e4-baad-164230d1df60",
    size = 172114124,
    mimeType = MediaTypes.`image/png`
  ))

}
