package blackjack.domain.person

import blackjack.domain.BetAmount
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Deck
import blackjack.domain.generateCustomDeck
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerBetInfoTest {
    private lateinit var playerBetInfo: PlayerBetInfo
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        playerBetInfo = PlayerBetInfo(Player("player"), BetAmount(100))
        dealer = Dealer()
    }

    private fun setupGame(
        playerCards: List<CardNumber>,
        dealerCards: List<CardNumber>,
    ) {
        val customDeck = generateCustomDeck(playerCards + dealerCards)
        drawCards(playerBetInfo.player, playerCards, customDeck)
        drawCards(dealer, dealerCards, customDeck)
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
        setupGame(listOf(CardNumber.ACE, CardNumber.JACK), emptyList())

        playerBetInfo.calculatePlayerPayout(dealer).value shouldBe 150.0
    }

    @Test
    fun `플레이어는 100원 배팅 후 승리하면 100원을 받는다`() {
        setupGame(listOf(CardNumber.JACK), emptyList())

        playerBetInfo.calculatePlayerPayout(dealer).value shouldBe 100.0
    }

    @Test
    fun `플레이어는 100원 배팅 후 패배하면 100원을 잃는다`() {
        val gameResult = setupGame(emptyList(), listOf(CardNumber.JACK))

        playerBetInfo.calculatePlayerPayout(dealer).value shouldBe -100.0
    }

    @Test
    fun `플레이어는 100원 배팅 후 딜러와 플레이어가 모두 블랙잭이면 배팅금을 돌려받는다`() {
        setupGame(
            listOf(CardNumber.JACK, CardNumber.ACE),
            listOf(CardNumber.JACK, CardNumber.ACE),
        )

        playerBetInfo.calculatePlayerPayout(dealer).value shouldBe 0.0
    }

    @Test
    fun `무승부 시 배팅금을 돌려받는다`() {
        setupGame(listOf(CardNumber.JACK), listOf(CardNumber.JACK))

        playerBetInfo.calculatePlayerPayout(dealer).value shouldBe 0.0
    }

    @Test
    fun `플레이어는 100원 배팅 후 플레이어가 블랙잭이고 딜러가 블랙잭이 아닌 21점이면 플레이어는 150원을 받는다`() {
        val playerCards = listOf(CardNumber.JACK, CardNumber.ACE)
        val dealerCards = listOf(CardNumber.JACK, CardNumber.SIX, CardNumber.FIVE)

        setupGame(playerCards, dealerCards)

        playerBetInfo.calculatePlayerPayout(dealer).value shouldBe 150.0
    }
}
