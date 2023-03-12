package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

abstract class FinishedState(protected val cards: Cards) : CardState {
    abstract val earningRate: Double
    override val isFinished: Boolean = true

    init {
        check(cards.isGreaterOrEqualsCardSize(MINIMUM_CARDS_SIZE)) { "카드를 최소 2장 보유해야 합니다." }
    }

    override fun draw(card: Card): CardState = this

    override fun stay(): CardState = this

    override fun getAllCards(): List<Card> = cards.items

    override fun getFirstCard(): Card = cards.getFirstCard()

    override fun getTotalScore(): Int = cards.calculateTotalScore()

    companion object {
        private const val MINIMUM_CARDS_SIZE = 2
    }
}
