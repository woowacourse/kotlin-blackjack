package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardGeneratorTest {

    @Test
    fun `카드 숫자를 랜덤으로 발행한다`() {
        val cardGenerator = CardGenerator(TestCardGenerator(CardNumber.TWO, CardShape.CLOVER))
        val actual = cardGenerator.generateCardNumber()
        assertThat(actual).isEqualTo(CardNumber.TWO)
    }

    @Test
    fun `카드 모양을 랜덤으로 발행한다`() {
        val cardGenerator = CardGenerator(TestCardGenerator(CardNumber.TWO, CardShape.HEART))
        val actual = cardGenerator.generateCardShape()
        assertThat(actual).isEqualTo(CardShape.HEART)
    }

    class TestCardGenerator(
        private val number: CardNumber,
        private val shape: CardShape
    ) : Generator {
        override fun generateCardNumber(): CardNumber = number

        override fun generateCardShape(): CardShape = shape
    }
}
