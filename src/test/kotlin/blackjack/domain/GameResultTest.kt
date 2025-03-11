package blackjack.domain

import blackjack.domain.card.CardNumber
import blackjack.domain.person.Dealer
import blackjack.domain.person.Player
import blackjack.domain.state.ResultState
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameResultTest {
    private lateinit var dealer: Dealer
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        dealer = Dealer()
        player = Player("player")
    }

    private fun drawCardsToPlayer(cards: List<CardNumber>) {
        val deck = generateCustomDeck(cards)
        repeat(cards.size) { player.draw(deck) }
    }

    private fun drawCardsToDealer(cards: List<CardNumber>) {
        val deck = generateCustomDeck(cards)
        repeat(cards.size) { dealer.draw(deck) }
    }

    @Test
    fun `플레이어가 20점, 딜러가 0점인 경우 WIN을 반환한다`() {
        val customCards = listOf(CardNumber.JACK, CardNumber.JACK)
        drawCardsToPlayer(customCards)

        val result = GameResult(dealer, listOf(player))

        result.winStatus[player] shouldBe ResultState.WIN
    }

    @Test
    fun `플레이어가 0점, 딜러가 12점인 경우 LOSE를 반환한다`() {
        val customCards = listOf(CardNumber.FOUR, CardNumber.EIGHT)
        drawCardsToDealer(customCards)

        val result = GameResult(dealer, listOf(player))

        result.winStatus[player] shouldBe ResultState.LOSE
    }

    @Test
    fun `플레이어가 18점, 딜러가 18점인 경우 DRAW를 반환한다`() {
        val customCards = listOf(CardNumber.JACK, CardNumber.EIGHT)
        drawCardsToPlayer(customCards)
        drawCardsToDealer(customCards)

        val result = GameResult(dealer, listOf(player))

        result.winStatus[player] shouldBe ResultState.DRAW
    }

    @Test
    fun `플레이어가 버스트되는 경우 LOSE를 반환한다`() {
        val customCards = listOf(CardNumber.JACK, CardNumber.JACK, CardNumber.JACK)
        drawCardsToPlayer(customCards)

        val result = GameResult(dealer, listOf(player))

        result.winStatus[player] shouldBe ResultState.LOSE
    }

    @Test
    fun `딜러가 버스트되고, 플레이어는 버스트가 아닌 경우`() {
        val playerCustomCards = listOf(CardNumber.EIGHT, CardNumber.EIGHT)
        drawCardsToPlayer(playerCustomCards)
        val dealerCustomCards = listOf(CardNumber.JACK, CardNumber.JACK, CardNumber.JACK)
        drawCardsToDealer(dealerCustomCards)

        val result = GameResult(dealer, listOf(player))

        result.winStatus[player] shouldBe ResultState.WIN
    }

    @Test
    fun `승패 여부를 계산할 수 있다`() {
        val playerCustomCards = listOf(CardNumber.JACK, CardNumber.JACK)
        drawCardsToPlayer(playerCustomCards)

        val result = GameResult(dealer, listOf(player))

        result.countByResultState().let {
            (it[ResultState.LOSE] ?: 0) shouldBe 0
            (it[ResultState.DRAW] ?: 0) shouldBe 0
            (it[ResultState.WIN] ?: 0) shouldBe 1
        }
    }
}
