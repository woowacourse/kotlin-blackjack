package blackjack

import blackjack.domain.Hand
import blackjack.domain.card.Card
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HandTest {
    private lateinit var hand: Hand

    @BeforeEach
    fun setUp() {
        hand = Hand()
    }

    @Test
    fun `카드의 총합을 계산할 수 있다`() {
        val card1 = Card.of(rank = Rank.TWO, suit = Suit.SPADE)
        val card2 = Card.of(rank = Rank.THREE, suit = Suit.SPADE)

        hand.addCard(card1)
        hand.addCard(card2)

        assertThat(hand.getTotalSum()).isEqualTo(5)
    }

    @Test
    fun `2와 Ace를 더했을 때 총합은 13이 되어야 한다`() {
        val card1 = Card.of(rank = Rank.TWO, suit = Suit.SPADE)
        val card2 = Card.of(rank = Rank.ACE, suit = Suit.SPADE)

        hand.addCard(card1)
        hand.addCard(card2)

        assertThat(hand.getTotalSum()).isEqualTo(13)
    }

    @Test
    fun `Ace 2장과 9을 더했을 때 총합은 21이 되어야 한다`() {
        val card1 = Card.of(rank = Rank.ACE, suit = Suit.SPADE)
        val card2 = Card.of(rank = Rank.NINE, suit = Suit.SPADE)
        val card3 = Card.of(rank = Rank.ACE, suit = Suit.HEART)

        hand.addCard(card1)
        hand.addCard(card2)
        hand.addCard(card3)

        assertThat(hand.getTotalSum()).isEqualTo(21)
    }

    @Test
    fun `Ace 2장과 10을 더했을 때 총합은 12가 되어야 한다`() {
        val card1 = Card.of(rank = Rank.ACE, suit = Suit.SPADE)
        val card2 = Card.of(rank = Rank.TEN, suit = Suit.SPADE)
        val card3 = Card.of(rank = Rank.ACE, suit = Suit.HEART)

        hand.addCard(card1)
        hand.addCard(card2)
        hand.addCard(card3)

        assertThat(hand.getTotalSum()).isEqualTo(12)
    }

    @Test
    fun `가지고 있는 카드가 블랙잭인지 확인할 수 있다`() {
        val card1 = Card.of(rank = Rank.ACE, suit = Suit.SPADE)
        val card2 = Card.of(rank = Rank.KING, suit = Suit.SPADE)

        hand.addCard(card1)
        hand.addCard(card2)

        assertThat(hand.isBlackJack()).isTrue()
    }

    @Test
    fun `가지고 있는 카드가 버스트된 상태인 지 확인할 수 있다`() {
        val card1 = Card.of(rank = Rank.KING, suit = Suit.SPADE)
        val card2 = Card.of(rank = Rank.QUEEN, suit = Suit.SPADE)
        val card3 = Card.of(rank = Rank.JACK, suit = Suit.SPADE)

        hand.addCard(card1)
        hand.addCard(card2)
        hand.addCard(card3)

        assertThat(hand.isBust()).isTrue()
    }
}
