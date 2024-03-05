package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `카드의 합이 기준점 이하인 경우에만 추가로 뽑을 수 있다`() {
        val dealer = Dealer(cards = mutableListOf(Card(CardNumber.THREE, CardShape.DIAMOND), Card(CardNumber.EIGHT, CardShape.DIAMOND)))
        val threshold = 16
        assertThat(dealer.shouldPick(threshold)).isTrue()
    }

    @Test
    fun `카드의 합이 기준점 초과라면 더 이상 카드를 뽑을 수 없다`() {
        val dealer = Dealer(cards = mutableListOf(Card(CardNumber.NINE, CardShape.DIAMOND), Card(CardNumber.EIGHT, CardShape.DIAMOND)))
        val threshold = 16
        assertThat(dealer.shouldPick(threshold)).isFalse()
    }
}
