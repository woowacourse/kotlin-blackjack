package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `카드 개수가 2장일 때 카드 합이 16 이하면 Hit 해야한다`() {
        val dealer = Dealer().apply {
            receive(Card(CardNumber.TWO, CardShape.CLOVER))
            receive(Card(CardNumber.TWO, CardShape.HEART))
        }

        assertThat(dealer.shouldHit()).isTrue
    }
}
