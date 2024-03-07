package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

class DenominationTest {
    @ValueSource(strings = ["a", "B", "k", "0", "11"])
    @ParameterizedTest
    fun `유효하지 않은 카드 숫자 혹은 알파벳일 경우 null을 반환한다`(denomination: String) {
        val actual = Denomination.from(denomination)
        assertNull(actual)
    }

    @MethodSource("유효한 카드 숫자 혹은 알파벳 테스트 데이터")
    @ParameterizedTest
    fun `유효한 카드 숫자 혹은 알파벳일 경우 알맞는 객체를 반환한다`(
        denomination: String,
        expected: Denomination,
    ) {
        val actual = Denomination.from(denomination)
        assertThat(actual).isEqualTo(expected)
    }

    @CsvSource("A, true", "10, false")
    @ParameterizedTest
    fun `A인지 확인한다`(
        denomination: String,
        expected: Boolean,
    ) {
        val actual = Denomination.from(denomination)?.isAce()
        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun `유효한 카드 숫자 혹은 알파벳 테스트 데이터`() =
            listOf(
                Arguments.of("A", Denomination.ACE),
                Arguments.of("8", Denomination.EIGHT),
                Arguments.of("10", Denomination.TEN),
                Arguments.of("K", Denomination.KING),
            )
    }
}
