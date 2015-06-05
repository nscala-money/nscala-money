# nscala-money

A Scala wrapper for [Joda Money](http://www.joda.org/joda-money/), in
the same vein as the
[nscala-time](https://github.com/nscala-time/nscala-time) wrapper for
Joda Time.

## Installation

Add the following to your sbt build (Scala 2.9.3, Scala 2.10.x, and Scala 2.11.x):

```scala
libraryDependencies += "com.github.nscala-money" %% "nscala-money" % "0.1.0"

If you want to use previous versions, you can find them [here](https://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.github.nscala-money%22).


## Usage

This is mostly a convenience wrapper around the Joda Money libraries, adding more pleasant syntax like operators for addition, subtraction, and comparison. Also, most fields usually available as `getField` are now simply available as `field`, following Scala convention.  Some instances of `asX` or `toX` have also been shortened.

### Import
```scala
import com.github.nscala_money.money.Imports._
```

### Money Operations
TODO

### Fluent interface
TODO


Please see Joda Money for a full explanation of key concepts and the API:
[http://www.joda.org/jode-money](http://www.joda.org/jode-money)

## Documents

 - [scaladoc (latest stable release)](http://javadoc-badge.appspot.com/com.github.nscala-money/nscala-money_2.11)

Documentation of joda-money will also be helpful.
 - [User guide](http://www.joda.org/joda-money/userguide.html)
 - [Javdoc](http://www.joda.org/joda-money/apidocs/index.html)

## Acknowledgements

This wrapper library is intended to for Joda Money what `nscala-time`
does for Joda Time. The project structure and much of the boilerplate
(including this README) were taken directly from `nscala-time`.
