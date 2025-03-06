package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantsTest {
    @Test
    fun `참가자들 중 딜러를 반환한다`() {
        val participants = Participants(listOf(Dealer(), Player("A"), Player("B")))
        assertThat(participants.findDealer()).isInstanceOf(Dealer::class.java)
    }

    @Test
    fun `참가자들 중 플레이어들을 반환한다`() {
        val participants = Participants(listOf(Dealer(), Player("A"), Player("B")))
        assertThat(participants.filterPlayers().all { it !is Dealer }).isTrue()
    }
}
