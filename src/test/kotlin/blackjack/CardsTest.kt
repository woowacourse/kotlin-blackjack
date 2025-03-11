package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CardsTest {
    private lateinit var cards: Cards

    private fun setCards(vararg card: Card) {
        card.forEach { cards.add(it) }
    }

    @BeforeEach
    fun clear() {
        cards = Cards()
    }

    @Test
    fun `add를 사용하면 인자로 받은 카드가 내부에 존재한다`() {
        val card = Card.of(Rank.KING, Suit.CLUB)
        setCards(card)
        assertThat(cards.toList()).contains(card)
    }

    @Test
    fun `getCards를 사용하면 내부적으로 가지고 있는 요소를 List 형태로 반환한다`() {
        val card = Card.of(Rank.KING, Suit.CLUB)
        setCards(card)
        assertThat(cards.toList()).isEqualTo(listOf(card))
    }

    @Test
    fun `size를 사용하면 현재 가지고있는 카드의 개수를 반환한다`() {
        val card = Card.of(Rank.KING, Suit.CLUB)
        setCards(card)
        assertThat(cards.size()).isEqualTo(1)
    }

    @Test
    fun `countAce를 사용하면 현재 가지고 있는 ACE의 개수를 반환한다`() {
        val card = Card.of(Rank.ACE, Suit.CLUB)
        setCards(card)
        assertThat(cards.countAce()).isEqualTo(1)
    }

    @Test
    fun `countScoredTen 사용하면 현재 가지고 있는 10점짜리 카드의 개수를 반환한다`() {
        val card1 = Card.of(Rank.TEN, Suit.CLUB)
        val card2 = Card.of(Rank.KING, Suit.CLUB)
        setCards(card1, card2)
        assertThat(cards.countScoredTen()).isEqualTo(2)
    }

    @Test
    fun `카드가 자기 자신의 총합을 계산하면 카드의 총합을 알 수 있다`() {
        setCards(
            Card.of(Rank.TWO, Suit.SPADE),
            Card.of(Rank.THREE, Suit.SPADE),
        )

        assertThat(cards.calculateTotalSum()).isEqualTo(5)
    }

    @Test
    fun `카드가 자기 자신의 총합을 계산하면 카드의 총합을 알 수 있다(ACE 1장)`() {
        setCards(
            Card.of(Rank.TWO, Suit.SPADE),
            Card.of(Rank.ACE, Suit.SPADE),
        )

        assertThat(cards.calculateTotalSum()).isEqualTo(13)
    }

    @Test
    fun `카드가 자기 자신의 총합을 계산하면 카드의 총합을 알 수 있다(ACE 2장)`() {
        setCards(
            Card.of(Rank.ACE, Suit.SPADE),
            Card.of(Rank.NINE, Suit.SPADE),
            Card.of(Rank.ACE, Suit.HEART),
        )

        assertThat(cards.calculateTotalSum()).isEqualTo(21)
    }
}
