package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DealerTest {

    @Test
    fun `딜러는 첫번째 카드를 공개할 수 있다`() {
        val dealer = Dealer()
        val card = Card(Denomination.NINE, Suit.DIAMOND)

        dealer.draw(card)
        val firstDealerCard = dealer.openFirstCard()
        assertThat(firstDealerCard.getCardSuit()).isEqualTo(card.getCardSuit())
        assertThat(firstDealerCard.getCardDenomination()).isEqualTo(card.getCardDenomination())
    }

    @Test
    fun `딜러는 첫번째 카드를 공개할 수 없으면, 에러를 던진다`() {
        val dealer = Dealer()
        assertThrows<IllegalArgumentException> { dealer.openFirstCard() }
    }
}
