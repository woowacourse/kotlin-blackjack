package blackjack

import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.model.ResultType
import blackjack.model.card.Card
import blackjack.model.card.Number
import blackjack.model.card.Shape
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
        player = Player("Mr.플레이어")
    }

    @Test
    fun `플레이어 카드 합이 딜러의 카드 합보다 작으면 LOSS를 반환한다`() {
        dealer.addCard(Card(Shape.CLOVER, Number.NINE))
        dealer.addCard(Card(Shape.CLOVER, Number.EIGHT))
        player.addCard(Card(Shape.HEART, Number.EIGHT))
        player.addCard(Card(Shape.HEART, Number.SEVEN))
        val expected = ResultType.LOSE

        val actual = ResultType.judgeForPlayer(player, dealer)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `플레이어와 딜러 모두 블랙잭인 경우 TIE를 반환한다`() {
        dealer.addCard(Card(Shape.CLOVER, Number.ACE))
        dealer.addCard(Card(Shape.CLOVER, Number.JACK))
        player.addCard(Card(Shape.HEART, Number.ACE))
        player.addCard(Card(Shape.HEART, Number.QUEEN))
        val expected = ResultType.TIE

        val actual = ResultType.judgeForPlayer(player, dealer)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `플레이어와 딜러 모두 버스트된 경우 딜러는 WIN을, 플레이어는 LOSE를 반환한다`() {
        dealer.addCard(Card(Shape.CLOVER, Number.JACK))
        dealer.addCard(Card(Shape.CLOVER, Number.QUEEN))
        dealer.addCard(Card(Shape.CLOVER, Number.KING))
        player.addCard(Card(Shape.HEART, Number.JACK))
        player.addCard(Card(Shape.HEART, Number.QUEEN))
        player.addCard(Card(Shape.HEART, Number.KING))
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
