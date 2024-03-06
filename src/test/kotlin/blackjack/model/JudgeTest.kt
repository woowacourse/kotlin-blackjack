package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

val cards =
    setOf(
        Card.of(Shape.CLOVER, CardValue.SIX, 0),
        Card.of(Shape.HEART, CardValue.K, 6),
    )

class JudgeTest {
    @Test
    fun `딜러가 나머지 두명보다 총합이 기준에 더 가까울 때 승리한다`() {
        val dealerStat = Stat("딜러", 21, cards)
        val playerStats = listOf(Stat("케이엠", 20, cards), Stat("해음", 22, cards))
        val judge = Judge(dealerStat, playerStats)
        val c: DealerResult = judge.getDealerResult()
        assertThat(c).isEqualTo(DealerResult(2, 0, 0))
    }

    @Test
    fun `딜러가 나머지 두명보다 총합이 같을때 무승부한다`() {
        val dealerStat = Stat("딜러", 21, cards)
        val playerStats = listOf(Stat("케이엠", 21, cards), Stat("해음", 21, cards))
        val judge = Judge(dealerStat, playerStats)
        val c: DealerResult = judge.getDealerResult()
        assertThat(c).isEqualTo(DealerResult(0, 2, 0))
    }

    @Test
    fun `딜러만 버스트 되었을때 패배한다`() {
        val dealerStat = Stat("딜러", 22, cards)
        val playerStats = listOf(Stat("케이엠", 21, cards), Stat("해음", 21, cards))
        val judge = Judge(dealerStat, playerStats)
        val c: DealerResult = judge.getDealerResult()
        assertThat(c).isEqualTo(DealerResult(0, 0, 2))
    }

    @Test
    fun `딜러가 나머지 두명보다 총합이 기준에 더 멀 때 패배한다`() {
        val dealerStat = Stat("딜러", 19, cards)
        val playerStats = listOf(Stat("케이엠", 20, cards), Stat("해음", 21, cards))
        val judge = Judge(dealerStat, playerStats)
        val c: DealerResult = judge.getDealerResult()
        assertThat(c).isEqualTo(DealerResult(0, 0, 2))
    }
}
