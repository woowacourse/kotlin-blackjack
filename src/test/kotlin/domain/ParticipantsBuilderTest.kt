package domain

import domain.card.Deck
import domain.person.Participants
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class ParticipantsBuilderTest {

    private fun testNameMakers() = listOf("ver, bix")

    private fun testNamePrinter(participant: Participants) {}

    @Test
    fun `딜러와 플레이어들은 초기에 카드를 두 개와 이름을 갖는다`() {
        // given
        val participantsBuilder = ParticipantsBuilder(Deck.create())

        // when
        val participants = participantsBuilder.getInitialPersons(::testNameMakers, ::testNamePrinter)

        // then

        assertAll(
            { assertThat(participants.dealer.cards.value).hasSize(2) },
            { participants.players.forEach { player -> assertThat(player.cards.value).hasSize(2) } },
            { assertThat(participants.dealer.name).isEqualTo("딜러") },
            { participants.players.forEach { player -> assertThat(player.name).isIn(testNameMakers()) } },
        )
    }
}
