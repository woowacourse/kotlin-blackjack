package blackjack.model.participant

import blackjack.model.deck.HandCards

abstract class GameParticipant(protected val handCards: HandCards) {
    fun isBust(): Boolean = handCards.calculateCardScore() > BLACKJACK_NUMBER

    fun isBlackjack(): Boolean = handCards.isBlackjackCard()

    fun getAllCards(): String = handCards.getAllCards()

    fun getScore(): Int = handCards.calculateCardScore()

    companion object {
        const val SPLIT_DELIMITER = ", "
        const val BLACKJACK_NUMBER = 21
    }
}
