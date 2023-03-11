package domain.person

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class Participants2Test {
    @Test
    fun `플레이어 이름은 중복되면 안된다`() {
        // given
        val dealer = Dealer2()
        val players =
            listOf(
                Player2("베르"),
                Player2("베르"),
            )

        // then
        assertThrows<IllegalArgumentException> { Participants2(dealer, players) }
    }
}
