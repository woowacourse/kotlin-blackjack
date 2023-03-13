package domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScoreTest {

    private fun Cards(vararg cards: Int): Cards {
        return Cards(
            cards.map { number ->
                Card.of(
                    CardCategory.CLOVER,
                    CardNumber.values().find { it.value == number } ?: CardNumber.FIVE,
                )
            },
        )
    }

    @Test
    fun `카드의 합이 21이 넘으면 isBurst가 true 이다`() {
        // given
        val score = Score(Cards(10, 10, 10))
        // when
        val actual = score.isBurst()
        // then
        assertThat(actual).isTrue
    }

    @Test
    fun `카드의 합이 21이 넘지 않으면 isBurst가 false 이다`() {
        // given
        val score = Score(Cards(10, 10))
        // when
        val actual = score.isBurst()
        // then
        assertThat(actual).isFalse
    }

    @Test
    fun `Ace 카드가 있을 시, 10을 더하여 버스트가 되지 않는다면, 10을 더한 값을 반환한다`() {
        // given
        val score = Score(Cards(1, 9))
        // when
        val actual = score.getValue()
        val expected = 20
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Ace 카드가 있을 시, 10을 더하여 버스트가 된다면, 10을 더하지 않은 값을 반환한다`() {
        // given
        val score = Score(Cards(1, 5, 10))
        // when
        val actual = score.getValue()
        val expected = 16
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Ace 카드가 없을 시, 카드의 합 그대로 반환한다`() {
        // given
        val score = Score(Cards(8, 2))
        // when
        val actual = score.getValue()
        val expected = 10
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `카드사이즈가 2이고, 합이 21이면 블랙잭이다`() {
        // given
        val score = Score(Cards(1, 10))
        // when
        val actual = score.isBlackJack()
        // then
        assertThat(actual).isTrue
    }
}
