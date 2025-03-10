package blackjack.domain.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ParticipantsTest {
    @Test
    fun `플레이어들의 이름 중 중복이 있을 시 오류가 발생한다`() {
        assertThrows<IllegalArgumentException> { Participants(Dealer(), listOf(Player("A"), Player("A"))) }
    }

    @Test
    fun `딜러와 플레이어의 이름이 중복될 시 오류가 발생한다`() {
        assertThrows<IllegalArgumentException> { Participants(Dealer("딜러"), listOf(Player("딜러"), Player("A"))) }
    }
}
