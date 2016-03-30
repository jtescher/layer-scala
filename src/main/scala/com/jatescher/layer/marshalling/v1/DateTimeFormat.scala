package com.jatescher.layer.marshalling.v1

import akka.http.scaladsl.model.DateTime
import spray.json._

object DateTimeFormat extends DefaultJsonProtocol {

  implicit val DateTimeJsonFormat: JsonFormat[DateTime] = new JsonFormat[DateTime] {
    def write(dateTime: DateTime) = dateTime.toIsoDateTimeString.toJson
    def read(value: JsValue) = value match {
      case JsString(dateTimeString) => DateTime.fromIsoDateTimeString(dateTimeString.stripSuffix("+00:00")) match {
        case Some(dateTime) => dateTime
        case _ => deserializationError("DateTime expected to be in format `yyyy-mm-ddThh:mm:ss[.SSSZ]`")
      }
      case _ => deserializationError("Date time cannot be blank")
    }
  }

}
