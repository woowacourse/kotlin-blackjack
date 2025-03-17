package blackjack.domain.participant

import blackjack.domain.SIX_HEART
import blackjack.domain.TEN_HEART
import blackjack.model.card.Card
import blackjack.model.card.CardCount
import blackjack.model.participant.Money
import blackjack.model.participant.Name
import blackjack.model.participant.PlayerAction
import blackjack.model.participant.Players
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class PlayersTest {
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
        val playerNames = List(8) { Name("Player$it") }

        // when & then
        assertThrows<IllegalArgumentException> {
            Players.from(playerNames)
        }
    }

    @Test
    fun `플레이어 이름은 중복될 수 없다`() {
        // given & when & then
        assertThrows<IllegalArgumentException> {
            Players.from("공백", "공백", "시아")
        }
    }

    @Test
    fun `유효한 플레이어 목록이면 정상적으로 생성된다`() {
        // given & when & then
        assertDoesNotThrow {
            Players.from("인협", "동주", "민정", "메다", "제이", "디랙", "조이")
        }
    }

    @Test
    fun `존재하는 플레이어가 돈을 받으면 금액이 증가한다`() {
        // given
        val players = Players.from("공백", "오이", "시아")
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
        val players = Players.from("공백", "오이", "시아")
        val nonExistentPlayer = Name("없는사람")
        val initialMoneyState = players.value.map { it.money }

        // when
        players.receiveMoney(nonExistentPlayer, Money(5000.0))

        // then
        val updatedMoneyState = players.value.map { it.money }
        assertEquals(initialMoneyState, updatedMoneyState)
    }

    @Test
    fun `플레이어가 HIT을 선택하면 카드를 받는다`() {
        // given
        val players = Players.from("공백")
        val draw: (CardCount) -> List<Card> = { listOf(TEN_HEART) }
        val getCommand: (Name) -> PlayerAction = { PlayerAction.HIT }
        val onCardReceived: (Name, List<Card>) -> Unit = { _, _ -> }

        // when
        players.progressDraw(draw, getCommand, onCardReceived)

        // then
        assertEquals(
            3,
            players.value
                .first()
                .cards.size,
        )
    }

    @Test
    fun `플레이어가 STAY를 선택하면 카드를 받지 않는다`() {
        // given
        val players = Players.from("공백")
        val draw: (CardCount) -> List<Card> = { listOf(SIX_HEART) }
        val getCommand: (Name) -> PlayerAction = { PlayerAction.STAY }
        val onCardReceived: (Name, List<Card>) -> Unit = { _, _ -> }

        // when
        players.progressDraw(draw, getCommand, onCardReceived)

        // then
        assertEquals(
            0,
            players.value
                .first()
                .cards.size,
        )
    }

    @Test
    fun `플레이어가 잘못된 명령을 입력하면 예외가 발생한다`() {
        // given
        val players = Players.from("공백")
        val draw: (CardCount) -> List<Card> = { listOf(SIX_HEART) }
        val getCommand: (Name) -> PlayerAction = { PlayerAction.UNKNOWN }
        val onCardReceived: (Name, List<Card>) -> Unit = { _, _ -> }

        // when & then
        assertThrows<IllegalArgumentException> {
            players.progressDraw(draw, getCommand, onCardReceived)
        }
    }
}
