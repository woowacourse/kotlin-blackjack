package blackjack.model.participant

import blackjack.model.deck.HandCards

abstract class GameParticipant(protected val handCards: HandCards) {
    fun isBust(): Boolean = handCards.calculateCardScore() > BLACKJACK_NUMBER

    fun isBlackjack(): Boolean = handCards.isBlackjackCard()

    fun getAllCards() = handCards.cards.joinToString(SPLIT_DELIMITER) { "${it.cardNumber.value}${it.shape.value}" }

    fun getScore() = handCards.calculateCardScore()

    companion object {
        const val SPLIT_DELIMITER = ", "
        const val BLACKJACK_NUMBER = 21
    }
}
