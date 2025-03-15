package blackjack

import blackjack.CardFixture.Companion.CLOVER_ACE
import blackjack.CardFixture.Companion.CLOVER_JACK
import blackjack.CardFixture.Companion.DIAMOND_ACE
import blackjack.CardFixture.Companion.HEART_ACE
import blackjack.CardFixture.Companion.HEART_JACK
import blackjack.CardFixture.Companion.HEART_KING
import blackjack.CardFixture.Companion.HEART_QUEEN
import blackjack.model.Money
import blackjack.model.ProfitCalculator
import blackjack.model.card.Card
import blackjack.model.user.Dealer
import blackjack.model.user.Participant
import blackjack.model.user.Player
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ProfitCalculatorTest {
    private lateinit var player: Player
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        player = Player("플레이어", Money(1_000L))
        dealer = Dealer()
    }

    @Test
    fun `플레이어가 Bust 일 경우 원금을 잃는다`() {
        player.drawCards(CLOVER_JACK, HEART_QUEEN, HEART_KING)
        val expected = Money(-1_000L)

        val actual = ProfitCalculator.calculateProfit(dealer, player)

        assertEquals(expected, actual)
    }

    @Test
    fun `플레이어만 Blackjack 이면 원금의 150%를 얻는다`() {
        player.drawCards(CLOVER_JACK, CLOVER_ACE)
        val expected = Money(1_500L)

        val actual = ProfitCalculator.calculateProfit(dealer, player)

        assertEquals(expected, actual)
    }

    @Test
    fun `플레이어와 딜러가 Blackjack 이면 0원을 얻는다`() {
        player.drawCards(CLOVER_JACK, CLOVER_ACE)
        dealer.drawCards(HEART_QUEEN, HEART_ACE)
        val expected = Money(0L)

        val actual = ProfitCalculator.calculateProfit(dealer, player)

        assertEquals(expected, actual)
    }

    @Test
    fun `딜러가 bust 이고 player가 bust가 아니면 원금을 얻는다`() {
        player.drawCards(CLOVER_ACE, DIAMOND_ACE, HEART_ACE)
        dealer.drawCards(HEART_QUEEN, HEART_KING, HEART_JACK)
        val expected = Money(1_000L)

        val actual = ProfitCalculator.calculateProfit(dealer, player)

        assertEquals(expected, actual)
    }

    @Test
    fun `딜러와 Player 모두 bust, blackjack이 아니면 21에 가까운 점수를 기준으로 금액을 반환한다`() {
        player.drawCards(CLOVER_ACE, DIAMOND_ACE)
        dealer.drawCards(HEART_KING, HEART_JACK)
        val expected = Money(-1_000L)

        val actual = ProfitCalculator.calculateProfit(dealer, player)

        assertEquals(expected, actual)
    }

    private fun Participant.drawCards(vararg cards: Card) {
        cards.forEach { card -> this.addCard(card) }
    }
}
