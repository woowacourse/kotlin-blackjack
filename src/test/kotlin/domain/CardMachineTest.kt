package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardMachineTest {
    @Test
    fun `카드 6장을 뽑는다`() {
        // given
        val count = 6
        val cardMachine = CardMachine()

        // when
        val actual = cardMachine.getCards(count)

        // then
        assertThat(actual).hasSize(count)
    }
}
