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

package com.github.nscala_money.money.json.play

import com.github.nscala_money.money.Imports._
import play.api.libs.functional.syntax._
import play.api.libs.json._

object JodaMoneyWrites extends JodaMoneyWrites

trait JodaMoneyWrites {

  // ======================================= CurrencyUnit Writes =======================================
  val DefaultCurrencyWrites = CurrencyUnitCodeWrites

  object CurrencyUnitCodeWrites extends Writes[CurrencyUnit] {
    override def writes(cu: CurrencyUnit): JsString = JsString(cu.code)
  }

  object CurrencyUnitNumericCodeWrites extends Writes[CurrencyUnit] {
    override def writes(cu: CurrencyUnit): JsString = JsString(cu.numericCodeString)
  }

  // ======================================= Money Writes =======================================
  def DefaultMoneyWrites(implicit currencyWrites: Writes[CurrencyUnit]) = MoneyWrites

  def MoneyWrites(implicit currencyWrites: Writes[CurrencyUnit]): Writes[Money] = new Writes[Money] {
    override def writes(m: Money): JsObject =
      ((__ \ "currency").write[CurrencyUnit] and (__ \ "amount").write[BigDecimal])
      .tupled
      .writes(m.currencyUnit, m.amount)
  }

  object MoneyStringWrites extends Writes[Money] {
    override def writes(m: Money): JsString = JsString(m.toString)
  }

  // ======================================= BigMoney Writes =======================================
  def DefaultBigMoneyWrites(implicit currencyWrites: Writes[CurrencyUnit]) = BigMoneyWrites

  def BigMoneyWrites(implicit currencyWrites: Writes[CurrencyUnit]): Writes[BigMoney] = new Writes[BigMoney] {
    override def writes(m: BigMoney): JsObject =
      ((__ \ "currency").write[CurrencyUnit] and (__ \ "amount").write[BigDecimal])
      .tupled
      .writes(m.currencyUnit, m.amount)
  }

  object BigMoneyStringWrites extends Writes[BigMoney] {
    override def writes(m: BigMoney): JsString = JsString(m.toString)
  }
}
