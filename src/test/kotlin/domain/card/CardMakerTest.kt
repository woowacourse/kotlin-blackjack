package domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardMakerTest {
    @Test
    fun `카드를 만들었을 때 52장이다`() {
        val cards = CardMaker().makeCards()
        assertThat(cards.size).isEqualTo(52)
    }
}
