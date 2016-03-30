# Layer Scala

[![Build Status](https://travis-ci.org/jtescher/layer-scala.svg?branch=master)](https://travis-ci.org/jtescher/layer-scala)
[![Codacy Badge](https://api.codacy.com/project/badge/grade/bb203081831c4963a8f11a105a2c1ced)](https://www.codacy.com/app/jatescher/layer-scala)

Scala library for interacting with the [Layer](https://layer.com) API in an [Akka](http://akka.io) or
[Play](https://www.playframework.com) application.

## Useful Links

The following is a list of useful links related to Layer

* [Layer Website](https://www.layer.com)
* [Layer Docs](https://developer.layer.com/docs)
* [Layer on Github](https://github.com/layerhq)

## Installation

Add the following to your `build.sbt` file:

```scala
libraryDependencies ++= Seq(
  "com.jatescher" %% "layer-client" % "0.0.1"
  ...
)
```

## Configuration

Log in to the [Layer Developer Site](https://developer.layer.com/login) to find your app id and token, then add the
following to your `application.conf` file:

```
layer {
  app_id = "your-layer-app-id"
  token = "your-layer-token"
}
```

## Usage

```scala
val client = new LayerClient(router, config)
client.sendMessage(message) // => Future[Either[ErrorResponse,Message]]
```
