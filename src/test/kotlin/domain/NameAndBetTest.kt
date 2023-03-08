package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class NameAndBetTest {
    @Test
    fun `베팅 금액은 0원보다 작을 수 없다`() {
        assertThrows<IllegalStateException> { NameAndBet(Name("mendel"), -1) }
    }

    @Test
    fun `베팅 금액은 양수여야 한다`() {
        assertDoesNotThrow { NameAndBet(Name("mendel"), 10000) }
    }
}
