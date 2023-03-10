package blackjack.model

import model.cards.Card
import model.cards.CardPack
import model.cards.Rank
import model.cards.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardPackTest {
    @Test
    fun `카드를 한 장 지우고 반환할 수 있다`() {
        val cardPack = CardPack(listOf(Card(Rank.ACE, Suit.CLOVER), Card(Rank.ACE, Suit.DIAMOND)))
        val card = cardPack.pop()
        assertThat(cardPack.size).isEqualTo(1)
        assertThat(card).isEqualTo(Card(Rank.ACE, Suit.CLOVER))
    }

    @Test
    fun `섞은 카드를 반환할 수 있다`() {
        val cardPack = CardPack(listOf(Card(Rank.ACE, Suit.CLOVER), Card(Rank.ACE, Suit.DIAMOND), Card(Rank.ACE, Suit.HEART)))
        val shuffledPack = cardPack.shuffled()
        assertThat(shuffledPack).isNotEqualTo(cardPack)
    }
}
