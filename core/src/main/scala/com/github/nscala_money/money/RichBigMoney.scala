/*
 * Copyright 2015-2016 David R. Bild
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.nscala_money.money

import com.github.nscala_money.EnrichedType
import org.joda.money._

import scala.collection.JavaConverters._
import scala.math.BigDecimal.RoundingMode.RoundingMode

class RichBigMoney(val underlying: BigMoney) extends Super with EnrichedType[BigMoney] with Conversions {

  def money: Money = underlying.toMoney
  def money(mode: RoundingMode): Money = underlying.toMoney(mode)

  def convert(currency: CurrencyUnit, multiplier: BigDecimal): BigMoney = underlying.convertedTo(currency, multiplier.bigDecimal)
  def convert(currency: CurrencyUnit, multiplier: BigDecimal, mode: RoundingMode): BigMoney = underlying.convertRetainScale(currency, multiplier.bigDecimal, mode)

  def ++(addends: Iterable[BigMoneyProvider]): BigMoney = underlying.plus(addends.asJava)
  def +(addend: BigMoneyProvider): BigMoney = underlying.plus(addend)
  def +(addend: BigDecimal): BigMoney = underlying.plus(addend.bigDecimal)
  def +(addend: Double): BigMoney = underlying.plus(addend)
  def +(addend: BigMoneyProvider, mode: RoundingMode): BigMoney = underlying.plusRetainScale(addend, mode)
  def +(addend: BigDecimal, mode: RoundingMode): BigMoney = underlying.plusRetainScale(addend.bigDecimal, mode)
  def +(addend: Double, mode: RoundingMode): BigMoney = underlying.plusRetainScale(addend, mode)

  def --(subtrahends: Iterable[BigMoneyProvider]): BigMoney = underlying.minus(subtrahends.asJava)
  def -(subtrahend: BigMoneyProvider): BigMoney = underlying.minus(subtrahend)
  def -(subtrahend: BigDecimal): BigMoney = underlying.minus(subtrahend.bigDecimal)
  def -(subtrahend: Double): BigMoney = underlying.minus(subtrahend)
  def -(subtrahend: BigMoneyProvider, mode: RoundingMode): BigMoney = underlying.minusRetainScale(subtrahend, mode)
  def -(subtrahend: BigDecimal, mode: RoundingMode): BigMoney = underlying.minusRetainScale(subtrahend.bigDecimal, mode)
  def -(subtrahend: Double, mode: RoundingMode): BigMoney = underlying.minusRetainScale(subtrahend, mode)

  def *(multiplier: BigDecimal): BigMoney = underlying.multipliedBy(multiplier.bigDecimal)
  def *(multiplier: Double): BigMoney = underlying.multipliedBy(multiplier)
  def *(multiplier: BigDecimal, mode: RoundingMode): BigMoney = underlying.multiplyRetainScale(multiplier.bigDecimal, mode)
  def *(multiplier: Double, mode: RoundingMode): BigMoney = underlying.multiplyRetainScale(multiplier, mode)

  def /(divisor: BigDecimal, mode: RoundingMode): BigMoney = underlying.dividedBy(divisor.bigDecimal, mode)
  def /(divisor: Double, mode: RoundingMode): BigMoney = underlying.dividedBy(divisor, mode)
  def /(divisor: Long, mode: RoundingMode): BigMoney = underlying.dividedBy(divisor, mode)

  def rounded(scale: Int, mode: RoundingMode): BigMoney = underlying.rounded(scale, mode)

  def withScale(scale: Int, mode: RoundingMode): BigMoney = underlying.withScale(scale, mode)
  def withCurrencyScale(mode: RoundingMode): BigMoney = underlying.withCurrencyScale(mode)
  def withAmount(amount: BigDecimal): BigMoney = underlying.withAmount(amount.bigDecimal)

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
