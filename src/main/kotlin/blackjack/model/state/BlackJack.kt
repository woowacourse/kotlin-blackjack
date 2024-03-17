package blackjack.model.state

import blackjack.base.BaseHolder
import blackjack.model.GameResult
import blackjack.model.Hand

class BlackJack(hand: Hand) : Finished(hand) {
    // 자신이 블랙잭일 때 상대와 비교
    override fun decideWinner(opponent: BaseHolder): GameResult {
        // 상대가 블랙잭일 때 무승부
        if (opponent.state is BlackJack)
            return GameResult(push = 1)
        return GameResult(win = 1)
    }
}
