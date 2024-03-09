package blackjack

import blackjack.model.card.Card
import blackjack.model.card.Denomination
import blackjack.model.card.Hand
import blackjack.model.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `Hand 객체 생성`() {
        val cards = mutableListOf(Card(Denomination.ACE, Suit.HEARTS), Card(Denomination.SIX, Suit.SPADES))
        val hand = Hand(cards)
        assertThat(hand.cards).isEqualTo(cards)
    }

    @Test
    fun `draw 카드 추가 확인`() {
        val cards = mutableListOf(Card(Denomination.ACE, Suit.HEARTS), Card(Denomination.SIX, Suit.SPADES))
        val hand = Hand(cards)
        val card = Card(Denomination.TWO, Suit.HEARTS)
        hand
            .draw(card)
        assertThat(hand.cards.size).isEqualTo(3)
    }

    @Test
    fun `스코어 계산하기`() {
        val cards = mutableListOf(Card(Denomination.ACE, Suit.HEARTS), Card(Denomination.SIX, Suit.SPADES))
        val hand = Hand(cards)
        hand.draw(Card(Denomination.FOUR, Suit.HEARTS))

        assertThat(hand.totalScore).isEqualTo(21)
    }

    @Test
    fun `만약 버스트 and ACE 가지고 있으면 ACE를 1로 변환`() {
        val cards =
            mutableListOf(
                Card(Denomination.ACE, Suit.HEARTS),
                Card(Denomination.JACK, Suit.SPADES),
            )
        val hand = Hand(cards)
        hand.draw(Card(Denomination.ACE, Suit.SPADES))
        assertThat(hand.totalScore).isEqualTo(12)
    }
}
