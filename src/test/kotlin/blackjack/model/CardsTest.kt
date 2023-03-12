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

    @Test
    fun `A클로버, 2클로버 카드들의 맨 앞 카드 A클로버를 알 수 있다`() {
        val cards = Cards(setOf(Card(Rank.ACE, Suit.CLOVER), Card(Rank.DEUCE, Suit.CLOVER)))
        assertThat(cards.firstCard()).isEqualTo(Card(Rank.ACE, Suit.CLOVER))
    }

    @Test
    fun `카드의 합이 21을 초과하면 bust이다`() {
        val cards = Cards(setOf(Card(Rank.KING, Suit.DIAMOND), Card(Rank.QUEEN, Suit.CLOVER), Card(Rank.JACK, Suit.HEART)))
        assertThat(cards.isBust()).isTrue
    }

    @Test
    fun `카드의 수가 2장이고, 합이 21이면 BLACKJACK이다`() {
        val cards = Cards(setOf(Card(Rank.ACE, Suit.DIAMOND), Card(Rank.QUEEN, Suit.CLOVER)))
        assertThat(cards.isBlackJack()).isTrue
    }
}