package com.jatescher.layer.marshalling

import akka.http.scaladsl.model.DateTime
import com.jatescher.layer.marshalling.v1.DateTimeFormat._
import org.scalatest.{ Matchers, WordSpecLike }
import spray.json._

class DateTimeFormatSpec extends WordSpecLike with Matchers {
  val dateTimeString = "2014-09-09T04:44:47"
  val dateTime = DateTime.fromIsoDateTimeString(dateTimeString).get

  "#write" should {
    "marshall DateTime objects as ISO date time string" in {
      dateTime.toJson shouldBe dateTimeString.toJson
    }
  }

  "#read" should {
    "unmarshall messages that are ISO 8601 format without timezone" in {
      JsString(dateTimeString).convertTo[DateTime] shouldBe dateTime
    }

    "unmarshall messages that contain GMT timezone information" in {
      JsString(dateTimeString + "+00:00").convertTo[DateTime] shouldBe dateTime
    }

    "raise an exception if the time is not in GMT" in {
      intercept[DeserializationException] {
        JsString(dateTimeString + "+07:00").convertTo[DateTime]
      }
    }

    "raise an exception if the time is not ISO 8601" in {
      intercept[DeserializationException] {
        JsString("09/09/2014 04:44:47").convertTo[DateTime]
      }
    }

    "raise an exception if the time is not a string" in {
      intercept[DeserializationException] {
        JsNumber(1459359862).convertTo[DateTime]
      }
    }
  }

}
