package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerTest {
    private val cardDeck: CardDeck = CardDeck(listOf(Card(Suit.CLOVER, Denomination.SIX)))

    @Test
    fun `플레이어가 딜러라는 이름을 가질 경우 예외를 발생시킨다`() {
        assertThrows<IllegalArgumentException> { Player("딜러", Hand(emptyList())) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "뭉치뭉치뭉치"])
    fun `플레이어의 이름 길이가 1~5 사이의 길이가 아닐 경우 예외를 발생시킨다`(name: String) {
        assertThrows<IllegalArgumentException> { Player(name, Hand(emptyList())) }
    }

    @Test
    fun `플레이어는 이름과 카드 리스트를 가진다`() {
        val cards = listOf((Card(Suit.HEART, Denomination.FIVE)), Card(Suit.CLOVER, Denomination.TWO))
        val player = Player("모찌", Hand(cards))
        assertAll({
            assertThat(player.name).isEqualTo("모찌")
            assertThat(player.hand.value).isEqualTo(cards)
        })
    }

    @Test
    fun `플레이어는 카드를 추가로 받을 수 있다`() {
        val initialHand =
            Hand(
                listOf((Card(Suit.HEART, Denomination.FIVE)), Card(Suit.CLOVER, Denomination.TWO)),
            )
        val player = Player("모찌", initialHand)
        player.pickCard(cardDeck)

        assertThat(player.hand.value).isEqualTo(
            listOf(
                Card(Suit.HEART, Denomination.FIVE),
                Card(Suit.CLOVER, Denomination.TWO),
                Card(Suit.CLOVER, Denomination.SIX),
            ),
        )
    }

    @Test
    fun `플레이어가 이름만 가질 경우, 가진 카드 리스트는 비어있다`() {
        val player = Player("모찌")
        assertThat(player.hand.value.size).isEqualTo(0)
    }
}
