package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러는 카드 총 합이 16 이하면 카드를 더 뽑아야 한다`() {
        val dealer = Dealer()
        dealer.addCard(Card(CardNumber.Three, Suit.Heart))
        val actual = dealer.isHitable()
        assertThat(actual).isEqualTo(true)
    }

    @Test
    fun `딜러는 카드 총 합이 17 이상이면 카드 뽑는것을 멈춘다`() {
        val dealer = Dealer()
        dealer.addCard(Card(CardNumber.Ten, Suit.Heart))
        dealer.addCard(Card(CardNumber.Seven, Suit.Heart))
        val actual = dealer.isHitable()
        assertThat(actual).isEqualTo(false)
    }
}
