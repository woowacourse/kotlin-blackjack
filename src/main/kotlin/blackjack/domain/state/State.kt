package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.CardBunchForState

interface State {
    val hand: CardBunchForState

    fun draw(card: Card): State = throw IllegalStateException("더이상 카드를 뽑을수 없는 상태입니다.")
}
