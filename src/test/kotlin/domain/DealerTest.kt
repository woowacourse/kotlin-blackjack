package domain

import domain.card.Card
import domain.card.CardCategory
import domain.card.CardNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `카드의 총합이 16이하면 카드를 더 받아야 한다`() {
        val dealer = Dealer()
        dealer.addCard(Card.of(CardCategory.CLOVER, CardNumber.EIGHT))
        dealer.addCard(Card.of(CardCategory.SPADE, CardNumber.SIX))
        dealer
        val actual = dealer.isPossibleDrawCard()
        assertThat(actual).isTrue
    }
}
