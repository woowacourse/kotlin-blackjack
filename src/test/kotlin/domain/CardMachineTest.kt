package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardMachineTest {
    @Test
    fun `3명일 경우, 카드 세 쌍을 뽑는다`() {
        // given
        val count = 3
        val cardMachine = CardMachine()

        // when
        val actual = cardMachine.getCardPairs(count)

        // then
        assertThat(actual).hasSize(3)
    }
}
