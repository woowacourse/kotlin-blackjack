package blackjack.domain

import domain.CardPackGenerator
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class CardPackGeneratorTest {
    @Test
    fun `카드팩을 중복 없이 52장 생성한다`() {
        assertDoesNotThrow {
            CardPackGenerator().createCardPack()
        }
    }
}
