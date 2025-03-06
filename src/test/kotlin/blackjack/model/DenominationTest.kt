package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class DenominationTest {
    @ParameterizedTest
    @ValueSource(strings = ["JACK", "QUEEN", "KING"])
    fun `끗수가 J,Q,K일 경우 숫자가 10이다`(denomination: Denomination) {
        assertThat(denomination.number).isEqualTo(10)
    }

    @ParameterizedTest
    @CsvSource(value = ["TWO, 2", "THREE, 3", "FOUR, 4", "FIVE, 5", "SIX, 6", "SEVEN, 7", "EIGHT,8", "NINE,9", "TEN, 10"])
    fun `끗수가 2~10사이의 숫자일 경우 숫자 그대로를 반환한다`(
        denomination: Denomination,
        expected: Int,
    ) {
        assertThat(denomination.number).isEqualTo(expected)
    }

    @Test
    fun `끗수가 Ace일 경우 숫자가 0이다`() {
        val actual = Denomination.ACE.number
        assertThat(actual).isEqualTo(0)
    }
}
