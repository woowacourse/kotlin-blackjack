package blackjack.model.participant

import blackjack.model.HandCards
import blackjack.model.card.Card
import blackjack.model.card.Denomination
import blackjack.model.card.Suite
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandCardsTest {
    @Test
    fun `카드가 추가되는지 확인한다`() {
        // given
        val handCards = HandCards(listOf(Card.of(Denomination.ACE, Suite.HEART)))
        handCards.addCard(Card.of(Denomination.TWO, Suite.HEART))

        // when
        val actual = handCards.cards
        val expected = listOf(Card.of(Denomination.ACE, Suite.HEART), Card.of(Denomination.TWO, Suite.HEART))

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `카드들의 합을 구하는지 확인한다`() {
        // given
        val cards = listOf(Card.of(Denomination.ACE, Suite.HEART), Card.of(Denomination.KING, Suite.HEART))
        val handCards = HandCards(cards)

        // when
        val actual = handCards.getCardSum()
        val expected = 21

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
