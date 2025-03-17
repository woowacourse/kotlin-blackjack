package blackjack.domain.betting

import blackjack.model.betting.BettingTable
import blackjack.model.participant.Money
import blackjack.model.participant.Name
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BettingTableTest {
    private lateinit var bettingTable: BettingTable

    @BeforeEach
    fun setup() {
        bettingTable = BettingTable()
    }

    @Test
    fun `새로운 플레이어가 베팅하면 해당 플레이어의 이름과 금액이 추가된다`() {
        // given
        val name = Name("공백")
        val money = Money(1000.0)

        // when
        bettingTable.add(name, money)

        // then
        assertEquals(money, bettingTable.get(name))
    }

    @Test
    fun `같은 플레이어가 다시 베팅하면 기존 금액에서 더해진다`() {
        // given
        val name = Name("비비")
        val money1 = Money(10000.0)
        val money2 = Money(20000.0)

        // when
        bettingTable.add(name, money1)
        bettingTable.add(name, money2)

        // then
        assertEquals(Money(30000.0), bettingTable.get(name))
    }

    @Test
    fun `존재하지 않는 플레이어의 금액을 가져오면 0원을 반환한다`() {
        // given
        val unknownName = Name("오이")

        // when
        val money = bettingTable.get(unknownName)

        // then
        assertEquals(Money.ZERO, money)
    }

    @Test
    fun `reset 호출 후 모든 베팅 금액이 사라진다`() {
        // given
        val name1 = Name("메다")
        val name2 = Name("공백")
        bettingTable.add(name1, Money(3000.0))
        bettingTable.add(name2, Money(4000.0))

        // when
        bettingTable.reset()

        // then
        assertEquals(Money.ZERO, bettingTable.get(name1))
        assertEquals(Money.ZERO, bettingTable.get(name2))
        assertTrue(bettingTable.table.value.isEmpty())
    }
}
