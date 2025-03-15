package blackjack.domain

import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player
import blackjack.domain.participant.PlayerState
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class ParticipantsTest {
    @Test
    fun `플레이어 이름이 중복되면 에러가 발생한다`() {
        val players =
            listOf(
                Player(
                    PlayerState("peto", BettingAmount(1)),
                ),
                Player(
                    PlayerState("peto", BettingAmount(1)),
                ),
            )
        assertThrows<IllegalArgumentException>(
            message = "플레이어 이름은 중복될 수 없습니다.",
        ) {
            Participants(players)
        }
    }

    @MethodSource("invalidPlayerCounts")
    @ParameterizedTest
    fun `플레이어는 최소 2명이다`(players: List<Player>) {
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
                Arguments.of(listOf(PlayerState("peto", BettingAmount(1)))),
                Arguments.of(
                    listOf(
                        PlayerState("peto", BettingAmount(1)),
                        PlayerState("bibi", BettingAmount(1)),
                        PlayerState("hwano", BettingAmount(1)),
                        PlayerState("por", BettingAmount(1)),
                        PlayerState("kream", BettingAmount(1)),
                        PlayerState("eden", BettingAmount(1)),
                        PlayerState("jerry", BettingAmount(1)),
                        PlayerState("tama", BettingAmount(1)),
                        PlayerState("eden", BettingAmount(1)),
                        PlayerState("Lea", BettingAmount(1)),
                    ),
                ),
            )
    }
}
