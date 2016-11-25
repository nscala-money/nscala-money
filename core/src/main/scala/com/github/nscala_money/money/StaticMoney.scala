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

object StaticMoney extends StaticMoney

trait StaticMoney extends Conversions {

  def nonNull(currency: CurrencyUnit, money: Money): Money = Money.nonNull(money, currency)

  def zero(currency: CurrencyUnit): Money = Money.zero(currency)
  def parse(str: String): Money = Money.parse(str)

  def of(provider: BigMoneyProvider): Money = Money.of(provider)
  def of(provider: BigMoneyProvider, mode: RoundingMode): Money = Money.of(provider, mode)
  def of(currency: CurrencyUnit, amount: BigDecimal): Money = Money.of(currency, amount.bigDecimal)
  def of(currency: CurrencyUnit, amount: BigDecimal, mode: RoundingMode): Money = Money.of(currency, amount.bigDecimal, mode)

  def ofMajor(currency: CurrencyUnit, amount: Long): Money = Money.ofMajor(currency, amount)
  def ofMinor(currency: CurrencyUnit, amount: Long): Money = Money.ofMinor(currency, amount)

  def total(monies: Iterable[Money]): Money = Money.total(monies.asJava)
  def total(monies: Money*): Money = Money.total(monies: _*)
  def total(currency: CurrencyUnit, monies: Iterable[Money]): Money = Money.total(currency, monies.asJava)
  def total(currency: CurrencyUnit, monies: Money*): Money = Money.total(currency, monies: _*)

  def min(money1: Money, money2: Money): Money = MoneyUtils.min(money1, money2)
  def max(money1: Money, money2: Money): Money = MoneyUtils.max(money1, money2)
}
