/*
 * Copyright 2015 David R. Bild
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

object Implicits extends Implicits
object NumericImplicits extends NumericImplicits
object StringImplicits extends StringImplicits
object OrderingImplicits extends OrderingImplicits
object JodaImplicits extends JodaImplicits

trait Implicits extends NumericImplicits with StringImplicits with OrderingImplicits with JodaImplicits

trait NumericImplicits {
  implicit def richInt(n: Int): RichInt = new com.github.nscala_money.money.RichInt(n)
  implicit def richLong(n: Long): RichLong = new com.github.nscala_money.money.RichLong(n)
  implicit def richDouble(d: Double): RichDouble = new com.github.nscala_money.money.RichDouble(d)

  implicit def richBigDecimal(bd: BigDecimal): RichBigDecimal = new RichBigDecimal(bd)
}

trait StringImplicits {
  implicit def richString(s: String): RichString = new com.github.nscala_money.money.RichString(s)
}

trait OrderingImplicits {
  implicit def BigMoneyOrdering: Ordering[BigMoney] = order[BigMoney, BigMoneyProvider]
  implicit def MoneyOrdering: Ordering[Money] = order[Money, BigMoneyProvider]
  private def order[A <: Comparable[B], B](implicit ev: A <:< B): Ordering[A] = new Ordering[A] {
    override def compare(x: A, y: A): Int = x compareTo y
  }
}

trait JodaImplicits {
  implicit def richMoney(m: Money): RichMoney = new RichMoney(m)
  implicit def richBigMoney(bm: BigMoney): RichBigMoney = new RichBigMoney(bm)
  implicit def richBigMoneyProvider(bmp: BigMoneyProvider): RichBigMoneyProvider = new RichBigMoneyProvider(bmp)
  implicit def richCurrencyUnit(cu: CurrencyUnit): RichCurrencyUnit = new RichCurrencyUnit(cu)
}
