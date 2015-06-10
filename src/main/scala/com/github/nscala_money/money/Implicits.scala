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

import org.joda.money._

object Implicits extends Implicits
object JodaImplicits extends JodaImplicits

trait Implicits extends JodaImplicits

trait JodaImplicits {
  implicit def richMoney(m: Money): RichMoney = new RichMoney(m)
  implicit def richBigMoney(bm: BigMoney): RichBigMoney = new RichBigMoney(bm)
  implicit def richBigMoneyProvider(bmp: BigMoneyProvider): RichBigMoneyProvider = new RichBigMoneyProvider(bmp)
}
