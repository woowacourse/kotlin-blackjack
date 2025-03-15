package blackjack.domain.participant

import blackjack.domain.SIX_HEART
import blackjack.domain.TEN_HEART
import blackjack.model.participant.Name
import blackjack.model.participant.ParticipantManager
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ParticipantManagerTest {
    @Test
    fun `참가자가 정상적으로 생성된다`() {
        // given
        val participantManager = ParticipantManager()

        // when
        val participants =
            participantManager.prepareParticipants(
                dealerName = Name("딜러"),
                distributeCards = { listOf(TEN_HEART, SIX_HEART) },
                getPlayerNames = { listOf("공백", "오이").map { Name(it) } },
            )

        // then
        assertEquals("딜러", participants.dealer.name.value)
        assertEquals(2, participants.players.value.size)
        assertTrue(participants.players.value.any { it.name.value == "공백" })
        assertTrue(participants.players.value.any { it.name.value == "오이" })
    }

    @Test
    fun `딜러가 정상적으로 생성되면 카드를 받는다`() {
        // given
        val participantManager = ParticipantManager()

        // when
        val dealer =
            participantManager
                .prepareParticipants(
                    dealerName = Name("딜러"),
                    distributeCards = { listOf(TEN_HEART, SIX_HEART) },
                    getPlayerNames = { listOf("공백", "오이").map { Name(it) } },
                ).dealer

        // then
        assertEquals("딜러", dealer.name.value)
        assertEquals(2, dealer.cards.size)
        assertEquals(TEN_HEART, dealer.cards[0])
        assertEquals(SIX_HEART, dealer.cards[1])
    }

    @Test
    fun `플레이어들이 정상적으로 생성되면 카드를 받는다`() {
        // given
        val participantManager = ParticipantManager()

        // when
        val players =
            participantManager
                .prepareParticipants(
                    dealerName = Name("딜러"),
                    distributeCards = { listOf(TEN_HEART, SIX_HEART) },
                    getPlayerNames = { listOf("공백", "오이").map { Name(it) } },
                ).players

        // then
        assertEquals(2, players.value.size)
        players.value.forEach { player ->
            assertEquals(2, player.cards.size)
            assertEquals(TEN_HEART, player.cards[0])
            assertEquals(SIX_HEART, player.cards[1])
        }
    }
}
