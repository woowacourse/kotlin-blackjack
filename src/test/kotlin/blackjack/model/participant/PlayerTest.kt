package blackjack.model.participant

import blackjack.model.ResultCalculator
import blackjack.model.ResultType
import blackjack.model.amount.BetAmount
import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        player = Player("미플", BetAmount(1))
    }

    @Test
    fun `플레이어는 이름을 가진다`() {
        assertThat(player.name).isEqualTo("미플")
    }

    @Test
    fun `플레이어가 처음 공개하는 카드는 2장이다`() {
        player.addCard(Card(Shape.SPADE, CardNumber.NINE))
        player.addCard(Card(Shape.CLOVER, CardNumber.QUEEN))
        val expect = 2

        val actual = player.getInitialCard().size

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `플레이어 카드 합이 딜러의 카드 합보다 작으면 진다`() {
        val dealer = Dealer()
        dealer.addCard(Card(Shape.CLOVER, CardNumber.NINE))
        dealer.addCard(Card(Shape.CLOVER, CardNumber.EIGHT))
        player.addCard(Card(Shape.HEART, CardNumber.EIGHT))
        player.addCard(Card(Shape.HEART, CardNumber.SEVEN))
        val expect = ResultType.LOSS

        val actual = ResultCalculator.judgeScore(dealer, player)

        assertThat(actual).isEqualTo(expect)
    }
}
