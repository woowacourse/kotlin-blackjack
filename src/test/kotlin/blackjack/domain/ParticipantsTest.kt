package blackjack.domain

import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class ParticipantsTest {
    @Test
    fun `플레이어 이름이 중복되면 에러가 발생한다`() {
        val players = listOf(Player("peto"), Player("peto"))
        assertThrows<IllegalArgumentException>(
            message = "플레이어 이름은 중복될 수 없습니다.",
        ) {
            Participants(players)
        }
    }

    @MethodSource("invalidPlayerCounts")
    @ParameterizedTest
    fun `플레이어는 최소 2명이다`() {
        val players = listOf(Player("peto"))

        assertThrows<IllegalArgumentException>(
            message = "플레이어는 최소 2명부터 최대 8명입니다.",
        ) {
            Participants(players)
        }
    }

    companion object {
        @JvmStatic
        fun invalidPlayerCounts() =
            listOf(
                Arguments.of(Player("peto")),
                Arguments.of(
                    listOf(
                        Player("peto"), Player("bibi"), Player("hwano"), Player("por"),
                        Player("kream"), Player("eden"), Player("tama"), Player("jerry"),
                        Player("Lea"),
                    ),
                ),
            )
    }
}
