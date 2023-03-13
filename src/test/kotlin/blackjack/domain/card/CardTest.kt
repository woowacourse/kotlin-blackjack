package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardTest {
    @ParameterizedTest
    @CsvSource(
        "ACE,SPADE", "ACE,HEART", "ACE,DIAMOND", "ACE,CLOVER",
        "TWO,SPADE", "TWO,HEART", "TWO,DIAMOND", "TWO,CLOVER",
        "THREE,SPADE", "THREE,HEART", "THREE,DIAMOND", "THREE,CLOVER",
        "FOUR,SPADE", "FOUR,HEART", "FOUR,DIAMOND", "FOUR,CLOVER",
        "FIVE,SPADE", "FIVE,HEART", "FIVE,DIAMOND", "FIVE,CLOVER",
        "SIX,SPADE", "SIX,HEART", "SIX,DIAMOND", "SIX,CLOVER",
        "SEVEN,SPADE", "SEVEN,HEART", "SEVEN,DIAMOND", "SEVEN,CLOVER",
        "EIGHT,SPADE", "EIGHT,HEART", "EIGHT,DIAMOND", "EIGHT,CLOVER",
        "NINE,SPADE", "NINE,HEART", "NINE,DIAMOND", "NINE,CLOVER",
        "TEN,SPADE", "TEN,HEART", "TEN,DIAMOND", "TEN,CLOVER",
        "JACK,SPADE", "JACK,HEART", "JACK,DIAMOND", "JACK,CLOVER",
        "QUEEN,SPADE", "QUEEN,HEART", "QUEEN,DIAMOND", "QUEEN,CLOVER",
        "KING,SPADE", "KING,HEART", "KING,DIAMOND", "KING,CLOVER"
    )
    fun `카드는 각 모양별로 2부터 10, A, J, Q, K가 존재한다`(cardNumber: CardNumber, suit: Suit) {
        assertThat(Card.all()).contains(Card(cardNumber, suit))
    }
}
