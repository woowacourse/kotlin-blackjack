package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ParticipantsTest {
    class MockParticipant(name: String) : Participant(name)

    @Test
    fun `정상적인 참가자들 Hit 상태 리스트 반환 테스트 `() {
        val dealer = Dealer()
        val mockNunuParticipant = MockParticipant("누누")
        val mockKkosangParticipant = MockParticipant("꼬상")
        val participants = Participants(listOf(dealer, mockNunuParticipant, mockKkosangParticipant))

        assertThat(participants.getAliveParticipants().size).isEqualTo(3)
    }

    @Test
    fun `참여자들중 첫번째 플레이어가 딜러일 때 정상적인 반환 테스트`() {
        val dealer = Dealer()
        val participants = Participants(participants = listOf(dealer, Player("누누"), Player("채드"), Player("꼬상")))
        assertThat(participants.getDealer().getName()).isEqualTo(dealer.getName())
    }

    @Test
    fun `참여자들 중 딜러가 없을 때 예외 발생 테스트`() {
        assertThrows<IllegalArgumentException> {
            Participants(participants = listOf(Player("누누"), Player("채드"), Player("꼬상")))
        }
    }

    @Test
    fun `참여자들 중 딜러가 첫번째 참가자가 아닐 때 예외 발생 테스트`() {
        val dealer = Dealer()
        assertThrows<IllegalArgumentException> {
            Participants(participants = listOf(Player("누누"), dealer, Player("채드"), Player("꼬상")))
        }
    }

    @Test
    fun `참여자들 중 플레이어가 있을 때 정상적인 동작 테스트`() {
        val dealer = Dealer()
        val participants = Participants(participants = listOf(dealer, Player("누누"), Player("채드"), Player("꼬상")))
        assertThat(participants.getPlayers().size).isEqualTo(3)
    }

    @Test
    fun `참여자들 중 플레이어가 없을 때 예외 발생 테스트`() {
        val dealer = Dealer()
        assertThrows<IllegalArgumentException> {
            Participants(participants = listOf(dealer))
        }
    }

    @Test
    fun `참자여들 중 딜러가 1명을 초과할 때 예외 발생 테스트`() {
        val firstDealer = Dealer()
        val secondDealer = Dealer()
        assertThrows<IllegalArgumentException> {
            Participants(participants = listOf(firstDealer, secondDealer))
        }
    }
}
