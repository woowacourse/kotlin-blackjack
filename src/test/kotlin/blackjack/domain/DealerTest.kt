package blackjack.domain

import blackjack.domain.card.Deck
import blackjack.domain.person.Dealer
import blackjack.domain.state.DealerState
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var deck: Deck
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setup() {
        deck = Deck()
        dealer = Dealer()
    }

    @Test
    fun `딜러의 초기 상태는 FIRST_TURN이어야 한다`() {
        dealer.gameState shouldBe DealerState.FIRST_TURN
    }

    @Test
    fun `상태가 FIRST_TURN이면 2장을 드로우한다`() {
        dealer.gameState shouldBe DealerState.FIRST_TURN
        dealer.draw(deck)

        dealer.cards().size shouldBe 2
    }

    @Test
    fun `상태가 HIT이면 1장을 드로우한다`() {
        setupPlayerWithState(DealerState.HIT)
        dealer.draw(deck)

        dealer.cards().size shouldBe 3
    }

    @Test
    fun `상태가 FIRST_TURN이라면 true를 반환한다`() {
        dealer.canDraw() shouldBe true
    }

    @Test
    fun `상태가 FINISH라면 false를 반환한다`() {
        setupPlayerWithState(DealerState.FINISH)
        dealer.canDraw() shouldBe false
    }

    @Test
    fun `상태가 HIT이라면 true를 반환한다`() {
        setupPlayerWithState(DealerState.HIT)
        dealer.canDraw() shouldBe true
    }

    private fun setupPlayerWithState(state: DealerState) {
        while (dealer.gameState != state) {
            dealer.draw(deck)
        }
    }
}
