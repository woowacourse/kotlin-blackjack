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
        assertThat(hands.showCards(1)).isEqualTo(listOf(Card(Suit.HEART, Rank.ACE)))
    }

    @Test
    fun `플레이어는 카드를 받는다`() {
        val actual =
            Player(
                "동전",
                Card(Suit.HEART, Rank.ACE),
                Card(Suit.HEART, Rank.KING),
                Card(Suit.HEART, Rank.SIX),
            )
        hands.accept(listOf(Card(Suit.HEART, Rank.SIX)))
        assertThat(hands.showCards()).isEqualTo(actual.hands.showCards())
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
