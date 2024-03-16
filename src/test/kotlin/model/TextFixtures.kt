package model

import DeckExplicitGenerationStrategy
import model.card.Card
import model.card.Deck
import model.card.MarkType
import model.card.ValueType
import model.participants.Dealer
import model.participants.Hand
import model.participants.ParticipantState
import model.participants.Player
import model.participants.Players

fun makeTestDeck(vararg cards: Card): Deck {
    return Deck.create(DeckExplicitGenerationStrategy(cards.toMutableList()))
}

fun makeDealer(): Dealer {
    return Dealer(ParticipantState.Playing(Hand()))
}

fun makePlayer(): Player {
    return Player(ParticipantState.Playing(Hand()))
}

fun makePlayers(vararg name: String): Players {
    val playerNames =
        if (name.isEmpty()) {
            mutableListOf("pang") // 기본값을 사용하도록 설정
        } else {
            name.toMutableList()
        }
    return Players.ofList(playerNames)
}

val HEART_ACE = Card(ValueType.ACE, MarkType.HEART)
val HEART_TWO = Card(ValueType.TWO, MarkType.HEART)
val HEART_THREE = Card(ValueType.THREE, MarkType.HEART)
val HEART_FOUR = Card(ValueType.FOUR, MarkType.HEART)
val HEART_FIVE = Card(ValueType.FIVE, MarkType.HEART)
val HEART_SIX = Card(ValueType.SIX, MarkType.HEART)
val HEART_SEVEN = Card(ValueType.SEVEN, MarkType.HEART)
val HEART_EIGHT = Card(ValueType.EIGHT, MarkType.HEART)
val HEART_NINE = Card(ValueType.NINE, MarkType.HEART)
val HEART_TEN = Card(ValueType.TEN, MarkType.HEART)
val HEART_JACK = Card(ValueType.JACK, MarkType.HEART)
val HEART_KING = Card(ValueType.KING, MarkType.HEART)
val HEART_QUEEN = Card(ValueType.QUEEN, MarkType.HEART)
