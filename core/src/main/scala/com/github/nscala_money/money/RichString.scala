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

import scala.util.control.Exception._

import com.github.nscala_money.EnrichedType

import com.github.nscala_money.money.Imports._

class RichString(val underlying: String) extends Super with EnrichedType[String] {
  def toCurrency: CurrencyUnit = CurrencyUnit.of(underlying)
  def toMoney: Money = Money.parse(underlying)
  def toBigMoney: BigMoney = BigMoney.parse(underlying)

  def toCurrencyOption: Option[CurrencyUnit] = catching(classOf[IllegalCurrencyException]) opt {
    toCurrency
  }

  def toMoneyOption: Option[Money] = catching(classOf[ArithmeticException], classOf[IllegalArgumentException]) opt {
    toMoney
  }

  def toBigMoneyOption: Option[BigMoney] = catching(classOf[ArithmeticException], classOf[IllegalArgumentException]) opt {
    toBigMoney
  }
}
