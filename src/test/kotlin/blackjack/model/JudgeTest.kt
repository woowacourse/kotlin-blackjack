package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

val burstDealerInfo: GameInfo =
    GameInfo(
        "딜러",
        setOf(
            Card.of(Shape.CLOVER, CardValue.SIX, 0),
            Card.of(Shape.HEART, CardValue.K, 6),
            Card.of(Shape.DIAMOND, CardValue.K, 16),
        ),
    )

val aliveDealerInfo: GameInfo =
    GameInfo(
        "딜러",
        setOf(
            Card.of(Shape.CLOVER, CardValue.SIX, 0),
            Card.of(Shape.HEART, CardValue.K, 6),
        ),
    )

val playersInfo: List<GameInfo> =
    listOf(
        GameInfo(
            "케이엠",
            setOf(
                Card.of(Shape.DIAMOND, CardValue.SEVEN, 0),
                Card.of(Shape.DIAMOND, CardValue.K, 7),
            ),
        ),
        GameInfo(
            "해음",
            setOf(
                Card.of(Shape.DIAMOND, CardValue.SIX, 0),
                Card.of(Shape.SPADE, CardValue.K, 6),
            ),
        ),
        GameInfo(
            "차람",
            setOf(
                Card.of(Shape.DIAMOND, CardValue.FIVE, 0),
                Card.of(Shape.CLOVER, CardValue.K, 5),
            ),
        ),
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
