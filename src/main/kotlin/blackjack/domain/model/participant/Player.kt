package blackjack.domain.model.participant

import blackjack.domain.model.GameResult
import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Hand

class Player(
    name: String = DEFAULT_NAME,
    hand: Hand = Hand(),
) : Participant(name, hand) {
    override fun showFirstHand(): List<Card> = handCards()

    override fun compareTo(opponent: Participant): GameResult {
        if (hand.isBust()) {
            return GameResult.LOSE
        }
        return super.compareTo(opponent)
    }

    override fun isDrawable(): Boolean = !hand.isBust()

    companion object {
        private const val DEFAULT_NAME = "이름 없음"
    }
}
