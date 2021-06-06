package blackjack.state

import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.Hand
import blackjack.domain.gamer.Money
import blackjack.domain.gamer.Score
import blackjack.domain.result.GameResult
import java.lang.IllegalArgumentException

abstract class Running(hand: Hand) : Started(hand) {
    override fun stay(): State {
        return Stay(hand)
    }

    override fun isFinish(): Boolean {
        return false
    }

    override fun result(dealerScore: Score): GameResult {
        throw IllegalAccessException("결과를 구할 수 있는 상태가 아닙니다.")
    }

    override fun earningRate(dealer: Dealer): Double {
        throw IllegalArgumentException("수익률을 구할 수 없습니다.")
    }
}