package blackjack

import blackjack.CardFixture.Companion.CLOVER_ACE
import blackjack.CardFixture.Companion.CLOVER_EIGHT
import blackjack.CardFixture.Companion.CLOVER_JACK
import blackjack.CardFixture.Companion.CLOVER_KING
import blackjack.CardFixture.Companion.CLOVER_QUEEN
import blackjack.CardFixture.Companion.HEART_ACE
import blackjack.CardFixture.Companion.HEART_EIGHT
import blackjack.CardFixture.Companion.HEART_JACK
import blackjack.CardFixture.Companion.HEART_KING
import blackjack.CardFixture.Companion.HEART_NINE
import blackjack.CardFixture.Companion.HEART_QUEEN
import blackjack.CardFixture.Companion.HEART_SEVEN
import blackjack.model.Money
import blackjack.model.state.ResultType
import blackjack.model.user.Dealer
import blackjack.model.user.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class ResultTypeTest {
    private lateinit var dealer: Dealer
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        dealer = Dealer()
        player = Player("Mr.플레이어", Money(1_000L))
    }

    @Test
    fun `플레이어 카드 합이 딜러의 카드 합보다 작으면 LOSS를 반환한다`() {
        dealer.addCard(HEART_NINE)
        dealer.addCard(HEART_EIGHT)
        player.addCard(CLOVER_EIGHT)
        player.addCard(HEART_SEVEN)
        val expected = ResultType.LOSE

        val actual = ResultType.judgeForPlayer(player, dealer)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `플레이어와 딜러 모두 블랙잭인 경우 TIE를 반환한다`() {
        dealer.addCard(HEART_ACE)
        dealer.addCard(HEART_JACK)
        player.addCard(CLOVER_ACE)
        player.addCard(HEART_QUEEN)
        val expected = ResultType.TIE

        val actual = ResultType.judgeForPlayer(player, dealer)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `플레이어와 딜러 모두 버스트된 경우 딜러는 WIN을, 플레이어는 LOSE를 반환한다`() {
        dealer.addCard(HEART_JACK)
        dealer.addCard(HEART_QUEEN)
        dealer.addCard(HEART_KING)
        player.addCard(CLOVER_JACK)
        player.addCard(CLOVER_QUEEN)
        player.addCard(CLOVER_KING)
        val dealerExpected = ResultType.WIN
        val playerExpected = ResultType.LOSE

        val dealerActual = ResultType.judgeForDealer(dealer, player)
        val playerActual = ResultType.judgeForPlayer(player, dealer)

        assertAll(
            { assertThat(dealerActual).isEqualTo(dealerExpected) },
            { assertThat(playerActual).isEqualTo(playerExpected) },
        )
    }
}
