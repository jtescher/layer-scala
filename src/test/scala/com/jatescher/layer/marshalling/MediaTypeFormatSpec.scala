package com.jatescher.layer.marshalling

import akka.http.scaladsl.model.{ MediaType, MediaTypes }
import com.jatescher.layer.marshalling.v1.MediaTypeFormat._
import org.scalatest.{ Matchers, WordSpec }
import spray.json._

class MediaTypeFormatSpec extends WordSpec with Matchers {
  val mediaTypeString = "image/png"
  val mediaType: MediaType = MediaTypes.`image/png`

  "#write" should {
    "marshall the media type as string" in {
      mediaType.toJson shouldBe mediaTypeString.toJson
    }
  }

  "#read" should {
    "unmarshall media types" in {
      JsString(mediaTypeString).convertTo[MediaType] shouldBe mediaType
    }

    "raise an exception if the media type is not a string" in {
      intercept[DeserializationException] {
        JsNumber(1).convertTo[MediaType]
      }
    }

    "raise an exception if the media type is not valid" in {
      intercept[DeserializationException] {
        JsString("non-media-type").convertTo[MediaType]
      }
    }
  }

}
