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

object JodaMoneyReads extends JodaMoneyReads

trait JodaMoneyReads {
  private def error(message: String, args: Any*) = JsError(Seq(JsPath() -> Seq(JsonValidationError(message, args: _*))))
  private def safe[R](f: => R): Option[R] = scala.util.control.Exception.allCatch opt (f)

  // ======================================= CurrencyUnit Reads =======================================
  object DefaultCurrencyUnitReads extends Reads[CurrencyUnit] {
    private val all = CurrencyUnitCurrencyCodeReads or CurrencyUnitNumericCodeReads or CurrencyUnitCountryReads

    override def reads(json: JsValue): JsResult[CurrencyUnit] = all.reads(json) match {
      case s@JsSuccess(cu, p) => s
      case JsError(_) => error("error.expected.currency")
    }
  }

  object CurrencyUnitCurrencyCodeReads extends Reads[CurrencyUnit] {
    override def reads(json: JsValue): JsResult[CurrencyUnit] = json match {
      case JsString(s) => safe(CurrencyUnit.of(s)) match {
        case Some(cu) => JsSuccess(cu)
        case None => error("error.expected.currency.code")
      }
      case _ => error("error.expected.currency.code")
    }
  }

  object CurrencyUnitNumericCodeReads extends Reads[CurrencyUnit] {
    override def reads(json: JsValue): JsResult[CurrencyUnit] = json match {
      case JsNumber(n) => safe(CurrencyUnit.ofNumericCode(n.toIntExact)) match {
        case Some(cu) => JsSuccess(cu)
        case None => error("error.expected.currency.numericcode")
      }
      case JsString(s) => safe(CurrencyUnit.ofNumericCode(s)) match {
        case Some(cu) => JsSuccess(cu)
        case None => error("error.expected.currency.numericcode")
      }
      case _ => error("error.expected.currency.numericcode")
    }
  }

  object CurrencyUnitCountryReads extends Reads[CurrencyUnit] {
    override def reads(json: JsValue): JsResult[CurrencyUnit] = json match {
      case JsString(s) => safe(CurrencyUnit.ofCountry(s)) match {
        case Some(cu) => JsSuccess(cu)
        case None => error("error.expected.currency.country")
      }
      case _ => error("error.expected.currency.country")
    }
  }

  // ======================================= Money Reads =======================================
  def DefaultMoneyReads(implicit currencyReads: Reads[CurrencyUnit]): Reads[Money] = new Reads[Money] {
    private val all = MoneyReads or MoneyParseReads

    override def reads(json: JsValue): JsResult[Money] = all.reads(json) match {
      case JsSuccess(m, p) => JsSuccess(m, p)
      case JsError(_) => error("error.expected.money")
    }
  }

  def MoneyReads(implicit currencyReads: Reads[CurrencyUnit]): Reads[Money] = new Reads[Money] {
    private val reader = ((__ \ "currency").read[CurrencyUnit] and (__ \ "amount").read[BigDecimal]).tupled

    override def reads(json: JsValue): JsResult[Money] = reader.reads(json) match {
      case JsSuccess((currency, amount), p) => safe(Money.of(currency, amount)) match {
        case Some(m) => JsSuccess(m, p)
        case None => error("error.expected.money")
      }
      case e@JsError(_) => e
    }
  }

  object MoneyParseReads extends Reads[Money] {
    override def reads(json: JsValue): JsResult[Money] = json match {
      case JsString(s) => safe(Money.parse(s)) match {
        case Some(m) => JsSuccess(m)
        case None => error("error.expected.money")
      }
      case _ => error("error.expected.money")
    }
  }

  // ======================================= BigMoney Reads =======================================
  def DefaultBigMoneyReads(implicit currencyReads: Reads[CurrencyUnit]): Reads[BigMoney] = new Reads[BigMoney] {
    private val all = BigMoneyReads or BigMoneyParseReads

    override def reads(json: JsValue): JsResult[BigMoney] = all.reads(json) match {
      case JsSuccess(m, p) => JsSuccess(m, p)
      case JsError(_) => error("error.expected.money")
    }
  }

  def BigMoneyReads(implicit currencyReads: Reads[CurrencyUnit]): Reads[BigMoney] = new Reads[BigMoney] {
    private val reader = ((__ \ "currency").read[CurrencyUnit] and (__ \ "amount").read[BigDecimal]).tupled

    override def reads(json: JsValue): JsResult[BigMoney] = reader.reads(json) match {
      case JsSuccess((currency, amount), p) => safe(BigMoney.of(currency, amount)) match {
        case Some(m) => JsSuccess(m, p)
        case None => error("error.expected.money")
      }
      case e@JsError(_) => e
    }
  }

  object BigMoneyParseReads extends Reads[BigMoney] {
    override def reads(json: JsValue): JsResult[BigMoney] = json match {
      case JsString(s) => safe(BigMoney.parse(s)) match {
        case Some(m) => JsSuccess(m)
        case None => error("error.expected.money")
      }
      case _ => error("error.expected.money")
    }
  }

}
