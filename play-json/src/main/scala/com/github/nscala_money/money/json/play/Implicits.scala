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

package com.github.nscala_money.money.json.play

object Implicits extends Implicits

object ReadsImplicits extends ReadsImplicits
object WritesImplicits extends WritesImplicits

trait Implicits extends ReadsImplicits with WritesImplicits

trait ReadsImplicits {
  implicit val currencyUnitReads = JodaMoneyReads.DefaultCurrencyUnitReads
  implicit val moneyReads = JodaMoneyReads.DefaultMoneyReads
  implicit val bigMoneyReads = JodaMoneyReads.DefaultBigMoneyReads
}

trait WritesImplicits {
  implicit val currencyUnitWrites = JodaMoneyWrites.DefaultCurrencyWrites
  implicit val moneyWrites = JodaMoneyWrites.DefaultMoneyWrites
  implicit val bigMoneyWrites = JodaMoneyWrites.DefaultBigMoneyWrites
}
