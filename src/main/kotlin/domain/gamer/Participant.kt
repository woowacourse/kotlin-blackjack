package domain.gamer

import domain.card.Card
import domain.card.CardValue
import domain.deck.Deck
import domain.gamer.cards.Cards
import domain.judge.Referee

abstract class Participant(open val cards: Cards) {

    fun makeStartDeck() {
        repeat(START_DECK_CARD_COUNT) {
            pickCard(Deck.giveCard())
        }
    }

    open fun pickCard(card: Card) {
        cards.addCard(card)
    }

    fun calculateCardSum(): Int {
        var value = 0
        cards.getCards().forEach {
            value += getCardValue(it, value)
        }
        return value
    }

    private fun getCardValue(card: Card, value: Int) =
        if (card.cardValue == CardValue.ACE) {
            getAceValue(value)
        } else {
            card.cardValue.value
        }

    private fun getAceValue(value: Int) =
        if (countAce() >= ACE_COUNT_VALUE_CHANGE_CONDITION) {
            CardValue.ACE.value
        } else if (value > Referee.CARD_SUM_MAX_VALUE - CardValue.ACE_ELEVEN_VALUE) {
            CardValue.ACE.value
        } else {
            CardValue.ACE_ELEVEN_VALUE
        }

    private fun countAce() = cards.getCards().count { it.cardValue.title == "ACE" }

    fun checkBurst(): Boolean = calculateCardSum() > Referee.CARD_SUM_MAX_VALUE

    fun checkBlackjack(): Boolean =
        calculateCardSum() == Referee.CARD_SUM_MAX_VALUE && cards.getCards().size == START_DECK_CARD_COUNT

    companion object {
        private const val ACE_COUNT_VALUE_CHANGE_CONDITION = 2
        private const val START_DECK_CARD_COUNT = 2
    }
}
