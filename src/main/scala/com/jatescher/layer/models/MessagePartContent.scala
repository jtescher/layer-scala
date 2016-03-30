package com.jatescher.layer.models

import akka.http.scaladsl.model.MediaType

sealed trait MessagePartContent {
  val id: String
  val mimeType: MediaType
}

object MessagePartContents {

  // Rich content message part
  case class ImageMessagePartContent(
    id: String,
    downloadUrl: String,
    expiration: String,
    refreshUrl: String,
    size: Long,
    mimeType: MediaType
  ) extends MessagePartContent

}
