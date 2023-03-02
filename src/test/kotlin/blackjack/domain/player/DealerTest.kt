package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `카드를 더 받아야 하는 상태인지 확인한다`() {
        val dealer = Dealer("aaa")
        dealer.addCard(Card(CardNumber.EIGHT, CardShape.CLOVER))
        val actual = dealer.checkMustGenerateCard()
        assertThat(actual).isEqualTo(true)
    }
}
