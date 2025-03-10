package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HandsTest {
    private lateinit var hands: Hands

    @BeforeEach
    fun `setUp`() {
        hands = Hands(Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.KING))
    }

    @Test
    fun `지정한 개수의 카드민 반환한다`() {
        assertThat(hands.extractCards(1)).isEqualTo(listOf(Card(Suit.HEART, Rank.ACE)))
    }

    @Test
    fun `카드를 추가해서 새로 만든다`() {
        val actual = listOf(Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.KING), Card(Suit.HEART, Rank.SIX))
        val nextHands = hands.nextHand(Card(Suit.HEART, Rank.SIX))
        assertThat(nextHands.cards).isEqualTo(actual)
    }

    @Test
    fun `카드의 보너스 점수를 추가한 총합을 반환한다`() {
        hands = Hands(Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.KING))
        assertThat(hands.getScore()).isEqualTo(21)
    }

    @Test
    fun `카드의 보너스 점수가 없는 총합을 반환한다`() {
        hands = Hands(Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.KING), Card(Suit.SPADE, Rank.KING))
        assertThat(hands.getScore()).isEqualTo(21)
    }
}
