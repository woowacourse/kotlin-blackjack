package domain.participants

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class MoneyTest {
    @Test
    fun `0을 입력한 경우 예외를 발생시킨다`() {
        assertThrows<IllegalArgumentException> {
            Money(0)
        }
    }
}
