package blackjack.state

import blackjack.domain.card.Card
import blackjack.domain.gamer.Hand
import blackjack.domain.gamer.Money
import blackjack.domain.gamer.Score
import java.lang.IllegalArgumentException

abstract class Finish(hand: Hand) : Started(hand) {
    override fun draw(card: Card): State {
        throw IllegalArgumentException("카드를 더 받을 수 없는 상태입니다.")
    }

    override fun stay(): State {
        throw IllegalArgumentException("stay할 수 없는 상태입니다.")
    }

    override fun isFinish(): Boolean {
        return true
    }
}