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

    fun getProfit(gameResult: GameResult): Double =
        when (gameResult) {
            GameResult.BLACKJACK -> bettingMoney.value * 1.5
            GameResult.WIN -> bettingMoney.value.toDouble()
            GameResult.DRAW -> 0.0
            else -> (0 - bettingMoney.value).toDouble()
        }

    companion object {
        const val PLAYER_INITIAL_CARD_COUNT = 2
    }
}
