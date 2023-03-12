package blackjack.domain.paticipant

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Suit
import blackjack.domain.participant.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PlayerInfoTest {
    lateinit var player: Player

    @BeforeEach
    fun setUp() {
        player = Player("pobi", 0)
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
        player.addCard(Card(CardNumber.KING, Suit.SPADE))
        player.addCard(Card(CardNumber.QUEEN, Suit.SPADE))
        player.addCard(Card(CardNumber.ACE, Suit.SPADE))

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
}
