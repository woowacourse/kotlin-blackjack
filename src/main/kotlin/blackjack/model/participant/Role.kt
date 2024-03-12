package blackjack.model.participant

import blackjack.model.card.Card

abstract class Role {
    private val handCards = HandCards()

    abstract fun decideMoreCard(): Boolean

    fun receiveCard(cards: List<Card>) {
        handCards.addCard(cards)
    }

    fun isBurst() = handCards.getCardSum(BLACKJACK_VALUE) > BLACKJACK_VALUE

    fun isBlackjack() = handCards.getCardSum(BLACKJACK_VALUE) == BLACKJACK_VALUE

    fun getCardSum() = handCards.getCardSum(BLACKJACK_VALUE)

    fun getCards() = handCards.cards

    companion object {
        private const val BLACKJACK_VALUE = 21
    }
}
