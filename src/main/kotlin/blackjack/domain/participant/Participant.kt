package blackjack.domain.participant

import blackjack.domain.BlackJackGame.Companion.BUST_STANDARD
import blackjack.domain.GameResult
import blackjack.domain.ParticipantCards
import blackjack.domain.card.TrumpCard

abstract class Participant(
    val cards: ParticipantCards,
) {
    abstract fun getInitialCards(): List<TrumpCard>

    fun receiveCard(card: TrumpCard) {
        cards.add(card)
    }

    fun getAllCards(): List<TrumpCard> = cards.allCards

    fun isBust(extraScore: Int = ACE_HARD_SCORE): Boolean = cards.sumOfCards + extraScore > BUST_STANDARD

    fun finalScore(): Int =
        if (cards.hasAce() && !isBust(ACE_SOFT_SCORE)) {
            cards.sumOfCards + ACE_SOFT_SCORE
        } else {
            cards.sumOfCards
        }

    abstract fun getResult(other: Participant): GameResult

    companion object {
        const val ACE_SOFT_SCORE = 10
        const val ACE_HARD_SCORE = 0
    }
}
