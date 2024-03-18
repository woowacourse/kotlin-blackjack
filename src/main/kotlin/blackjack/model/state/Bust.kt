package blackjack.model.state

import blackjack.base.BaseHolder
import blackjack.model.GameResult
import blackjack.model.Hand
import blackjack.model.Player
import blackjack.model.Profit

class Bust(
    hand: Hand,
    profit: Profit
) : Finished(hand, profit) {
    init {
        this.profit.lostAllBettingMoney()
    }

    override fun decideWinner(opponent: BaseHolder): GameResult {
        if (opponent is Player && opponent.state is Bust) return makeOpponentLoser(opponent)
        return makeOpponentWinner(opponent)
    }
}
