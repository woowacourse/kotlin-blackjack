package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardGeneratorTest {

    @Test
    fun `카드 숫자를 랜덤으로 발행한다`() {
        val cardGenerator = CardGenerator(TestCardGenerator(CardNumber.TWO))
        val actual = cardGenerator.generateCardNumber()
        assertThat(actual).isEqualTo(CardNumber.TWO)
    }

    class TestCardGenerator(private val number: CardNumber) : Generator {
        override fun generateCardNumber(): CardNumber {
            return number
        }
    }
}
