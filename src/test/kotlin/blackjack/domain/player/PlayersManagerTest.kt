package blackjack.domain.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayersManagerTest {
    @Test
    fun `초기에 모든 플레이어에게 카드 두장을 나누어준다`() {
        // given
        val playersManager = PlayersManager()
        val dealer = Dealer()
        val participant1 = Participant("aaa")
        val participant2 = Participant("bbb")

        // when
        playersManager.settingPlayersCards(dealer, Participants(listOf(participant1, participant2)))
        val actual = listOf(dealer.cards.values.size, participant1.cards.values.size, participant2.cards.values.size)

        // then
        assertThat(actual).isEqualTo(listOf(2, 2, 2))
    }
}
