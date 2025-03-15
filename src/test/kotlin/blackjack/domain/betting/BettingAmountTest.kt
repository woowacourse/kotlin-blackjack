package blackjack.domain.betting

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BettingAmountTest {
    @Test
    fun `금액을 받는다`() {
        // When
        val amount = 1000
        val bettingAmount = BettingAmount(amount)

        // Then
        bettingAmount.value shouldBe amount
    }

    @Test
    fun `금액은 0보다 크다`() {
        shouldThrowExactly<IllegalArgumentException> {
            BettingAmount(-1)
        }
    }
}
