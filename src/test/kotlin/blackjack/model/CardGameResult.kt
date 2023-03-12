package blackjack.model

import model.CardGameResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardGameResult {
    @Test
    fun `BLACKJACK은 1_5배 값을 갖는다`() {
        assertThat(CardGameResult.BLACKJACK.multiple).isEqualTo(1.5f)
    }

    @Test
    fun `WIN은 1배 값을 갖는다`() {
        assertThat(CardGameResult.WIN.multiple).isEqualTo(1f)
    }

    @Test
    fun `DRAW은 0배 값을 갖는다`() {
        assertThat(CardGameResult.DRAW.multiple).isEqualTo(0f)
    }

    @Test
    fun `LOSE는 -1배 값을 갖는다`() {
        assertThat(CardGameResult.LOSE.multiple).isEqualTo(-1f)
    }
}
