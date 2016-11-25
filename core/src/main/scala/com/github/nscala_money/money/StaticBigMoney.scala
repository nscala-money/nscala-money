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

import org.joda.money._

import scala.collection.JavaConverters._
import scala.math.BigDecimal.RoundingMode.RoundingMode

object StaticBigMoney extends StaticBigMoney

trait StaticBigMoney extends Conversions {

  def nonNull(currency: CurrencyUnit, money: BigMoney): BigMoney = BigMoney.nonNull(money, currency)

  def zero(currency: CurrencyUnit): BigMoney = BigMoney.zero(currency)
  def zero(currency: CurrencyUnit, scale: Int): BigMoney = BigMoney.zero(currency, scale)
  def parse(str: String): BigMoney = BigMoney.parse(str)

  def of(provider: BigMoneyProvider): BigMoney = BigMoney.of(provider)
  def of(currency: CurrencyUnit, amount: BigDecimal): BigMoney = BigMoney.of(currency, amount.bigDecimal)
  def of(currency: CurrencyUnit, amount: Double): BigMoney = BigMoney.of(currency, amount)

  def ofMajor(currency: CurrencyUnit, amount: Long): BigMoney = BigMoney.ofMajor(currency, amount)
  def ofMinor(currency: CurrencyUnit, amount: Long): BigMoney = BigMoney.ofMinor(currency, amount)

  def ofScale(currency: CurrencyUnit, amount: BigDecimal, scale: Int): BigMoney = BigMoney.ofScale(currency, amount.bigDecimal, scale)
  def ofScale(currency: CurrencyUnit, amount: BigDecimal, scale: Int, mode: RoundingMode): BigMoney = BigMoney.ofScale(currency, amount.bigDecimal, scale, mode)
  def ofScale(currency: CurrencyUnit, amount: Long, scale: Int): BigMoney = BigMoney.ofScale(currency, amount, scale)

  def total(monies: BigMoneyProvider*): BigMoney = BigMoney.total(monies: _*)
  def total(monies: Iterable[BigMoneyProvider]): BigMoney = BigMoney.total(monies.asJava)
  def total(currency: CurrencyUnit, monies: BigMoneyProvider*): BigMoney = BigMoney.total(currency, monies: _*)
  def total(currency: CurrencyUnit, monies: Iterable[BigMoneyProvider]): BigMoney = BigMoney.total(currency, monies.asJava)

  def min(money1: BigMoney, money2: BigMoney): BigMoney = MoneyUtils.min(money1, money2)
  def max(money1: BigMoney, money2: BigMoney): BigMoney = MoneyUtils.max(money1, money2)
}
