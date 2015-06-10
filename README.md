# nscala-money

A Scala wrapper for [Joda Money](http://www.joda.org/joda-money/), in
the same vein as the
[nscala-time](https://github.com/nscala-time/nscala-time) wrapper for
Joda Time.

## Installation

Add the following to your sbt build (Scala 2.9.3, Scala 2.10.x, and Scala 2.11.x):

```scala
libraryDependencies += "com.github.nscala-money" %% "nscala-money" % "0.9.0"
```

If you want to use previous versions, you can find them [here](https://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.github.nscala-money%22).

## Usage

This is mostly a convenience wrapper around the Joda Money libraries, adding more pleasant syntax like operators for addition, subtraction, and comparison. Also, most fields usually available as `getField` are now simply available as `field`, following Scala convention.  Some instances of `asX` or `toX` have also been shortened.

### Import
```scala
import com.github.nscala_money.money.Imports._
```

### Money Operations
```scala
"USD 10.99".toMoney // returns org.joda.money.Money

"USD".toCurrency // returns org.joda.money.CurrencyUnit

"USD 10.99".toMoney + "USD 1.00".toMoney // returns org.joda.money.Money = USD 11.99
```

Please see Joda Money for a full explanation of key concepts and the API:
[http://www.joda.org/joda-money](http://www.joda.org/joda-money)

## Documents

 - [scaladoc (latest stable release)](http://javadoc-badge.appspot.com/com.github.nscala-money/nscala-money_2.11)

Documentation of joda-money will also be helpful.
 - [User guide](http://www.joda.org/joda-money/userguide.html)
 - [Javdoc](http://www.joda.org/joda-money/apidocs/index.html)

## Acknowledgements

This wrapper library is intended to do for Joda Money what
`nscala-time` does for Joda Time. The project structure and much of
the boilerplate (including this README) were taken directly from
`nscala-time`.

## Contributing

Please submit bugs, questions, suggestions, or (ideally) contributions
as issues and pull requests on Github.

### Maintainers
**David R. Bild**

+ [https://www.davidbild.org](https://www.davidbild.org)
+ [https://github.com/drbild](https://github.com/drbild)

## License
Copyright 2015 David R. Bild

Licensed under the Apache License, Version 2.0 (the "License"); you may not use
this work except in compliance with the License. You may obtain a copy of the
License from the LICENSE.txt file or at

[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software distributed
under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
CONDITIONS OF ANY KIND, either express or implied. See the License for the
specific language governing permissions and limitations under the License.
