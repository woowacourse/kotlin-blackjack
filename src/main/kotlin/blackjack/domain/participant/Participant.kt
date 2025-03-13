package blackjack.domain.participant

import blackjack.domain.BlackJackGame.Companion.BUST_STANDARD
import blackjack.domain.GameResult
import blackjack.domain.ParticipantCards
import blackjack.domain.card.TrumpCard

abstract class Participant(
    val cards: ParticipantCards,
) {
    abstract fun showInitialCards(): List<TrumpCard>

    fun receiveCard(card: TrumpCard) {
        cards.add(card)
    }

    fun takeCards(count: Int): List<TrumpCard> = getAllCards().take(count)

    fun getAllCards(): List<TrumpCard> = cards.allCards

    abstract fun isDrawable(): Boolean

    fun isBust(extraScore: Int = ACE_HARD_SCORE): Boolean = cards.sumOfCards + extraScore > BUST_STANDARD

    fun finalScore(): Int =
        if (cards.hasAce() && !isBust(ACE_SOFT_SCORE)) {
            cards.sumOfCards + ACE_SOFT_SCORE
        } else {
            cards.sumOfCards
        }

    fun isBlackJack(): Boolean = cards.size() == INITIAL_CARD_COUNT && this.finalScore() == BLACKJACK_SCORE

    abstract fun getResult(other: Participant): GameResult

    companion object {
        const val ACE_SOFT_SCORE = 10
        const val ACE_HARD_SCORE = 0
        const val INITIAL_CARD_COUNT = 2
        const val BLACKJACK_SCORE = 21
    }
}
