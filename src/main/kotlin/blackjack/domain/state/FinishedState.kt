package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

abstract class FinishedState(private val cards: Cards) : CardState {
    init {
        check(cards.isGreaterOrEqualsThan(MINIMUM_CARDS_SIZE)) { "카드를 최소 2장 보유해야 합니다." }
    }

    override fun draw(card: Card): CardState {
        throw IllegalStateException("더 이상 카드를 뽑을 수 없습니다.")
    }

    override fun stay(): CardState {
        throw IllegalStateException("게임이 종료되어 스테이할 수 없습니다.")
    }

    companion object {
        private const val MINIMUM_CARDS_SIZE = 2
    }
}
