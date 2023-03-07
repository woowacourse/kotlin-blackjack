package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DealerTest {
    lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        dealer = Dealer()
    }

    @Test
    fun `딜러는 이름을 갖는다`() {
        assertThat(dealer.name).isEqualTo("딜러")
    }

    @Test
    fun `딜러가 처음 공개할 카드는 1장이다`() {
        with(dealer) {
            addCard(Card(Suit.SPADE, CardNumber.JACK))
            addCard(Card(Suit.SPADE, CardNumber.QUEEN))
        }

        assertThat(dealer.getFirstOpenCards().size).isEqualTo(1)
    }

    @Test
    fun `딜러는 자신이 처음 공개할 카드를 반환한다`() {
        dealer.addCard(Card(Suit.SPADE, CardNumber.ACE))
        dealer.addCard(Card(Suit.SPADE, CardNumber.FIVE))

        assertThat(dealer.getFirstOpenCards()).isEqualTo(listOf(Card(Suit.SPADE, CardNumber.ACE)))
    }

    @Test
    fun `딜러는 카드의 합이 16점 이하면 카드를 뽑을 수 있다`() {
        dealer.addCard(Card(Suit.SPADE, CardNumber.ACE))
        dealer.addCard(Card(Suit.SPADE, CardNumber.FIVE))

        assertThat(dealer.canDraw()).isTrue
    }

    @Test
    fun `딜러는 카드의 합이 17점 이상이면 더 이상 카드를 뽑을 수 없다`() {
        dealer.addCard(Card(Suit.SPADE, CardNumber.ACE))
        dealer.addCard(Card(Suit.SPADE, CardNumber.SIX))

        assertThat(dealer.canDraw()).isFalse
    }

    @Test
    fun `딜러는 카드 목록에 카드를 추가한다`() {
        dealer.addCard(Card(Suit.SPADE, CardNumber.TWO))

        assertThat(dealer.getCards()).containsExactly(Card(Suit.SPADE, CardNumber.TWO))
    }

    @Test
    fun `딜러가 보유한 카드를 반환한다`() {
        dealer.addCard(Card(Suit.SPADE, CardNumber.ACE))
        dealer.addCard(Card(Suit.SPADE, CardNumber.JACK))

        assertThat(dealer.getCards()).isEqualTo(
            listOf(
                Card(Suit.SPADE, CardNumber.ACE),
                Card(Suit.SPADE, CardNumber.JACK)
            )
        )
    }

    @ParameterizedTest
    @CsvSource(
        "SPADE, ACE, HEART, ACE, 12",
        "SPADE, ACE, SPADE, JACK, 21",
        "SPADE, TEN, HEART, SEVEN, 17"
    )
    fun `자신의 점수를 반환한다`(
        firstCardSuit: Suit,
        firstCardNumber: CardNumber,
        secondCardSuit: Suit,
        secondCardNumber: CardNumber,
        expected: Int
    ) {
        dealer.addCard(Card(firstCardSuit, firstCardNumber))
        dealer.addCard(Card(secondCardSuit, secondCardNumber))

        assertThat(dealer.getTotalScore()).isEqualTo(expected)
    }

    @Test
    fun `플레이어가 21점을 초과하면 패배한다`() {
        with(dealer) {
            addCard(Card(Suit.SPADE, CardNumber.JACK))
            addCard(Card(Suit.SPADE, CardNumber.QUEEN))
            addCard(Card(Suit.SPADE, CardNumber.KING))
        }

        assertThat(dealer judge 22).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `딜러만 21점을 초과하면 플레이어가 승리한다`() {
        with(dealer) {
            addCard(Card(Suit.SPADE, CardNumber.JACK))
            addCard(Card(Suit.SPADE, CardNumber.QUEEN))
            addCard(Card(Suit.SPADE, CardNumber.KING))
        }

        assertThat(dealer judge 21).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `딜러와 플레이어 모두 21점을 초과하지 않고 플레이어가 딜러보다 점수가 높으면 플레이어가 승리한다`() {
        with(dealer) {
            addCard(Card(Suit.SPADE, CardNumber.JACK))
            addCard(Card(Suit.SPADE, CardNumber.QUEEN))
        }

        assertThat(dealer judge 21).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `딜러와 플레이어 모두 21점을 초과하지 않고 플레이어와 딜러의 점수가 같으면 무승부이다`() {
        with(dealer) {
            addCard(Card(Suit.SPADE, CardNumber.ACE))
            addCard(Card(Suit.SPADE, CardNumber.QUEEN))
        }

        assertThat(dealer judge 21).isEqualTo(GameResult.DRAW)
    }

    @Test
    fun `딜러와 플레이어 모두 21점을 초과하지 않고 딜러가 플레이어보다 점수가 높으면 플레이어가 패배한다`() {
        with(dealer) {
            addCard(Card(Suit.SPADE, CardNumber.ACE))
            addCard(Card(Suit.SPADE, CardNumber.QUEEN))
        }

        assertThat(dealer judge 20).isEqualTo(GameResult.LOSE)
    }
}
