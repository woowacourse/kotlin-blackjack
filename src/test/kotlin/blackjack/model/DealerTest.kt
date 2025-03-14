package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class DealerTest {
    private val cardDeck: CardDeck = CardDeck(listOf(Card(CardShape.CLOVER, Denomination.SIX)))

    @Test
    fun `딜러는 이름과 카드들을 가진다`() {
        val hand = Hand(listOf((Card(CardShape.HEART, Denomination.FIVE)), Card(CardShape.CLOVER, Denomination.TWO)))
        val dealer = Dealer(hand = hand)

        assertAll({ assertThat(dealer.name).isEqualTo("딜러") }, { assertThat(dealer.hand).isEqualTo(hand) })
    }

    @Test
    fun `딜러는 이름이 없을 경우, 딜러라는 이름을 가진다`() {
        val dealer = Dealer()

        val expected = "딜러"

        assertThat(dealer.name).isEqualTo(expected)
    }

    @Test
    fun `딜러의 카드 리스트의 초기값은 비어있다`() {
        val dealer = Dealer()

        val expected = 0

        assertThat(dealer.hand.value.size).isEqualTo(expected)
    }

    @Test
    fun `딜러는 카드를 추가로 받을 수 있다`() {
        val dealer = Dealer()
        dealer.pickCard(cardDeck)

        val expected: List<Card> = listOf(Card(CardShape.CLOVER, Denomination.SIX))
        assertThat(dealer.hand.value).isEqualTo(expected)
    }

    @Test
    fun `딜러의 스코어가 16 이하일 경우 isHit은 true를 반환한다`() {
        val hand =
            Hand(
                listOf(
                    Card(CardShape.DIAMOND, Denomination.TEN),
                    Card(CardShape.CLOVER, Denomination.TWO),
                ),
            )
        val dealer = Dealer(hand = hand)

        val actual = dealer.canHit()

        val expected = true

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `딜러의 스코어가 16초과일 경우 isHit은 false를 반환한다`() {
        val hand =
            Hand(
                listOf(
                    Card(CardShape.DIAMOND, Denomination.TEN),
                    Card(CardShape.CLOVER, Denomination.TEN),
                ),
            )
        val dealer = Dealer(hand = hand)

        val actual = dealer.canHit()

        val expected = false

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `플레이어의 스코어를 통해 딜러는 자신의 결과를 반환한다`() {
        val hand =
            Hand(
                listOf(
                    Card(CardShape.DIAMOND, Denomination.TEN),
                    Card(CardShape.CLOVER, Denomination.TEN),
                ),
            )
        val dealer = Dealer(hand = hand)

        val actual = dealer.getResult(playerScore = 19)

        val expected = GameResult.WIN

        assertThat(actual).isEqualTo(expected)
    }
}
