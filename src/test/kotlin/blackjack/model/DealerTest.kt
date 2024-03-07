package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private fun createDealer(vararg numbers: Card): Dealer {
    return Dealer(state = Hit(Hand(numbers.toList())))
}

private fun Card(value: Int): Card {
    return Card(CardNumber.entries.find { it.value == value }!!, CardShape.HEART)
}

class DealerTest {
    @Test
    fun `딜러의 카드의 합이 기준점을 넘는 경우, False를 반환한다`() {
        val dealer = createDealer(Card(8), Card(9))
        val threshold = 16
        assertThat(dealer.isUnderHitThreshold(threshold)).isFalse()
    }

    @Test
    fun `딜러의 카드의 합이 기준점을 넘지 않는 경우, true를 반환한다`() {
        val dealer = createDealer(Card(7), Card(2))
        val threshold = 16
        assertThat(dealer.isUnderHitThreshold(threshold)).isTrue()
    }
}
