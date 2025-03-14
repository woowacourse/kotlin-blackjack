package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class MoneyTest {
    @Test
    fun `돈은 마이너스가 될 수 없다`() {
        assertThatThrownBy { Money(-1.0) }.isInstanceOf(IllegalArgumentException::class.java)
    }
}
