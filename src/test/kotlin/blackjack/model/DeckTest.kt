package blackjack.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `카드 덱에 Ace, Queen이 있을 때, 카드 점수는 21이다`() {
        val deck = Deck(listOf(Card(Pattern.HEART, CardNumber.ACE), Card(Pattern.HEART, CardNumber.QUEEN)))
        Assertions.assertThat(deck.calculate()).isEqualTo(21)
    }

    @Test
    fun `카드 덱에 Ace, Queen, King이 있을 때, 카드 점수는 21이다`() {
        val deck =
            Deck(
                listOf(
                    Card(Pattern.HEART, CardNumber.ACE),
                    Card(Pattern.HEART, CardNumber.QUEEN),
                    Card(Pattern.HEART, CardNumber.KING),
                ),
            )
        Assertions.assertThat(deck.calculate()).isEqualTo(21)
    }

    @Test
    fun `카드 덱에 Ace 두 장과 9 한 장이 있을 때, 카드 점수는 21이다`() {
        val deck =
            Deck(
                listOf(
                    Card(Pattern.HEART, CardNumber.ACE),
                    Card(Pattern.SPACE, CardNumber.ACE),
                    Card(Pattern.HEART, CardNumber.NINE),
                ),
            )
        Assertions.assertThat(deck.calculate()).isEqualTo(21)
    }

    @Test
    fun `카드 덱에 Ace 두 장, King 한 장, 9 한 장이 있을 때, 카드 점수는 21이다`() {
        val deck =
            Deck(
                listOf(
                    Card(Pattern.HEART, CardNumber.ACE),
                    Card(Pattern.SPACE, CardNumber.ACE),
                    Card(Pattern.SPACE, CardNumber.KING),
                    Card(Pattern.HEART, CardNumber.NINE),
                ),
            )
        Assertions.assertThat(deck.calculate()).isEqualTo(21)
    }
}
