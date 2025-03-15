package blackjack.domain.model.participant

import blackjack.domain.model.GameResult
import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Hand

abstract class Participant(
    val name: String,
    val hand: Hand,
) {
    fun drawCard(cards: List<Card>) {
        cards.forEach(hand::add)
    }

    fun handCards(): List<Card> = hand.cards

    open fun compareTo(opponent: Participant): GameResult {
        val myScore: Int = hand.getScore()
        val opponentScore: Int = opponent.hand.getScore()

        return when {
            hand.isBlackJack() && opponent.hand.isNotBlackJack() -> GameResult.BLACKJACK_WIN
            hand.isBlackJack() && opponent.hand.isBlackJack() -> GameResult.DRAW
            opponent.hand.isBust() || myScore > opponentScore -> GameResult.WIN
            myScore == opponentScore -> GameResult.DRAW
            else -> GameResult.LOSE
        }
    }

    abstract fun showFirstHand(): List<Card>

    abstract fun isDrawable(): Boolean
}
