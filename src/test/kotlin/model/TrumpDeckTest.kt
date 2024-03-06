package model

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class TrumpDeckTest {
    @Test
    fun `모든 카드는 중복되지 않는다`() {
        val trumpDeck = TrumpDeck()
        trumpDeck.cards.toSet().size shouldBe trumpDeck.cards.size
    }

    @Test
    fun `카드의 총 갯수는 52장이다`() {
        TrumpDeck().cards.size shouldBe 52
    }
}
