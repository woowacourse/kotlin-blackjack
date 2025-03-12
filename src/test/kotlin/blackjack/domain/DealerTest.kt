package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러가 카드를 한 장 지급 받으면 딜러의 패는 한 장이다`() {
        // given
        val dealer = Dealer()
        val card = Card(Rank.ACE, Suit.SPADE)

        // when
        dealer.addCard(card)

        // then
        assertThat(dealer.hand.cards.size).isEqualTo(1)
    }
}
