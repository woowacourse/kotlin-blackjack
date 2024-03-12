package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class DealerTest {
    @Test
    fun `딜러 카드의 총합을 계산한다`() {
        val dealer =
            Dealer(
                dealerGameInfo = createHitDealerGameInfo(),
            )
        assertThat(dealer.dealerGameInfo.sumOfCards).isEqualTo(16)
    }

    @Test
    fun `딜러의 카드 총합이 카드를 뽑을 수 있는 총합의 최댓값 이하일 때, 카드를 더 받을 수 있다`() {
        val dealer =
            Dealer(
                dealerGameInfo = createHitDealerGameInfo(),
            )

        val pickingState =
            dealer.drawSingleCard {
                Card.of(Shape.CLOVER, CardValue.TWO, 16)
            }

        val actualSize = dealer.dealerGameInfo.cards.size
        val expectedSize = 3

        assertAll(
            { assertThat(actualSize).isEqualTo(expectedSize) },
            { assertThat(pickingState).isEqualTo(PickingState.HIT) },
        )
    }

    @Test
    fun `딜러의 카드 총합이 카드를 뽑을 수 있는 총합의 최댓값을 초과했을 때, 카드를 받지 않는다`() {
        val dealer =
            Dealer(
                dealerGameInfo = createStandDealerGameInfo(),
            )

        val pickingState =
            dealer.drawSingleCard {
                Card.of(Shape.CLOVER, CardValue.TWO, 17)
            }

        val actualSize = dealer.dealerGameInfo.cards.size
        val expectedSize = 2

        assertAll(
            { assertThat(actualSize).isEqualTo(expectedSize) },
            { assertThat(pickingState).isEqualTo(PickingState.STAND) },
        )
    }

    @Test
    fun `딜러의 카드 총합이 카드를 뽑을 수 있는 총합의 최댓값 이하인 상태에서 1을 뽑았을 때, 올바른 총합을 계산한다`() {
        val dealer =
            Dealer(
                dealerGameInfo = createHitDealerGameInfo(),
            )

        dealer.drawSingleCard {
            Card.of(Shape.CLOVER, CardValue.ACE, 16)
        }

        val actualTotal = dealer.dealerGameInfo.cards.sumOf { it.value }
        val expectedTotal = 17
        assertThat(actualTotal).isEqualTo(expectedTotal)
    }

    @Test
    fun `딜러의 카드 총합이 카드를 뽑을 수 있는 총합의 최댓값을 초과한 상태에서 1을 뽑았을 때, 올바른 총합을 계산한다`() {
        val dealer =
            Dealer(
                dealerGameInfo = createStandDealerGameInfo(),
            )

        dealer.drawSingleCard {
            Card.of(Shape.CLOVER, CardValue.ACE, 17)
        }

        val actualTotal = dealer.dealerGameInfo.cards.sumOf { it.value }
        val expectedTotal = 17
        assertThat(actualTotal).isEqualTo(expectedTotal)
    }

    @ParameterizedTest
    @MethodSource("provideCards")
    fun `숫자가 1인 카드를 뽑았을 때, 딜러의 카드 총합에 따라서 올바른 총합을 계산한다`(providedCard: Map<Card, Int>) {
        val dealer =
            Dealer(
                dealerGameInfo = GameInfo("딜러", providedCard.keys),
            )
        dealer.drawSingleCard {
            Card.of(Shape.CLOVER, CardValue.ACE, dealer.dealerGameInfo.cards.sumOf { it.value })
        }

        val actualTotal = dealer.dealerGameInfo.cards.sumOf { it.value }
        val expectedTotal = providedCard.values.sum()
        assertThat(actualTotal).isEqualTo(expectedTotal)
    }

    companion object {
        @JvmStatic
        fun provideCards(): Stream<Map<Card, Int>> {
            return Stream.of(
                mapOf(Card.of(Shape.HEART, CardValue.K, 0) to 21),
                mapOf(Card.of(Shape.HEART, CardValue.ACE, 0) to 12),
            )
        }
    }
}
