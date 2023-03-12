package state

import domain.card.Card
import domain.card.Cards

abstract class FinishedState(val cards: Cards) : State {
    override fun draw(card: Card): State {
        throw IllegalStateException(ERROR_CARD_STATE_FINISHED_DRAWN)
    }

    override fun next(nextCards: Cards): State {
        throw IllegalStateException(ERROR_CARD_STATE_FINISHED_DRAWN)
    }

    override fun stay(): State {
        throw IllegalStateException(ERROR_ALREADY_STAY)
    }

    companion object {
        private const val ERROR_CARD_STATE_FINISHED_DRAWN = "[ERROR] 이미 카드를 뽑는 턴이 종료되어 더이상 카드를 추가할 수 없습니다."
        private const val ERROR_ALREADY_STAY = "[ERROR] 이미 카드를 뽑는 턴이 종료되어 stay될 수 없습니다."
    }
}
