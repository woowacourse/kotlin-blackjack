package domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DeckTest {
    @ValueSource(ints = [1, 51, 53])
    @ParameterizedTest
    fun `덱은 처음에 카드 52장으로 구성되야 한다`(size: Int) {
        val cards = MutableList<Card>(size) { Card(CardShape.HEART, CardNumber.FOUR) }
        assertThrows<IllegalArgumentException> { Deck(cards) }
    }

    @Test
    fun `덱에서 카드를 뽑을 수 있다`() {
        val cards = MutableList<Card>(52) { Card(CardShape.HEART, CardNumber.FOUR) }
        assertThat(Deck(cards).getCard()).isInstanceOf(Card::class.java)
    }
}
