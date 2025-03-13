package blackjack.domain

import blackjack.model.card.Card
import blackjack.model.card.CardRank
import blackjack.model.card.CardSuit
import blackjack.model.participant.Hand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HandTest {
    private lateinit var hand: Hand

    @BeforeEach
    fun setup() {
        hand = Hand()
    }

    @Test
    fun `카드를 추가하면 카드 목록에 포함된다`() {
        // given
        val card1 = Card(CardRank.KING, CardSuit.HEART)
        val card2 = Card(CardRank.QUEEN, CardSuit.HEART)

        // when
        hand.addAll(listOf(card1, card2))

        // then
        assertThat(hand.cards).containsExactly(card1, card2)
    }

    @Test
    fun `점수를 정확하게 반환한다`() {
        // given
        hand.addAll(
            listOf(
                Card(CardRank.KING, CardSuit.HEART),
                Card(CardRank.QUEEN, CardSuit.HEART),
            ),
        )

        // when
        val score = hand.score()

        // then
        assertThat(score).isEqualTo(20)
    }

    @Test
    fun `21점을 초과하면 버스트된다`() {
        // given
        hand.addAll(
            listOf(
                Card(CardRank.KING, CardSuit.HEART),
                Card(CardRank.KING, CardSuit.HEART),
                Card(CardRank.KING, CardSuit.HEART),
            ),
        )

        // when
        val isBust = hand.isBust()

        // then
        assertThat(isBust).isTrue()
    }

    @Test
    fun `21점 이하면 버스트되지 않는다`() {
        // given
        hand.addAll(
            listOf(
                Card(CardRank.KING, CardSuit.HEART),
                Card(CardRank.ACE, CardSuit.HEART),
            ),
        )

        // when
        val isBust = hand.isBust()

        // then
        assertThat(isBust).isFalse()
    }
}
