package com.jatescher.layer.marshalling.v1

import com.jatescher.layer.models.ErrorResponse
import spray.json.{ DefaultJsonProtocol, RootJsonFormat }

object ErrorResponseFormat extends DefaultJsonProtocol {

  implicit val ErrorResponseJsonFormat: RootJsonFormat[ErrorResponse] = jsonFormat4(ErrorResponse)

}
