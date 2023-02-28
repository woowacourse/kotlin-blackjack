package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardMachineTest {
    @Test
    fun `카드 한 쌍을 뽑는다`() {
        // when
        val actual = CardMachine().getCardPair()

        // then
        assertThat(actual).hasSize(2)
    }
}
