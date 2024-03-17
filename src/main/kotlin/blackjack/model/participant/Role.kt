package blackjack.model.participant

import blackjack.model.card.Card

abstract class Role {
    private val handCards = HandCards()

    abstract fun decideMoreCard(): Boolean

    fun receiveCard(card: Card) {
        handCards.addCard(card)
    }

    fun isBurst() = handCards.getCardSum() > GAME_MAX_SCORE

    fun isBlackjack(): Boolean {
        return handCards.cards.size == BLACKJACK_CARD_SIZE && handCards.getCardSum() == GAME_MAX_SCORE
    }

    fun isMaxScore() = handCards.getCardSum() == GAME_MAX_SCORE

    fun getCardSum() = handCards.getCardSum()

    fun getCards() = handCards.cards

    companion object {
        private const val BLACKJACK_CARD_SIZE = 2
        private const val GAME_MAX_SCORE = 21
    }
}
