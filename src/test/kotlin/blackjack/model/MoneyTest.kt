package blackjack.model

import model.Money
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MoneyTest {
    @ParameterizedTest
    @ValueSource(longs = [-1_000L, 999L, 0L])
    fun `Money 값이 1000이상이 아니라면 예외가 발생한다`(value: Long) {
        assertThrows<IllegalArgumentException> { Money(value) }
    }
}
