package blackjack.domain.state

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class ScoreTest {
    @Test
    fun `점수가 21점이면 블랙잭이다`() {
        assertThat(Score(21).isBlackJack).isTrue
    }

    @Test
    fun `점수가 21점이 아니면 블랙잭이 아니다`() {
        assertAll(
            { assertThat(Score(20).isBlackJack).isFalse },
            { assertThat(Score(22).isBlackJack).isFalse },
        )
    }

    @Test
    fun `점수가 21점 초과면 버스트이다`() {
        assertThat(Score(22).isBust).isTrue
    }

    @Test
    fun `점수가 21점 이하면 버스트가 아니다`() {
        assertAll(
            { assertThat(Score(20).isBust).isFalse },
            { assertThat(Score(21).isBust).isFalse },
        )
    }
}
