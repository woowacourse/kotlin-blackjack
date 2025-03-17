package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HandTest {
    private lateinit var hand: Hand

    private fun setCards(vararg card: Card) {
        card.forEach { hand.add(it) }
    }

    @BeforeEach
    fun clear() {
        hand = Hand()
    }

    @Test
    fun `add를 사용하면 인자로 받은 카드가 내부에 존재한다`() {
        val card = Card.of(Rank.KING, Suit.CLUB)
        setCards(card)
        assertThat(hand.toList()).contains(card)
    }

    @Test
    fun `getCards를 사용하면 내부적으로 가지고 있는 요소를 List 형태로 반환한다`() {
        val card = Card.of(Rank.KING, Suit.CLUB)
        setCards(card)
        assertThat(hand.toList()).isEqualTo(listOf(card))
    }

    @Test
    fun `size를 사용하면 현재 가지고있는 카드의 개수를 반환한다`() {
        val card = Card.of(Rank.KING, Suit.CLUB)
        setCards(card)
        assertThat(hand.size()).isEqualTo(1)
    }

    @Test
    fun `countAce를 사용하면 현재 가지고 있는 ACE의 개수를 반환한다`() {
        val card = Card.of(Rank.ACE, Suit.CLUB)
        setCards(card)
        assertThat(hand.countAce()).isEqualTo(1)
    }

    @Test
    fun `countScoredTen 사용하면 현재 가지고 있는 10점짜리 카드의 개수를 반환한다`() {
        val card1 = Card.of(Rank.TEN, Suit.CLUB)
        val card2 = Card.of(Rank.KING, Suit.CLUB)
        setCards(card1, card2)
        assertThat(hand.countScoredTen()).isEqualTo(2)
    }
}
