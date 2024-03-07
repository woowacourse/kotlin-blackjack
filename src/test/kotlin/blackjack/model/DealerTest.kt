package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

fun getPlayers(numberOfPlayers: Int): List<String> {
    return List(numberOfPlayers) { "player$it" }
}

class DealerTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 9])
    fun `참여자 수가 허용 범위를 벗어나면, 예외를 발생시킨다`(numberOfPlayers: Int) {
        val players = getPlayers(numberOfPlayers)
        val exception =
            assertThrows<IllegalArgumentException> {
                Dealer(players)
            }
        assertThat(exception.message).isEqualTo("플레이어의 수로 ${players.size}를 입력했습니다. 플레이어 수는 2부터 8까지 가능합니다.")
    }

    @Test
    fun `참여자 이름이 중복이 있으면, 예외를 발생시킨다`() {
        val players = listOf("케이엠", "케이엠", "해음")
        val exception =
            assertThrows<IllegalArgumentException> {
                Dealer(players)
            }
        assertThat(exception.message).isEqualTo("중복된 플레이어의 이름을 입력하셨습니다.")
    }

    @Test
    fun `딜러 카드의 총합을 계산한다`() {
        val dealer =
            Dealer(
                players = getPlayers(2),
                gameInfo =
                    GameInfo(
                        "딜러",
                        setOf(
                            Card.of(Shape.CLOVER, CardValue.SIX, 0),
                            Card.of(Shape.HEART, CardValue.K, 6),
                        ),
                    ),
            )

        assertThat(dealer.gameInfo.total).isEqualTo(16)
    }

    @Test
    fun `딜러의 카드 총합이 카드를 뽑을 수 있는 총합의 최댓값 이하일 때, 카드를 더 받을 수 있다`() {
        val dealer =
            Dealer(
                players = getPlayers(2),
                gameInfo =
                    GameInfo(
                        "딜러",
                        setOf(
                            Card.of(Shape.CLOVER, CardValue.SIX, 0),
                            Card.of(Shape.HEART, CardValue.K, 6),
                        ),
                    ),
            )

        val pickingState =
            dealer.drawCard {
                Card.of(Shape.CLOVER, CardValue.TWO, 16)
            }

        val actualSize = dealer.gameInfo.cards.size
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
                players = getPlayers(2),
                gameInfo =
                    GameInfo(
                        "딜러",
                        setOf(
                            Card.of(Shape.CLOVER, CardValue.SEVEN, 0),
                            Card.of(Shape.HEART, CardValue.K, 7),
                        ),
                    ),
            )

        val pickingState =
            dealer.drawCard {
                Card.of(Shape.CLOVER, CardValue.TWO, 17)
            }

        val actualSize = dealer.gameInfo.cards.size
        val expectedSize = 2

        assertAll({
            assertThat(actualSize).isEqualTo(expectedSize)
        }, {
            assertThat(pickingState).isEqualTo(PickingState.STAND)
        })
    }

    @Test
    fun `딜러의 카드 총합이 카드를 뽑을 수 있는 총합의 최댓값 이하인 상태에서 1을 뽑았을 때, 올바른 총합을 계산한다`() {
        val dealer =
            Dealer(
                players = getPlayers(2),
                gameInfo =
                    GameInfo(
                        "딜러",
                        setOf(
                            Card.of(Shape.CLOVER, CardValue.SIX, 0),
                            Card.of(Shape.HEART, CardValue.K, 6),
                        ),
                    ),
            )

        dealer.drawCard {
            Card.of(Shape.CLOVER, CardValue.ONE, 16)
        }

        val actualTotal = dealer.gameInfo.cards.sumOf { it.value }
        val expectedTotal = 17

        assertThat(actualTotal).isEqualTo(expectedTotal)
    }

    @Test
    fun `딜러의 카드 총합이 카드를 뽑을 수 있는 총합의 최댓값을 초과한 상태에서 1을 뽑았을 때, 올바른 총합을 계산한다`() {
        val dealer =
            Dealer(
                players = getPlayers(2),
                gameInfo =
                    GameInfo(
                        "딜러",
                        setOf(
                            Card.of(Shape.CLOVER, CardValue.SEVEN, 0),
                            Card.of(Shape.HEART, CardValue.K, 7),
                        ),
                    ),
            )

        dealer.drawCard {
            Card.of(Shape.CLOVER, CardValue.ONE, 17)
        }

        val actualTotal = dealer.gameInfo.cards.sumOf { it.value }
        val expectedTotal = 17

        assertThat(actualTotal).isEqualTo(expectedTotal)
    }

    @ParameterizedTest
    @MethodSource("provideCards")
    fun `숫자가 1인 카드를 뽑았을 때, 딜러의 카드 총합에 따라서 올바른 총합을 계산한다`(providedCard: Map<Card, Int>) {
        val dealer =
            Dealer(
                players = getPlayers(2),
                gameInfo = GameInfo("딜러", providedCard.keys),
            )
        dealer.drawCard {
            Card.of(Shape.CLOVER, CardValue.ONE, dealer.gameInfo.cards.sumOf { it.value })
        }

        val actualTotal = dealer.gameInfo.cards.sumOf { it.value }
        val expectedTotal = providedCard.values.sum()

        assertThat(actualTotal).isEqualTo(expectedTotal)
    }

    companion object {
        @JvmStatic
        fun provideCards(): Stream<Map<Card, Int>> {
            return Stream.of(
                mapOf(Card.of(Shape.HEART, CardValue.K, 0) to 21),
                mapOf(Card.of(Shape.HEART, CardValue.ONE, 0) to 12),
            )
        }
    }
}
