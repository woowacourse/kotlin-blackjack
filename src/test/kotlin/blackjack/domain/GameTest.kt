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
        val player = Player("test")
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
        val player = Player("test")
        val fakeDeck =
            Deck(
                mutableListOf(
                    Card(CardNumber.JACK, CardPattern.CLOVER),
                    Card(CardNumber.JACK, CardPattern.CLOVER),
                    Card(CardNumber.JACK, CardPattern.CLOVER),
                    Card(CardNumber.JACK, CardPattern.CLOVER),
                    Card(CardNumber.TWO, CardPattern.CLOVER),
                ),
            )
        val dealer = Dealer(fakeDeck)
        val game = Game(dealer, listOf(player))

        // When
        game.askHit(
            decideHit = { true },
            onHit = {},
        )

        // Then
        player.hand.size shouldBe 3
    }

    @Test
    fun `플레이어가 HIT를 요청하지 않으면 카드를 지급하지 않는다`() {
        // Given
        val player = Player("test")
        val game = Game(dealer, listOf(player))

        // When
        game.askHit(
            decideHit = { false },
            onHit = {},
        )

        // Then
        player.hand.size shouldBe 2
    }

    @Test
    fun `플레이어가 BUST되면 카드를 지급하지 않는다`() {
        // Given
        val player = Player("test")
        val fakeDeck =
            Deck(
                mutableListOf(
                    Card(CardNumber.JACK, CardPattern.CLOVER),
                    Card(CardNumber.JACK, CardPattern.CLOVER),
                    Card(CardNumber.JACK, CardPattern.CLOVER),
                    Card(CardNumber.JACK, CardPattern.CLOVER),
                    Card(CardNumber.TWO, CardPattern.CLOVER),
                    Card(CardNumber.TWO, CardPattern.CLOVER),
                ),
            )
        val dealer = Dealer(fakeDeck)
        val game = Game(dealer, listOf(player))

        // When
        game.askHit(
            decideHit = { true },
            onHit = {},
        )

        // Then
        assertSoftly(player) {
            isBust() shouldBe true
            hand.size shouldBe 3
        }
    }

    @Test
    fun `딜러는 패의 점수가 16 이하일 경우 카드를 1장 받는다`() {
        // Given
        val player = Player("test")
        val fakeDeck =
            Deck(
                mutableListOf(
                    Card(CardNumber.ACE, CardPattern.CLOVER),
                    Card(CardNumber.FIVE, CardPattern.CLOVER),
                    Card(CardNumber.ACE, CardPattern.CLOVER),
                    Card(CardNumber.FIVE, CardPattern.CLOVER),
                    Card(CardNumber.FIVE, CardPattern.CLOVER),
                ),
            )
        val dealer = Dealer(fakeDeck)
        val game = Game(dealer, listOf(player))

        // Then
        game.processDealerHit() shouldBe true
    }

    @Test
    fun `딜러는 BUST가 되면 카드를 받지 않는다`() {
        // Given
        val player = Player("test")
        val game = Game(dealer, listOf(player))
        val cards = List(3) { Card(CardNumber.JACK, CardPattern.CLOVER) }
        cards.forEach { card ->
            dealer.addCard(card)
        }

        // Then
        game.processDealerHit() shouldBe false
    }
}
