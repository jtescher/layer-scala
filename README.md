# Layer Scala

[![Build Status](https://travis-ci.org/jtescher/layer-scala.svg?branch=master)](https://travis-ci.org/jtescher/layer-scala)

Scala library for interacting with the layer API.

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
