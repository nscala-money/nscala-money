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

object Imports extends Imports
object TypeImports extends TypeImports
object StaticForwarderImports extends StaticForwarderImports

trait Imports extends TypeImports with StaticForwarderImports with Implicits

trait TypeImports {
  type Money = org.joda.money.Money
  type BigMoney = org.joda.money.BigMoney
  type BigMoneyProvider = org.joda.money.BigMoneyProvider
  type CurrencyUnit = org.joda.money.CurrencyUnit
}

trait StaticForwarderImports {
  val Money = com.github.nscala_money.money.StaticMoney
  val BigMoney = com.github.nscala_money.money.StaticBigMoney
  val CurrencyUnit = com.github.nscala_money.money.StaticCurrencyUnit
}
