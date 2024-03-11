package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    fun `카드 총 합이 16 이하면 true를 반환한다`() {
        val dealer = Dealer()
        dealer.addCard(Card(CardNumber.`3`, Suit.`하트`))
        val actual = dealer.isHitable()
        assertThat(actual).isEqualTo(true)
    }

    @Test
    fun `카드 총 합이 17 이상이면 false를 반환한다`() {
        val dealer = Dealer()
        dealer.addCard(Card(CardNumber.`10`, Suit.`하트`))
        dealer.addCard(Card(CardNumber.`7`, Suit.`하트`))
        val actual = dealer.isHitable()
        assertThat(actual).isEqualTo(false)
    }
}
