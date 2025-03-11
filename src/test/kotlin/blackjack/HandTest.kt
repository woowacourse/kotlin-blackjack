package blackjack

import blackjack.domain.Card
import blackjack.domain.Hand
import blackjack.domain.Rank
import blackjack.domain.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HandTest {
    private lateinit var hand: Hand

    @BeforeEach
    fun setUp(){
        hand = Hand()
    }

    @Test
    fun `카드의 총합을 계산할 수 있다`() {
        val card1 = Card.of(rank = Rank.TWO, suit = Suit.SPADE)
        val card2 = Card.of(rank = Rank.THREE, suit = Suit.SPADE)

        hand.addCard(card1)
        hand.addCard(card2)

        assertThat(hand.getCardSum()).isEqualTo(5)
    }

    @Test
    fun `플레이어는 가지고 있는 카드의 합을 계산할 수 있다(ACE 1장)`() {
        val card1 = Card.of(rank = Rank.TWO, suit = Suit.SPADE)
        val card2 = Card.of(rank = Rank.ACE, suit = Suit.SPADE)

        hand.addCard(card1)
        hand.addCard(card2)

        assertThat(hand.getCardSum()).isEqualTo(13)
    }

    @Test
    fun `플레이어는 가지고 있는 카드의 합을 계산할 수 있다(ACE 2장)`() {
        val card1 = Card.of(rank = Rank.ACE, suit = Suit.SPADE)
        val card2 = Card.of(rank = Rank.NINE, suit = Suit.SPADE)
        val card3 = Card.of(rank = Rank.ACE, suit = Suit.HEART)

        hand.addCard(card1)
        hand.addCard(card2)
        hand.addCard(card3)

        assertThat(hand.getCardSum()).isEqualTo(21)
    }
}