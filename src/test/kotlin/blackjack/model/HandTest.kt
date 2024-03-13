package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private fun Hand(vararg cards: Card): Hand {
    return Hand(cards.toMutableList())
}

private fun Card(value: Int): Card {
    return Card(CardNumber.entries.find { it.value == value }!!, CardShape.DIAMOND)
}

class HandTest {
    @Test
    fun `선택한 카드를 추가할 수 있다`() {
        val hand = Hand(Card(8), Card(9))
        hand.addCard(Card(CardNumber.TEN, CardShape.DIAMOND))

        assertThat(hand.getCards()).isEqualTo(
            Hand(Card(8), Card(9), Card(10)).getCards(),
        )
    }

    @Test
    fun `ACE(1 혹은 11)을 최적으로 계산하여 합계를 구한다 - Ace가 1로 계산되는 경우`() {
        val hand = Hand(Card(11), Card(9), Card(10))
        assertThat(hand.calculateCardsSum()).isEqualTo(20)
    }

    @Test
    fun `ACE(1 혹은 11)을 최적으로 계산하여 합계를 구한다 - Ace가 11로 계산되는 경우`() {
        val hand = Hand(Card(11), Card(2), Card(8))
        assertThat(hand.calculateCardsSum()).isEqualTo(21)
    }
}
