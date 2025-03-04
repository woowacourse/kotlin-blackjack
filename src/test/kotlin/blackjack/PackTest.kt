package blackjack

import blackjack.domain.Pack
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PackTest {
    @Test
    fun `게임에 필요한 카드 52장을 준비한다`() {
        val pack = Pack()
        assertThat(pack.cards.size).isEqualTo(52)
    }
}
