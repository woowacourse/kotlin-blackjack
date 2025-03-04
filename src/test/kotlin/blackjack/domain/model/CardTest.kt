package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CardTest {
    private lateinit var card: Card

    @BeforeEach
    fun setUp() {
        card = Card(Suit.HEART, Rank.KING)
    }

    @Test
    fun `카드는 문양을 가진다`() {
        assertThat(card.suit).isEqualTo("하트")
    }

    @Test
    fun `카드는 등급을 가진다`() {
        assertThat(card.rank).isEqualTo("K")
    }
}