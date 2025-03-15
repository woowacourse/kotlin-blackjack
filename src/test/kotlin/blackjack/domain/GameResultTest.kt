package blackjack.domain

import blackjack.domain.betting.BettingAmount
import blackjack.domain.betting.BettingInfo
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

    @BeforeEach
    fun setUp() {
        val dealerCards =
            listOf(
                Card(CardNumber.JACK, CardPattern.HEART),
                Card(CardNumber.EIGHT, CardPattern.HEART),
            )
        dealer = Dealer(Deck.createDefaultDeck(), dealerCards)
    }

    @Test
    fun `패의 총합이 플레이어가 큰 경우 플레이어가 승리한다`() {
        // Given
        val playerCards =
            listOf(
                Card(CardNumber.JACK, CardPattern.HEART),
                Card(CardNumber.JACK, CardPattern.HEART),
            )
        val player = Player("test", playerCards)

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
        val player = Player("test", playerCards)

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
        val player = Player("test", playerCards)

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
        val player = Player("test", playerCards)

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
        val player = Player("test", playerCards)

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
        val blackjackDealer = Dealer(Deck.createDefaultDeck(), blackjackCards)
        val player = Player("test", blackjackCards)

        // When
        val result = GameResult.create(blackjackDealer, listOf(player))

        // Then
        result.winStatus[player] shouldBe ResultState.BLACKJACK_WIN
    }

    @Test
    fun `승리한 경우 베팅 금액만큼 받는다`() {
        // Given
        val player = Player("test")
        val bettingAmount = BettingAmount(10000)
        val bettingInfo = BettingInfo(player, bettingAmount)
        val gameResult = GameResult(mapOf(player to ResultState.WIN))

        // When
        val profit = gameResult.calculateProfits(listOf(bettingInfo))

        // Then
        profit[player]?.value shouldBe 10000
    }

    @Test
    fun `블랙잭으로 승리한 경우 베팅 금액의 1․5배만큼 받는다`() {
        // Given
        val player = Player("test")
        val bettingAmount = BettingAmount(10000)
        val bettingInfo = BettingInfo(player, bettingAmount)
        val gameResult = GameResult(mapOf(player to ResultState.BLACKJACK_WIN))

        // When
        val profit = gameResult.calculateProfits(listOf(bettingInfo))

        // Then
        profit[player]?.value shouldBe 15000
    }

    @Test
    fun `패배한 경우 베팅 금액만큼 잃는다`() {
        // Given
        val player = Player("test")
        val bettingAmount = BettingAmount(10000)
        val bettingInfo = BettingInfo(player, bettingAmount)
        val gameResult = GameResult(mapOf(player to ResultState.LOSE))

        // When
        val profit = gameResult.calculateProfits(listOf(bettingInfo))

        // Then
        profit[player]?.value shouldBe -10000
    }

    @Test
    fun `무승부인 경우 0원을 받는다`() {
        // Given
        val player = Player("test")
        val bettingAmount = BettingAmount(10000)
        val bettingInfo = BettingInfo(player, bettingAmount)
        val gameResult = GameResult(mapOf(player to ResultState.DRAW))

        // When
        val profit = gameResult.calculateProfits(listOf(bettingInfo))

        // Then
        profit[player]?.value shouldBe 0
    }
}
