package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class CardsGeneratorTest {
    private val cards = CardsGenerator().generateCards()

    @Test
    fun `게임에 사용될 52장의 카드를 생성한다`() {
        val cardsLength = cards.totalCount
        assertThat(cardsLength).isEqualTo(52)
    }

    @ParameterizedTest
    @MethodSource("testDeck")
    fun `카드 목록에 생성된 카드 이름이 있는지 검증한다`(card: Pair<String, String>) {
        val result = cards.names.contains(card)
        Assertions.assertTrue(result)
    }

    companion object {
        @JvmStatic
        private fun testDeck() = listOf(
            "ACE" to "HEART",
            "ACE" to "SPADE",
            "ACE" to "CLUB",
            "ACE" to "DIAMOND",

            "KING" to "HEART",
            "KING" to "SPADE",
            "KING" to "CLUB",
            "KING" to "DIAMOND",

            "QUEEN" to "HEART",
            "QUEEN" to "SPADE",
            "QUEEN" to "CLUB",
            "QUEEN" to "DIAMOND",

            "JACK" to "HEART",
            "JACK" to "SPADE",
            "JACK" to "CLUB",
            "JACK" to "DIAMOND",

            "TEN" to "HEART",
            "TEN" to "SPADE",
            "TEN" to "CLUB",
            "TEN" to "DIAMOND",

            "NINE" to "HEART",
            "NINE" to "SPADE",
            "NINE" to "CLUB",
            "NINE" to "DIAMOND",

            "EIGHT" to "HEART",
            "EIGHT" to "SPADE",
            "EIGHT" to "CLUB",
            "EIGHT" to "DIAMOND",

            "SEVEN" to "HEART",
            "SEVEN" to "SPADE",
            "SEVEN" to "CLUB",
            "SEVEN" to "DIAMOND",

            "SIX" to "HEART",
            "SIX" to "SPADE",
            "SIX" to "CLUB",
            "SIX" to "DIAMOND",

            "FIVE" to "HEART",
            "FIVE" to "SPADE",
            "FIVE" to "CLUB",
            "FIVE" to "DIAMOND",

            "FOUR" to "HEART",
            "FOUR" to "SPADE",
            "FOUR" to "CLUB",
            "FOUR" to "DIAMOND",

            "THREE" to "HEART",
            "THREE" to "SPADE",
            "THREE" to "CLUB",
            "THREE" to "DIAMOND",

            "TWO" to "HEART",
            "TWO" to "SPADE",
            "TWO" to "CLUB",
            "TWO" to "DIAMOND"
        ).stream()
    }
}
