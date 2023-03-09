package domain.gamer

import domain.card.Card
import domain.card.CardValue
import domain.deck.Deck
import domain.gamer.cards.Cards
import domain.judge.Referee

abstract class Participant(val cards: Cards) {

    fun makeStartDeck(deck: Deck) {
        repeat(START_DECK_CARD_COUNT) {
            pickCard(deck.giveCard())
        }
    }

    open fun pickCard(card: Card) {
        cards.addCard(card)
    }

    companion object {
        const val START_DECK_CARD_COUNT = 2
    }
}
