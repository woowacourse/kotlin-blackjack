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

class PlayerTest {
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        player = Player("반달")
    }

    @Test
    fun `플레이어는 이름을 갖는다`() {
        assertThat(player.name).isEqualTo("반달")
    }

    @Test
    fun `플레이어가 처음 공개할 카드는 2장이다`() {
        with(player) {
            addCard(Card(CardNumber.JACK, Suit.SPADE))
            addCard(Card(CardNumber.QUEEN, Suit.SPADE))
        }

        assertThat(player.getFirstOpenCards().size).isEqualTo(2)
    }

    @Test
    fun `플레이어는 자신이 처음 공개할 카드를 반환한다`() {
        player.addCard(Card(CardNumber.ACE, Suit.SPADE))
        player.addCard(Card(CardNumber.FIVE, Suit.SPADE))

        assertThat(player.getFirstOpenCards()).isEqualTo(
            listOf(
                Card(CardNumber.ACE, Suit.SPADE),
                Card(CardNumber.FIVE, Suit.SPADE)
            )
        )
    }

    @Test
    fun `카드의 합이 21을 초과하지 않으면 카드를 뽑을 수 있다`() {
        player.addCard(Card(CardNumber.ACE, Suit.SPADE))
        player.addCard(Card(CardNumber.KING, Suit.SPADE))

        assertThat(player.canDraw()).isTrue
    }

    @Test
    fun `카드의 합이 21을 초과하면 더 이상 카드를 뽑을 수 없다`() {
        player.addCard(Card(CardNumber.FOUR, Suit.SPADE))
        player.addCard(Card(CardNumber.EIGHT, Suit.SPADE))
        player.addCard(Card(CardNumber.KING, Suit.SPADE))

        assertThat(player.canDraw()).isFalse
    }

    @Test
    fun `플레이어는 카드 목록에 카드를 추가한다`() {
        player.addCard(Card(CardNumber.TWO, Suit.SPADE))

        assertThat(player.getCards()).containsExactly(Card(CardNumber.TWO, Suit.SPADE))
    }

    @Test
    fun `플레이어가 보유한 카드를 반환한다`() {
        player.addCard(Card(CardNumber.ACE, Suit.SPADE))
        player.addCard(Card(CardNumber.JACK, Suit.SPADE))

        assertThat(player.getCards()).isEqualTo(
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
        player.addCard(Card(firstCardNumber, firstCardSuit))
        player.addCard(Card(secondCardNumber, secondCardSuit))

        assertThat(player.getTotalScore()).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "JACK, DIAMOND, QUEEN, DIAMOND, TWO, DIAMOND",
        "JACK, DIAMOND, SIX, DIAMOND, TWO, DIAMOND",
    )
    fun `플레이어가 21점을 초과하면 딜러가 어느 상황이던 패배한다`(
        firstCardNumber: CardNumber,
        firstCardSuit: Suit,
        secondCardNumber: CardNumber,
        secondCardSuit: Suit,
        thirdCardNumber: CardNumber,
        thirdCardSuit: Suit,
    ) {
        val player = Player("반달").apply {
            addCard(Card(CardNumber.JACK, Suit.SPADE))
            addCard(Card(CardNumber.QUEEN, Suit.SPADE))
            addCard(Card(CardNumber.TWO, Suit.SPADE))
        }
        val dealer = Dealer().apply {
            addCard(Card(firstCardNumber, firstCardSuit))
            addCard(Card(secondCardNumber, secondCardSuit))
            addCard(Card(thirdCardNumber, thirdCardSuit))
        }

        assertThat(player judge dealer).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `딜러만 21점을 초과하면 플레이어가 승리한다`() {
        val player = Player("반달").apply {
            addCard(Card(CardNumber.JACK, Suit.SPADE))
            addCard(Card(CardNumber.QUEEN, Suit.SPADE))
        }
        val dealer = Dealer().apply {
            addCard(Card(CardNumber.JACK, Suit.DIAMOND))
            addCard(Card(CardNumber.QUEEN, Suit.DIAMOND))
            addCard(Card(CardNumber.TWO, Suit.SPADE))
        }

        assertThat(player judge dealer).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `플레이어와 딜러가 21점을 초과하지 않고 점수가 동일하면 무승부이다`() {
        val player = Player("반달").apply {
            addCard(Card(CardNumber.JACK, Suit.SPADE))
            addCard(Card(CardNumber.QUEEN, Suit.SPADE))
        }
        val dealer = Dealer().apply {
            addCard(Card(CardNumber.JACK, Suit.DIAMOND))
            addCard(Card(CardNumber.QUEEN, Suit.DIAMOND))
        }

        assertThat(player judge dealer).isEqualTo(GameResult.DRAW)
    }

    @Test
    fun `플레이어와 딜러가 21점을 초과하지 않고 플레이어의 점수가 높으면 플레이어가 승리한다`() {
        val player = Player("반달").apply {
            addCard(Card(CardNumber.JACK, Suit.SPADE))
            addCard(Card(CardNumber.QUEEN, Suit.SPADE))
        }
        val dealer = Dealer().apply {
            addCard(Card(CardNumber.JACK, Suit.DIAMOND))
            addCard(Card(CardNumber.SEVEN, Suit.DIAMOND))
            addCard(Card(CardNumber.TWO, Suit.DIAMOND))
        }

        assertThat(player judge dealer).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `플레이어와 딜러가 21점을 초과하지 않고 딜러의 점수가 높으면 플레이어가 패배한다`() {
        val player = Player("반달").apply {
            addCard(Card(CardNumber.JACK, Suit.SPADE))
            addCard(Card(CardNumber.SEVEN, Suit.SPADE))
            addCard(Card(CardNumber.TWO, Suit.SPADE))
        }
        val dealer = Dealer().apply {
            addCard(Card(CardNumber.JACK, Suit.DIAMOND))
            addCard(Card(CardNumber.QUEEN, Suit.DIAMOND))
        }

        assertThat(player judge dealer).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `플레이어의 처음 받은 카드가 두장이고 그 합이 21점이면 블랙잭이다`() {
        val player = Player("반달").apply {
            addCard(Card(CardNumber.JACK, Suit.SPADE))
            addCard(Card(CardNumber.ACE, Suit.SPADE))
        }
        val dealer = Dealer().apply {
            addCard(Card(CardNumber.JACK, Suit.SPADE))
            addCard(Card(CardNumber.QUEEN, Suit.SPADE))
        }

        assertThat(player judge dealer).isEqualTo(GameResult.BLACKJACK_WIN)
    }

    @Test
    fun `플레이어가 블랙잭이고 딜러도 블랙잭이라면 무승부다`() {
        val player = Player("반달").apply {
            addCard(Card(CardNumber.JACK, Suit.SPADE))
            addCard(Card(CardNumber.ACE, Suit.SPADE))
        }
        val dealer = Dealer().apply {
            addCard(Card(CardNumber.QUEEN, Suit.HEART))
            addCard(Card(CardNumber.ACE, Suit.HEART))
        }

        assertThat(player judge dealer).isEqualTo(GameResult.DRAW)
    }
}
