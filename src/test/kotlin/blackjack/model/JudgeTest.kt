package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

val burstDealerInfo: GameInfo =
    GameInfo("딜러").apply {
        addCard(Card(Shape.CLOVER, CardRank.SIX))
        addCard(Card(Shape.HEART, CardRank.K))
        addCard(Card(Shape.DIAMOND, CardRank.K))
    }

val aliveDealerInfo: GameInfo =
    GameInfo(
        "딜러",
    ).apply {
        addCard(Card(Shape.CLOVER, CardRank.SIX))
        addCard(Card(Shape.HEART, CardRank.K))
    }

val playersInfo: List<GameInfo> =
    listOf(
        GameInfo("케이엠").apply {
            addCard(Card(Shape.DIAMOND, CardRank.SEVEN))
            addCard(Card(Shape.DIAMOND, CardRank.K))
        },
        GameInfo("해음").apply {
            addCard(Card(Shape.DIAMOND, CardRank.SIX))
            addCard(Card(Shape.SPADE, CardRank.K))
        },
        GameInfo("차람").apply {
            addCard(Card(Shape.DIAMOND, CardRank.FIVE))
            addCard(Card(Shape.CLOVER, CardRank.K))
        },
    )

class JudgeTest {
    @Test
    fun `딜러가 버스트 되지 않았을 때 경기 결과를 계산한다`() {
        val judge = Judge(aliveDealerInfo, playersInfo)
        val actualResult: Scoreboard = judge.getDealerResult()
        assertThat(actualResult).isEqualTo(Scoreboard(1, 1, 1))
    }

    @Test
    fun `딜러가 버스트 되었을 때 경기 결과를 계산한다`() {
        val judge = Judge(burstDealerInfo, playersInfo)
        val actualResult: Scoreboard = judge.getDealerResult()
        assertThat(actualResult).isEqualTo(Scoreboard(0, 0, 3))
    }

    @Test
    fun `플레이어들의 경기 결과를 계산한다`() {
        val judge = Judge(aliveDealerInfo, playersInfo)
        val actualResult = judge.getPlayerResults()
        assertThat(actualResult).isEqualTo(listOf("승", "무", "패"))
    }
}
