package domain

import domain.card.Card
import domain.card.CardCategory
import domain.card.CardNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantsTest {
    @Test
    fun `딜러와 플레이어들을 모두 가져온다`() {
        val participants = Participants(
            Players(
                Player(
                    NameAndBet(Name("pobi"), 0),
                    Cards(
                        Card.of(CardCategory.CLOVER, CardNumber.EIGHT),
                        Card.of(CardCategory.SPADE, CardNumber.NINE)
                    )
                ),
                Player(
                    NameAndBet(Name("jason"), 0),
                    Cards(
                        Card.of(CardCategory.CLOVER, CardNumber.EIGHT),
                        Card.of(CardCategory.SPADE, CardNumber.NINE)
                    )
                )
            ),
            Dealer(
                Cards(
                    Card.of(CardCategory.CLOVER, CardNumber.EIGHT),
                    Card.of(CardCategory.SPADE, CardNumber.NINE)
                )
            )
        )
        val result = participants.all.joinToString(", ") { it.name.value }
        assertThat(result).isEqualTo("딜러, pobi, jason")
    }
}
