package blackjack.model

import blackjack.fixture.createCard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class HandTest {
    @ParameterizedTest
    @CsvSource(
        value = [
            "JACK:QUEEN:TWO:true",
            "NINE:ACE:ACE:false",
        ],
        delimiter = ':',
    )
    fun `HandCards의 합이 null이면 버스트된다`(
        rank: Rank,
        rank2: Rank,
        rank3: Rank,
        isBust: Boolean,
    ) {
        // given
        val hand =
            Hand(
                createCard(rank = rank),
                createCard(rank = rank2),
                createCard(rank = rank3),
            )
        // when
        val actual = hand.isBust()
        // then
        assertThat(actual).isEqualTo(isBust)
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "JACK:ACE:true",
            "NINE:ACE:false",
        ],
        delimiter = ':',
    )
    fun `HandCards의 합이 21이고 2장이면 블랙잭이다`(
        rank: Rank,
        rank2: Rank,
        isBlackJack: Boolean,
    ) {
        // given
        val hand =
            Hand(
                createCard(rank = rank),
                createCard(rank = rank2),
            )
        // when
        val actual = hand.isBlackjack()
        // then
        assertThat(actual).isEqualTo(isBlackJack)
    }
}
