package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class CardTest {
    @Test
    fun `카드는 모양과 끗수를 가진다`() {
        val card = Card(CardShape.HEART, "1")

        assertAll(
            { assertThat(card.shape).isEqualTo(CardShape.HEART) },
            { assertThat(card.denomination).isEqualTo("1") },
        )
    }

    @ParameterizedTest
    @ValueSource(strings = ["J", "Q", "K"])
    fun `Card의 끗수가 J,Q,K일 경우 10을 반환한다`(denomination: String) {
        val card = Card(CardShape.HEART, denomination)

        val actual = card.changeDenominationToInt()

        assertThat(actual).isEqualTo(10)
    }

    @ParameterizedTest
    @CsvSource(value = ["2, 2", "3, 3", "4, 4", "5, 5", "6, 6", "7,7", "8,8", "9,9", "10, 10"])
    fun `Card의 끗수가 2~10사이의 숫자일 경우 숫자 그대로를 반환한다`(
        denomination: String,
        expected: Int,
    ) {
        val card = Card(CardShape.HEART, denomination)

        val actual = card.changeDenominationToInt()

        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @ValueSource(strings = ["A", "N", "M", "11", "0"])
    fun `Card의 끗수가 2~10 사이의 숫자 혹은 J, Q, K가 아닐 경우 0을 반환한다`(denomination: String) {
        val card = Card(CardShape.HEART, denomination)

        val actual = card.changeDenominationToInt()

        assertThat(actual).isEqualTo(0)
    }
}
