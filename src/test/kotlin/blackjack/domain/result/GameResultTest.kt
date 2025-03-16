package blackjack.domain.result

import blackjack.domain.BetAmount
import blackjack.domain.card.CardNumber
import blackjack.domain.generateCustomDeck
import blackjack.domain.person.Dealer
import blackjack.domain.person.Person
import blackjack.domain.person.Player
import blackjack.domain.person.PlayerBetInfo
import io.kotest.matchers.doubles.shouldBeExactly
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameResultTest {
    private lateinit var playerBetInfo: PlayerBetInfo
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        playerBetInfo = PlayerBetInfo(Player("player"), BetAmount(100))
        dealer = Dealer()
    }

    private fun drawCards(
        target: Person,
        cards: List<CardNumber>,
    ) {
        val deck = generateCustomDeck(cards)
        repeat(cards.size) { target.draw(deck) }
    }

    @Test
    fun `딜러는 플레이어의 손실만큼 수익을 받는다`() {
        val players = listOf(PlayerBetInfo(Player("player1"), BetAmount(200)), PlayerBetInfo(Player("player2"), BetAmount(300)))
        val customCards = listOf(CardNumber.JACK, CardNumber.JACK)
        drawCards(dealer, customCards)

        val gameResult = GameResult(dealer, players)
        gameResult.dealerProfit.value shouldBeExactly 500.0
    }

    @Test
    fun `총 플레이어 수익이 0인 경우 딜러 수익금 계산 처리 테스트`() {
        val gameResult = GameResult(dealer, listOf(playerBetInfo))
        gameResult.dealerProfit.value shouldBeExactly 0.0
    }
}
