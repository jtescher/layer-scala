package com.jatescher.layer.marshalling.v1

import akka.http.scaladsl.model.MediaType
import spray.json._

object MediaTypeFormat extends DefaultJsonProtocol {

  implicit val MediaTypeJsonFormat: JsonFormat[MediaType] = new JsonFormat[MediaType] {
    def write(mediaType: MediaType) = mediaType.toString.toJson
    def read(value: JsValue) = value match {
      case JsString(mediaTypeString) => MediaType.parse(mediaTypeString) match {
        case Right(mediaType) => mediaType
        case _ => deserializationError("Valid media type expected")
      }
      case _ => deserializationError("Media type cannot be blank")
    }
  }

}
