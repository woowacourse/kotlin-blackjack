package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardTest {
    @ParameterizedTest
    @CsvSource(
        "SPADE, ACE", "SPADE, TWO", "SPADE, THREE", "SPADE, FOUR", "SPADE, FIVE",
        "SPADE, SIX", "SPADE, SEVEN", "SPADE, EIGHT", "SPADE, NINE", "SPADE, TEN",
        "SPADE, JACK", "SPADE, QUEEN", "SPADE, KING",
        "HEART, ACE", "HEART, TWO", "HEART, THREE", "HEART, FOUR", "HEART, FIVE",
        "HEART, SIX", "HEART, SEVEN", "HEART, EIGHT", "HEART, NINE", "HEART, TEN",
        "HEART, JACK", "HEART, QUEEN", "HEART, KING",
        "DIAMOND, ACE", "DIAMOND, TWO", "DIAMOND, THREE", "DIAMOND, FOUR", "DIAMOND, FIVE",
        "DIAMOND, SIX", "DIAMOND, SEVEN", "DIAMOND, EIGHT", "DIAMOND, NINE", "DIAMOND, TEN",
        "DIAMOND, JACK", "DIAMOND, QUEEN", "DIAMOND, KING",
        "CLOVER, ACE", "CLOVER, TWO", "CLOVER, THREE", "CLOVER, FOUR", "CLOVER, FIVE",
        "CLOVER, SIX", "CLOVER, SEVEN", "CLOVER, EIGHT", "CLOVER, NINE", "CLOVER, TEN",
        "CLOVER, JACK", "CLOVER, QUEEN", "CLOVER, KING",
    )
    fun `카드는 각 모양별로 2부터 10, A, J, Q, K가 존재한다`(suit: Suit, cardNumber: CardNumber) {
        assertThat(Card.all()).containsExactly(Card(suit, cardNumber))
    }
}
