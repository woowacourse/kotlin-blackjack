package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ScoreTest {

    @Test
    fun `카드의 합은 최소 2 이상이어야 한다`() {
        assertThrows<IllegalArgumentException> { Score(1, true) }
    }

    @Test
    fun `카드의 합이 21이 넘으면 isBurst가 true 이다`() {
        // given
        val score = Score(22, true)
        // when
        val actual = score.isBurst()
        // then
        assertThat(actual).isTrue
    }

    @Test
    fun `카드의 합이 21이 넘지 않으면 isBurst가 false 이다`() {
        // given
        val score = Score(20, true)
        // when
        val actual = score.isBurst()
        // then
        assertThat(actual).isFalse
    }

    @Test
    fun `Ace 카드가 있을 시, 10을 더하여 버스트가 되지 않는다면, 10을 더한 값을 반환한다`() {
        // given
        val score = Score(10, true)
        // when
        val actual = score.getValue()
        val expected = 20
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Ace 카드가 있을 시, 10을 더하여 버스트가 된다면, 10을 더하지 않은 값을 반환한다`() {
        // given
        val score = Score(13, true)
        // when
        val actual = score.getValue()
        val expected = 13
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Ace 카드가 없을 시, 카드의 합 그대로 반환한다`() {
        // given
        val score = Score(10, false)
        // when
        val actual = score.getValue()
        val expected = 10
        // then
        assertThat(actual).isEqualTo(expected)
    }
}
