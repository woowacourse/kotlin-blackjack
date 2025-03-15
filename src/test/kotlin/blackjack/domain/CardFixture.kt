package blackjack.domain

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.CardNumber
import blackjack.domain.model.card.Shape

val SPADE_ACE = Card(Shape.Spade, CardNumber.Ace)
val SPADE_TWO = Card(Shape.Spade, CardNumber.Two)
val SPADE_THREE = Card(Shape.Spade, CardNumber.Three)
val SPADE_FOUR = Card(Shape.Spade, CardNumber.Four)
val SPADE_FIVE = Card(Shape.Spade, CardNumber.Five)
val SPADE_SIX = Card(Shape.Spade, CardNumber.Six)
val SPADE_SEVEN = Card(Shape.Spade, CardNumber.Seven)
val SPADE_EIGHT = Card(Shape.Spade, CardNumber.Eight)
val SPADE_NINE = Card(Shape.Spade, CardNumber.Nine)
val SPADE_TEN = Card(Shape.Spade, CardNumber.Ten)
val SPADE_JACK = Card(Shape.Spade, CardNumber.Jack)
val SPADE_QUEEN = Card(Shape.Spade, CardNumber.Queen)
val SPADE_KING = Card(Shape.Spade, CardNumber.King)

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
