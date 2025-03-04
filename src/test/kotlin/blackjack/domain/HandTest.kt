package blackjack.domain

import blackjack.model.Card
import blackjack.model.CardRank.ACE
import blackjack.model.CardRank.TWO
import blackjack.model.CardSuit.CLUB
import blackjack.model.Hand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `뽑은 카드를 핸드에 추가한다`() {
        // given
        val hand = Hand()
        val card = Card(ACE, CLUB)

        // when
        hand.add(card)

        // then
        assertTrue(hand.cards.contains(card))
    }

    @Test
    fun `핸드에 2클로버 한 장을 가지고 있으면 2점을 반환한다`() {
        // given
        val expectedScore = 2
        val hand = Hand()
        val card = Card(TWO, CLUB)
        hand.add(card)

        // when
        val score = hand.score()

        // then
        assertThat(score).isEqualTo(expectedScore)
    }
}
