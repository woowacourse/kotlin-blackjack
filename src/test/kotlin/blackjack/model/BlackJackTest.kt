package blackjack.model

import blackjack.state.State
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BlackJackTest {
    private lateinit var defaultBlackJack: BlackJack
    private lateinit var bustBlackJack: BlackJack
    private lateinit var aceCountOneBlackJack: BlackJack
    private lateinit var aceCountFourBlackJack: BlackJack

    private fun createCards(vararg cards: Card): BlackJack {
        return BlackJack().apply {
            cards.forEach { addCard(it) }
        }
    }

    @BeforeEach
    fun setUp() {
        defaultBlackJack = BlackJack()
        bustBlackJack =
            createCards(
                SPADE_KING,
                CLOVER_KING,
                DIAMOND_KING,
            )
        aceCountOneBlackJack =
            createCards(
                SPADE_ACE,
                CLOVER_KING,
                DIAMOND_NINE,
            )
        aceCountFourBlackJack =
            createCards(
                SPADE_ACE,
                CLOVER_ACE,
                HEART_ACE,
                DIAMOND_ACE,
                DIAMOND_SEVEN,
                SPADE_NINE,
            )
    }

    @Test
    fun `유저가 더 이상 카드를 드로우 하지 않을 경우 테스트`() {
        defaultBlackJack.switchToStayState()
        assertThat(defaultBlackJack.state).isEqualTo(State.Finish.Stay)
    }

    @Test
    fun `BlackJack 생성시 상태 확인 테스트`() {
        assertThat(defaultBlackJack.checkDrawState()).isEqualTo(true)
    }

    @Test
    fun `카드를 드로우 했을 때, 정상적인 Hit 상태 유지 테스트`() {
        val blackJack = createCards(SPADE_NINE, DIAMOND_SEVEN)

        blackJack.changeState()
        assertThat(blackJack.state).isEqualTo(State.Action.Hit)
    }

    @Test
    fun `카드를 드로우 했을 때, 정상적인 BLACK_JACK 상태로 전환`() {
        val blackJack = createCards(SPADE_ACE, CLOVER_JACK)

        blackJack.changeState()
        assertThat(blackJack.state).isEqualTo(State.Finish.BlackJack)
    }

    @Test
    fun `카드를 드로우 했을 때, 정상적인 Bust 상태로 전환 테스트`() {
        bustBlackJack.changeState()
        assertThat(bustBlackJack.state).isEqualTo(State.Finish.Bust)
    }

    @Test
    fun `카드를 드로우 했을 때, ACE가 1개 있는 경우 버스트 상태에서 정상적으로 Hit 상태 전환 테스트`() {
        aceCountOneBlackJack.changeState()
        assertThat(aceCountOneBlackJack.state).isEqualTo(State.Action.Hit)
    }

    @Test
    fun `카드를 드로우 했을 때, ACE가 4개 있는 경우 버스트 상태에서 정상적으로 Hit 상태 전환 테스트`() {
        aceCountFourBlackJack.changeState()
        assertThat(aceCountFourBlackJack.state).isEqualTo(State.Action.Hit)
    }
}
