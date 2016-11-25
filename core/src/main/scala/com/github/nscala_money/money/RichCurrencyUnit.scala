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

import java.util.{Currency, Locale}

import com.github.nscala_money.EnrichedType
import org.joda.money._

import scala.collection.JavaConverters._

class RichCurrencyUnit(val underlying: CurrencyUnit) extends Super with EnrichedType[CurrencyUnit] {

  def code: String = underlying.getCode
  def countryCodes: Set[String] = underlying.getCountryCodes.asScala.toSet
  def currencyCode: String = underlying.getCurrencyCode
  def decimalPlaces: Int = underlying.getDecimalPlaces
  def defaultFractionDigits: Int = underlying.getDefaultFractionDigits
  def numericCode: Int = underlying.getNumericCode
  def numericCodeString: String = underlying.getNumeric3Code
  def symbol: String = underlying.getSymbol
  def symbol(locale: Locale): String = underlying.getSymbol(locale)
  def currency: Currency = underlying.toCurrency
}
