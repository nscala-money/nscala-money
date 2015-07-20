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

import java.util.{Locale, Currency}

import org.joda.money._

import scala.collection.JavaConverters._

object StaticCurrencyUnit extends StaticCurrencyUnit

trait StaticCurrencyUnit {

  def registered: Seq[CurrencyUnit] = CurrencyUnit.registeredCurrencies().asScala

  def register(currencyCode: String,
               numericCurrencyCode: Int,
               decimalPlaces: Int,
               countryCodes: Seq[String]): CurrencyUnit =
    CurrencyUnit.registerCurrency(currencyCode, numericCurrencyCode, decimalPlaces, countryCodes.asJava)

  def register(currencyCode: String,
               numericCurrencyCode: Int,
               decimalPlaces: Int,
               countryCodes: Seq[String],
               force: Boolean): CurrencyUnit =
    CurrencyUnit.registerCurrency(currencyCode, numericCurrencyCode, decimalPlaces, countryCodes.asJava, force)

  def of(currency: Currency): CurrencyUnit = CurrencyUnit.of(currency)
  def of(locale: Locale): CurrencyUnit = CurrencyUnit.of(locale)
  def of(currencyCode: String): CurrencyUnit = CurrencyUnit.of(currencyCode)
  def ofCountry(countryCode: String): CurrencyUnit = CurrencyUnit.ofCountry(countryCode)
  def ofNumericCode(currencyCode: String): CurrencyUnit = CurrencyUnit.ofNumericCode(currencyCode)
  def ofNumericCode(currencyCode: Int): CurrencyUnit = CurrencyUnit.ofNumericCode(currencyCode)
}
