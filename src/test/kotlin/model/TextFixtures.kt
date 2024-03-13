package model

import DeckExplicitGenerationStrategy
import model.card.Card
import model.card.Deck
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
