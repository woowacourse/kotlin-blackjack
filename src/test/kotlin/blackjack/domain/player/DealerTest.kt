package blackjack.domain.player

import blackjack.domain.Result
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러가 카드를 8클로버만 가지고 있을 때, 카드를 더 받아야 하는 상태인지 확인하면, true이다`() {
        val dealer = Dealer()
        dealer.addCard(Card(CardNumber.EIGHT, CardShape.CLOVER))
        val actual = dealer.checkProvideCardPossible()
        assertThat(actual).isEqualTo(true)
    }

    @Test
    fun `플레이어들의 승패가 1패 1승일때, 딜러의 승패를 계산하면, 1승 1패이다`() {
        // given
        val participantsResults =
            ParticipantsResults(listOf(ParticipantResult("aaa", Result.LOSE), ParticipantResult("bbb", Result.WIN)))

        // when
        val actual = Dealer().calculateResults(participantsResults)

        // then
        assertThat(actual.results).isEqualTo(listOf(Result.WIN, Result.LOSE))
    }

    @Test
    fun `플레이어들의 승패가 2승일 때, 딜러의 승패를 계산하면, 2패이다`() {
        // given
        val participantsResults =
            ParticipantsResults(listOf(ParticipantResult("aaa", Result.WIN), ParticipantResult("bbb", Result.WIN)))

        // when
        val actual = Dealer().calculateResults(participantsResults)

        // then
        assertThat(actual.results).isEqualTo(listOf(Result.LOSE, Result.LOSE))
    }

    @Test
    fun `플레이어들의 승패가 1무 1승일때, 딜러의 승패를 계산하면, 1무 1패이다`() {
        // given
        val participantsResults =
            ParticipantsResults(listOf(ParticipantResult("aaa", Result.DRAW), ParticipantResult("bbb", Result.WIN)))

        // when
        val actual = Dealer().calculateResults(participantsResults)

        // then
        assertThat(actual.results).isEqualTo(listOf(Result.DRAW, Result.LOSE))
    }
}
