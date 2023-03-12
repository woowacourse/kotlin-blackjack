package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

open class State(val cards: Cards) {
    val score: Score = cards.calculateScore()
    val size = cards.size

    open fun draw(card: Card): State {
        throw IllegalStateException(ERROR_NO_MORE_DRAW)
    }

    open fun matchWith(otherState: State): Outcome {
        throw IllegalStateException(ERROR_MESSAGE_DRAW_TWO_CARD)
    }

    companion object {
        private const val ERROR_MESSAGE_DRAW_TWO_CARD = "끝나기전에는 결과를 비교할 수 없습니다."
        private const val ERROR_NO_MORE_DRAW = "더이상 카드를 뽑을 수 없습니다."
    }
}
