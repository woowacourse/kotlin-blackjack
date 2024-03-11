package blackjack.model

import blackjack.fixture.createCard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantsHandTest {
    @Test
    fun `카드를 2장씩 묶어서 참여자들의 손패를 만든다`() {
        // given
        val cards = listOf(createCard(), createCard(), createCard(), createCard())
        val expect =
            ParticipantsHand(
                playerHandCards =
                    listOf(
                        Hand(createCard(), createCard()),
                    ),
                dealerHand = Hand(createCard(), createCard()),
            )
        // when
        val actual = ParticipantsHand.from(cards.toMutableList())
        // then
        assertThat(actual).isEqualTo(expect)
    }
}
