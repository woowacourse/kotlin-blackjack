package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BetAmountTest {
    private lateinit var betAmount: BetAmount

    @BeforeEach
    fun setUp() {
        betAmount = BetAmount(2000)
    }

    @Test
    fun `배팅 금액은 양수여야 한다`() {
        shouldThrow<IllegalArgumentException> {
            BetAmount(-1000)
        }
    }

    @Test
    fun `BetAmount와 Int 값과의 곱셈 연산을 할 수 있다`() {
        val expected = 6000
        val actual = betAmount * 3

        actual shouldBe expected
    }

    @Test
    fun `BetAmount와 Double 곱셈 연산을 할 수 있다`() {
        val expected = 6000.0
        val actual = betAmount * 3.0

        actual shouldBe expected
    }
}
