package blackjack.model

import model.Card
import model.Cards
import model.Rank
import model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `중복된 카드는 들어가지 않는다`() {
        assertThat(Cards(setOf(Card(Rank.ACE, Suit.CLOVER), Card(Rank.ACE, Suit.CLOVER))).size == 1).isTrue
    }

    @Test
    fun `카드를 한 장 추가할 수 있다`() {
        val cards = Cards(setOf(Card(Rank.ACE, Suit.CLOVER), Card(Rank.ACE, Suit.DIAMOND)))
        cards.add(Card(Rank.DEUCE, Suit.DIAMOND))
        assertThat(cards.size).isEqualTo(3)
    }

    @Test
    fun `11클로버와 2다이아 카드의 합이 13이다`() {
        val cards = Cards(setOf(Card(Rank.ACE, Suit.CLOVER), Card(Rank.DEUCE, Suit.DIAMOND)))
        assertThat(cards.sum()).isEqualTo(13)
    }
}
