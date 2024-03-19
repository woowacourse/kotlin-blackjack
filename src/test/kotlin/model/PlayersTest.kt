package model

import model.human.Players
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.lang.IllegalArgumentException

class PlayersTest {
    private lateinit var hand: Hand

    @BeforeEach
    fun setUp() {
        hand = Hand()
    }

    @Test
    fun `참여하는 플레이어의 수가 10명을 초과하면 예외 발생`() {
        val names = listOf<String>("12", "34", "56", "78", "99", "13", "14", "15", "16", "18", "19")
        assertThatThrownBy {
            Players.ofList(names)
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(Players.ERROR_EXCEED_PLAYERS)
    }

    @Test
    fun `참여하는 플레이어가 없으면 예외 발생`() {
        val names = listOf<String>()
        assertThatThrownBy {
            Players.ofList(names)
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(Players.ERROR_EXCEED_PLAYERS)
    }

    @Test
    fun `참여하는 플레이어의 수는 1 ~ 10명이다`() {
        val names = listOf<String>("12", "34", "56")
        assertDoesNotThrow {
            Players.ofList(names)
        }
    }
}
