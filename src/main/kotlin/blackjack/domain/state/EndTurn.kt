package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import kotlin.IllegalStateException

abstract class EndTurn(final override val cards: Cards) : State {
    override val score: Score = cards.calculateScore()
    override val size: Int = cards.size
    override fun draw(card: Card): State {
        throw IllegalStateException("더이상 카드를 뽑을 수 없습니다..")
    }
}
