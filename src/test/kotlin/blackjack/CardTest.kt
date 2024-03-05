package blackjack

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

sealed class Card(val name: String) {
    companion object {}
}

data class Spade(val number: Int) : Card("스페이드")

data class Heart(val number: Int) : Card("하트")

data class Diamond(val number: Int) : Card("다이아")

data class Club(val number: Int) : Card("클로버")

// CardType
class CardTest {
    @Test
    fun `카드 종류는 하트, 클로버, 다이아, 스페이드다`() {
        assertDoesNotThrow { Spade(1) }
        assertDoesNotThrow { Heart(1) }
        assertDoesNotThrow { Diamond(1) }
        assertDoesNotThrow { Club(1) }
    }
}
