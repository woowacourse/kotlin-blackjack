package blackjack.domain

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Denomination
import blackjack.domain.model.card.Suit

val SPADE_ACE = Card(Suit.Spade, Denomination.Ace)
val SPADE_TWO = Card(Suit.Spade, Denomination.Two)
val SPADE_THREE = Card(Suit.Spade, Denomination.Three)
val SPADE_FOUR = Card(Suit.Spade, Denomination.Four)
val SPADE_FIVE = Card(Suit.Spade, Denomination.Five)
val SPADE_SIX = Card(Suit.Spade, Denomination.Six)
val SPADE_SEVEN = Card(Suit.Spade, Denomination.Seven)
val SPADE_EIGHT = Card(Suit.Spade, Denomination.Eight)
val SPADE_NINE = Card(Suit.Spade, Denomination.Nine)
val SPADE_TEN = Card(Suit.Spade, Denomination.Ten)
val SPADE_JACK = Card(Suit.Spade, Denomination.Jack)
val SPADE_QUEEN = Card(Suit.Spade, Denomination.Queen)
val SPADE_KING = Card(Suit.Spade, Denomination.King)

fun bustCardList(): List<Card> =
    listOf(
        SPADE_KING,
        SPADE_QUEEN,
        SPADE_JACK,
    )

fun blackjackCardList(): List<Card> =
    listOf(
        SPADE_KING,
        SPADE_ACE,
    )

fun notBustCardList(): List<Card> =
    listOf(
        SPADE_TWO,
        SPADE_THREE,
    )

fun twoAceCardList(): List<Card> =
    listOf(
        SPADE_ACE,
        SPADE_ACE,
    )

fun notBustBut21(): List<Card> =
    listOf(
        SPADE_SEVEN,
        SPADE_SEVEN,
        SPADE_SEVEN,
    )
