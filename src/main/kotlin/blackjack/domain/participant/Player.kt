package blackjack.domain.participant

import blackjack.domain.BlackJackGame.Companion.BUST_STANDARD
import blackjack.domain.GameResult
import blackjack.domain.Money
import blackjack.domain.ParticipantCards
import blackjack.domain.card.TrumpCard

class Player(
    val name: String,
    cards: ParticipantCards,
    val bettingMoney: Money,
) : Participant(cards) {
    override fun showInitialCards(): List<TrumpCard> = takeCards(PLAYER_INITIAL_CARD_COUNT)

    override fun isDrawable(): Boolean = cards.sumOfCards <= BUST_STANDARD

    override fun getResult(other: Participant): GameResult {
        val myScore = this.finalScore()
        val otherScore = other.finalScore()

        return when {
            other.isBlackJack() && !isBlackJack() -> GameResult.LOSE
            isBlackJack() && !other.isBlackJack() -> GameResult.BLACKJACK
            this.isBust() -> GameResult.LOSE
            other.isBust() && !this.isBust() -> GameResult.WIN
            myScore > otherScore -> GameResult.WIN
            myScore < otherScore -> GameResult.LOSE
            else -> GameResult.DRAW
        }
    }

    override fun getProfit(gameResult: GameResult): Double =
        when (gameResult) {
            GameResult.BLACKJACK -> bettingMoney.value * PLAYER_BLACKJACK_MULTIPLY
            GameResult.WIN -> bettingMoney.value * WIN_MULTIPLY
            GameResult.DRAW -> DRAW_MULTIPLY
            else -> bettingMoney.value * LOSE_MULTIPLY
        }

    companion object {
        private const val PLAYER_INITIAL_CARD_COUNT = 2
        private const val PLAYER_BLACKJACK_MULTIPLY = 1.5
        const val WIN_MULTIPLY = 1.0
        const val DRAW_MULTIPLY = 0.0
        const val LOSE_MULTIPLY = -1.0
    }
}
