package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class PlayerBehaviorTest {
    @ParameterizedTest
    @CsvSource(value = ["Y, HIT", "N, STAY"])
    fun `플레이어 행동에 대한 답변으로 플레이어 행동을 반환한다`(
        answer: String,
        expected: PlayerBehavior,
    ) {
        val actual = PlayerBehavior.from(answer)

        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @ValueSource(strings = ["1", "4", "dsdaf"])
    fun `플레이어 행동의 응답이 y 또는 n이 아닐 경우 예외를 발생시킨다`(answer: String) {
        assertThrows<IllegalArgumentException> { PlayerBehavior.from(answer) }
    }

    @ParameterizedTest
    @CsvSource(value = ["16, HIT", "17, STAY"])
    fun `딜러 점수가 16이하일 경우 HIT을, 아닐 경우 STAY를 반환한다`(
        dealerScore: Int,
        expected: PlayerBehavior,
    ) {
        val actual = PlayerBehavior.from(dealerScore)

        assertThat(actual).isEqualTo(expected)
    }
}
