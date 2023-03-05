package blackjack.model

import model.Card
import model.Cards
import model.Dealer
import model.Name
import model.Participants
import model.Player
import model.Players
import model.Rank
import model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantsTest {
    @Test
    fun `딜러와 플레이어의 참가자 정보를 생성할 수 있다`() {
        val dealer = Dealer(Cards(listOf(Card(Rank.KING, Suit.HEART))))
        val players = Players(
            listOf(
                Player(
                    Cards(
                        listOf(
                            Card(Rank.ACE, Suit.HEART)
                        )
                    ),
                    Name("jason")
                ),
                Player(
                    Cards(
                        listOf(
                            Card(Rank.DEUCE, Suit.HEART)
                        )
                    ),
                    Name("pobi")
                )
            )
        )
        val actual = Participants(listOf(dealer) + players)
        assertThat(actual.participants.size).isEqualTo(3)
        assertThat(actual.participants[0].name.value).isEqualTo("딜러")
        assertThat(actual.participants[1].name.value).isEqualTo("jason")
        assertThat(actual.participants[2].name.value).isEqualTo("pobi")
    }
}
