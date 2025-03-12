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
    fun `지정한 1개의 카드만 반환한다`() {
        assertThat(hands.extractCards(1)).isEqualTo(listOf(Card(Suit.HEART, Rank.ACE)))
    }

    @Test
    fun `카드를 추가해서 새로 만든다`() {
        val actual = listOf(Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.KING), Card(Suit.HEART, Rank.SIX))
        val nextHands = hands.nextHand(Card(Suit.HEART, Rank.SIX))
        assertThat(nextHands.cards).isEqualTo(actual)
    }

    @Test
    fun `카드를 더한 값이 21이 넘을 경우 버스트 된다 `() {
        hands = Hands(Card(Suit.HEART, Rank.JACK), Card(Suit.HEART, Rank.KING), Card(Suit.HEART, Rank.KING)) // 30
        assertThat(hands.isBust()).isTrue()
    }

    @Test
    fun `카드를 더한 값이 21이 넘지 않을 경우 버스트를 되지 않는다`() {
        hands = Hands(Card(Suit.HEART, Rank.JACK), Card(Suit.HEART, Rank.KING)) // 20
        assertThat(hands.isBust())
    }

    @Test
    fun `카드를 더한 값이 11이하이고 에이스가 포함될 경우 10점 보너스를 받는다`() {
        hands = Hands(Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.KING)) // 1+10 +10
        assertThat(hands.getScore()).isEqualTo(21)
    }

    @Test
    fun `카드를 더한 값이 11이상일 경우 에이스가 포함되어도 보너스 값을 받지 않는다`() {
        hands = Hands(Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.KING), Card(Suit.SPADE, Rank.KING)) // 1 +10 +10
        assertThat(hands.getScore()).isEqualTo(21)
    }
}
