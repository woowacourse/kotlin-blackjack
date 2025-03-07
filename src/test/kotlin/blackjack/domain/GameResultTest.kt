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
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        dealer = Dealer(createHand(CardNumber.JACK, CardNumber.EIGHT))
    }

    @Test
    fun `플레이어가 이기는 경우`() {
        val player = createPlayer(CardNumber.JACK, CardNumber.JACK)
        val result = GameResult(dealer, listOf(player))
        result.winStatus[player] shouldBe ResultState.WIN
    }

    @Test
    fun `플레이어가 지는 경우`() {
        val player = createPlayer(CardNumber.FOUR, CardNumber.EIGHT)
        val result = GameResult(dealer, listOf(player))
        result.winStatus[player] shouldBe ResultState.LOSE
    }

    @Test
    fun `플레이어가 비기는 경우`() {
        val player = createPlayer(CardNumber.JACK, CardNumber.EIGHT)
        val result = GameResult(dealer, listOf(player))
        result.winStatus[player] shouldBe ResultState.DRAW
    }

    @Test
    fun `플레이어가 버스트되는 경우`() {
        val player = createPlayer(CardNumber.JACK, CardNumber.JACK, CardNumber.JACK)
        val result = GameResult(dealer, listOf(player))
        result.winStatus[player] shouldBe ResultState.LOSE
    }

    @Test
    fun `승패 여부를 계산할 수 있다`() {
        val player = createPlayer(CardNumber.JACK, CardNumber.JACK)
        val result = GameResult(dealer, listOf(player))

        result.countByResultState().let {
            (it[ResultState.LOSE] ?: 0) shouldBe 0
            (it[ResultState.DRAW] ?: 0) shouldBe 0
            (it[ResultState.WIN] ?: 0) shouldBe 1
        }
    }

    private fun createHand(vararg numbers: CardNumber): Hand {
        return Hand().apply {
            numbers.forEach { addCard(Card.create(it, CardPattern.HEART)) }
        }
    }

    private fun createPlayer(vararg numbers: CardNumber): Player {
        return Player("test", createHand(*numbers))
    }
}
