package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

abstract class FinishedState(cards: Cards) : CardState(cards) {
    protected abstract val earningRate: Double
    override val isRunning: Boolean = false

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
