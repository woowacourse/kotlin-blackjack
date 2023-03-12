package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.card.Cards
import blackjack.domain.card.MultiDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class PlayerTest {

    @Test
    fun `이름을 생성할 수 있다`() {
        assertDoesNotThrow { TestPlayer("수달") }
    }

    @Test
    fun `카드들을 가진다`() {
        val player = TestPlayer("aa")
        assertThat(player.cards.values).isEqualTo(Cards().values)
    }

    @Test
    fun `새로운 카드를 받아 가진 카드에 추가한다`() {
        val player = TestPlayer("aa")
        val expected = Card.from(CardNumber.FOUR, CardShape.HEART)
        player.addCard(expected)
        assertThat(player.cards.values).isEqualTo(listOf(expected))
    }

    @Test
    fun `갖고 있는 카드 숫자의 합을 계산해 반환한다`() {
        val player = TestPlayer(
            "aa",
            Cards(
                Pair(CardNumber.FOUR, CardShape.HEART),
                Pair(CardNumber.EIGHT, CardShape.CLOVER)
            )
        )
        val actual = player.cards.sum()
        assertThat(actual).isEqualTo(12)
    }

    @Test
    fun `카드를 발급받아 카드의 개수가 늘었는지 확인한다`() {
        val player = TestPlayer("aaa")
        val multiDeck = MultiDeck()
        player.addCard(multiDeck.draw())
        assertThat(player.cards.values.size).isEqualTo(1)
    }

    class TestPlayer(name: String, cards: Cards = Cards()) : Player(name, cards) {
        override fun canHit(): Boolean {
            TODO("Not yet implemented")
        }
    }
}
