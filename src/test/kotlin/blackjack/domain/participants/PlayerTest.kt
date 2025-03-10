package blackjack.domain.participants

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.card.Deck
import blackjack.domain.state.GameState
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var deck: Deck

    @BeforeEach
    fun setup() {
        deck = Deck()
    }

    @Test
    fun `플레이어는 이름을 가지고 있어야 한다`() {
        val player = Player("pobi") { true }
        player.name shouldBe "pobi"
    }

    @Test
    fun `게임 상태를 가지고 있다`() {
        val player = Player("pobi") { true }
        player.gameState shouldBe GameState.FIRST_TURN
    }

    @Test
    fun `카드를 받을 수 있다`() {
        // Given
        val player = Player("pobi") { true }
        val card = deck.draw()

        // When
        player.addCard(card)

        // Then
        player.hand.size shouldBe 1
    }

    @Test
    fun `가지고 있는 패의 총 합을 계산한다`() {
        // Given
        val player = Player("pobi") { true }
        val cards = List(2) { Card(CardNumber.KING, CardPattern.HEART) }

        // When
        cards.forEach { card ->
            player.addCard(card)
        }

        // Then
        player.score() shouldBe 20
    }

    @Test
    fun `HIT 여부를 판단한다`() {
        // Given
        val player = Player("pobi") { false }
        val cards = List(3) { Card(CardNumber.KING, CardPattern.HEART) }

        // When
        cards.forEach { card ->
            player.addCard(card)
        }

        // Then
        player.shouldHit() shouldBe false
    }

    @Test
    fun `패에 2장만 존재하고, 총 합이 21이면 블랙잭이다`() {
        // Given
        val player = Player("pobi") { true }
        val cards =
            listOf(
                Card(CardNumber.ACE, CardPattern.HEART),
                Card(CardNumber.KING, CardPattern.HEART),
            )

        // When
        cards.forEach { card ->
            player.addCard(card)
        }

        // Then
        player.gameState shouldBe GameState.BLACKJACK
    }

    @Test
    fun `카드의 총 합이 21이 넘으면 버스트가 된다`() {
        // Given
        val player = Player("pobi") { true }
        val cards = List(3) { Card(CardNumber.KING, CardPattern.HEART) }

        // When
        cards.forEach { card ->
            player.addCard(card)
        }

        // Then
        player.gameState shouldBe GameState.BUST
    }

    @Test
    fun `초기 상태일 경우 카드를 2장 받는다`() {
        val player = Player("pobi") { true }
        player.getDrawAmount() shouldBe 2
    }

    @Test
    fun `HIT일 경우 카드를 1장 받는다`() {
        // Given
        val player = Player("pobi") { true }
        val card = Card(CardNumber.ACE, CardPattern.HEART)

        // When
        player.addCard(card)

        // Then
        player.getDrawAmount() shouldBe 1
    }

    @Test
    fun `버스트가 된 경우 카드를 받을 수 없다`() {
        // Given
        val player = Player("pobi") { true }
        val cards = List(3) { Card(CardNumber.KING, CardPattern.HEART) }

        // When
        cards.forEach { card ->
            player.addCard(card)
        }

        // Then
        shouldThrowExactly<IllegalArgumentException> { player.getDrawAmount() }
    }
}
