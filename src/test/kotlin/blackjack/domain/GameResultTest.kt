package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.person.Dealer
import blackjack.domain.person.Player
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameResultTest {
    private lateinit var gameResult: GameResult
    private lateinit var dealer: Dealer
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        dealer = Dealer("dealer")
        dealer.hand.addCard(
            listOf(
                Card.create(CardNumber.JACK, CardPattern.HEART),
                Card.create(CardNumber.EIGHT, CardPattern.HEART),
            ),
        )
        gameResult = GameResult(dealer)
        player = Player("player")
    }

    @Test
    fun `플레이어가 이기는 경우`() {
        player.hand.addCard(
            listOf(
                Card.create(CardNumber.JACK, CardPattern.HEART),
                Card.create(CardNumber.JACK, CardPattern.HEART),
            ),
        )

        val result = gameResult.calculateWin(listOf(player))
        result[player] shouldBe ResultState.WIN
    }

    @Test
    fun `플레이어가 지는 경우`() {
        player.hand.addCard(
            listOf(
                Card.create(CardNumber.FOUR, CardPattern.HEART),
                Card.create(CardNumber.EIGHT, CardPattern.HEART),
            ),
        )

        val result = gameResult.calculateWin(listOf(player))
        result[player] shouldBe ResultState.LOSE
    }

    @Test
    fun `플레이어가 비기는 경우`() {
        player.hand.addCard(
            listOf(
                Card.create(CardNumber.JACK, CardPattern.HEART),
                Card.create(CardNumber.EIGHT, CardPattern.HEART),
            ),
        )

        val result = gameResult.calculateWin(listOf(player))
        result[player] shouldBe ResultState.DRAW
    }

    @Test
    fun `플레이어가 버스트되는 경우`() {
        player.hand.addCard(
            listOf(
                Card.create(CardNumber.JACK, CardPattern.HEART),
                Card.create(CardNumber.JACK, CardPattern.HEART),
                Card.create(CardNumber.JACK, CardPattern.HEART),
            ),
        )

        val result = gameResult.calculateWin(listOf(player))
        result[player] shouldBe ResultState.LOSE
    }
}
