package blackjack.model

import model.Cards
import model.Dealer
import model.Name
import model.Participants
import model.Player
import model.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantsTest {
    @Test
    fun `딜러와 플레이어의 참가자 정보를 생성할 수 있다`() {
        val dealer = Dealer(emptyCards())
        val players = Players(
            listOf(
                Player(emptyCards(), Name("jason")),
                Player(emptyCards(), Name("pobi"))
            )
        )

        val actual = Participants.of(dealer, players).toList()

        assertThat(actual.size).isEqualTo(3)
        assertThat(actual[0].name.value).isEqualTo("딜러")
        assertThat(actual[1].name.value).isEqualTo("jason")
        assertThat(actual[2].name.value).isEqualTo("pobi")
    }

    private fun emptyCards() = Cards(emptyList())
}
