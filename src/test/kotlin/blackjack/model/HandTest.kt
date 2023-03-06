package blackjack.model

import model.Card
import model.Hand
import model.Rank
import model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class HandTest {

    @Test
    fun `카드를 한 장 추가할 수 있다`() {
        val hand = Hand(listOf(Card(Rank.ACE, Suit.CLOVER), Card(Rank.ACE, Suit.DIAMOND)))
        hand.add(Card(Rank.DEUCE, Suit.DIAMOND))
        assertThat(hand.size).isEqualTo(3)
    }

    @Test
    fun `플레이어 카드에 이미 존재하는 카드를 넣으면 에러를 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Hand(
                listOf(
                    Card(Rank.ACE, Suit.CLOVER),
                    Card(Rank.ACE, Suit.DIAMOND),
                ),
            ).add(Card(Rank.ACE, Suit.DIAMOND))
        }
    }

    @Test
    fun `11클로버와 2다이아 카드의 합이 13이다`() {
        val hand = Hand(listOf(Card(Rank.ACE, Suit.CLOVER), Card(Rank.DEUCE, Suit.DIAMOND)))
        assertThat(hand.sum()).isEqualTo(13)
    }
}
