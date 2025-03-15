package blackjack.domain.model.participant

import blackjack.domain.model.GameResult
import blackjack.domain.model.betting.BetAmount
import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Hand

class Player(
    name: String = DEFAULT_NAME,
    hand: Hand = Hand(),
    private val betAmount: BetAmount = BetAmount(0.0),
) : Participant(name, hand) {
    fun makeProfitRecord(dealer: Dealer): Map<Player, Double> {
        val gameResult: GameResult = compareTo(dealer)
        val profit: Double = betAmount.toProfit(gameResult)
        return mapOf(this to profit)
    }

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
