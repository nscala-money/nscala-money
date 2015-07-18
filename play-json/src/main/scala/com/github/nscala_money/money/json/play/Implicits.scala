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

