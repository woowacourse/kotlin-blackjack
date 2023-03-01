package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class CardsTest {
    @Test
    fun `카드 목록에 카드를 추가한다`() {
        val cards = Cards()

        cards.add(Card.of(1))
        cards.add(Card.of(3))
        cards.add(Card.of(5))

        assertThat(cards.items.size).isEqualTo(3)
    }

    @Test
    fun `A는 기존 총 점수에 11점을 더한 값이 21점 이하이면 11점으로 계산한다`() {
        val cards = Cards()
        cards.add(Card.of(1))

        assertThat(cards.calculateTotalScore()).isEqualTo(11)
    }

    @Test
    fun `A는 기존 총 점수에 11점을 더한 값이 21점을 초과하면 1점으로 계산한다`() {
        val cards = Cards()
        cards.add(Card.of(1))
        cards.add(Card.of(14))

        assertThat(cards.calculateTotalScore()).isEqualTo(12)
    }

    @ParameterizedTest
    @ValueSource(ints = [11, 12, 13])
    fun `J, Q, K는 모두 10점으로 계산한다`(number: Int) {
        val cards = Cards()
        cards.add(Card.of(number))

        assertThat(cards.calculateTotalScore()).isEqualTo(10)
    }

    @ParameterizedTest
    @CsvSource("2, 2", "3, 3", "4, 4", "5, 5", "6, 6", "7, 7", "8, 8", "9, 9", "10, 10")
    fun `2에서 10은 숫자 그대로 계산한다`(number: Int, expected: Int) {
        val cards = Cards()
        cards.add(Card.of(number))

        assertThat(cards.calculateTotalScore()).isEqualTo(expected)
    }
}
