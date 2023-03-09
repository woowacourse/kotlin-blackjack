package blackjack.model

import model.Dealer
import model.Hand
import model.Name
import model.Participants
import model.Player
import model.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantsTest {
    @Test
    fun `딜러와 플레이어의 참가자 정보를 생성할 수 있다`() {
        val dealer = Dealer(emptyHand())
        val players = Players(
            listOf(
                Player(emptyHand(), Name("jason")),
                Player(emptyHand(), Name("pobi"))
            )
        )

        val actual = Participants(dealer, players).toList()

        assertThat(actual.size).isEqualTo(3)
        assertThat(actual[0].name.value).isEqualTo("딜러")
        assertThat(actual[1].name.value).isEqualTo("jason")
        assertThat(actual[2].name.value).isEqualTo("pobi")
    }

    private fun emptyHand() = Hand(emptyList())
}
