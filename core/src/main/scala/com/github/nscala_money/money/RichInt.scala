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
import com.github.nscala_money.money.Imports._

import scala.util.control.Exception._

class RichInt(val underlying: Int) extends Super with EnrichedType[Int] {
  def toCurrency: CurrencyUnit = CurrencyUnit.ofNumericCode(underlying)

  def toCurrencyUnitOption: Option[CurrencyUnit] = catching(classOf[IllegalCurrencyException]) opt {
    toCurrency
  }
}
