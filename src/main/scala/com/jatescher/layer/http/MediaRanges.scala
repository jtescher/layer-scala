package com.jatescher.layer.http

import akka.http.scaladsl.model.MediaRange

object MediaRanges {

  val LayerJsonMediaRange = MediaRange.custom("application/vnd.layer+json", params = Map("version" -> "1.1"))

}
