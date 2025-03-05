package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardsTest {
    @Test
    fun `원하는 개수 만큼 카드를 뽑을 수 있다`() {
        val cards = CardsGenerator().generateCards().allCards
        val drawCards = Cards(cards).drawCards(2)

        assertThat(drawCards.getCardsCount()).isEqualTo(2)
    }

    @Test
    fun `지정된 개수만큼 카드를 뽑은 후 남은 카드를 반환한다`() {
        val cards = CardsGenerator().generateCards()
        cards.drawCards(2)
        assertThat(cards.getCardsCount()).isEqualTo(50)
    }

    @Test
    fun `카드는 중복될 수 없다`() {
        val card1 = Card(CardRank.ACE, Shape.CLUB)
        val card2 = Card(CardRank.ACE, Shape.CLUB)

        val cards = mutableListOf(card1, card2)
        assertThrows<IllegalArgumentException> { Cards(cards) }
    }

    @Test
    fun `카드는 1장 이상 가져야한다`() {
        assertThrows<IllegalArgumentException> {  Cards(mutableListOf()) }
    }
}
