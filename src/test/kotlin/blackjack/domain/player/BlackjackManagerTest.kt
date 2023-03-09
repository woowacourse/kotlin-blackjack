package blackjack.domain.player

import blackjack.domain.Result
import blackjack.domain.card.TestCardsGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackManagerTest {

    @Test
    fun `BlackjackManager를 생성할 때 이름을 넘겨주면, 객체를 생성할 때, 해당 이름을 가진 참가자를 생성한다`() {

        // given
        val blackjackManager = BlackjackManager(TestCardsGenerator()) { listOf("aaa", "bbb") }

        // when
        val actual = blackjackManager.participants.values.map { it.name }

        // then
        assertThat(actual).isEqualTo(listOf("aaa", "bbb"))
    }

    @Test
    fun `setup 함수를 호출하면 모든 플레이어에게 카드 두장을 발행한다`() {
        // given
        val blackjackManager = BlackjackManager(TestCardsGenerator()) { listOf("aaa", "bbb") }
        blackjackManager.setup()

        // when
        val dealerCardsSize = blackjackManager.dealer.cards.values.size
        val participantsCardsSize = blackjackManager.participants.values.map { it.cards.values.size }

        // then
        assertThat(dealerCardsSize).isEqualTo(2)
        assertThat(participantsCardsSize).isEqualTo(listOf(2, 2))
    }

    @Test
    fun `추가 발행을 한번 원하고 더 이상 원하지 않는다면 참가자에게 카드를 추가로 한장만 나눠준다`() {
        // given
        val blackjackManager = BlackjackManager(TestCardsGenerator()) { listOf("aaa", "bbb") }
        blackjackManager.setup()

        // when
        val participant1 = blackjackManager.participants.values[0]
        var index = 0
        blackjackManager.provideParticipantMoreCard(participant1, { _: String -> listOf(true, false)[index++] }) {}

        val actual = participant1.cards.values.size

        // then
        assertThat(actual).isEqualTo(3)
    }

    @Test
    fun `추가 발행을 원하지 않는다면 참가자에게 카드를 발행하지 않는다`() {
        // given
        val blackjackManager = BlackjackManager(TestCardsGenerator()) { listOf("aaa", "bbb") }
        blackjackManager.setup()

        // when
        val participant1 = blackjackManager.participants.values[0]
        blackjackManager.provideParticipantMoreCard(participant1, { false }) {}

        val actual = participant1.cards.values.size

        // then
        assertThat(actual).isEqualTo(2)
    }

    @Test
    fun `카드 숫자 합이 16이 넘지 않는동안 딜러에게 계속 카드를 발행한다`() {
        // given
        val blackjackManager = BlackjackManager(TestCardsGenerator()) { listOf("aaa", "bbb") }
        blackjackManager.setup()

        // when
        blackjackManager.provideDealerMoreCard {}
        val actual = blackjackManager.dealer.cards.values.size

        // then
        assertThat(actual).isEqualTo(4)
    }

    @Test
    fun `플레이어1은 14 플레이어2는 7 딜러는 9일때, 참가자들의 승패를 계산한다`() {
        // given
        val blackjackManager = BlackjackManager(TestCardsGenerator()) { listOf("aaa", "bbb") }
        blackjackManager.setup()

        // when
        var participantsResults = ParticipantsResults(listOf())
        var dealerResult = DealerResult(listOf())
        blackjackManager.calculatePlayersResult { _participantsResults, _dealerResult ->
            participantsResults = _participantsResults
            dealerResult = _dealerResult
        }
        val actual1 = participantsResults.results[0].result
        val actual2 = participantsResults.results[1].result
        val actual3 = dealerResult.results

        // then
        assertThat(actual1).isEqualTo(Pair("aaa", Result.WIN))
        assertThat(actual2).isEqualTo(Pair("bbb", Result.LOSE))
        assertThat(actual3).contains(Result.WIN, Result.LOSE)
    }
}
