package blackjack.domain

import blackjack.domain.card.Deck
import blackjack.domain.person.Player
import blackjack.domain.state.PlayerState
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var deck: Deck
    private lateinit var player: Player

    @BeforeEach
    fun setup() {
        deck = Deck()
        player = Player("pobi")
    }

    @Test
    fun `player는 이름을 가지고 있어야 한다`() {
        player.name shouldBe "pobi"
    }

    @Test
    fun `player의 초기 상태는 FIRST_TRUN이어야 한다`() {
        player.gameState shouldBe PlayerState.FIRST_TURN
    }

    @Test
    fun `isHit이 true면 카드를 덱에서 뽑아 패에 추가한다`() {
        player.draw(deck, true)
        player.cards().size shouldBe 2
    }

    @Test
    fun `isHit이 false면 카드를 뽑지 않고 상태를 STAY로 변경한다`() {
        player.draw(deck, false)

        player.cards().size shouldBe 0
        player.gameState shouldBe PlayerState.STAY
    }

    @Test
    fun `상태가 FIRST_TURN이면 2장을 드로우한다`() {
        player.gameState shouldBe PlayerState.FIRST_TURN
        player.draw(deck, true)

        player.cards().size shouldBe 2
    }

    @Test
    fun `상태가 HIT이면 1장을 드로우한다`() {
        setupPlayerWithState(PlayerState.HIT)
        player.draw(deck, true)

        player.cards().size shouldBe 3
    }

    @Test
    fun `상태가 STAY라면 false를 반환한다`() {
        player.draw(deck, false)
        player.canDraw() shouldBe false
    }

    @Test
    fun `상태가 BUST라면 false를 반환한다`() {
        setupPlayerWithState(PlayerState.BUST)
        player.canDraw() shouldBe false
    }

    @Test
    fun `상태가 FIRST_TRUN이라면 true를 반환한다`() {
        player.canDraw() shouldBe true
    }

    @Test
    fun `상태가 HIT이라면 true를 반환한다`() {
        setupPlayerWithState(PlayerState.HIT)
        player.canDraw() shouldBe true
    }

    private fun setupPlayerWithState(state: PlayerState) {
        while (player.gameState != state) {
            player.draw(deck, true)
        }
    }
}
