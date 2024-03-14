package blackjack.model.participant

import blackjack.model.Betting
import blackjack.model.Profit
import blackjack.model.card.Card
import blackjack.model.card.Hand
import blackjack.state.State

sealed class Participant(
    val name: String,
    val betting: Betting = Betting(0),
    initState: State,
) {
    private var state = initState

    private var finishState: State.Finish? = null
    val hand: Hand get() = state.hand

    fun sumScore(): Int = state.sumScore()

    abstract fun play(
        onDraw: () -> Card,
        onDone: (Participant) -> Unit,
    )

    protected fun play(
        onHitCondition: () -> Boolean,
        onDraw: () -> Card,
        onDone: (Participant) -> Unit,
    ) {
        if (onHitCondition()) {
            state = State.Running(hand).hit(onDraw())
            onDone(this)
            play(onDraw, onDone)
        } else {
            State.Running(hand).stay().also {
                state = it
                finishState = it
            }
        }
    }

    fun isBust() = state is State.Finish.Bust

    open fun judge(
        betting: Betting,
        other: Participant,
    ): Profit {
        val finish = finishState ?: throw IllegalStateException(ERROR_JUDGE_WHEN_RUNNING)
        val otherFinish = other.finishState ?: throw IllegalStateException(ERROR_JUDGE_WHEN_RUNNING)
        return finish.calculateProfit(betting, otherFinish.ratePoint)
    }

    protected companion object {
        const val ERROR_JUDGE_WHEN_RUNNING = "Participant 가 Running 일 때는 judge 를 할 수 없습니다"
    }
}
