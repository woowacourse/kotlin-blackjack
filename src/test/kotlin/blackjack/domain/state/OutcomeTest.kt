package blackjack.domain.state

import blackjack.domain.card.Cards
import blackjack.domain.state.Fixtures.CLOVER_ACE
import blackjack.domain.state.Fixtures.CLOVER_EIGHT
import blackjack.domain.state.Fixtures.CLOVER_KING
import blackjack.domain.state.Fixtures.CLOVER_NINE
import blackjack.domain.state.Fixtures.CLOVER_QUEEN
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OutcomeTest {
    @Test
    fun `유저가 두장으로 블랙잭이 되면 딜러가 블랙잭이여도 이긴다`() {
        val dealerState = BlackJack(Cards(CLOVER_ACE, CLOVER_QUEEN))
        val guestState = BlackJack(Cards(CLOVER_ACE, CLOVER_QUEEN))

        assertThat(guestState.matchWith(dealerState)).isEqualTo(Outcome.WIN_WITH_BLACKJACK)
    }

    @Test
    fun `유저 혼자 블랙잭이면 이긴다`() {
        val dealerState = Stay(Cards(CLOVER_KING, CLOVER_QUEEN))
        val guestState = BlackJack(Cards(CLOVER_ACE, CLOVER_KING, CLOVER_QUEEN))

        assertThat(guestState.matchWith(dealerState)).isEqualTo(Outcome.WIN)
    }

    @Test
    fun `유저가 3장이상의 블랙잭이고 딜러가 블랙잭이면 비긴다`() {
        val dealerState = BlackJack(Cards(CLOVER_ACE, CLOVER_KING))
        val guestState = BlackJack(Cards(CLOVER_ACE, CLOVER_KING, CLOVER_QUEEN))

        assertThat(guestState.matchWith(dealerState)).isEqualTo(Outcome.DRAW)
    }

    @Test
    fun `딜러와 유저의 점수가 둘 다 21점 초과면 비긴다`() {
        val dealerState = Bust(Cards(CLOVER_KING, CLOVER_QUEEN, CLOVER_NINE))
        val guestState = Bust(Cards(CLOVER_KING, CLOVER_QUEEN, CLOVER_EIGHT))

        assertThat(guestState.matchWith(dealerState)).isEqualTo(Outcome.DRAW)
    }

    @Test
    fun `딜러 혼자 점수가 21을 넘으면 유저가 이긴다`() {
        val dealerState = Bust(Cards(CLOVER_KING, CLOVER_QUEEN, CLOVER_NINE))
        val guestState = Stay(Cards(CLOVER_KING, CLOVER_QUEEN))

        assertThat(guestState.matchWith(dealerState)).isEqualTo(Outcome.WIN)
    }

    @Test
    fun `유저 혼자 점수가 21점을 넘으면 유저가 진다`() {
        val dealerState = Stay(Cards(CLOVER_KING, CLOVER_QUEEN))
        val guestState = Bust(Cards(CLOVER_KING, CLOVER_QUEEN, CLOVER_NINE))

        assertThat(guestState.matchWith(dealerState)).isEqualTo(Outcome.LOSE)
    }

    @Test
    fun `딜러와 유저의 점수가 같으면 비긴다`() {
        val dealerState = Stay(Cards(CLOVER_NINE, CLOVER_EIGHT))
        val guestState = Stay(Cards(CLOVER_NINE, CLOVER_EIGHT))

        assertThat(guestState.matchWith(dealerState)).isEqualTo(Outcome.DRAW)
    }

    @Test
    fun `유저 점수가 딜러 점수보다 크면 유저가 이긴다`() {
        val dealerState = Stay(Cards(CLOVER_KING, CLOVER_NINE))
        val guestState = Stay(Cards(CLOVER_KING, CLOVER_QUEEN))

        assertThat(guestState.matchWith(dealerState)).isEqualTo(Outcome.WIN)
    }

    @Test
    fun `딜러 점수가 유저 점수보다 크면 유저가 진다`() {
        val dealerState = Stay(Cards(CLOVER_KING, CLOVER_QUEEN))
        val guestState = Stay(Cards(CLOVER_KING, CLOVER_NINE))

        assertThat(guestState.matchWith(dealerState)).isEqualTo(Outcome.LOSE)
    }
}
