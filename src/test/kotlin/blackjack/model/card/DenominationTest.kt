package blackjack.model.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DenominationTest {
    @Test
    fun `A인지 확인한다`() {
        val actualTrueCase = Denomination.ACE.isAce()
        val actualFalseCase = Denomination.TWO.isAce()
        assertThat(actualTrueCase).isEqualTo(true)
        assertThat(actualFalseCase).isEqualTo(false)
    }
}
