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

    fun takeCards(count: Int): List<TrumpCard> = cards.allCards.take(count)

    open fun getResult(other: Participant): GameResult {
        val myScore = cards.finalScore()
        val otherScore = other.cards.finalScore()

        return when {
            !cards.isBlackJack() && other.cards.isBlackJack() -> GameResult.LOSE
            cards.isBlackJack() && !other.cards.isBlackJack() -> GameResult.BLACKJACK
            other.cards.isBust() -> GameResult.WIN
            myScore > otherScore -> GameResult.WIN
            myScore < otherScore -> GameResult.LOSE
            else -> GameResult.DRAW
        }
    }

    abstract fun isDrawable(): Boolean
}
