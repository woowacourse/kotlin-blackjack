package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.IllegalArgumentException

class ParticipantsTest {
    private val defaultParticipants =
        Participants(
            listOf(
                Dealer(
                    CardHand(
                        Card(CardShape.CLOVER, CardNumber.ACE),
                        Card(CardShape.SPADE, CardNumber.SIX),
                    ),
                ),
                Player(
                    "심지",
                    CardHand(
                        Card(CardShape.CLOVER, CardNumber.FIVE),
                        Card(CardShape.SPADE, CardNumber.KING),
                    ),
                ),
                Player(
                    "해나",
                    CardHand(
                        Card(CardShape.HEART, CardNumber.SIX),
                        Card(CardShape.DIAMOND, CardNumber.QUEEN),
                    ),
                ),
                Player(
                    "악어",
                    CardHand(
                        Card(CardShape.HEART, CardNumber.SEVEN),
                        Card(CardShape.CLOVER, CardNumber.THREE),
                    ),
                ),
                Player(
                    "팡태",
                    CardHand(
                        Card(CardShape.DIAMOND, CardNumber.ACE),
                        Card(CardShape.SPADE, CardNumber.SIX),
                    ),
                ),
            ),
        )

    @Test
    fun `딜러를 포함한 참가자의 수는 2명 이상, 7명 이하이다`() {
        assertDoesNotThrow {
            Participants(
                listOf(
                    Dealer(CardHand(emptyList())),
                    Player("심지", CardHand(emptyList())),
                    Player("해나", CardHand(emptyList())),
                ),
            )
        }
    }

    @Test
    fun `딜러를 포함한 참가자의 수는 2명 미만이면 예외를 던진다`() {
        assertThatThrownBy {
            Participants(
                listOf(
                    Dealer(CardHand(emptyList())),
                ),
            )
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("딜러를 포함한 참가자의 수가 2명 이상, 7명 이하여야 합니다.")
    }

    @Test
    fun `딜러를 포함한 참가자의 수는 7명 초과이면 예외를 던진다`() {
        assertThatThrownBy {
            Participants(
                listOf(
                    Dealer(CardHand(emptyList())),
                    Player("심지", CardHand(emptyList())),
                    Player("해나", CardHand(emptyList())),
                    Player("악어", CardHand(emptyList())),
                    Player("팡태", CardHand(emptyList())),
                    Player("채채", CardHand(emptyList())),
                    Player("서기", CardHand(emptyList())),
                    Player("벼리", CardHand(emptyList())),
                    Player("올리브", CardHand(emptyList())),
                ),
            )
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("딜러를 포함한 참가자의 수가 2명 이상, 7명 이하여야 합니다.")
    }

    @Test
    fun `참가자들에게 카드를 2장씩 준다`() {
        val participants =
            Participants(
                listOf(
                    Dealer(CardHand(emptyList())),
                    Player("심지", CardHand(emptyList())),
                    Player("해나", CardHand(emptyList())),
                ),
            )

        participants.addInitialCards()

        val cardHandSize1 = participants.participants[0].cardHand.hand.size
        val cardHandSize2 = participants.participants[1].cardHand.hand.size
        val cardHandSize3 = participants.participants[2].cardHand.hand.size

        assertThat(cardHandSize1).isEqualTo(2)
        assertThat(cardHandSize2).isEqualTo(2)
        assertThat(cardHandSize3).isEqualTo(2)
    }

    @Test
    fun `딜러 카드 패의 합을 가져온다`() {
        val actual = defaultParticipants.getDealerSum()

        assertThat(actual).isEqualTo(17)
    }

    @Test
    fun `플레이어들의 카드 패의 합을 이름과 짝 지어서 가져온다`() {
        val actual = defaultParticipants.getPlayerResult()

        assertThat(actual).isEqualTo(
            mapOf(
                "심지" to 15,
                "해나" to 16,
                "악어" to 10,
                "팡태" to 17,
            ),
        )
    }
}
