package domain.result

import domain.Dummy.CLOVER_ACE
import domain.Dummy.CLOVER_FIVE
import domain.Dummy.CLOVER_SIX
import domain.Dummy.CLOVER_TEN
import domain.Dummy.CLOVER_TWO
import domain.person.Dealer
import domain.person.Persons
import domain.person.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CasinoTest {
    private lateinit var casino: Casino

    @BeforeEach
    private fun setUp() {
        val dealer = Dealer(CLOVER_ACE, CLOVER_SIX).apply { toNextState(CLOVER_TWO) }
        val players = listOf(
            Player("BIX", CLOVER_SIX, CLOVER_TEN).apply { toStay() },
            Player("VER", CLOVER_ACE, CLOVER_TEN).apply { toNextState(CLOVER_FIVE) },
            Player("MAT", CLOVER_ACE, CLOVER_SIX).apply { toStay() },
        )
        val persons = Persons(dealer, players)
        val bets = listOf<Double>(8000.0, 10000.0, 9000.0)
        casino = Casino(persons, bets)
    }

    @Test
    fun `플레이어의 이익을 계산한다`() {
        val playersProfit = casino.getPlayersProfit()
        val expected = mapOf<String, Double>(
            "BIX" to -8000.0,
            "VER" to 15000.0,
            "MAT" to 0.0,
        )

        assertThat(playersProfit).isEqualTo(expected)
    }

    @Test
    fun `딜러의 이익을 계산한다`() {
        val dealerProfit = casino.getDealerProfit()

        assertThat(dealerProfit).isEqualTo(-7000.0)
    }
}
