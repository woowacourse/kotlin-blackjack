package state

import domain.card.Card
import domain.card.Cards

abstract class FinishedState(cards: Cards) : StartedState(cards) {
    override val isFinished: Boolean
        get() = true

    override fun draw(card: Card): State {
        throw IllegalStateException(ERROR_CARD_STATE_FINISHED_DRAWN)
    }

    override fun next(nextCards: Cards): State {
        throw IllegalStateException(ERROR_CARD_STATE_FINISHED_DRAWN)
    }

    override fun stay(): State {
        throw IllegalStateException(ERROR_ALREADY_STAY)
    }

    protected fun otherIsFinished(other: State) {
        if (!other.isFinished) throw IllegalStateException(ERROR_OTHER_ILLEGAL_STATE)
    }

    companion object {
        private const val ERROR_CARD_STATE_FINISHED_DRAWN = "[ERROR] 이미 카드를 뽑는 턴이 종료되어 더이상 카드를 추가할 수 없습니다."
        private const val ERROR_ALREADY_STAY = "[ERROR] 이미 카드를 뽑는 턴이 종료되어 stay 할 수 없습니다."
        private const val ERROR_OTHER_ILLEGAL_STATE = "[ERROR] 비교하려는 상태가 종료상태가 아니여서 비교할 수 없습니다."
    }
}
