package com.github.nscala_money.money

import scala.math.BigDecimal.RoundingMode.RoundingMode

private[money] trait Conversions {
  implicit def convertRoundingMode(roundingMode: RoundingMode): java.math.RoundingMode =
    java.math.RoundingMode.valueOf(roundingMode.id)
}
