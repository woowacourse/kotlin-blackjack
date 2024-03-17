package blackjack.model.state

import blackjack.base.BaseHolder
import blackjack.model.GameResult
import blackjack.model.Hand
import blackjack.model.Player

class Bust(hand: Hand) : Finished(hand) {
    // 자신이 Bust일 때 상대와 비교
    override fun decideWinner(opponent: BaseHolder): GameResult {
        // 플레이어(상대)가 버스트라면 딜러 승리
        if (opponent is Player && opponent.state is Bust)
            return GameResult(win = 1)
        return GameResult(defeat = 1)
    }
}
