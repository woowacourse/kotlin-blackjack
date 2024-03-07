package blackjack.model

import blackjack.fixture.createCard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class HandCardsTest {
    @Test
    fun `손패는 2장 이상이다`() {
        assertThrows<IllegalArgumentException> {
            HandCards(emptyList())
        }
    }

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
        val handCards =
            HandCards(
                createCard(rank = rank),
                createCard(rank = rank2),
                createCard(rank = rank3),
            )
        // when
        val actual = handCards.isBust()
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
        val handCards =
            HandCards(
                createCard(rank = rank),
                createCard(rank = rank2),
            )
        // when
        val actual = handCards.isBlackjack()
        // then
        assertThat(actual).isEqualTo(isBlackJack)
    }
}

fun createHandCards(): HandCards {
    val deck = Cards.createDeck()
    return HandCards(
        deck.cards
            .shuffled()
            .take(2),
    )
}

// 중복 있이 손패 뿌리기
fun createHandCards(size: Int): List<Card> {
    val deck = Cards.createDeck()
    return deck.cards
        .shuffled()
        .take(size)
}
