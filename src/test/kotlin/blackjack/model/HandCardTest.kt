package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandCardTest {
    private fun createHandCard(vararg cards: Card): HandCard {
        val handCard = HandCard()
        cards.forEach { handCard.addCard(it) }
        return handCard
    }

    @Test
    fun `손패에서 카드들의 숫자를 올바르게 반환하는지 테스트`() {
        val handCard = createHandCard(SPADE_KING, SPADE_FOUR, SPADE_FIVE)
        val totalSum = handCard.getTotalCardsSum()
        assertThat(totalSum).isEqualTo(19)
    }

    @Test
    fun `손패에 ACE가 있고 카드들의 합이 Bust일 때, ACE 점수 변환 테스트`() {
        val bustHandCard = createHandCard(SPADE_ACE, SPADE_TWO, SPADE_KING)
        val totalSum = bustHandCard.getTotalCardsSum()
        assertThat(totalSum).isEqualTo(13)
    }

    @Test
    fun `손패에서 동일한 카드가 추가되는지 테스트`() {
        val card = SPADE_KING
        val handCard = HandCard()
        handCard.addCard(card)
        assertThat(card.getCardDenomination()).isEqualTo(Denomination.KING)
        assertThat(card.getCardSuit()).isEqualTo(Suit.SPADE)
    }

    @Test
    fun `HandCard에서 정확한 ACE 수를 반환하는지 테스트 `() {
        val handCard = createHandCard(SPADE_ACE, HEART_ACE, DIAMOND_ACE)
        val aceCount = handCard.getAceCount()
        assertThat(aceCount).isEqualTo(3)
    }
}
