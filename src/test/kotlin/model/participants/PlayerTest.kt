package model.participants

import model.HEART_ACE
import model.HEART_JACK
import model.HEART_QUEEN
import model.HEART_SEVEN
import model.HEART_TEN
import model.HEART_TWO
import model.card.Deck
import model.createBlackJackDealer
import model.createBlackJackPlayerWithMoney
import model.createBustedDealer
import model.createBustedPlayer
import model.createBustedPlayerWithMoney
import model.createDealer
import model.createPlayer
import model.createPlayingDealer
import model.createPlayingPlayerWithMoney
import model.createTestDeck
import model.result.Profit
import model.result.ResultType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var testDeck: Deck

    @BeforeEach
    fun setUp() {
        testDeck =
            createTestDeck(
                HEART_TWO,
                HEART_JACK,
                HEART_QUEEN,
                HEART_ACE,
            )
    }

    @Test
    fun `Player에서 게임 결과를 판단할 수 있다`() {
        val player = createPlayer()
        val dealer = createDealer()

        dealer.hit(testDeck.pop())
        player.hit(testDeck.pop())

        val result = dealer.judge(player)

        assertThat(result).isEqualTo(ResultType.LOSE)
    }

    @Test
    fun `Player와 Dealer 모두 bust라면 Player의 패배`() {
        val bustedPlayer = createBustedPlayer()
        val bustedDealer = createBustedDealer()
        val result = bustedPlayer.judge(bustedDealer)

        assertThat(result).isEqualTo(ResultType.LOSE)
    }

    @Test
    fun `Player와 Dealer 모두 BlakJack이면 Profit은 0`() {
        val player = createBlackJackPlayerWithMoney(1000)
        val dealer = createBlackJackDealer()

        assertThat(player.judgeProfit(dealer)).isEqualTo(Profit(0.0))
    }

    @Test
    fun `Player만 BlackJack이면 Profit은 150%`() {
        val player = createBlackJackPlayerWithMoney(1000)
        val dealer = createPlayingDealer()

        assertThat(player.judgeProfit(dealer)).isEqualTo(Profit(1500.0))
    }

    @Test
    fun `Player가 Bust면 Profit은 -100%`() {
        val player = createBustedPlayerWithMoney(1000)
        val dealer = createPlayingDealer()

        assertThat(player.judgeProfit(dealer)).isEqualTo(-Profit(1000.0))
    }

    @Test
    fun `Dealer만 Bust면 Profit은 100%`() {
        val player = createPlayingPlayerWithMoney(money = 1000)
        val dealer = createBustedDealer()

        assertThat(player.judgeProfit(dealer)).isEqualTo(Profit(1000.0))
    }

    @Test
    fun `Dealer 점수보다 Player 점수가 높으면 Profit은 100%`() {
        val playerHand = Hand()
        playerHand.draw(HEART_TEN)
        playerHand.draw(HEART_JACK)

        val dealerHand = Hand()
        dealerHand.draw(HEART_SEVEN)
        dealerHand.draw(HEART_QUEEN)

        val player = createPlayingPlayerWithMoney(playerHand, 1000)
        val dealer = createPlayingDealer(dealerHand)

        assertThat(player.judgeProfit(dealer)).isEqualTo(Profit(1000.0))
    }

    @Test
    fun `Player 점수보다 Dealer 점수가 높으면 Profit은 -100%`() {
        val playerHand = Hand()
        playerHand.draw(HEART_SEVEN)
        playerHand.draw(HEART_QUEEN)

        val dealerHand = Hand()
        dealerHand.draw(HEART_TEN)
        dealerHand.draw(HEART_JACK)

        val player = createPlayingPlayerWithMoney(playerHand, 1000)
        val dealer = createPlayingDealer(dealerHand)

        assertThat(player.judgeProfit(dealer)).isEqualTo(Profit(-1000.0))
    }
}
