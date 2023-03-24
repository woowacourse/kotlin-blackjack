package model.domain.card

import model.tools.CardNumber

class Hand(cardsInHand: List<Card>) {

    constructor(vararg card: Card) : this(card.toList())

    private val _cards: MutableList<Card> = cardsInHand.toMutableList()
    val cards: List<Card> get() = _cards.toList()

    fun dealerIsNotOver17(): Boolean = getTotalScoreWithAceCard() < DEALER_SCORE_RULE

    fun drawOneCard(card: Card) {
        _cards.add(card)
    }

    fun isBust(): Boolean = getTotalScoreWithAceCard() > BlACK_JACK_SCORE

    fun isBlackJack(): Boolean =
        (getTotalScoreWithAceCard() == BlACK_JACK_SCORE) and (cards.size == DEFAULT_HAND_CARDS)

    fun getTotalScoreWithAceCard(): Int {
        if ((getTotalScoreOfCards() <= BLACK_JACK_LESS_TEN) and (hasAceCard())) {
            return getTotalScoreOfCards() + ACE_CARD_PLUS_TEN
        }

        return getTotalScoreOfCards()
    }

    private fun getTotalScoreOfCards(): Int {
        return cards.sumOf { card ->
            card.cardNumber.number
        }
    }

    private fun hasAceCard(): Boolean = _cards.any { card ->
        card.cardNumber == CardNumber.ACE
    }

    companion object {
        private const val DEALER_SCORE_RULE = 17
        private const val BlACK_JACK_SCORE = 21
        private const val DEFAULT_HAND_CARDS = 2
        private const val BLACK_JACK_LESS_TEN = 11
        private const val ACE_CARD_PLUS_TEN = 10
    }
}
