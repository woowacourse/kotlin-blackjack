package blackjack.domain.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackManagerTest {

    @Test
    fun `입력받은 이름의 참가자를 생성한다`() {

        // given
        val blackjackManager = BlackjackManager(TestCardsGenerator())
        blackjackManager.generateParticipants { listOf("aaa", "bbb") }

        // when
        val actual = blackjackManager.participants.values.map { it.name }

        // then
        assertThat(actual).isEqualTo(listOf("aaa", "bbb"))
    }

    @Test
    fun `초기에 모든 플레이어에게 카드 두장을 나누어준다`() {
        // given
        val blackjackManager = BlackjackManager(TestCardsGenerator())
        val dealer = Dealer()
        val participant1 = Participant("aaa")
        val participant2 = Participant("bbb")

        // when
        blackjackManager.settingPlayersCards(dealer, Participants(listOf(participant1, participant2)))
        val actual = listOf(dealer.cards.values.size, participant1.cards.values.size, participant2.cards.values.size)

        // then
        assertThat(actual).isEqualTo(listOf(2, 2, 2))
    }
}
