package blackjack.model

import blackjack.model.card.Card
import blackjack.model.card.Denomination
import blackjack.model.card.Suite
import blackjack.model.participant.BattingAmount
import blackjack.model.participant.HandCards
import blackjack.model.participant.Player
import blackjack.model.participant.PlayerName

val HEART_ACE = Card.of(Denomination.ACE, Suite.HEART)
val HEART_TWO = Card.of(Denomination.TWO, Suite.HEART)
val HEART_THREE = Card.of(Denomination.THREE, Suite.HEART)
val HEART_FOUR = Card.of(Denomination.FOUR, Suite.HEART)
val HEART_FIVE = Card.of(Denomination.FIVE, Suite.HEART)
val HEART_SIX = Card.of(Denomination.SIX, Suite.HEART)
val HEART_SEVEN = Card.of(Denomination.SEVEN, Suite.HEART)
val HEART_EIGHT = Card.of(Denomination.EIGHT, Suite.HEART)
val HEART_NINE = Card.of(Denomination.NINE, Suite.HEART)
val HEART_TEN = Card.of(Denomination.TEN, Suite.HEART)
val HEART_KING = Card.of(Denomination.KING, Suite.HEART)
val HEART_QUEEN = Card.of(Denomination.QUEEN, Suite.HEART)
val HEART_JACK = Card.of(Denomination.JACK, Suite.HEART)

fun HandCards(cards: List<Card>): HandCards {
    val handCards = HandCards()
    cards.forEach {
        handCards.addCard(it)
    }
    return handCards
}

fun Player(name: String): Player {
    return Player(PlayerName(name), BattingAmount(1000))
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
