package model.participants

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.lang.IllegalArgumentException

class PlayersTest {
    @Test
    fun `참여하는 플레이어의 수가 10명을 초과하면 예외 발생`() {
        val names = listOf<String>("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k")
        val money = names.map { Money(1000) }

        assertThatThrownBy {
            Players.ofList(names, money)
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(Players.ERROR_EXCEED_PLAYERS)
    }

    @Test
    fun `참여하는 플레이어가 없으면 예외 발생`() {
        val names = listOf<String>()
        val money = names.map { Money(1000) }
        assertThatThrownBy {
            Players.ofList(names, money)
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(Players.ERROR_EXCEED_PLAYERS)
    }

    @Test
    fun `참여하는 플레이어의 수는 1 ~ 10명이다`() {
        val names = listOf<String>("a", "b", "c")
        val money = names.map { Money(1000) }
        assertDoesNotThrow {
            Players.ofList(names, money)
        }
    }
}
