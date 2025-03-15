package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러는 딜러라는 이름을 가진다`() {
        val dealer = Dealer()
        assertThat(dealer.name).isEqualTo("딜러")
    }

    @Test
    fun `딜러는 뽑은 카드를 핸드에 추가한다`() {
        val dealer = Dealer()
        val deck = Deck()
        dealer.draw(deck)
        assertThat(dealer.hand.cards.size).isEqualTo(1)
    }
}