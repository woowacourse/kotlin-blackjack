package blackjack.model.participant

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BattingAmountTest {
    @Test
    fun `배팅 금액이 10보다 작은 경우 예외가 발생한다`() {
        val exception = assertThrows<IllegalArgumentException> { BattingAmount(9) }
        assertThat(exception.message).isEqualTo("배팅 금액은 10보다 커야 합니다. 현재 입력 값: 9")
    }

    @Test
    fun `배팅 금액이 10의 배수가 아닌 경우 예외가 발생한다`() {
        val exception = assertThrows<IllegalArgumentException> { BattingAmount(15) }
        assertThat(exception.message).isEqualTo("배팅 금액은 10의 배수여야 합니다. 현재 입력 값: 15")
    }
}
