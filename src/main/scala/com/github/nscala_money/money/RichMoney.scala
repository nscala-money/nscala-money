/**
 * Copyright 2015 David R. Bild
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.github.nscala_money.money

import com.github.nscala_money.EnrichedType
import org.joda.money._

import scala.collection.JavaConverters._
import scala.math.BigDecimal.RoundingMode.RoundingMode

class RichMoney(val underlying: Money) extends Super with EnrichedType[Money] with Conversions {

  def convert(currency: CurrencyUnit, multiplier: BigDecimal, mode: RoundingMode): Money = underlying.convertedTo(currency, multiplier.bigDecimal, mode)

  def ++(addends: Iterable[Money]): Money = underlying.plus(addends.asJava)
  def +(addend: Money): Money = underlying.plus(addend)
  def +(addend: BigDecimal): Money = underlying.plus(addend.bigDecimal)
  def +(addend: BigDecimal, mode: RoundingMode): Money = underlying.plus(addend.bigDecimal, mode)
  def +(addend: Double): Money = underlying.plus(addend)
  def +(addend: Double, mode: RoundingMode): Money = underlying.plus(addend, mode)

  def --(subtrahends: Iterable[Money]): Money = underlying.minus(subtrahends.asJava)
  def -(subtrahend: Money): Money = underlying.minus(subtrahend)
  def -(subtrahend: BigDecimal): Money = underlying.minus(subtrahend.bigDecimal)
  def -(subtrahend: BigDecimal, mode: RoundingMode): Money = underlying.minus(subtrahend.bigDecimal, mode)
  def -(subtrahend: Double): Money = underlying.minus(subtrahend)
  def -(subtrahend: Double, mode: RoundingMode): Money = underlying.minus(subtrahend, mode)

  def *(multiplier: BigDecimal, mode: RoundingMode): Money = underlying.multipliedBy(multiplier.bigDecimal, mode)
  def *(multiplier: Double, mode: RoundingMode): Money = underlying.multipliedBy(multiplier, mode)
  def *(multiplier: Long): Money = underlying.multipliedBy(multiplier)

  def /(divisor: BigDecimal, mode: RoundingMode): Money = underlying.dividedBy(divisor.bigDecimal, mode)
  def /(divisor: Double, mode: RoundingMode): Money = underlying.dividedBy(divisor, mode)
  def /(divisor: Long, mode: RoundingMode): Money = underlying.dividedBy(divisor, mode)

  def rounded(scale: Int, mode: RoundingMode): Money = underlying.rounded(scale, mode)

  def withAmount(amount: BigDecimal): Money = underlying.withAmount(amount.bigDecimal)
  def withAmount(amount: BigDecimal, mode: RoundingMode): Money = underlying.withAmount(amount.bigDecimal, mode)
  def withAmount(amount: Double, mode: RoundingMode): Money = underlying.withAmount(amount, mode)
  def withCurrencyUnit(currency: CurrencyUnit, mode: RoundingMode): Money = underlying.withCurrencyUnit(currency, mode)

  def currencyUnit: CurrencyUnit = underlying.getCurrencyUnit
  def scale: Int = underlying.getScale
  def amount: BigDecimal = underlying.getAmount
  def amountMajor: BigDecimal = underlying.getAmountMajor
  def amountMajorLong: Long = underlying.getAmountMajorLong
  def amountMajorInt: Int = underlying.getAmountMajorInt
  def amountMinor: BigDecimal = underlying.getAmountMinor
  def amountMinorLong: Long = underlying.getAmountMinorLong
  def amountMinorInt: Int = underlying.getAmountMinorInt
  def minorPart: Int = underlying.getMinorPart
}
