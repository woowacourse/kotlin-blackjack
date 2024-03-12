package blackjack.model

import blackjack.model.TestUtils.Card
import blackjack.model.TestUtils.Hand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `선택한 카드를 추가할 수 있다`() {
        val hand = Hand(Card(8), Card(9))
        hand.addCard(Card(CardNumber.TEN, CardShape.HEART))

        assertThat(hand.cards).isEqualTo(
            Hand(Card(8), Card(9), Card(10)).cards,
        )
    }

    @Test
    fun `ACE(1 혹은 11)을 최적으로 계산하여 합계를 구한다 - Ace가 1로 계산되는 경우`() {
        val hand = Hand(Card(11), Card(9), Card(11))
        assertThat(hand.sumUpCardValues()).isEqualTo(21)
    }

    @Test
    fun `ACE(1 혹은 11)을 최적으로 계산하여 합계를 구한다 - Ace가 11로 계산되는 경우`() {
        val hand = Hand(Card(11), Card(2), Card(8))
        assertThat(hand.sumUpCardValues()).isEqualTo(21)
    }
}
