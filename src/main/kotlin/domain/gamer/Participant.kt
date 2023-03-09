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

    fun calculateCardSum(): Int {
        return cards.getCards()
            .sumOf { it.cardValue.value } +
            if (isAceValueToEleven()) CardValue.ACE_ELEVEN_VALUE - CardValue.ACE.value else 0
    }

    private fun isAceValueToEleven(): Boolean {
        return checkAceContained() && cards.getCards()
            .sumOf { it.cardValue.value } <= CardValue.ACE_ELEVEN_VALUE
    }

    private fun checkAceContained() = cards.getCards().any { it.cardValue.title == CardValue.ACE.title }

    fun checkBurst(): Boolean = calculateCardSum() > Referee.CARD_SUM_MAX_VALUE

    fun checkBlackjack(): Boolean =
        calculateCardSum() == Referee.CARD_SUM_MAX_VALUE && cards.getCards().size == START_DECK_CARD_COUNT

    companion object {
        private const val ACE_COUNT_VALUE_CHANGE_CONDITION = 2
        private const val START_DECK_CARD_COUNT = 2
    }
}
