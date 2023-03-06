package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckMakerTest {
    @Test
    fun `덱은 처음에 카드 52장을 갖고 있다`() {
        assertThat(DeckMaker.getDeck().size).isEqualTo(52)
    }
}
