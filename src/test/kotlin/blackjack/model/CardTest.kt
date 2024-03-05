package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardTest {
    @CsvSource("K, 하트, 10", "1, 다이아몬드, 1", "Q, 클로버, 10", "8, 스페이드, 8")
    @ParameterizedTest
    fun `A가 아닐 때 카드의 값을 구한다`(
        denomination: String,
        suite: String,
        expected: Int,
    ) {
        val actual = Card(denomination, suite).getValue()
        assertThat(actual).isEqualTo(expected)
    }

    @CsvSource("A, 하트, true", "10, 다이아몬드, false")
    @ParameterizedTest
    fun `A인지 확인한다`(
        denomination: String,
        suite: String,
        expected: Boolean,
    ) {
        val actual = Card(denomination, suite).isA()
        assertThat(actual).isEqualTo(expected)
    }
}
