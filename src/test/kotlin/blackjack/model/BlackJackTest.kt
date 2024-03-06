package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackTest {
    class BlackJack {
        private val handCard = HandCard()
        private var _state: State = State.Action.Hit
        val state: State get() = _state

        //TODO checkDrawState -> (y : addCard -> changeState, n : switchToStayState)
        fun checkDrawState(): Boolean {
            return when (state) {
                is State.Action.Hit -> true
                is State.Finish -> false
            }
        }

        fun switchToStayState() {
            _state = State.Finish.Stay
        }

        fun addCard(card: Card) {
            handCard.addCard(card)
        }

        fun changeState() {
            val totalScore = handCard.getTotalCardsSum()
            when (totalScore) {
                in MIN_SCORE until BLACK_JACK_SCORE -> _state = State.Action.Hit
                BLACK_JACK_SCORE -> _state = State.Finish.BlackJack
                in BUST_SCORE..MAX_SCORE -> _state = State.Finish.Bust
            }
        }

        companion object {
            private const val BLACK_JACK_SCORE: Int = 21
            private const val MIN_SCORE: Int = 0
            private const val BUST_SCORE: Int = 22
            private const val MAX_SCORE: Int = 30
        }
    }

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
}
