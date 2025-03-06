package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.person.Dealer
import blackjack.domain.person.Hand
import blackjack.domain.person.Player
import blackjack.domain.state.ResultState
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameResultTest {
    private lateinit var gameResult: GameResult
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        val dealerHand = Hand()
        dealerHand.addCard(Card.create(CardNumber.JACK, CardPattern.HEART))
        dealerHand.addCard(Card.create(CardNumber.EIGHT, CardPattern.HEART))
        dealer = Dealer(dealerHand)

        gameResult = GameResult(dealer)
    }

    @Test
    fun `플레이어가 이기는 경우`() {
        val playerHand = Hand()
        playerHand.addCard(Card.create(CardNumber.JACK, CardPattern.HEART))
        playerHand.addCard(Card.create(CardNumber.JACK, CardPattern.HEART))
        val player = Player("test", playerHand)

        val result = gameResult.calculateWin(listOf(player))
        result[player] shouldBe ResultState.WIN
    }

    @Test
    fun `플레이어가 지는 경우`() {
        val playerHand = Hand()
        playerHand.addCard(Card.create(CardNumber.FOUR, CardPattern.HEART))
        playerHand.addCard(Card.create(CardNumber.EIGHT, CardPattern.HEART))
        val player = Player("test", playerHand)

        val result = gameResult.calculateWin(listOf(player))
        result[player] shouldBe ResultState.LOSE
    }

    @Test
    fun `플레이어가 비기는 경우`() {
        val playerHand = Hand()
        playerHand.addCard(Card.create(CardNumber.JACK, CardPattern.HEART))
        playerHand.addCard(Card.create(CardNumber.EIGHT, CardPattern.HEART))
        val player = Player("test", playerHand)

        val result = gameResult.calculateWin(listOf(player))
        result[player] shouldBe ResultState.DRAW
    }

    @Test
    fun `플레이어가 버스트되는 경우`() {
        val playerHand = Hand()
        playerHand.addCard(Card.create(CardNumber.JACK, CardPattern.HEART))
        playerHand.addCard(Card.create(CardNumber.JACK, CardPattern.HEART))
        playerHand.addCard(Card.create(CardNumber.JACK, CardPattern.HEART))
        val player = Player("test", playerHand)

        val result = gameResult.calculateWin(listOf(player))
        result[player] shouldBe ResultState.LOSE
    }
}
