package blackjack.domain.state.finished

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.state.State

abstract class Finished(val cards: Cards) : State {
    override fun draw(card: Card): State {
        throw IllegalStateException("카드를 더 이상 뽑을 수 없습니다.")
    }
}
