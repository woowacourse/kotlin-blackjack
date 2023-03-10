package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import kotlin.IllegalStateException

abstract class EndTurn(protected val cards: Cards) : State {
    override fun draw(card: Card): State {
        throw IllegalStateException("더이상 뽑을 카드가 없습니다.")
    }
}
