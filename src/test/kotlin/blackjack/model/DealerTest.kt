package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러는 뽑은 카드를 갖는다`() {
        val dealer = Dealer()
        val card = Card(Pattern.HEART, CardNumber.ACE)
        dealer.takeCard(card)

        assertThat(dealer.deck.cards.last()).isEqualTo(card)
    }
}
