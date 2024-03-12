package blackjack.model

import blackjack.model.card.Card
import blackjack.model.card.Denomination
import blackjack.model.card.Suite

fun Card(denomination: String): Card {
    val denominationValue = denomination.value()
    requireNotNull(denominationValue) { "유효하지 않은 알파벳 혹은 숫자입니다." }
    return Card(denominationValue, Suite.HEART)
}

private fun String.value(): Denomination? =
    when (this) {
        "A" -> Denomination.ACE
        "2" -> Denomination.TWO
        "3" -> Denomination.THREE
        "4" -> Denomination.FOUR
        "5" -> Denomination.FIVE
        "6" -> Denomination.SIX
        "7" -> Denomination.SEVEN
        "8" -> Denomination.EIGHT
        "9" -> Denomination.NINE
        "10" -> Denomination.TEN
        "K" -> Denomination.KING
        "Q" -> Denomination.QUEEN
        "J" -> Denomination.JACK
        else -> null
    }
