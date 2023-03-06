package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerHandTest {
    @Test
    fun `카드 목록에 카드를 추가한다`() {
        val playerHand = PlayerHand()

        playerHand.add(Card(Suit.SPADE, CardNumber.ACE))
        playerHand.add(Card(Suit.SPADE, CardNumber.THREE))
        playerHand.add(Card(Suit.SPADE, CardNumber.FIVE))

        assertThat(playerHand.cards.size).isEqualTo(3)
    }

    @Test
    fun `A는 기존 총 점수에 11점을 더한 값이 21점 이하이면 11점으로 계산한다`() {
        val playerHand = PlayerHand()
        playerHand.add(Card(Suit.SPADE, CardNumber.ACE))

        assertThat(playerHand.calculateTotalScore()).isEqualTo(11)
    }

    @Test
    fun `A는 기존 총 점수에 11점을 더한 값이 21점을 초과하면 1점으로 계산한다`() {
        val playerHand = PlayerHand()
        playerHand.add(Card(Suit.SPADE, CardNumber.ACE))
        playerHand.add(Card(Suit.HEART, CardNumber.ACE))

        assertThat(playerHand.calculateTotalScore()).isEqualTo(12)
    }
}
