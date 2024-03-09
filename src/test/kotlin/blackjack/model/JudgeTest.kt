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
}
