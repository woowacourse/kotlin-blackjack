package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `갖고 있는 카드를 확인할 수 있다`() {
        val card = Card(Ace(), Suit.SPADE)
        val cards = Hand(listOf(card))
        assertThat(cards.value).isEqualTo(listOf(card))
    }

    @Test
    fun `카드 총합을 알 수 있다`() {
        val cards =
            Hand(
                listOf(
                    Card(Ace(), Suit.SPADE),
                    Card(Ace(), Suit.HEART),
                    Card(Number(4), Suit.DIAMOND),
                    Card(Character.JACK, Suit.CLOVER),
                ),
            )
        assertThat(cards.getScore()).isEqualTo(16)
    }
}
