package blackjack.model.participants

import blackjack.model.card.Card
import blackjack.model.card.CardValue
import blackjack.model.card.Shape
import blackjack.model.createBurstDealerGameInfo
import blackjack.model.createDealerOnBlackjackInfo
import blackjack.model.createHitDealerGameInfo
import blackjack.model.createMultiPlayersResultInfo
import blackjack.model.gameInfo.GameInfo
import blackjack.model.gameInfo.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class JudgeTest {
    @Test
    fun `딜러가 모든 플레이어에 대해 승리했을 때 딜러의 최종 수익을 계산한다`() {
        val judge = Judge(createDealerOnBlackjackInfo(), createMultiPlayersResultInfo())
        val actualResult: Money = judge.getDealerIncome()
        assertThat(actualResult.amount).isEqualTo(6000)
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
        assertThat(actualResult.amount).isEqualTo(4000)
    }

    @Test
    fun `딜러가 버스트되었을 때 딜러의 최종 수익을 계산한다`() {
        val judge = Judge(createBurstDealerGameInfo(), createMultiPlayersResultInfo())
        val actualResult: Money = judge.getDealerIncome()
        assertThat(actualResult.amount).isEqualTo(-4000)
    }

    @Test
    fun `플레이어가 초기화된 2개의 카드로 딜러에 대해 블랙잭 승리하였을 때 플레이어의 최종 수익을 계산한다`() {
        val players =
            listOf(
                GameInfo(
                    name = "케이엠",
                    moneyAmount = Money(1000),
                    cards =
                        setOf(
                            Card.of(Shape.CLOVER, CardValue.ACE, 0),
                            Card.of(Shape.HEART, CardValue.K, 11),
                        ),
                ),
            )
        val judge = Judge(createHitDealerGameInfo(), players)
        val actualResult = judge.getPlayersIncome().first()
        assertThat(actualResult.amount).isEqualTo(1500)
    }

    @Test
    fun `플레이어가 딜러에 대해 승리하였지만 초기에 뽑은 카드가 블랙잭은 아닐 때 플레이어의 최종 수익을 계산한다`() {
        val players =
            listOf(
                GameInfo(
                    name = "케이엠",
                    moneyAmount = Money(1000),
                    cards =
                        setOf(
                            Card.of(Shape.CLOVER, CardValue.SEVEN, 0),
                            Card.of(Shape.HEART, CardValue.SEVEN, 7),
                            Card.of(Shape.SPADE, CardValue.SIX, 14),
                        ),
                ),
            )
        val judge = Judge(createHitDealerGameInfo(), players)
        val actualResult = judge.getPlayersIncome().first()
        assertThat(actualResult.amount).isEqualTo(1000)
    }

    @Test
    fun `플레이어가 딜러에 대해 패배했지만 버스트는 아닐 때 플레이어의 최종 수익을 계산한다`() {
        val players =
            listOf(
                GameInfo(
                    name = "케이엠",
                    moneyAmount = Money(1000),
                    cards =
                        setOf(
                            Card.of(Shape.CLOVER, CardValue.SEVEN, 0),
                            Card.of(Shape.HEART, CardValue.SEVEN, 7),
                        ),
                ),
            )
        val judge = Judge(createHitDealerGameInfo(), players)
        val actualResult = judge.getPlayersIncome().first()
        assertThat(actualResult.amount).isEqualTo(-1000)
    }

    @Test
    fun `플레이어가 버스트되었을 때의 최종 수익을 계산한다`() {
        val players =
            listOf(
                GameInfo(
                    name = "케이엠",
                    moneyAmount = Money(1000),
                    cards =
                        setOf(
                            Card.of(Shape.CLOVER, CardValue.SEVEN, 0),
                            Card.of(Shape.HEART, CardValue.SEVEN, 7),
                            Card.of(Shape.SPADE, CardValue.Q, 14),
                        ),
                ),
            )
        val judge = Judge(createHitDealerGameInfo(), players)
        val actualResult = judge.getPlayersIncome().first()
        assertThat(actualResult.amount).isEqualTo(-1000)
    }

    @Test
    fun `딜러와 플레이어가 동점일 때 플레이어의 최종 수익을 계산한다`() {
        val players =
            listOf(
                GameInfo(
                    name = "케이엠",
                    moneyAmount = Money(1000),
                    cards =
                        setOf(
                            Card.of(Shape.CLOVER, CardValue.SIX, 0),
                            Card.of(Shape.HEART, CardValue.K, 6),
                        ),
                ),
            )
        val judge = Judge(createHitDealerGameInfo(), players)
        val actualResult = judge.getPlayersIncome().first()
        assertThat(actualResult.amount).isEqualTo(0)
    }
}
