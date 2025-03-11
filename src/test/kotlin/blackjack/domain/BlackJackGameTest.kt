package blackjack.domain

import blackjack.domain.person.Dealer
import blackjack.domain.person.Player
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BlackJackGameTest {
    private lateinit var dealer: Dealer
    private lateinit var players: List<Player>
    private lateinit var game: BlackJackGame

    @BeforeEach
    fun setUp() {
        dealer = Dealer()
        players = listOf(Player("Alice"), Player("Bob"))
        game = BlackJackGame(dealer, players)
    }

    @Test
    fun `딜러와 플레이어가 각각 2장의 카드를 받아야 한다`() {
        game.dealCards()

        assertAll(
            { dealer.cards.size shouldBe 2 },
            { players.all { it.cards.size == 2 } shouldBe true },
        )
    }

    @Test
    fun `hitFlag이 true이면 플레이어가 카드를 추가로 뽑는다`() {
        game.dealCards()
        game.playPlayersTurns(getHitFlag = { name -> name == "Alice" }, {})

        val alice = players.find { it.name == "Alice" }!!
        val bob = players.find { it.name == "Bob" }!!

        assertAll(
            { (alice.cards.size > 2) shouldBe true },
            { bob.cards.size shouldBe 2 },
        )
    }

    @Test
    fun `딜러는 17 이상이 될 때까지 카드를 뽑는다`() {
        game.dealCards()
        game.playDealerTurns {}

        (dealer.score >= 17) shouldBe true
    }

    @Test
    fun `플레이어와 딜러의 승패를 올바르게 판단해야 한다`() {
        game.dealCards()
        val result = game.gameResult()

        assertNotNull(result)
    }
}
