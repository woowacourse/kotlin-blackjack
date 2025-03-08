package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ScoreTest {
    @ParameterizedTest
    @ValueSource(ints = [22, 23, 24, 25])
    fun `점수가 21보다 크면 버스트이다`(number: Int) {
        val score = Score(number)
        assertThat(score.isBust()).isTrue()
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 20, 21])
    fun `점수가 21이하이면 버스트가 아니다`(number: Int) {
        val score = Score(number)
        assertThat(score.isBust()).isFalse()
    }
}
