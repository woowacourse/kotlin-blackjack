package blackjack.domain.carddeck.cardnumbergenerator

import blackjack.Shape.HEART
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RandomCardNumberGeneratorTest {
    private val randomCardNumberGenerator: RandomCardNumberGenerator = RandomCardNumberGenerator()

    @Test
    fun `하트 카드 14장을 뽑을 수 없다`() {
        // given
        val shape = HEART
        repeat(13) { randomCardNumberGenerator.getCardNumber(shape) }

        val actual = randomCardNumberGenerator.getCardNumber(shape)

        assertThat(actual).isNull()
    }
}
