package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class
HandCardTest {

    @Test
    fun `손패에서 카드들의 숫자를 올바르게 반환하는지 테스트`() {
        val actualCard = HandCard()
        actualCard.apply {
            addCard(Card(Denomination.KING, Suit.SPADE))
            addCard(Card(Denomination.FOUR, Suit.SPADE))
            addCard(Card(Denomination.FIVE, Suit.SPADE))
        }
        assertThat(actualCard.getTotalCardsSum()).isEqualTo(19)
    }

    @Test
    fun `손패에서 동일한 카드가 추가되는지 테스트`() {
        val card = Card(Denomination.KING, Suit.SPADE)
        val handCard = HandCard()
        handCard.addCard(card)
        assertThat(card.getCardDenomination()).isEqualTo(Denomination.KING)
        assertThat(card.getCardSuit()).isEqualTo(Suit.SPADE)
    }
}
