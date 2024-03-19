package blackjack.model.state

import blackjack.base.BaseHolder
import blackjack.model.GameResult
import blackjack.model.Hand
import blackjack.model.Profit

class BlackJack(
    hand: Hand,
    profit: Profit,
) : Finished(hand, profit) {
    init {
        this.profit.earnProfitForBlackJack()
    }

    override fun decideWinner(opponent: BaseHolder): GameResult {
        if (opponent.state is BlackJack) return pushWithOpponent(opponent)
        return winOverOpponent(opponent)
    }
}
