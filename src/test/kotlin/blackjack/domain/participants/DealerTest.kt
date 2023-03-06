package blackjack.domain.participants

import blackjack.domain.card.Card
import blackjack.domain.card.CardMark
import blackjack.domain.card.CardValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `ACE를 11로 둘 때 17이상이면 뽑을 수 없다`() {
        val dealer = Dealer()
        dealer.draw(Card(CardMark.CLOVER, CardValue.ACE))
        dealer.draw(Card(CardMark.CLOVER, CardValue.SIX))
        assertThat(dealer.isContinue()).isFalse
    }

    @Test
    fun `ACE를 11로 둘 때 17미만이면 뽑을 수 있다`() {
        val dealer = Dealer()
        dealer.draw(Card(CardMark.CLOVER, CardValue.ACE))
        dealer.draw(Card(CardMark.CLOVER, CardValue.FIVE))
        assertThat(dealer.isContinue()).isTrue
    }
}
