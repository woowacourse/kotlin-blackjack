package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

abstract class InTurn(final override val cards: Cards) : State {
    override val score: Score = cards.calculateScore()
    override val size = cards.size

    override fun draw(card: Card): State {
        throw IllegalStateException("지정되지 않은 행위입니다.")
    }
}
