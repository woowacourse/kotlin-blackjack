package blackjack

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

sealed class TrumpCard(val name: String) {
    companion object {}
}

data class Spade(val number: Int) : TrumpCard("스페이드")

data class Heart(val number: Int) : TrumpCard("하트")

data class Diamond(val number: Int) : TrumpCard("다이아")

data class Club(val number: Int) : TrumpCard("클로버")

// ---------------
data class Club2(val number: String) : TrumpCard("클로버")

data class Spade2(val number: String) : TrumpCard("스페이드")

data class Heart2(val number: String) : TrumpCard("하트")

data class Diamond2(val number: String) : TrumpCard("다이아")

@JvmInline
value class PointEachCard(val amount: Int) {
    init {
        require(amount in 1..11)
    }
}

enum class CardNumber(val label: String, val point: Int) {
    ACE("1", 1),
    TWO("2", 2),
    Three("3", 3),
    Four("4", 4),
    Five("5", 5),
    Six("6", 6),
    Seven("7", 7),
    Eight("8", 8),
    Nine("9", 9),
    Ten("10", 10),
    Jack("J", 10),
    Queen("Q", 10),
    King("K", 10),
}

// CardType
class TrumpCardTest {
    @Test
    fun `카드 종류는 하트, 클로버, 다이아, 스페이드다`() {
        assertDoesNotThrow { Spade(1) }
        assertDoesNotThrow { Heart(1) }
        assertDoesNotThrow { Diamond(1) }
        assertDoesNotThrow { Club(1) }
    }

    @Test
    fun `t`() {
        assertDoesNotThrow { Spade2("15") }
        assertDoesNotThrow { Heart2("K") }
        assertDoesNotThrow { Diamond2("1") }
        assertDoesNotThrow { Club2("A") }
    }
}
