package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PlayerTest {
    @Test
    fun `플레이어는 이름과 카드 리스트를 가진다`() {
        val cards = listOf((Card(CardShape.HEART, Denomination.FIVE)), Card(CardShape.CLOVER, Denomination.TWO))
        val player = Player("모찌", Cards(cards))
        assertAll({
            assertThat(player.name).isEqualTo("모찌")
            assertThat(player.cards.value).isEqualTo(cards)
        })
    }

    @Test
    fun `플레이어는 카드를 추가로 받을 수 있다`() {
        val initialCards =
            Cards(
                listOf((Card(CardShape.HEART, Denomination.FIVE)), Card(CardShape.CLOVER, Denomination.TWO)),
            )
        val player = Player("모찌", initialCards)
        val card = Card(CardShape.CLOVER, Denomination.SIX)
        player.appendCard(card)

        assertThat(player.cards.value).isEqualTo(
            listOf(
                Card(CardShape.HEART, Denomination.FIVE),
                Card(CardShape.CLOVER, Denomination.TWO),
                Card(CardShape.CLOVER, Denomination.SIX),
            ),
        )
    }

    @Test
    fun `플레이어가 이름만 가질 경우, 가진 카드 리스트는 비어있다`() {
        val player = Player("모찌")
        assertThat(player.cards.value.size).isEqualTo(0)
    }

    @Test
    fun `플레이어의 턴이 처음이고, 카드가 blackjack이면 true를 반환한다`() {
        val cards =
            Cards(
                listOf(
                    Card(CardShape.HEART, Denomination.TEN),
                    Card(CardShape.CLOVER, Denomination.ACE),
                ),
            )
        val player = Player("모찌", cards)

        val actual = player.isBlackjack(true)

        assertThat(actual).isEqualTo(true)
    }

    @Test
    fun `플레이어의 턴이 처음이고, 카드가 blackjack이 아니면 false를 반환한다`() {
        val cards =
            Cards(
                listOf(
                    Card(CardShape.HEART, Denomination.TEN),
                    Card(CardShape.DIAMOND, Denomination.NINE),
                ),
            )
        val player = Player("모찌", cards)

        val actual = player.isBlackjack(true)

        assertThat(actual).isEqualTo(false)
    }

    @Test
    fun `플레이어의 카드가 bust이면 true를 반환한다`() {
        val cards =
            Cards(
                listOf(
                    Card(CardShape.HEART, Denomination.TEN),
                    Card(CardShape.CLOVER, Denomination.TEN),
                    Card(CardShape.DIAMOND, Denomination.NINE),
                ),
            )
        val player = Player("모찌", cards)

        val actual = player.isBust()

        assertThat(actual).isEqualTo(true)
    }

    @Test
    fun `플레이어의 카드가 bust가 아니면 false를 반환한다`() {
        val cards =
            Cards(
                listOf(
                    Card(CardShape.HEART, Denomination.TEN),
                    Card(CardShape.DIAMOND, Denomination.NINE),
                ),
            )
        val player = Player("모찌", cards)

        val actual = player.isBust()

        assertThat(actual).isEqualTo(false)
    }

    @ParameterizedTest
    @CsvSource(value = ["WIN, LOSE", "LOSE, WIN", "PUSH, PUSH"])
    fun `딜러의 게임 결과를 받아서 반대의 값을 저장한다`(
        dealerGameResult: GameResult,
        expected: GameResult,
    ) {
        val player = Player("모찌")
        player.updateResult(dealerGameResult)

        assertThat(player.result).isEqualTo(expected)
    }
}
