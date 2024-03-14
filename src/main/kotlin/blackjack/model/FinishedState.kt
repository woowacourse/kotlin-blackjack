package blackjack.model

import blackjack.base.BaseHolder

sealed class Finished(override val hand: Hand) : State(hand), CompareImpl

class Bust(hand: Hand) : Finished(hand) {
    // 자신이 Bust일 때 상대와 비교
    override fun decideWinner(opponent: BaseHolder): GameResult {
        // 플레이어(상대)가 버스트라면 딜러 승리
        if (opponent is Player && opponent.state is Bust)
            return GameResult(win = 1)
        return GameResult(defeat = 1)
    }
}

class BlackJack(hand: Hand) : Finished(hand) {
    // 자신이 블랙잭일 때 상대와 비교
    override fun decideWinner(opponent: BaseHolder): GameResult {
        // 상대가 블랙잭일 때 무승부
        if (opponent.state is BlackJack)
            return GameResult(push = 1)
        return GameResult(win = 1)
    }
}

class Stay(hand: Hand) : Finished(hand) {
    // 자신이 스테이일 때 상대와 비교
    override fun decideWinner(opponent: BaseHolder): GameResult {
        return when (opponent.state as blackjack.model.Finished) {
            is Bust -> GameResult(win = 1)
            is BlackJack -> GameResult(defeat = 1)
            is Stay -> compareWhenBothStay((opponent.state as Stay).hand.calculate())
        }
    }

    private fun compareWhenBothStay(opponentPoint: Int): GameResult {
        val myPoint = hand.calculate()
        return if (myPoint > opponentPoint) GameResult(win = 1)
        else if (myPoint < opponentPoint) GameResult(defeat = 1)
        else GameResult(push = 1)
    }
}

interface CompareImpl {
    fun decideWinner(opponent: BaseHolder): GameResult
}
