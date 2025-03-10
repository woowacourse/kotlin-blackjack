package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.card.Deck
import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Player
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameTest {
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setup() {
        dealer = Dealer(Deck())
    }

    @Test
    fun `게임 시작 시 딜러와 플레이어에게 카드를 분배한다`() {
        // Given
        val player = Player("test") { true }
        val game = Game(dealer, listOf(player))

        // Then
        assertSoftly {
            player.hand.size shouldBe 2
            dealer.hand.size shouldBe 2
        }
    }

    @Test
    fun `플레이어가 HIT를 요청하면 카드를 1장 지급한다`() {
        // Given
        val player = Player("test") { true }
        val game = Game(dealer, listOf(player))

        // When
        game.askHit {}

        // Then
        player.hand.size shouldBe 3
    }

    @Test
    fun `플레이어가 HIT를 요청하지 않으면 카드를 지급하지 않는다`() {
        // Given
        val player = Player("test") { false }
        val game = Game(dealer, listOf(player))

        // When
        game.askHit {}

        // Then
        player.hand.size shouldBe 2
    }

    @Test
    fun `딜러는 BUST가 되면 카드를 받지 않는다`() {
        // Given
        val player = Player("test") { false }
        val game = Game(dealer, listOf(player))
        val cards = List(3) { Card(CardNumber.JACK, CardPattern.CLOVER) }
        cards.forEach { card ->
            dealer.addCard(card)
        }

        // Then
        game.processDealerHit() shouldBe false
    }
}
