package blackjack.domain.player

import blackjack.domain.Result
import blackjack.domain.card.TestCardsGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackManagerTest {

    @Test
    fun `입력받은 이름의 참가자를 생성한다`() {

        // given
        val blackjackManager = BlackjackManager(TestCardsGenerator())
        blackjackManager.setup { listOf("aaa", "bbb") }

        // when
        val actual = blackjackManager.participants.values.map { it.name }

        // then
        assertThat(actual).isEqualTo(listOf("aaa", "bbb"))
    }

    @Test
    fun `초기에 모든 플레이어에게 카드 두장을 나누어준다`() {
        // given
        val blackjackManager = BlackjackManager(TestCardsGenerator())
        blackjackManager.setup { listOf("aaa", "bbb") }

        // when
        val dealerCardsSize = blackjackManager.dealer.cards.values.size
        val participantsCardsSize = blackjackManager.participants.values.map { it.cards.values.size }

        // then
        assertThat(dealerCardsSize).isEqualTo(2)
        assertThat(participantsCardsSize).isEqualTo(listOf(2, 2))
    }

    @Test
    fun `카드 발행 가능 상태이고 추가 발행을 원한다면 참가자에게 카드를 나눠준다`() {
        // given
        val blackjackManager = BlackjackManager(TestCardsGenerator())
        blackjackManager.setup { listOf("aaa", "bbb") }

        // when
        val participant1 = blackjackManager.participants.values[0]
        var index = 0
        blackjackManager.provideParticipantMoreCard(participant1, { _: String -> listOf(true, false)[index++] }) {}

        val actual = participant1.cards.values.size

        // then
        assertThat(actual).isEqualTo(3)
    }

    @Test
    fun `카드 발행 가능 상태이고 추가 발행을 원하지 않는다면 참가자에게 카드를 나눠주지 않는다`() {
        // given
        val blackjackManager = BlackjackManager(TestCardsGenerator())
        blackjackManager.setup { listOf("aaa", "bbb") }

        // when
        val participant1 = blackjackManager.participants.values[0]
        blackjackManager.provideParticipantMoreCard(participant1, { false }) {}

        val actual = participant1.cards.values.size

        // then
        assertThat(actual).isEqualTo(2)
    }

    @Test
    fun `카드 발행 가능 상태인 동안 딜러에게 계속 카드를 발행한다`() {
        // given
        val blackjackManager = BlackjackManager(TestCardsGenerator())
        blackjackManager.setup { listOf("aaa", "bbb") }

        // when
        blackjackManager.provideDealerMoreCard {}
        val actual = blackjackManager.dealer.cards.values.size

        // then
        assertThat(actual).isEqualTo(4)
    }

    @Test
    fun `플레이어들의 게임 결과를 업데이트한다`() {
        // given
        val blackjackManager = BlackjackManager(TestCardsGenerator())
        blackjackManager.setup { listOf("aaa", "bbb") }

        // when
        blackjackManager.updatePlayersResult()
        val dealerResults = blackjackManager.dealer.results
        val participant1Result = blackjackManager.participants.values[0].result
        val participant2Result = blackjackManager.participants.values[1].result

        // then
        assertThat(dealerResults).isEqualTo(mapOf(Result.WIN to 1, Result.LOSE to 1, Result.DRAW to 0))
        assertThat(participant1Result).isEqualTo(Result.WIN)
        assertThat(participant2Result).isEqualTo(Result.LOSE)
    }
}
