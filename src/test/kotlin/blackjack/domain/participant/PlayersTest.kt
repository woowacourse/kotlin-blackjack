package blackjack.domain.participant

import blackjack.model.participant.Money
import blackjack.model.participant.Name
import blackjack.model.participant.Players
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class PlayersTest {
    private lateinit var players: Players

    @BeforeEach
    fun setup() {
        players = Players.from(listOf("공백", "오이", "시아"))
    }

    @Test
    fun `플레이어 인원 수는 0명 초과이다`() {
        // given & when & then
        assertThrows<IllegalArgumentException> {
            Players.from(emptyList())
        }
    }

    @Test
    fun `플레이어 인원 수는 8명 미만이다`() {
        // given
        val players = List(8) { "Player$it" }

        // when & then
        assertThrows<IllegalArgumentException> {
            Players.from(players)
        }
    }

    @Test
    fun `플레이어 이름은 중복될 수 없다`() {
        // given
        val players = listOf("공백", "공백", "시아")

        // when & then
        assertThrows<IllegalArgumentException> {
            Players.from(players)
        }
    }

    @Test
    fun `유효한 플레이어 목록이면 정상적으로 생성된다`() {
        // given
        val players = listOf("인협", "동주", "민정", "메다", "제이", "디랙", "조이")

        // when & then
        assertDoesNotThrow {
            Players.from(players)
        }
    }

    @Test
    fun `존재하는 플레이어가 돈을 받으면 금액이 증가한다`() {
        // given
        val playerName = Name("공백")
        val initialMoney = players.value.find { it.name == playerName }!!.money
        val additionalMoney = Money(1000.0)

        // when
        players.receiveMoney(playerName, additionalMoney)

        // then
        val updatedMoney = players.value.find { it.name == playerName }!!.money
        assertEquals(initialMoney + additionalMoney, updatedMoney)
    }

    @Test
    fun `존재하지 않는 플레이어에게 돈을 지급해도 변화가 없다`() {
        // given
        val nonExistentPlayer = Name("없는사람")
        val initialMoneyState = players.value.map { it.money }

        // when
        players.receiveMoney(nonExistentPlayer, Money(5000.0))

        // then
        val updatedMoneyState = players.value.map { it.money }
        assertEquals(initialMoneyState, updatedMoneyState)
    }
}
