package blackjack.domain.participant

import blackjack.domain.DIAMOND_ACE
import blackjack.domain.DIAMOND_JACK
import blackjack.domain.SPADE_ACE
import blackjack.domain.SPADE_FIVE
import blackjack.domain.SPADE_FOUR
import blackjack.domain.SPADE_JACK
import blackjack.domain.SPADE_KING
import blackjack.domain.SPADE_SIX
import blackjack.domain.SPADE_THREE
import blackjack.domain.SPADE_TWO
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Suit
import blackjack.domain.money.BetMoney
import blackjack.domain.money.Money
import blackjack.domain.state.BlackjackState
import blackjack.domain.state.HitState
import blackjack.domain.state.StayState
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DealerTest {
    @Test
    fun `딜러는 이름을 갖는다`() {
        val dealer = Dealer()
        assertThat(dealer.name).isEqualTo("딜러")
    }

    @Test
    fun `딜러가 처음 공개할 카드는 1장이다`() {
        val dealer = Dealer(cardState = HitState(SPADE_JACK, SPADE_KING))

        assertThat(dealer.getFirstOpenCards().size).isEqualTo(1)
    }

    @Test
    fun `딜러는 자신이 처음 공개할 카드를 반환한다`() {
        val dealer = Dealer(cardState = HitState(SPADE_JACK, SPADE_KING))

        assertThat(dealer.getFirstOpenCards()).isEqualTo(listOf(SPADE_JACK))
    }

    @Test
    fun `딜러는 카드의 합이 16점 이하면 카드를 뽑을 수 있다`() {
        val dealer = Dealer(cardState = HitState(SPADE_ACE, SPADE_FIVE))

        assertThat(dealer.canDraw()).isTrue
    }

    @Test
    fun `딜러는 카드의 합이 17점 이상이면 더 이상 카드를 뽑을 수 없다`() {
        val dealer = Dealer(cardState = HitState(SPADE_ACE, SPADE_SIX))

        assertThat(dealer.canDraw()).isFalse
    }

    @Test
    fun `딜러는 카드 목록에 카드를 추가한다`() {
        val dealer = Dealer(cardState = HitState(SPADE_ACE, SPADE_TWO))
            .draw(SPADE_THREE)

        assertThat(dealer.getCards()).containsExactly(SPADE_ACE, SPADE_TWO, SPADE_THREE)
    }

    @Test
    fun `딜러가 보유한 카드를 반환한다`() {
        val dealer = Dealer(cardState = HitState(SPADE_ACE, SPADE_JACK))

        assertThat(dealer.getCards()).isEqualTo(
            listOf(SPADE_ACE, SPADE_JACK)
        )
    }

    @Test
    fun `딜러의 수익은 플레이어들 손익의 반대이다`() {
        val dealer = Dealer(cardState = StayState(SPADE_KING, SPADE_JACK))
        val actual = dealer.getProfit(
            listOf(
                // 수익 : -1000
                Player(
                    "buna",
                    money = BetMoney(1000),
                    cardState = StayState(SPADE_TWO, SPADE_THREE)
                ),
                // 수익 : -1000
                Player(
                    "bandal",
                    money = BetMoney(1000),
                    cardState = StayState(SPADE_FOUR, SPADE_FIVE)
                ),
                // 수익 : 6000
                Player(
                    "glo",
                    money = BetMoney(4000),
                    cardState = BlackjackState(DIAMOND_ACE, DIAMOND_JACK)
                ),
            )
        )
        val expected = Money(-4000)

        assertThat(actual).isEqualTo(expected)
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
        val dealer = Dealer()
            .draw(Card(firstCardNumber, firstCardSuit))
            .draw(Card(secondCardNumber, secondCardSuit))

        assertThat(dealer.getTotalScore()).isEqualTo(expected)
    }
}
