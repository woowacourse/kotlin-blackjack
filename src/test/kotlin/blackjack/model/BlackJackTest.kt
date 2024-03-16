package blackjack.model

import blackjack.state.State
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackTest {
    @Test
    fun `유저가 더 이상 카드를 드로우 하지 않을 경우 테스트`() {
        val blackJack = BlackJack()
        blackJack.switchToStayState()
        assertThat(blackJack.getState()).isEqualTo(State.Finish.Stay)
    }

    @Test
    fun `BlackJack 생성시 상태 확인 테스트`() {
        val blackjack = BlackJack()
        assertThat(blackjack.checkDrawState()).isEqualTo(true)
    }

    @Test
    fun `카드를 드로우 했을 때, 정상적인 Hit 상태 유지 테스트`() {
        val blackJack = BlackJack()
        blackJack.apply {
            addCard(Card(Denomination.NINE, Suit.SPADE))
            addCard(Card(Denomination.SEVEN, Suit.CLOVER))
        }
        assertThat(blackJack.getState()).isEqualTo(State.Action.Hit)
    }

    @Test
    fun `카드를 드로우 했을 때, 정상적인 BLACK_JACK 상태로 전환`() {
        val blackJack = BlackJack()
        blackJack.apply {
            addCard(Card(Denomination.ACE, Suit.SPADE))
            addCard(Card(Denomination.JACK, Suit.CLOVER))
        }
        assertThat(blackJack.getState()).isEqualTo(State.Finish.BlackJack)
    }

    @Test
    fun `카드를 드로우 했을 때, 정상적인 Bust 상태로 전환 테스트`() {
        val bustBlackJack = BlackJack()
        bustBlackJack.apply {
            addCard(Card(Denomination.KING, Suit.SPADE))
            addCard(Card(Denomination.KING, Suit.CLOVER))
            addCard(Card(Denomination.KING, Suit.DIAMOND))
        }
        assertThat(bustBlackJack.getState()).isEqualTo(State.Finish.Bust)
    }
}
