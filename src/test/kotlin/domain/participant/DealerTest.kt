package domain.participant

import domain.card.Card
import domain.card.CardCategory
import domain.card.CardNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    private fun Card(number: Int): Card {
        return Card.of(CardCategory.CLOVER, CardNumber.values().find { it.value == number } ?: CardNumber.FIVE)
    }

    @Test
    fun `게임 시작 시 딜러의 처음 카드를 하나 보여준다`() {
        // / given
        val dealer = Dealer()
        // when
        dealer.draw(Card(8))
        dealer.draw(Card(9))
        val actual = dealer.showInitCards()
        val expected = listOf(Card.of(CardCategory.CLOVER, CardNumber.EIGHT))
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `카드의 총합이 16이하면 카드를 더 받아야 한다`() {
        // / given
        val dealer = Dealer()
        // when
        dealer.draw(Card(6))
        dealer.draw(Card(5))
        val actual = dealer.isPossibleDrawCard()
        val expected = true
        // then
        assertThat(actual).isEqualTo(expected)
    }
}
