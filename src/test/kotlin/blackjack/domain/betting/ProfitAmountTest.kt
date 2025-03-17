package blackjack.domain.betting

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ProfitAmountTest {
    @Test
    fun `수익금을 받는다`() {
        // Given
        val amount = -10000.0

        // When
        val profitAmount = ProfitAmount(amount)

        // Then
        profitAmount.value shouldBe -10000.0
    }
}
