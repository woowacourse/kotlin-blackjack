package blackjack.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PlayerTest {
    lateinit var player: Player

    @BeforeEach
    fun setUp() {
        player = Player("pobi")
    }

    @Test
    fun `플레이어는 이름을 갖는다`() {
        Assertions.assertThat(player.name).isEqualTo("pobi")
    }

    @Test
    fun `플레이어는 카드 목록에 카드를 추가한다`() {
        player.addCard(Card(Suit.SPADE, CardNumber.TWO))

        // Assertions.assertThat(player.hand.cards).containsExactly(Card(Suit.SPADE, CardNumber.TWO))
    }

    @Test
    fun `카드의 합이 21을 초과하지 않으면 카드를 뽑을 수 있다`() {
        player.addCard(Card(Suit.SPADE, CardNumber.ACE))
        player.addCard(Card(Suit.SPADE, CardNumber.KING))

        Assertions.assertThat(player.canDraw()).isFalse
    }

    @Test
    fun `카드의 합이 21을 초과하면 더 이상 카드를 뽑을 수 없다`() {
        player.addCard(Card(Suit.SPADE, CardNumber.FOUR))
        player.addCard(Card(Suit.SPADE, CardNumber.EIGHT))
        player.addCard(Card(Suit.SPADE, CardNumber.KING))

        Assertions.assertThat(player.canDraw()).isTrue
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
        player.addCard(Card(firstCardSuit, firstCardNumber))
        player.addCard(Card(secondCardSuit, secondCardNumber))

        Assertions.assertThat(player.getTotalScore()).isEqualTo(expected)
    }

    @Test
    fun `자신이 가진 카드를 반환한다`() {
        player.addCard(Card(Suit.SPADE, CardNumber.ACE))
        player.addCard(Card(Suit.SPADE, CardNumber.JACK))

        // Assertions.assertThat(player.getHand().hand).isEqualTo(listOf("A스페이드", "J다이아몬드"))
    }

    @Test
    fun `플레이어가 처음 공개할 카드는 2장이다`() {
        with(player) {
            addCard(Card(Suit.SPADE, CardNumber.JACK))
            addCard(Card(Suit.SPADE, CardNumber.QUEEN))
        }

        Assertions.assertThat(player.getFirstOpenCards().size).isEqualTo(2)
    }
}
