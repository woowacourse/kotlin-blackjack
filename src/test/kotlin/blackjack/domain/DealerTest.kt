package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Suit
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.domain.result.GameResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DealerTest {
    private lateinit var dealer: Dealer

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
            addCard(Card(CardNumber.JACK, Suit.SPADE))
            addCard(Card(CardNumber.QUEEN, Suit.SPADE))
        }

        assertThat(dealer.getFirstOpenCards().size).isEqualTo(1)
    }

    @Test
    fun `딜러는 자신이 처음 공개할 카드를 반환한다`() {
        dealer.addCard(Card(CardNumber.ACE, Suit.SPADE))
        dealer.addCard(Card(CardNumber.FIVE, Suit.SPADE))

        assertThat(dealer.getFirstOpenCards()).isEqualTo(listOf(Card(CardNumber.ACE, Suit.SPADE)))
    }

    @Test
    fun `딜러는 카드의 합이 16점 이하면 카드를 뽑을 수 있다`() {
        dealer.addCard(Card(CardNumber.ACE, Suit.SPADE))
        dealer.addCard(Card(CardNumber.FIVE, Suit.SPADE))

        assertThat(dealer.canDraw()).isTrue
    }

    @Test
    fun `딜러는 카드의 합이 17점 이상이면 더 이상 카드를 뽑을 수 없다`() {
        dealer.addCard(Card(CardNumber.ACE, Suit.SPADE))
        dealer.addCard(Card(CardNumber.SIX, Suit.SPADE))

        assertThat(dealer.canDraw()).isFalse
    }

    @Test
    fun `딜러는 카드 목록에 카드를 추가한다`() {
        dealer.addCard(Card(CardNumber.TWO, Suit.SPADE))

        assertThat(dealer.getCards()).containsExactly(Card(CardNumber.TWO, Suit.SPADE))
    }

    @Test
    fun `딜러가 보유한 카드를 반환한다`() {
        dealer.addCard(Card(CardNumber.ACE, Suit.SPADE))
        dealer.addCard(Card(CardNumber.JACK, Suit.SPADE))

        assertThat(dealer.getCards()).isEqualTo(
            listOf(
                Card(CardNumber.ACE, Suit.SPADE),
                Card(CardNumber.JACK, Suit.SPADE)
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
        dealer.addCard(Card(firstCardNumber, firstCardSuit))
        dealer.addCard(Card(secondCardNumber, secondCardSuit))

        assertThat(dealer.getTotalScore()).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "JACK, DIAMOND, QUEEN, DIAMOND, TWO, DIAMOND",
        "JACK, DIAMOND, SIX, DIAMOND, TWO, DIAMOND",
    )
    fun `플레이어가 21점을 초과하면 딜러가 어느 상황이던 승리한다`(
        firstCardNumber: CardNumber,
        firstCardSuit: Suit,
        secondCardNumber: CardNumber,
        secondCardSuit: Suit,
        thirdCardNumber: CardNumber,
        thirdCardSuit: Suit,
    ) {
        val dealer = Dealer().apply {
            addCard(Card(firstCardNumber, firstCardSuit))
            addCard(Card(secondCardNumber, secondCardSuit))
            addCard(Card(thirdCardNumber, thirdCardSuit))
        }
        val player = Player("반달").apply {
            addCard(Card(CardNumber.JACK, Suit.SPADE))
            addCard(Card(CardNumber.QUEEN, Suit.SPADE))
            addCard(Card(CardNumber.TWO, Suit.SPADE))
        }

        assertThat(dealer judge player).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `딜러와 플레이어가 21점을 초과하지 않고 점수가 동일하면 무승부이다`() {
        val dealer = Dealer().apply {
            addCard(Card(CardNumber.JACK, Suit.DIAMOND))
            addCard(Card(CardNumber.QUEEN, Suit.DIAMOND))
        }
        val player = Player("반달").apply {
            addCard(Card(CardNumber.JACK, Suit.SPADE))
            addCard(Card(CardNumber.QUEEN, Suit.SPADE))
        }

        assertThat(dealer judge player).isEqualTo(GameResult.DRAW)
    }

    @Test
    fun `딜러와 플레이어가 21점을 초과하지 않고 딜러의 점수가 높으면 딜러가 승리한다`() {
        val dealer = Dealer().apply {
            addCard(Card(CardNumber.JACK, Suit.DIAMOND))
            addCard(Card(CardNumber.QUEEN, Suit.SPADE))
        }
        val player = Player("반달").apply {
            addCard(Card(CardNumber.JACK, Suit.SPADE))
            addCard(Card(CardNumber.NINE, Suit.SPADE))
        }

        assertThat(dealer judge player).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `딜러와 플레이어가 21점을 초과하지 않고 플레이어의 점수가 더 높으면 딜러가 패배한다`() {
        val dealer = Dealer().apply {
            addCard(Card(CardNumber.JACK, Suit.DIAMOND))
            addCard(Card(CardNumber.NINE, Suit.DIAMOND))
        }
        val player = Player("반달").apply {
            addCard(Card(CardNumber.JACK, Suit.SPADE))
            addCard(Card(CardNumber.QUEEN, Suit.SPADE))
        }

        assertThat(dealer judge player).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `딜러의 처음 받은 카드가 두장이고 그 합이 21점이면 블랙잭이다`() {
        val dealer = Dealer().apply {
            addCard(Card(CardNumber.JACK, Suit.SPADE))
            addCard(Card(CardNumber.ACE, Suit.SPADE))
        }
        val player = Player("반달").apply {
            addCard(Card(CardNumber.JACK, Suit.HEART))
            addCard(Card(CardNumber.NINE, Suit.HEART))
        }

        assertThat(dealer judge player).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `딜러가 블랙잭이고 플레이어도 블랙잭이라면 무승부다`() {
        val dealer = Dealer().apply {
            addCard(Card(CardNumber.QUEEN, Suit.HEART))
            addCard(Card(CardNumber.ACE, Suit.HEART))
        }
        val player = Player("반달").apply {
            addCard(Card(CardNumber.JACK, Suit.SPADE))
            addCard(Card(CardNumber.ACE, Suit.SPADE))
        }

        assertThat(dealer judge player).isEqualTo(GameResult.DRAW)
    }
}
