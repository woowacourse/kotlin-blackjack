package model

import DeckExplicitGenerationStrategy
import model.card.Card
import model.card.Deck
import model.card.MarkType
import model.card.ValueType
import model.participants.Dealer
import model.participants.Hand
import model.participants.IdCard
import model.participants.Money
import model.participants.ParticipantState
import model.participants.Player
import model.participants.Players
import model.participants.Wallet

fun createTestDeck(vararg cards: Card): Deck {
    return Deck.create(DeckExplicitGenerationStrategy(cards.toList()))
}

fun createDealer(): Dealer {
    return Dealer(ParticipantState.None())
}

fun createBustedDealer(): Dealer {
    return Dealer(ParticipantState.Bust())
}

fun createPlayingDealer(hand: Hand = Hand()): Dealer {
    return Dealer(ParticipantState.Playing(hand))
}

fun createBlackJackDealer(): Dealer {
    return Dealer(ParticipantState.BlackJack())
}

fun createPlayer(): Player {
    return Player(ParticipantState.None())
}

fun createBustedPlayer(): Player {
    return Player(ParticipantState.Bust())
}

fun createPlayingPlayerWithMoney(
    hand: Hand = Hand(),
    money: Int,
): Player {
    return Player(ParticipantState.Playing(hand), Wallet(IdCard.fromInput("Player"), money = Money(money)))
}

fun createBustedPlayerWithMoney(money: Int): Player {
    return Player(ParticipantState.Bust(), Wallet(IdCard.fromInput("Player"), money = Money(money)))
}

fun createBlackJackPlayerWithMoney(money: Int): Player {
    return Player(ParticipantState.BlackJack(), Wallet(IdCard.fromInput("Player"), money = Money(money)))
}

fun createBlackJackPlayer(): Player {
    return Player(ParticipantState.BlackJack())
}

fun createPlayers(vararg name: String): Players {
    val playerNames =
        if (name.isEmpty()) {
            mutableListOf("pang") // 기본값을 사용하도록 설정
        } else {
            name.toMutableList()
        }

    val money = playerNames.map { Money(1000) }

    return Players.ofList(playerNames, money)
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
