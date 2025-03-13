package blackjack.model

import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.Shape
import blackjack.model.participant.Dealer
import blackjack.model.participant.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ResultCalculatorTest {
    private lateinit var player: Player
    private lateinit var dealer: Dealer

    private fun fakeCards(vararg cards: Card): List<Card> = cards.map { it }

    @BeforeEach
    fun setUp() {
        player = Player("미플", BetAmount(0))
        dealer = Dealer()
    }

    @Test
    fun `ACE를 11로 계산할 수 있을 때 11로 처리한다`() {
        val cards =
            fakeCards(
                Card(Shape.SPADE, CardNumber.TWO),
                Card(Shape.CLOVER, CardNumber.THREE),
                Card(Shape.DIAMOND, CardNumber.ACE),
            )
        val expect = 16

        val actual = ResultCalculator.calculate(cards)

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `ACE를 11로 계산할 수 없을 때 1로 처리한다`() {
        val cards =
            fakeCards(
                Card(Shape.SPADE, CardNumber.TEN),
                Card(Shape.CLOVER, CardNumber.TEN),
                Card(Shape.DIAMOND, CardNumber.ACE),
            )
        val expect = 21

        val actual = ResultCalculator.calculate(cards)

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `플레이어가 버스트되면 무조건 진다`() {
        player.addCard(Card(Shape.SPADE, CardNumber.TEN))
        player.addCard(Card(Shape.SPADE, CardNumber.TEN))
        player.addCard(Card(Shape.SPADE, CardNumber.TEN))
        val expect = ResultType.LOSS

        val actual = ResultCalculator.judgeScore(dealer, player)

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `플레이어가 버스트되지 않고 딜러가 버스트되면 이긴다`() {
        player.addCard(Card(Shape.SPADE, CardNumber.TEN))
        player.addCard(Card(Shape.SPADE, CardNumber.TEN))
        dealer.addCard(Card(Shape.SPADE, CardNumber.TEN))
        dealer.addCard(Card(Shape.SPADE, CardNumber.TEN))
        dealer.addCard(Card(Shape.SPADE, CardNumber.TEN))
        val expect = ResultType.WIN

        val actual = ResultCalculator.judgeScore(dealer, player)

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `플레이어의 score가 딜러의 score보다 작으면 진다`() {
        player.addCard(Card(Shape.SPADE, CardNumber.NINE))
        dealer.addCard(Card(Shape.SPADE, CardNumber.TEN))
        val expect = ResultType.LOSS

        val actual = ResultCalculator.judgeScore(dealer, player)

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `플레이어의 score가 딜러의 score보다 크면 이긴다`() {
        player.addCard(Card(Shape.SPADE, CardNumber.NINE))
        dealer.addCard(Card(Shape.SPADE, CardNumber.THREE))
        val expect = ResultType.WIN

        val actual = ResultCalculator.judgeScore(dealer, player)

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `플레이어의 score가 딜러의 score와 같으면 비긴다`() {
        player.addCard(Card(Shape.SPADE, CardNumber.NINE))
        dealer.addCard(Card(Shape.SPADE, CardNumber.NINE))
        val expect = ResultType.TIE

        val actual = ResultCalculator.judgeScore(dealer, player)

        assertThat(actual).isEqualTo(expect)
    }
}
