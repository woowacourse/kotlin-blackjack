package blackjack.view

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.person.Person
import blackjack.domain.result.Profit
import blackjack.domain.state.PersonState
import java.text.DecimalFormat

fun Profit.formatAmount(): String {
    val formatter = DecimalFormat("#.##")
    return formatter.format(this)
}

fun Person.isBlackJackString(): String = if (this.gameState == PersonState.BLACKJACK) "BLACKJACK!" else "${this.score()}"

fun List<Card>.toUiString(): String = joinToString(", ") { it.toUiString() }

fun Card.toUiString(): String = "${number.toUiString()}${pattern.toUiString()}"

fun CardNumber.toUiString(): String =
    when (this) {
        CardNumber.ACE -> "A"
        CardNumber.KING -> "K"
        CardNumber.QUEEN -> "Q"
        CardNumber.JACK -> "J"
        else -> this.value.toString()
    }

fun CardPattern.toUiString(): String =
    when (this) {
        CardPattern.HEART -> "하트"
        CardPattern.SPADE -> "스페이드"
        CardPattern.DIAMOND -> "다이아몬드"
        CardPattern.CLOVER -> "클로버"
    }
