package domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardMakerTest {
    @Test
    fun `카드를 만들었을 때 48장이다`() {
        val cards = CardMaker().make()
        assertThat(cards.size).isEqualTo(48)
    }
}
