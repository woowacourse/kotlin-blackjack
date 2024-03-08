package blackjack.model

import blackjack.state.State
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackTest {

    @Test
    fun `유저가 더 이상 카드를 드로우 하지 않을 경우 테스트`() {
        val blackJack = BlackJack()
        blackJack.switchToStayState()
        assertThat(blackJack.state).isEqualTo(State.Finish.Stay)
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
        blackJack.changeState()
        assertThat(blackJack.state).isEqualTo(State.Action.Hit)
    }

    @Test
    fun `카드를 드로우 했을 때, 정상적인 BLACK_JACK 상태로 전환`() {
        val blackJack = BlackJack()
        blackJack.apply {
            addCard(Card(Denomination.ACE, Suit.SPADE))
            addCard(Card(Denomination.JACK, Suit.CLOVER))
        }
        blackJack.changeState()
        assertThat(blackJack.state).isEqualTo(State.Finish.BlackJack)
    }

    @Test
    fun `카드를 드로우 했을 때, 정상적인 Bust 상태로 전환 테스트`() {
        val bustBlackJack = BlackJack()
        bustBlackJack.apply {
            addCard(Card(Denomination.KING, Suit.SPADE))
            addCard(Card(Denomination.KING, Suit.CLOVER))
            addCard(Card(Denomination.KING, Suit.DIAMOND))
        }
        bustBlackJack.changeState()
        assertThat(bustBlackJack.state).isEqualTo(State.Finish.Bust)
    }

    @Test
    fun `카드를 드로우 했을 때, ACE가 1개 있는 경우 버스트 상태에서 정상적으로 Hit 상태 전환 테스트`() {
        val aceBlackJack = BlackJack()
        aceBlackJack.apply {
            addCard(Card(Denomination.ACE, Suit.SPADE))
            addCard(Card(Denomination.KING, Suit.CLOVER))
            addCard(Card(Denomination.NINE, Suit.DIAMOND))
        }
        aceBlackJack.changeState()
        assertThat(aceBlackJack.state).isEqualTo(State.Action.Hit)
    }

    @Test
    fun `카드를 드로우 했을 때, ACE가 4개 있는 경우 버스트 상태에서 정상적으로 Hit 상태 전환 테스트`() {
        val aceBlackJack = BlackJack()
        aceBlackJack.apply {
            addCard(Card(Denomination.ACE, Suit.SPADE))
            addCard(Card(Denomination.ACE, Suit.CLOVER))
            addCard(Card(Denomination.ACE, Suit.HEART))
            addCard(Card(Denomination.ACE, Suit.DIAMOND))
            addCard(Card(Denomination.SEVEN, Suit.DIAMOND))
            addCard(Card(Denomination.NINE, Suit.SPADE))
        }
        aceBlackJack.changeState()
        assertThat(aceBlackJack.state).isEqualTo(State.Action.Hit)
    }
}
