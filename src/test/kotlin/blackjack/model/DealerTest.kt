package blackjack.model

import blackjack.model.TestUtils.Card
import blackjack.model.TestUtils.createCardDeckFrom
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private fun createDealer(vararg numbers: Card): Dealer {
    return Dealer(hand = Hand(numbers.toList()))
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

    @Test
    fun `딜러 카드 뽑기 테스트 - 카드의 합이 기준점 이하인 경우 카드를 더 뽑는다`() {
        val deck = createCardDeckFrom(6, 7, 11, 11, 10)
        val dealer = createDealer(Card(7), Card(2))
        dealer.playRound(deck) {}

        assertThat(dealer.getCards().size).isEqualTo(3)
        assertThat(dealer.getSumOfCards()).isEqualTo(19)
    }

    @Test
    fun `딜러 카드 뽑기 테스트 - 카드의 합이 기준점이 넘을 경우 카드를 뽑지 않는다`() {
        val deck = createCardDeckFrom(6, 7, 11, 11, 10)
        val dealer = createDealer(Card(7), Card(10))
        dealer.playRound(deck) {}

        assertThat(dealer.getCards().size).isEqualTo(2)
        assertThat(dealer.getSumOfCards()).isEqualTo(17)
    }
}
