package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScoreTest {
    @Test
    fun `점수가 22이면 버스트이다`() {
        // given
        val score = Score.valueOf(22)

        // when
        val actual = score.isBurst()

        // then
        assertThat(actual).isEqualTo(true)
    }

    @Test
    fun `점수가 21이면 블랙잭이다`() {
        // given
        val score = Score.valueOf(21)

        // when
        val actual = score.isBlackJack()

        // then
        assertThat(actual).isEqualTo(true)
    }

    @Test
    fun `점수 15는 점수 12 보다 크다`() {
        // given
        val score15 = Score.valueOf(15)
        val score12 = Score.valueOf(12)

        // when
        val actual = score15.isOver(score12)

        // then
        assertThat(actual).isEqualTo(true)
    }

    @Test
    fun `점수 15는 점수 15 보다 크지않다`() {
        // given
        val score15 = Score.valueOf(15)
        val anotherScore15 = Score.valueOf(15)

        // when
        val actual = score15.isOver(anotherScore15)

        // then
        assertThat(actual).isEqualTo(false)
    }
}
