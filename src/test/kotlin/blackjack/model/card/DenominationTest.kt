package blackjack.model.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.provider.Arguments

class DenominationTest {
    @Test
    fun `A인지 확인한다`() {
        val actualTrueCase = Denomination.ACE.isAce()
        val actualFalseCase = Denomination.TWO.isAce()
        assertThat(actualTrueCase).isEqualTo(true)
        assertThat(actualFalseCase).isEqualTo(false)
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
