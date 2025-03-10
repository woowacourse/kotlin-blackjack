package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.card.Deck
import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Player
import blackjack.domain.state.ResultState
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameResultTest {
    private lateinit var dealer: Dealer
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        dealer = Dealer(Deck())
        val dealerCards =
            listOf(
                Card(CardNumber.JACK, CardPattern.HEART),
                Card(CardNumber.EIGHT, CardPattern.HEART),
            )
        dealerCards.forEach { card ->
            dealer.addCard(card)
        }
        player = Player("pobi") { true }
    }

    @Test
    fun `패의 총합이 플레이어가 큰 경우 플레이어가 승리한다`() {
        // Given
        val playerCards =
            listOf(
                Card(CardNumber.JACK, CardPattern.HEART),
                Card(CardNumber.JACK, CardPattern.HEART),
            )
        playerCards.forEach { card ->
            player.addCard(card)
        }

        // When
        val result = GameResult.create(dealer, listOf(player))

        // Then
        result.winStatus[player] shouldBe ResultState.WIN
    }

    @Test
    fun `패의 총합이 딜러가 큰 경우 플레이어가 패배한다`() {
        // Given
        val playerCards =
            listOf(
                Card(CardNumber.JACK, CardPattern.HEART),
                Card(CardNumber.SEVEN, CardPattern.HEART),
            )
        playerCards.forEach { card ->
            player.addCard(card)
        }

        // When
        val result = GameResult.create(dealer, listOf(player))

        // Then
        result.winStatus[player] shouldBe ResultState.LOSE
    }

    @Test
    fun `패의 총 합이 같은 경우 무승부가 된다`() {
        // Given
        val playerCards =
            listOf(
                Card(CardNumber.JACK, CardPattern.HEART),
                Card(CardNumber.EIGHT, CardPattern.HEART),
            )
        playerCards.forEach { card ->
            player.addCard(card)
        }

        // When
        val result = GameResult.create(dealer, listOf(player))

        // Then
        result.winStatus[player] shouldBe ResultState.DRAW
    }

    @Test
    fun `플레이어가 버스트되면 플레이어가 패배한다`() {
        // Given
        val playerCards =
            listOf(
                Card(CardNumber.JACK, CardPattern.HEART),
                Card(CardNumber.JACK, CardPattern.HEART),
                Card(CardNumber.TWO, CardPattern.HEART),
            )
        playerCards.forEach { card ->
            player.addCard(card)
        }

        // When
        val result = GameResult.create(dealer, listOf(player))

        // Then
        result.winStatus[player] shouldBe ResultState.LOSE
    }

    @Test
    fun `딜러와 플레이어 모두 버스트되면 플레이어가 패배한다`() {
        // Given
        dealer.addCard(Card(CardNumber.FIVE, CardPattern.HEART))

        val playerCards =
            listOf(
                Card(CardNumber.JACK, CardPattern.HEART),
                Card(CardNumber.JACK, CardPattern.HEART),
                Card(CardNumber.TWO, CardPattern.HEART),
            )
        playerCards.forEach { card ->
            player.addCard(card)
        }

        // When
        val result = GameResult.create(dealer, listOf(player))

        // Then
        result.winStatus[player] shouldBe ResultState.LOSE
    }

    @Test
    fun `딜러와 플레이어 모두 블랙잭이 되면 플레이어가 승리한다`() {
        // Given
        val blackjackCards =
            listOf(
                Card(CardNumber.ACE, CardPattern.HEART),
                Card(CardNumber.KING, CardPattern.HEART),
            )
        val blackjackDealer = Dealer(Deck())

        blackjackCards.forEach { card ->
            player.addCard(card)
            blackjackDealer.addCard(card)
        }

        // When
        val result = GameResult.create(blackjackDealer, listOf(player))

        // Then
        result.winStatus[player] shouldBe ResultState.DRAW
    }
}
