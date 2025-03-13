package blackjack.domain

import blackjack.domain.card.CardNumber
import blackjack.domain.card.Deck
import blackjack.domain.person.Dealer
import blackjack.domain.person.Person
import blackjack.domain.person.Player
import blackjack.domain.result.GameResult
import io.kotest.matchers.doubles.shouldBeExactly
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameResultTest {
    private lateinit var player: Player
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        player = Player("player", BetAmount(100))
        dealer = Dealer()
    }

    private fun setupGame(
        playerCards: List<CardNumber>,
        dealerCards: List<CardNumber>,
    ): GameResult {
        val customDeck = generateCustomDeck(playerCards + dealerCards)
        drawCards(player, playerCards, customDeck)
        drawCards(dealer, dealerCards, customDeck)
        return GameResult(dealer, listOf(player))
    }

    private fun drawCards(
        person: Person,
        cards: List<CardNumber>,
        deck: Deck,
    ) {
        repeat(cards.size) { person.draw(deck) }
    }

    @Test
    fun `플레이어는 100원 배팅 후 블랙잭으로 승리하면 150원을 받는다`() {
        val gameResult = setupGame(listOf(CardNumber.ACE, CardNumber.JACK), emptyList())
        gameResult.playerPayouts.values.first() shouldBeExactly 150.0
    }

    @Test
    fun `플레이어는 100원 배팅 후 승리하면 100원을 받는다`() {
        val gameResult = setupGame(listOf(CardNumber.JACK), emptyList())
        gameResult.playerPayouts.values.first() shouldBeExactly 100.0
    }

    @Test
    fun `플레이어는 100원 배팅 후 패배하면 100원을 잃는다`() {
        val gameResult = setupGame(emptyList(), listOf(CardNumber.JACK))
        gameResult.playerPayouts.values.first() shouldBeExactly -100.0
    }

    @Test
    fun `플레이어는 100원 배팅 후 딜러와 플레이어가 모두 블랙잭이면 배팅금을 돌려받는다`() {
        val gameResult =
            setupGame(
                listOf(CardNumber.JACK, CardNumber.ACE),
                listOf(CardNumber.JACK, CardNumber.ACE),
            )
        gameResult.playerPayouts.values.first() shouldBeExactly 0.0
    }

    @Test
    fun `무승부 시 배팅금을 돌려받는다`() {
        val gameResult = setupGame(listOf(CardNumber.JACK), listOf(CardNumber.JACK))
        gameResult.playerPayouts.values.first() shouldBeExactly 0.0
        gameResult.dealerProfit shouldBeExactly 0.0
    }

    @Test
    fun `딜러는 플레이어의 손실만큼 수익을 받는다`() {
        val players = listOf(Player("player1", BetAmount(200)), Player("player2", BetAmount(300)))
        val customCards = listOf(CardNumber.JACK, CardNumber.JACK)
        val customDeck = generateCustomDeck(customCards)
        drawCards(dealer, customCards, customDeck)

        val gameResult = GameResult(dealer, players)
        gameResult.dealerProfit shouldBeExactly 500.0
    }

    @Test
    fun `플레이어는 100원 배팅 후 플레이어가 블랙잭이고 딜러가 블랙잭이 아닌 21점이면 플레이어는 150원을 받는다`() {
        val playerCards = listOf(CardNumber.JACK, CardNumber.ACE)
        val dealerCards = listOf(CardNumber.JACK, CardNumber.SIX, CardNumber.FIVE)

        val gameResult = setupGame(playerCards, dealerCards)

        gameResult.playerPayouts.values.first() shouldBeExactly 150.0
        gameResult.dealerProfit shouldBeExactly -150.0
    }
}
