package blackjack.domain.participant

import blackjack.domain.SPADE_ACE
import blackjack.domain.SPADE_EIGHT
import blackjack.domain.SPADE_FOUR
import blackjack.domain.SPADE_JACK
import blackjack.domain.SPADE_KING
import blackjack.domain.SPADE_THREE
import blackjack.domain.SPADE_TWO
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Suit
import blackjack.domain.money.BetMoney
import blackjack.domain.state.HitState
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PlayerTest {
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        val player = Player("pobi", money = BetMoney(1000))
    }

    @Test
    fun `플레이어는 이름을 갖는다`() {
        assertThat(player.name).isEqualTo("pobi")
    }

    @Test
    fun `플레이어가 처음 공개할 카드는 2장이다`() {
        val player = Player(
            name = "pobi",
            money = BetMoney(1000),
            cardState = HitState(SPADE_ACE, SPADE_TWO, SPADE_THREE)
        )

        assertThat(player.getFirstOpenCards().size).isEqualTo(2)
    }

    @Test
    fun `플레이어는 자신이 처음 공개할 카드를 반환한다`() {
        val player = Player(
            name = "pobi",
            money = BetMoney(1000),
            cardState = HitState(SPADE_ACE, SPADE_TWO, SPADE_THREE)
        )

        assertThat(player.getFirstOpenCards()).isEqualTo(
            listOf(SPADE_ACE, SPADE_TWO)
        )
    }

    @Test
    fun `카드의 합이 21을 초과하지 않으면 카드를 뽑을 수 있다`() {
        val player = Player(
            name = "pobi",
            money = BetMoney(1000),
            cardState = HitState(SPADE_ACE, SPADE_KING)
        )

        assertThat(player.canDraw()).isTrue
    }

    @Test
    fun `카드의 합이 21을 초과하면 더 이상 카드를 뽑을 수 없다`() {
        val player = Player(
            name = "pobi",
            money = BetMoney(1000),
            cardState = HitState(SPADE_FOUR, SPADE_EIGHT, SPADE_KING)
        )

        assertThat(player.canDraw()).isFalse
    }

    @Test
    fun `플레이어는 카드 목록에 카드를 추가한다`() {
        val player = Player(
            name = "pobi",
            money = BetMoney(1000),
            cardState = HitState(SPADE_FOUR, SPADE_EIGHT)
        )
        player.draw(SPADE_KING)

        assertThat(player.getCards()).containsExactly(SPADE_FOUR, SPADE_EIGHT, SPADE_KING)
    }

    @Test
    fun `플레이어가 보유한 카드를 반환한다`() {
        val player = Player(
            name = "pobi",
            money = BetMoney(1000),
            cardState = HitState(SPADE_ACE, SPADE_JACK)
        )

        assertThat(player.getCards()).containsExactly(SPADE_ACE, SPADE_JACK)
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
        val player = Player(name = "pobi", money = BetMoney(1000))
            .draw(Card(firstCardNumber, firstCardSuit))
            .draw(Card(secondCardNumber, secondCardSuit))

        assertThat(player.getTotalScore()).isEqualTo(expected)
    }
}
