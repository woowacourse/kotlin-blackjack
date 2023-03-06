package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
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
        assertThat(player.name).isEqualTo("pobi")
    }

    @Test
    fun `플레이어는 카드 목록에 카드를 추가한다`() {
        player.addCard(Card(Suit.SPADE, CardNumber.TWO))

        assertThat(player.hand.cards).containsExactly(Card(Suit.SPADE, CardNumber.TWO))
    }

    @Test
    fun `카드의 합이 21을 초과하면 버스트다`() {
        player.addCard(Card(Suit.SPADE, CardNumber.FOUR))
        player.addCard(Card(Suit.SPADE, CardNumber.EIGHT))
        player.addCard(Card(Suit.SPADE, CardNumber.KING))

        assertThat(player.isBust()).isTrue
    }

    @Test
    fun `카드의 합이 21을 초과하지 않으면 버스트가 아니다`() {
        player.addCard(Card(Suit.SPADE, CardNumber.ACE))
        player.addCard(Card(Suit.SPADE, CardNumber.KING))

        assertThat(player.isBust()).isFalse
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

        assertThat(player.getTotalScore()).isEqualTo(expected)
    }

    @Test
    fun `자신이 가진 카드를 반환한다`() {
        player.addCard(Card(Suit.SPADE, CardNumber.ACE))
        player.addCard(Card(Suit.SPADE, CardNumber.JACK))

        assertThat(player.getHand().hand).isEqualTo(listOf("A스페이드", "J다이아몬드"))
    }
}
