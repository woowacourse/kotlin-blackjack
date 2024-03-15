package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandCardTest {
    @Test
    fun `손패에서 카드들의 숫자를 올바르게 반환하는지 테스트`() {
        val actualCard = HandCard()
        actualCard.apply {
            addCard(Card(Denomination.KING, Suit.SPADE))
            addCard(Card(Denomination.FOUR, Suit.SPADE))
            addCard(Card(Denomination.FIVE, Suit.SPADE))
        }
        assertThat(actualCard.getGameScoreWithAceCount()).isEqualTo(19)
    }

    @Test
    fun `손패에서 동일한 카드가 추가되는지 테스트`() {
        val card = Card(Denomination.KING, Suit.SPADE)
        val handCard = HandCard()
        handCard.addCard(card)
        assertThat(card.denomination).isEqualTo(Denomination.KING)
        assertThat(card.suit).isEqualTo(Suit.SPADE)
    }

    @Test
    fun `HandCard에서 정확한 ACE 수를 반환하는지 테스트 `() {
        val aceHandCard = HandCard()
        aceHandCard.apply {
            addCard(Card(Denomination.ACE, Suit.SPADE))
            addCard(Card(Denomination.ACE, Suit.HEART))
            addCard(Card(Denomination.ACE, Suit.DIAMOND))
        }
        assertThat(aceHandCard.getAceCount()).isEqualTo(3)
    }
}
