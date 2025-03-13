package blackjack.domain.participant

import blackjack.domain.BlackJackGame.Companion.BUST_STANDARD
import blackjack.domain.GameResult
import blackjack.domain.ParticipantCards
import blackjack.domain.card.TrumpCard

class Player(
    val name: String,
    cards: ParticipantCards,
) : Participant(cards) {
    override fun showInitialCards(): List<TrumpCard> = cards.allCards.take(PLAYER_INITIAL_CARD_COUNT)

    override fun isDrawable(): Boolean = cards.sumOfCards <= BUST_STANDARD

    override fun getResult(other: Participant): GameResult {
        val myScore = this.finalScore()
        val otherScore = other.finalScore()

        return when {
            isBlackJack() -> GameResult.BLACKJACK
            this.isBust() -> GameResult.LOSE
            other.isBust() && !this.isBust() -> GameResult.WIN
            myScore > otherScore -> GameResult.WIN
            myScore < otherScore -> GameResult.LOSE
            else -> GameResult.DRAW
        }
    }

    companion object {
        const val PLAYER_INITIAL_CARD_COUNT = 2
    }
}
