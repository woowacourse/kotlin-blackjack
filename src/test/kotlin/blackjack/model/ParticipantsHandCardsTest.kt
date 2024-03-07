package blackjack.model

import blackjack.fixture.createCard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantsHandCardsTest {
    @Test
    fun `카드를 2장씩 묶어서 참여자들의 손패를 만든다`() {
        // given
        val cards = listOf(createCard(), createCard(), createCard(), createCard())
        val expect =
            ParticipantsHandCards(
                playerHandCards =
                    listOf(
                        HandCards(createCard(), createCard()),
                    ),
                dealerHandCards = HandCards(createCard(), createCard()),
            )
        // when
        val actual = ParticipantsHandCards.from(cards)
        // then
        assertThat(actual).isEqualTo(expect)
    }
}
