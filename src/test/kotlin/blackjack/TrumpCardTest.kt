package blackjack

import blackjack.model.CardNumber
import blackjack.model.TrumpCardType
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

@JvmInline
value class PointEachCard(val amount: Int) {
    init {
        require(amount in 1..11)
    }
}

data class TrumpCard2(val type: TrumpCardType, val number: CardNumber)

// CardType
class TrumpCardTest {
    @Test
    fun `카드 종류는 하트, 클로버, 다이아, 스페이드다`() {
        assertDoesNotThrow { TrumpCard2(TrumpCardType.CLUB, CardNumber.Six) }
        assertDoesNotThrow { TrumpCard2(TrumpCardType.CLUB, CardNumber.Six) }
    }
}
