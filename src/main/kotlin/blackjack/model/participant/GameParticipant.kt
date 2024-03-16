package blackjack.model.participant

import blackjack.model.deck.HandCards

abstract class GameParticipant(protected val handCards: HandCards) {
    fun initializeCards() = handCards.initialize()

    fun getAllCards(): String = handCards.getAllCards()

    fun addCard() = handCards.add()

    fun calculateScore(): Int {
        val baseScore: Int = handCards.getCardsScore()
        val hasAce: Boolean = handCards.hasAce()
        val adjustedScore: Int = baseScore + ACE_EXTRA_SCORE
        return if (hasAce && adjustedScore <= BLACKJACK_NUMBER) adjustedScore else baseScore
    }

    fun isBust(): Boolean = calculateScore() > BLACKJACK_NUMBER

    fun isBlackjack(): Boolean = handCards.getCardsSize() == 2 && calculateScore() == BLACKJACK_NUMBER

    companion object {
        private const val BLACKJACK_NUMBER = 21
        private const val ACE_EXTRA_SCORE = 10
    }
}
