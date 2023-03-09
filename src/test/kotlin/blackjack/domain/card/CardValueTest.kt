package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardValueTest {
    @ParameterizedTest
    @CsvSource(value = ["ACE,1", "KING,10", "QUEEN,10", "JACK,10", "TEN,10", "NINE,9", "EIGHT,8", "SEVEN,7", "SIX,6", "FIVE,5", "FOUR,4", "THREE,3", "TWO,2"])
    fun `카드의 값들이 나온다`(cardValue: CardValue, value: Int) {
        assertThat(cardValue.value).isEqualTo(value)
    }

    @Test
    fun `카드리스트들에 ACE 카드가 있으면 true가 반환된다`() {
        val cardValues = listOf(CardValue.TEN, CardValue.EIGHT, CardValue.ACE)
        assertThat(CardValue.containsAce(cardValues)).isTrue()
    }

    @Test
    fun `카드리스트들에 ACE 카드가 없으면 false가 반환된다`() {
        val cardValues = listOf(CardValue.TEN, CardValue.EIGHT, CardValue.SIX)
        assertThat(CardValue.containsAce(cardValues)).isFalse()
    }
}
