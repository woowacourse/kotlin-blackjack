package domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CardsTest {
    private lateinit var cards: Cards

    @BeforeEach
    fun setUp() {
        cards = Cards()
    }

    @Test
    fun `카드 하나를 추가할 수 있다`() {
        cards.add(Card(CardShape.HEART, CardNumber.KING))

        assertThat(cards.value.size).isEqualTo(1)
    }

    @Test
    fun `ACE 를 1개 넣으면 ACE 개수는 1개이다`() {
        cards.add(Card(CardShape.HEART, CardNumber.ACE))

        assertThat(cards.countAce()).isEqualTo(1)
    }
}
