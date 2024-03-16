package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ParticipantsTest {
    @Test
    fun `정상적인 참가자들 Hit 상태 리스트 반환 테스트 `() {
        val dealer = Dealer()
        val participants =
            Participants(
                dealer = dealer,
                listOf(Player(Wallet(Identification("누누"))), Player(Wallet(Identification("꼬상"))))
            )

        assertThat(participants.getAlivePlayers().size).isEqualTo(2)
    }

    @Test
    fun `참여자들 중 플레이어가 있을 때 정상적인 동작 테스트`() {
        val dealer = Dealer()
        val participants =
            Participants(
                dealer = dealer,
                players =
                listOf(
                    Player(Wallet(Identification("누누"))),
                    Player(Wallet(Identification("채드"))),
                    Player(Wallet(Identification("꼬상"))),
                ),
            )
        assertThat(participants.players.size).isEqualTo(3)
    }

    @Test
    fun `참여자들 중 플레이어가 없을 때 예외 발생 테스트`() {
        val dealer = Dealer()
        assertThrows<IllegalArgumentException> {
            Participants(dealer = dealer, players = listOf())
        }
    }
}
