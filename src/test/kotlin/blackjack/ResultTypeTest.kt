package blackjack

import blackjack.model.BetMoney
import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.Shape
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
        player = Player("Mr.플레이어", BetMoney(1_000L))
    }

    @Test
    fun `플레이어 카드 합이 딜러의 카드 합보다 작으면 LOSS를 반환한다`() {
        dealer.addCard(Card(Shape.CLOVER, CardNumber.NINE))
        dealer.addCard(Card(Shape.CLOVER, CardNumber.EIGHT))
        player.addCard(Card(Shape.HEART, CardNumber.EIGHT))
        player.addCard(Card(Shape.HEART, CardNumber.SEVEN))
        val expected = ResultType.LOSE

        val actual = ResultType.judgeForPlayer(player, dealer)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `플레이어와 딜러 모두 블랙잭인 경우 TIE를 반환한다`() {
        dealer.addCard(Card(Shape.CLOVER, CardNumber.ACE))
        dealer.addCard(Card(Shape.CLOVER, CardNumber.JACK))
        player.addCard(Card(Shape.HEART, CardNumber.ACE))
        player.addCard(Card(Shape.HEART, CardNumber.QUEEN))
        val expected = ResultType.TIE

        val actual = ResultType.judgeForPlayer(player, dealer)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `플레이어와 딜러 모두 버스트된 경우 딜러는 WIN을, 플레이어는 LOSE를 반환한다`() {
        dealer.addCard(Card(Shape.CLOVER, CardNumber.JACK))
        dealer.addCard(Card(Shape.CLOVER, CardNumber.QUEEN))
        dealer.addCard(Card(Shape.CLOVER, CardNumber.KING))
        player.addCard(Card(Shape.HEART, CardNumber.JACK))
        player.addCard(Card(Shape.HEART, CardNumber.QUEEN))
        player.addCard(Card(Shape.HEART, CardNumber.KING))
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
