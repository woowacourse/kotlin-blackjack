package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.card.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerTest {

    @Test
    fun `Player를 생성할 때 이름을 넘겨줄 수 있다`() {
        assertDoesNotThrow { TestPlayer("수달") }
    }

    @Test
    fun `Player의 이름은 2글자 이상 10글자 이하여야 한다`() {
        assertDoesNotThrow { TestPlayer("berry") }
    }

    @ParameterizedTest
    @ValueSource(strings = ["b", "berryyyyyyy"])
    fun `이름이 2글자 미만이거나 10글자 초과면 오류가 발생한다`(expected: String) {
        assertThrows<IllegalArgumentException> { TestPlayer(expected) }
    }

    @Test
    fun `Player는 카드들을 가진다`() {

        // when
        val player = TestPlayer("aa")

        // then
        assertThat(player.cards).isEqualTo(Cards())
    }

    @Test
    fun `새로운 카드를 발급받으면, Player가 기존에 가지고 있던 카드에 추가한다`() {

        // given
        val player = TestPlayer("aa", Cards(listOf(Card(CardNumber.FOUR, CardShape.HEART))))

        // when
        player.addCard(Card(CardNumber.EIGHT, CardShape.CLOVER))

        // then
        assertThat(player.cards).isEqualTo(
            Cards(
                listOf(
                    Card(CardNumber.FOUR, CardShape.HEART),
                    Card(CardNumber.EIGHT, CardShape.CLOVER)
                )
            )
        )
    }

    @Test
    fun `Player가 갖고 있는 카드 숫자의 합을 계산해 반환한다`() {

        // given
        val player = TestPlayer(
            "aa",
            Cards(listOf(Card(CardNumber.FOUR, CardShape.HEART), Card(CardNumber.EIGHT, CardShape.CLOVER)))
        )

        // when
        val actual: Int = player.cards.calculateScore()

        // then
        assertThat(actual).isEqualTo(12)
    }

    @Test
    fun `Player가 갖고 있는 카드에 따라 추가 발급 가능 여부를 판단한다`() {
        // given
        val testPlayer = TestPlayer(
            "aaa",
            Cards(listOf(Card(CardNumber.EIGHT, CardShape.CLOVER), Card(CardNumber.FOUR, CardShape.CLOVER)))
        )

        // when
        val actual: Boolean = testPlayer.checkProvideCardPossible()

        // then
        assertThat(actual).isTrue
    }

    class TestPlayer(name: String, cards: Cards = Cards()) : Player(name, cards) {
        override fun checkProvideCardPossible(): Boolean = (cards.calculateScore() <= TEST_PLAYER_MORE_CARD_CRITERIA)

        companion object {
            const val TEST_PLAYER_MORE_CARD_CRITERIA = 21
        }
    }
}
