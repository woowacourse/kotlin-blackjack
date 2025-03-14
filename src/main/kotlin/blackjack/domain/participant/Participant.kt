package blackjack.domain.participant

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

    abstract fun getResult(other: Participant): GameResult

    abstract fun getProfit(gameResult: GameResult): Double

    companion object {
        const val ACE_SOFT_SCORE = 10
        const val ACE_HARD_SCORE = 0
        const val INITIAL_CARD_COUNT = 2
        const val BLACKJACK_SCORE = 21
    }
}
