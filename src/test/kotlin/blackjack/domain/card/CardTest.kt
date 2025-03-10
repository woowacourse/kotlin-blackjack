package blackjack.domain.card

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `숫자와 패턴을 가진 카드를 생성한다`() {
        val card = Card.create(CardNumber.ACE, CardPattern.HEART)

        assertSoftly(card) {
            number shouldBe CardNumber.ACE
            pattern shouldBe CardPattern.HEART
        }
    }
}
