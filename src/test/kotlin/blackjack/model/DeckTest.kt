package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `덱 하나에는 조커를 제외한 모든 트럼프 카드 52장이 들어있다`() {
        val deck = Deck()
        val oneDeck =
            listOf(
                Card(CardNumber.A, Suit.`하트`),
                Card(CardNumber.A, Suit.`클로버`),
                Card(CardNumber.A, Suit.`스페이드`),
                Card(CardNumber.A, Suit.`다이아몬드`),
                Card(CardNumber.`2`, Suit.`하트`),
                Card(CardNumber.`2`, Suit.`클로버`),
                Card(CardNumber.`2`, Suit.`스페이드`),
                Card(CardNumber.`2`, Suit.`다이아몬드`),
                Card(CardNumber.`3`, Suit.`하트`),
                Card(CardNumber.`3`, Suit.`클로버`),
                Card(CardNumber.`3`, Suit.`스페이드`),
                Card(CardNumber.`3`, Suit.`다이아몬드`),
                Card(CardNumber.`4`, Suit.`하트`),
                Card(CardNumber.`4`, Suit.`클로버`),
                Card(CardNumber.`4`, Suit.`스페이드`),
                Card(CardNumber.`4`, Suit.`다이아몬드`),
                Card(CardNumber.`5`, Suit.`하트`),
                Card(CardNumber.`5`, Suit.`클로버`),
                Card(CardNumber.`5`, Suit.`스페이드`),
                Card(CardNumber.`5`, Suit.`다이아몬드`),
                Card(CardNumber.`6`, Suit.`하트`),
                Card(CardNumber.`6`, Suit.`클로버`),
                Card(CardNumber.`6`, Suit.`스페이드`),
                Card(CardNumber.`6`, Suit.`다이아몬드`),
                Card(CardNumber.`7`, Suit.`하트`),
                Card(CardNumber.`7`, Suit.`클로버`),
                Card(CardNumber.`7`, Suit.`스페이드`),
                Card(CardNumber.`7`, Suit.`다이아몬드`),
                Card(CardNumber.`8`, Suit.`하트`),
                Card(CardNumber.`8`, Suit.`클로버`),
                Card(CardNumber.`8`, Suit.`스페이드`),
                Card(CardNumber.`8`, Suit.`다이아몬드`),
                Card(CardNumber.`9`, Suit.`하트`),
                Card(CardNumber.`9`, Suit.`클로버`),
                Card(CardNumber.`9`, Suit.`스페이드`),
                Card(CardNumber.`9`, Suit.`다이아몬드`),
                Card(CardNumber.`10`, Suit.`하트`),
                Card(CardNumber.`10`, Suit.`클로버`),
                Card(CardNumber.`10`, Suit.`스페이드`),
                Card(CardNumber.`10`, Suit.`다이아몬드`),
                Card(CardNumber.J, Suit.`하트`),
                Card(CardNumber.J, Suit.`클로버`),
                Card(CardNumber.J, Suit.`스페이드`),
                Card(CardNumber.J, Suit.`다이아몬드`),
                Card(CardNumber.Q, Suit.`하트`),
                Card(CardNumber.Q, Suit.`클로버`),
                Card(CardNumber.Q, Suit.`스페이드`),
                Card(CardNumber.Q, Suit.`다이아몬드`),
                Card(CardNumber.K, Suit.`하트`),
                Card(CardNumber.K, Suit.`클로버`),
                Card(CardNumber.K, Suit.`스페이드`),
                Card(CardNumber.K, Suit.`다이아몬드`),
            )
        val temporary = mutableListOf<Card>()
        repeat(52) {
            temporary.add(deck.pick())
        }
        val actual = temporary.toSet().intersect(oneDeck.toSet()).size
        assertThat(actual).isEqualTo(52)
    }
}
