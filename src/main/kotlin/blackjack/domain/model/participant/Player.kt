package blackjack.domain.model.participant

import blackjack.domain.model.GameResult
import blackjack.domain.model.card.Deck
import blackjack.domain.model.card.Hand

class Player(
    name: String = DEFAULT_NAME,
    hand: Hand = Hand(),
) : Participant(name, hand) {
    fun play(
        deck: Deck,
        onResponse: (String) -> Boolean,
        displayCards: (Player) -> Unit,
    ) {
        while (isDrawable()) {
            if (!onResponse(name)) {
                displayCards(this)
                break
            }
            drawCard(deck)
            displayCards(this)
        }
    }

    override fun compareTo(opponent: Participant): GameResult {
        val myScore = hand.getScore()
        val opponentScore = opponent.hand.getScore()

        return when {
            hand.isBust() -> GameResult.LOSE
            myScore > opponentScore -> GameResult.WIN
            myScore == opponentScore -> GameResult.DRAW
            else -> GameResult.LOSE
        }
    }

    override fun isDrawable(): Boolean = !hand.isBust()

    companion object {
        private const val DEFAULT_NAME = "이름 없음"
    }
}
