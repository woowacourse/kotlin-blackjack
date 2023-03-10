package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PlayerTest {
    lateinit var player: Player

    @BeforeEach
    fun setUp() {
        // player = Player("pobi")
    }

    @Test
    fun `플레이어는 이름을 갖는다`() {
        assertThat(player.name).isEqualTo("pobi")
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

    // @Test
    // fun `본인과 상대방이 모두 21점을 초과하면 무승부이다`() {
    //     val me = Player("부나").apply {
    //         addCard(Card(CardNumber.JACK, Suit.SPADE))
    //         addCard(Card(CardNumber.QUEEN, Suit.SPADE))
    //         addCard(Card(CardNumber.TWO, Suit.SPADE))
    //     }
    //     val other = Player("반달").apply {
    //         addCard(Card(CardNumber.JACK, Suit.DIAMOND))
    //         addCard(Card(CardNumber.QUEEN, Suit.DIAMOND))
    //         addCard(Card(CardNumber.THREE, Suit.DIAMOND))
    //     }
    //
    //     assertThat(me judge other).isEqualTo(GameResult.DRAW)
    // }

    // @Test
    // fun `본인만 21점을 초과하면 패배한다`() {
    //     val me = Player("부나").apply {
    //         addCard(Card(CardNumber.JACK, Suit.SPADE))
    //         addCard(Card(CardNumber.QUEEN, Suit.SPADE))
    //         addCard(Card(CardNumber.TWO, Suit.SPADE))
    //     }
    //     val other = Player("반달").apply {
    //         addCard(Card(CardNumber.JACK, Suit.DIAMOND))
    //         addCard(Card(CardNumber.QUEEN, Suit.DIAMOND))
    //     }
    //
    //     assertThat(me judge other).isEqualTo(GameResult.LOSE)
    // }
    //
    // @Test
    // fun `상대방만 21점을 초과하면 승리한다`() {
    //     val me = Player("부나").apply {
    //         addCard(Card(CardNumber.JACK, Suit.SPADE))
    //         addCard(Card(CardNumber.QUEEN, Suit.SPADE))
    //     }
    //     val other = Player("반달").apply {
    //         addCard(Card(CardNumber.JACK, Suit.DIAMOND))
    //         addCard(Card(CardNumber.QUEEN, Suit.DIAMOND))
    //         addCard(Card(CardNumber.TWO, Suit.SPADE))
    //     }
    //
    //     assertThat(me judge other).isEqualTo(GameResult.WIN)
    // }
    //
    // @Test
    // fun `본인과 상대방이 21점을 초과하지 않고 점수가 동일하면 무승부이다`() {
    //     val me = Player("부나").apply {
    //         addCard(Card(CardNumber.JACK, Suit.SPADE))
    //         addCard(Card(CardNumber.QUEEN, Suit.SPADE))
    //     }
    //     val other = Player("반달").apply {
    //         addCard(Card(CardNumber.JACK, Suit.DIAMOND))
    //         addCard(Card(CardNumber.QUEEN, Suit.DIAMOND))
    //     }
    //
    //     assertThat(me judge other).isEqualTo(GameResult.DRAW)
    // }
    //
    // @Test
    // fun `본인과 상대방이 21점을 초과하지 않고 본인의 점수가 높으면 승리한다`() {
    //     val me = Player("부나").apply {
    //         addCard(Card(CardNumber.JACK, Suit.SPADE))
    //         addCard(Card(CardNumber.QUEEN, Suit.SPADE))
    //     }
    //     val other = Player("반달").apply {
    //         addCard(Card(CardNumber.JACK, Suit.DIAMOND))
    //         addCard(Card(CardNumber.TWO, Suit.DIAMOND))
    //     }
    //
    //     assertThat(me judge other).isEqualTo(GameResult.WIN)
    // }
    //
    // @Test
    // fun `본인과 상대방이 21점을 초과하지 않고 상대방의 점수가 높으면 패배한다`() {
    //     val me = Player("부나").apply {
    //         addCard(Card(CardNumber.JACK, Suit.SPADE))
    //         addCard(Card(CardNumber.TWO, Suit.SPADE))
    //     }
    //     val other = Player("반달").apply {
    //         addCard(Card(CardNumber.JACK, Suit.DIAMOND))
    //         addCard(Card(CardNumber.QUEEN, Suit.DIAMOND))
    //     }
    //
    //     assertThat(me judge other).isEqualTo(GameResult.LOSE)
    // }
}
