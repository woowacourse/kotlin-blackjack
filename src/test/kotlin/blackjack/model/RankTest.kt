package blackjack.model

import model.Card
import model.Rank
import model.Suit
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class RankTest {
    @Test
    fun `ACE 카드는 1점이다`() {
        val card = Card(Rank.ACE, Suit.CLOVER)
        Assertions.assertThat(card.rank.score).isEqualTo(1)
    }
    @Test
    fun `DEUCE 카드는 2점이다`() {
        val card = Card(Rank.DEUCE, Suit.CLOVER)
        Assertions.assertThat(card.rank.score).isEqualTo(2)
    }
    @Test
    fun `THREE 카드는 3점이다`() {
        val card = Card(Rank.THREE, Suit.CLOVER)
        Assertions.assertThat(card.rank.score).isEqualTo(3)
    }
    @Test
    fun `FOUR 카드는 4점이다`() {
        val card = Card(Rank.FOUR, Suit.CLOVER)
        Assertions.assertThat(card.rank.score).isEqualTo(4)
    }
    @Test
    fun `FIVE 카드는 5점이다`() {
        val card = Card(Rank.FIVE, Suit.CLOVER)
        Assertions.assertThat(card.rank.score).isEqualTo(5)
    }
    @Test
    fun `SIX 카드는 6점이다`() {
        val card = Card(Rank.SIX, Suit.CLOVER)
        Assertions.assertThat(card.rank.score).isEqualTo(6)
    }
    @Test
    fun `SEVEN 카드는 7점이다`() {
        val card = Card(Rank.SEVEN, Suit.CLOVER)
        Assertions.assertThat(card.rank.score).isEqualTo(7)
    }
    @Test
    fun `EIGHT 카드는 8점이다`() {
        val card = Card(Rank.EIGHT, Suit.CLOVER)
        Assertions.assertThat(card.rank.score).isEqualTo(8)
    }
    @Test
    fun `NINE 카드는 9점이다`() {
        val card = Card(Rank.NINE, Suit.CLOVER)
        Assertions.assertThat(card.rank.score).isEqualTo(9)
    }
    @Test
    fun `TEN 카드는 10점이다`() {
        val card = Card(Rank.TEN, Suit.CLOVER)
        Assertions.assertThat(card.rank.score).isEqualTo(10)
    }
    @Test
    fun `KING 카드는 10점이다`() {
        val card = Card(Rank.KING, Suit.CLOVER)
        Assertions.assertThat(card.rank.score).isEqualTo(10)
    }
    @Test
    fun `QUEEN 카드는 10점이다`() {
        val card = Card(Rank.QUEEN, Suit.CLOVER)
        Assertions.assertThat(card.rank.score).isEqualTo(10)
    }
    @Test
    fun `JACK 카드는 10점이다`() {
        val card = Card(Rank.JACK, Suit.CLOVER)
        Assertions.assertThat(card.rank.score).isEqualTo(10)
    }
}
