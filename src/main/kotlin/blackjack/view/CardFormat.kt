package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Rank
import blackjack.domain.Suit
import java.lang.StringBuilder

internal fun Cards.format(): String {
    val cardStr = StringBuilder()
    this.getCards().forEach { card ->
        cardStr.append(card.format())
        cardStr.append(", ")
    }
    return cardStr.toString()
}

internal fun Card.format(): String {
    return this.rank.toDisplayName() + this.suit.toDisplayName()
}

internal fun Rank.toDisplayName(): String {
    return when (this) {
        Rank.ACE -> "A"
        Rank.TWO -> "2"
        Rank.THREE -> "3"
        Rank.FOUR -> "4"
        Rank.FIVE -> "5"
        Rank.SIX -> "6"
        Rank.SEVEN -> "7"
        Rank.EIGHT -> "8"
        Rank.NINE -> "9"
        Rank.TEN -> "10"
        Rank.JACK -> "J"
        Rank.QUEEN -> "Q"
        Rank.KING -> "K"
    }
}

internal fun Suit.toDisplayName(): String {
    return when (this) {
        Suit.SPADE -> "스페이드"
        Suit.HEART -> "하트"
        Suit.DIAMOND -> "다이아몬드"
        Suit.CLUB -> "클로버"
    }
}
