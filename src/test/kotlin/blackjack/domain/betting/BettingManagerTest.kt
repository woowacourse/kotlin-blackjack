package blackjack.domain.betting

import blackjack.model.betting.BettingManager
import blackjack.model.betting.BettingTable
import blackjack.model.participant.Dealer
import blackjack.model.participant.Money
import blackjack.model.participant.Name
import blackjack.model.participant.Participants
import blackjack.model.participant.Players
import blackjack.model.winning.WinningResult
import blackjack.model.winning.WinningState
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class BettingManagerTest {
    @Test
    fun `베팅 금액이 0 이하이면 예외 발생`() {
        // given
        val bettingManager = BettingManager()
        val players = Players.from(listOf("시아", "공백"))
        val getBettingMoney: (Name) -> Money = { Money.ZERO }

        // when & then
        assertThrows<IllegalArgumentException> {
            bettingManager.getPlayersMoney(players, getBettingMoney)
        }
    }

    @Test
    fun `정상적인 베팅 금액이 입력되면 정상적으로 진행된다`() {
        // given
        val bettingManager = BettingManager()
        val players = Players.from(listOf("시아", "공백"))
        val getBettingMoney: (Name) -> Money = { Money(1000.0) }

        // when & then
        assertDoesNotThrow {
            bettingManager.getPlayersMoney(players, getBettingMoney)
        }
    }

    @Test
    fun `승패 결과에 따라 올바르게 베팅 결과가 계산된다`() {
        // given
        val dealer = Dealer.create()
        val players = Players.from(listOf("공백", "비비", "메다", "제이"))

        val participants = Participants(dealer, players)
        val bettingTable = BettingTable()
        val money1 = Money(1000.0)
        val money2 = Money(2000.0)
        val money3 = Money(3000.0)
        val money4 = Money(4000.0)

        bettingTable.add(Name("공백"), money1)
        bettingTable.add(Name("비비"), money2)
        bettingTable.add(Name("메다"), money3)
        bettingTable.add(Name("제이"), money4)

        val bettingManager = BettingManager(bettingTable)

        val winningResult =
            WinningResult(
                dealerResult = mapOf(),
                playerResults =
                    mapOf(
                        Name("공백") to WinningState.WIN_BY_BLACKJACK,
                        Name("비비") to WinningState.WIN_DEFAULT,
                        Name("메다") to WinningState.PUSH,
                        Name("제이") to WinningState.LOSE,
                    ),
            )

        // when
        val resultTable = bettingManager.result(winningResult, participants)

        // then
        assertEquals(money1.multiply(1.5), resultTable.value[Name("공백")])
        assertEquals(money2.multiply(1.0), resultTable.value[Name("비비")])
        assertEquals(money3.multiply(0.0), resultTable.value[Name("메다")])
        assertEquals(money4.multiply(-1.0), resultTable.value[Name("제이")])
    }
}
