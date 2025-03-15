package blackjack

import blackjack.CardFixture.Companion.CLOVER_NINE
import blackjack.CardFixture.Companion.HEART_ACE
import blackjack.CardFixture.Companion.HEART_QUEEN
import blackjack.CardFixture.Companion.HEART_TWO
import blackjack.model.GameJudge
import blackjack.model.state.GameStatus
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class GameJudgeTest {
    @Test
    fun `카드 2장의 합이 21이면 블랙잭 상태를 반환한다 `() {
        val expected = GameStatus.BLACKJACK
        val actual = GameJudge.judge(listOf(HEART_ACE, HEART_QUEEN))
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `카드 3장의 합이 21이면 스테이 상태를 반환한다 `() {
        val expected = GameStatus.STAY
        val actual = GameJudge.judge(listOf(HEART_TWO, HEART_QUEEN, CLOVER_NINE))
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `카드의 합이 21보다 작다면 스테이 상태를 반환한다 `() {
        val expected = GameStatus.STAY
        val actual = GameJudge.judge(listOf(HEART_TWO, HEART_QUEEN))
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `카드의 합이 21보다 크다면 버스트 상태를 반환한다 `() {
        val expected = GameStatus.BUST
        val actual = GameJudge.judge(listOf(HEART_TWO, HEART_QUEEN, CLOVER_NINE, HEART_ACE))
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}
