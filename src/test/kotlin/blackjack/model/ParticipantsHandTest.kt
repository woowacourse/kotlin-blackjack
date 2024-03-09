package blackjack.model

import blackjack.fixture.createCard
import blackjack.model.card.Hand
import blackjack.model.card.ParticipantHands
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantsHandTest {
    @Test
    fun `카드를 2장씩 묶어서 참여자들의 손패를 만든다`() {
        // given
        val cards = listOf(createCard(), createCard(), createCard(), createCard())
        val expect =
            ParticipantHands(
                playerHandCards =
                    listOf(
                        Hand(createCard(), createCard()),
                    ),
                dealerHand = Hand(createCard(), createCard()),
            )
        // when
        val actual = ParticipantHands.from(cards)
        // then
        assertThat(actual).isEqualTo(expect)
    }
}
