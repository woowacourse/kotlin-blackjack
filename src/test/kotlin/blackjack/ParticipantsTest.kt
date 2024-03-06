package blackjack

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.IllegalArgumentException

class ParticipantsTest {
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
}
