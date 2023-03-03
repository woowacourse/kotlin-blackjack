package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantsTest {
    @Test
    fun `딜러와 플레이어들을 모두 가져온다`() {
        val participants = Participants(
            Players(
                listOf(
                    Player(
                        Name("pobi"), Cards(
                            setOf(
                                Card(CardCategory.CLOVER, CardNumber.EIGHT),
                                Card(CardCategory.SPADE, CardNumber.NINE)
                            )
                        )
                    ),
                    Player(
                        Name("jason"), Cards(
                            setOf(
                                Card(CardCategory.CLOVER, CardNumber.EIGHT),
                                Card(CardCategory.SPADE, CardNumber.NINE)
                            )
                        )
                    )
                )
            ),
            Dealer(
                Cards(
                    setOf(
                        Card(CardCategory.CLOVER, CardNumber.EIGHT),
                        Card(CardCategory.SPADE, CardNumber.NINE)
                    )
                )
            )
        )
        val result = participants.participants.joinToString(", ") { it.name.name }
        assertThat(result).isEqualTo("딜러, pobi, jason")
    }
}
