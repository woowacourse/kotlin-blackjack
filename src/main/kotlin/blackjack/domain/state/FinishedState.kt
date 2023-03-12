package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

abstract class FinishedState(cards: Cards) : CardState(cards) {
    abstract val earningRate: Double
    override val isFinished: Boolean = true

    init {
        check(cards.isGreaterOrEqualsCardSize(MINIMUM_CARDS_SIZE)) { "카드를 최소 2장 보유해야 합니다." }
    }

    override fun draw(card: Card): CardState = this

    override fun stay(): CardState = this

    override fun getFirstCard(): Card = cards.getFirstCard()

    companion object {
        private const val MINIMUM_CARDS_SIZE = 2
    }
}
