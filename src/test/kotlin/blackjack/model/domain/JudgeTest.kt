package blackjack.model.domain

import blackjack.model.action.Action
import blackjack.model.dispatcher.Dispatcher
import blackjack.model.entitiy.Card
import blackjack.model.entitiy.CardRank
import blackjack.model.entitiy.GameResult
import blackjack.model.entitiy.Shape
import blackjack.model.store.Store
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private val dispatcher: Dispatcher = Dispatcher()
private val store: Store = Store(dispatcher)

fun initPlayer() {
    dispatcher.dispatch(Action.ReadNames(listOf("해음")))

    dispatcher.dispatch(
        Action.InitPlayersCard(1) {
            Card(Shape.DIAMOND, CardRank.NINE)
        },
    )
}

class JudgeTest {

    @Test
    fun `플레이어가 이겼을 때 경기 결과를 계산한다`() {
        initPlayer()

        dispatcher.dispatch(
            Action.InitDealerCard(1) {
                Card(Shape.DIAMOND, CardRank.EIGHT)
            },
        )

        val judge = Judge(store)
        val actualResult: List<GameResult> = judge.getGameResults()

        assertThat(actualResult).isEqualTo(listOf(GameResult.WIN))
    }

    @Test
    fun `플레이어가 졌을 때 경기 결과를 계산한다`() {
        initPlayer()

        dispatcher.dispatch(
            Action.InitDealerCard(1) {
                Card(Shape.DIAMOND, CardRank.TEN)
            },
        )

        val judge = Judge(store)
        val actualResult: List<GameResult> = judge.getGameResults()

        assertThat(actualResult).isEqualTo(listOf(GameResult.LOSE))
    }
}
