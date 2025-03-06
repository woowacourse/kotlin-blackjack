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
        val card = Card(CardShape.HEART, Denomination.TWO)

        assertAll(
            { assertThat(card.shape).isEqualTo(CardShape.HEART) },
            { assertThat(card.denomination).isEqualTo(Denomination.TWO) },
        )
    }

    @ParameterizedTest
    @ValueSource(strings = ["JACK", "QUEEN", "KING"])
    fun `Card의 끗수가 J,Q,K일 경우 10을 반환한다`(denomination: Denomination) {
        val card = Card(CardShape.HEART, denomination)

        val actual = card.changeDenominationToInt()

        assertThat(actual).isEqualTo(10)
    }

    @ParameterizedTest
    @CsvSource(value = ["TWO, 2", "THREE, 3", "FOUR, 4", "FIVE, 5", "SIX, 6", "SEVEN, 7", "EIGHT,8", "NINE,9", "TEN, 10"])
    fun `Card의 끗수가 2~10사이의 숫자일 경우 숫자 그대로를 반환한다`(
        denomination: Denomination,
        expected: Int,
    ) {
        val card = Card(CardShape.HEART, denomination)

        val actual = card.changeDenominationToInt()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Card의 끗수가 2~10 사이의 숫자 혹은 J, Q, K가 아닐 경우 0을 반환한다`() {
        val card = Card(CardShape.HEART, Denomination.ACE)

        val actual = card.changeDenominationToInt()

        assertThat(actual).isEqualTo(0)
    }
}
