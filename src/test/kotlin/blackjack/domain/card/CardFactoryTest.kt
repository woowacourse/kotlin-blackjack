package blackjack.domain.card

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardFactoryTest {
    @Test
    fun `52장의 카드를 생성한다`() {
        // When
        val cards = CardFactory.create()

        // Then
        cards.size shouldBe 52
    }
}
