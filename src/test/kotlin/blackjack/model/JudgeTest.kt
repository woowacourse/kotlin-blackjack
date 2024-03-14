package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class JudgeTest {
    @Test
    fun `딜러가 버스트 되지 않았을 때 경기 결과를 계산한다`() {
        val judge = Judge(createHitDealerGameInfo(), createMultiplePlayersGameInfo())
        val actualResult: Scoreboard = judge.getDealerResult()
        assertThat(actualResult).isEqualTo(Scoreboard(1, 1, 1))
    }

    @Test
    fun `딜러가 버스트 되었을 때 경기 결과를 계산한다`() {
        val judge = Judge(createBurstDealerGameInfo(), createMultiplePlayersGameInfo())
        val actualResult: Scoreboard = judge.getDealerResult()
        assertThat(actualResult).isEqualTo(Scoreboard(0, 0, 3))
    }

    @Test
    fun `플레이어들의 경기 결과를 계산한다`() {
        val judge = Judge(createHitDealerGameInfo(), createMultiplePlayersGameInfo())
        val actualResult = judge.getPlayerResults()
        assertThat(actualResult).isEqualTo(listOf("승", "무", "패"))
    }

    @Test
    fun `딜러가 모든 플레이어에 대해 승리했을 때 딜러의 최종 수익을 계산한다`() {
        val judge = Judge(createDealerOnBlackjackInfo(), createMultiPlayersResultInfo())
        val actualResult: Money = judge.getDealerIncome()
        assertThat(actualResult).isEqualTo(Money(6000))
    }

    @Test
    fun `딜러가 일부 플레이어에 대해 승리했을 때 딜러의 최종 수익을 계산한다`() {
        val dealer =
            GameInfo(
                "딜러",
                cards =
                    setOf(
                        Card.of(Shape.CLOVER, CardValue.SEVEN, 0),
                        Card.of(Shape.HEART, CardValue.SEVEN, 7),
                        Card.of(Shape.SPADE, CardValue.FIVE, 14),
                    ),
            )
        val judge = Judge(dealer, createMultiPlayersResultInfo())
        val actualResult: Money = judge.getDealerIncome()
        assertThat(actualResult).isEqualTo(Money(4000))
    }

    @Test
    fun `딜러가 버스트되었을 때 딜러의 최종 수익을 계산한다`() {
        val judge = Judge(createBurstDealerGameInfo(), createMultiPlayersResultInfo())
        val actualResult: Money = judge.getDealerIncome()
        assertThat(actualResult).isEqualTo(Money(-4000))
    }
}
