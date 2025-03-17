package blackjack.domain.state

import blackjack.domain.card.CardNumber
import blackjack.domain.generateCustomDeck
import blackjack.domain.person.Dealer
import blackjack.domain.person.Person
import blackjack.domain.person.Player
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ResultStateTest {
    private lateinit var player: Player
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        player = Player("player")
        dealer = Dealer()
    }

    private fun drawCards(
        target: Person,
        cardNumbers: List<CardNumber>,
    ) {
        val customDeck = generateCustomDeck(cardNumbers)
        repeat(cardNumbers.size) { target.draw(customDeck) }
    }

    @Test
    fun `플레이어가 버스트인 경우 플레이어는 패배한다`() {
        drawCards(player, listOf(CardNumber.JACK, CardNumber.JACK, CardNumber.JACK))
        ResultState.calculateWin(player, dealer) shouldBe ResultState.LOSE
    }

    @Test
    fun `플레이어는 버스트가 아니고 딜러는 버스트인 경우 플레이어는 승리한다`() {
        drawCards(dealer, listOf(CardNumber.JACK, CardNumber.JACK, CardNumber.JACK))
        ResultState.calculateWin(player, dealer) shouldBe ResultState.WIN
    }

    @Test
    fun `플레이어의 스코어가 딜러의 스코어보다 큰 경우 플레이어는 승리한다`() {
        drawCards(player, listOf(CardNumber.JACK, CardNumber.TWO))
        drawCards(dealer, listOf(CardNumber.TWO))
        ResultState.calculateWin(player, dealer) shouldBe ResultState.WIN
    }

    @Test
    fun `플레이어의 스코어가 딜러의 스코어보다 작은 경우 플레이어는 패배한다`() {
        drawCards(dealer, listOf(CardNumber.JACK, CardNumber.TWO))
        drawCards(player, listOf(CardNumber.TWO))
        ResultState.calculateWin(player, dealer) shouldBe ResultState.LOSE
    }

    @Test
    fun `플레이어와 딜러의 스코어가 같은 경우 무승부한다`() {
        drawCards(player, listOf(CardNumber.JACK))
        drawCards(dealer, listOf(CardNumber.JACK))
        ResultState.calculateWin(player, dealer) shouldBe ResultState.DRAW
    }

    @Test
    fun `딜러와 플레이어 모두 버스트가 아닐 때 플레이어만 블랙잭이라면 플레이어는 승리한다`() {
        drawCards(dealer, listOf(CardNumber.JACK, CardNumber.SIX, CardNumber.FIVE))
        drawCards(player, listOf(CardNumber.JACK, CardNumber.ACE))
        ResultState.calculateWin(player, dealer) shouldBe ResultState.WIN
    }
}
