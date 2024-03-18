package blackjack.model.participant

import blackjack.model.deck.Deck
import blackjack.model.deck.HandCards

abstract class GameParticipant(protected val handCards: HandCards, protected val deck: Deck) {
    fun initializeCards() = handCards.add(deck, INIT_CARD_AMOUNT)

    fun getAllCards(): String = handCards.getAllCards()

    fun addCard() = handCards.add(deck, HIT_CARD_AMOUNT)

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
        private const val INIT_CARD_AMOUNT = 2
        private const val HIT_CARD_AMOUNT = 1
    }
}
