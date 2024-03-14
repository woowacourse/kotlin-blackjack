package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDeckGeneratorTest {
    @Test
    fun `13개의 숫자와 4개의 모양으로 구성된 카드 52개를 생성한다`() {
        val cardDeck = CardDeckGenerator.generate()

        assertThat(cardDeck.getCards().size).isEqualTo(52)

        CardShape.entries.forEach { shape ->
            val count =
                cardDeck.getCards().count { card ->
                    card.shape == shape
                }
            assertThat(count).isEqualTo(13)
        }

        CardNumber.entries.forEach { number ->
            val count =
                cardDeck.getCards().count { card ->
                    card.number == number
                }
            assertThat(count).isEqualTo(4)
        }
    }
}
