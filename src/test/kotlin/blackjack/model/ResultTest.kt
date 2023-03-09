package blackjack.model

import model.Result
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResultTest {
    @Test
    fun `BLACKJACK은 1_5배 값을 갖는다`() {
        assertThat(Result.BLACKJACK.multiple).isEqualTo(1.5f)
    }

    @Test
    fun `WIN은 1배 값을 갖는다`() {
        assertThat(Result.WIN.multiple).isEqualTo(1f)
    }

    @Test
    fun `DRAW은 0배 값을 갖는다`() {
        assertThat(Result.DRAW.multiple).isEqualTo(0f)
    }

    @Test
    fun `LOSE는 -1배 값을 갖는다`() {
        assertThat(Result.LOSE.multiple).isEqualTo(-1f)
    }
}
