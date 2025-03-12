package blackjack.domain.model

import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Player
import blackjack.domain.model.participant.PlayerGroup
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerGroupTest {
    @Test
    fun `참여자의 최대 인원은 8명이다`() {
        // given
        val playersName = listOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "h")
        // when & then
        assertThrows<IllegalArgumentException> {
            PlayerGroup(playersName.map(::Player) + Dealer())
        }
    }

    @Test
    fun `참여자의 이름은 중복될 수 없다`() {
        // given
        val playersName = listOf("jerry", "jerry")
        // when & then
        assertThrows<IllegalArgumentException> {
            PlayerGroup(playersName.map(::Player) + Dealer())
        }
    }
}
