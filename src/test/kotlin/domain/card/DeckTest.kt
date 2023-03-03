package domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class DeckTest {

    @Test
    fun `카드는 52장 생성되어야 한다`() {
        assertDoesNotThrow { Deck() }
    }

    @Test
    fun `카드를 덱에서 한장씩 뽑을 수 있다`() {
        assertThat(Deck().getCard()).isInstanceOf(Card::class.java)
    }
}
