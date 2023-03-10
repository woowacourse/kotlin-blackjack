package domain.money

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MoneyTest {
    @Test
    fun `돈이 10원보다 작을 때 IllegalArgumentException이 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Money(9)
        }
    }
}
