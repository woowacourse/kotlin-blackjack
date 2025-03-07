package blackjack.model.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private val dealer = Dealer()

    @BeforeEach
    fun setup() {
        dealer.receiveCard(Card(Shape.Heart, CardNumber.Ace))
        dealer.receiveCard(Card(Shape.Spade, CardNumber.Six))
    }

    @Test
    fun `카드 숫자의 합을 계산한다`() {
        assertThat(dealer.sumCardNumber).isEqualTo(17)
    }

    @Test
    fun `받은 카드의 목록을 반환한다`() {
        assertThat(dealer.cardDeck).isEqualTo(listOf(Card(Shape.Heart, CardNumber.Ace), Card(Shape.Spade, CardNumber.Six)))
    }

    @Test
    fun `카드 숫자 합이 임계값보다 작은지 판단한다`() {
        assertThat(dealer.overThreshold()).isTrue()
    }
}
