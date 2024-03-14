package model.participants

import model.card.Card
import model.card.Deck
import model.card.MarkType
import model.card.ValueType
import model.makeTestDeck
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.lang.IllegalArgumentException

class PlayersTest {
    private lateinit var testDeck: Deck

    @BeforeEach
    fun setUp() {
        testDeck = makeTestDeck(Card(ValueType.TWO, MarkType.SPADE), Card(ValueType.THREE, MarkType.SPADE))
    }

    @Test
    fun `참여하는 플레이어의 수가 10명을 초과하면 예외 발생`() {
        val names = listOf<String>("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k")
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
        val names = listOf<String>("a", "b", "c")
        assertDoesNotThrow {
            Players.ofList(names)
        }
    }
}
