package blackjack.model

import model.EarningRate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EarningRate {
    @Test
    fun `BLACKJACK은 1_5배 값을 갖는다`() {
        assertThat(EarningRate.BLACKJACK.multiple).isEqualTo(1.5f)
    }

    @Test
    fun `WIN_OR_BLACKJACK_DRAW는 1배 값을 갖는다`() {
        assertThat(EarningRate.WIN_OR_BLACKJACK_DRAW.multiple).isEqualTo(1f)
    }

    @Test
    fun `DRAW은 0배 값을 갖는다`() {
        assertThat(EarningRate.DRAW.multiple).isEqualTo(0f)
    }

    @Test
    fun `LOSE는 -1배 값을 갖는다`() {
        assertThat(EarningRate.LOSE.multiple).isEqualTo(-1f)
    }
}
