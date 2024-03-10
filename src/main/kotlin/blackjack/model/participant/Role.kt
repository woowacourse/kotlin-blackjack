package blackjack.model.participant

import blackjack.model.card.Card

abstract class Role {
    private val scoreBoard = ScoreBoard()

    abstract fun decideMoreCard(): Boolean

    fun receiveCard(card: Card) {
        scoreBoard.addCardAndUpdateCardSum(card, BLACKJACK_VALUE)
    }

    fun isBurst() = scoreBoard.cardSum > BLACKJACK_VALUE

    fun isBlackjack() = scoreBoard.cardSum == BLACKJACK_VALUE

    fun getCardSum() = scoreBoard.cardSum

    fun getCards() = scoreBoard.handCards.cards

    companion object {
        private const val BLACKJACK_VALUE = 21
    }
}
