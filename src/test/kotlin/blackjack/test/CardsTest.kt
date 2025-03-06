package blackjack.test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Cards(
    val value: MutableList<Card>,
) {
    fun getScore(): Int {
        val aces: List<Card> = value.filter { card: Card -> card.rank is Ace }
        val acesSums: List<Int> =
            when (aces.size) {
                0 -> listOf(0)
                1 -> listOf(1, 11)
                2 -> listOf(2, 12, 22)
                3 -> listOf(3, 13, 23, 33)
                4 -> listOf(4, 14, 24, 34, 44)
                else -> throw IllegalArgumentException("Cards can't contain more than 4 aces")
            }

        val otherCards: List<Card> = value.filter { card: Card -> card.rank !is Ace }
        val otherCardsSum: Int =
            otherCards.sumOf { card: Card ->
                card.rank.possibleValues.first()
            }
        val possibleSums: List<Int> = acesSums.map { acesSum -> acesSum + otherCardsSum }
        val score: Int = possibleSums.sortedDescending().first { possibleSum -> possibleSum <= 21 }
        return score
    }

    fun add(card: Card) {
        require(getScore() < 21) { "모든 카드의 합이 21 미만이 될 수 있을 경우에만 카드를 얻을 수 있습니다." }
        value.add(card)
    }
}

fun Cards(value: List<Card>): Cards = Cards(value.toMutableList())

class CardsTest {
    @Test
    fun `갖고 있는 카드를 확인할 수 있다`() {
        val card = Card(Ace(), Suit.SPADE)
        val cards = Cards(listOf(card))
        assertThat(cards.value).isEqualTo(listOf(card))
    }

    @Test
    fun `카드 총합을 알 수 있다`() {
        val cards =
            Cards(
                listOf(
                    Card(Ace(), Suit.SPADE),
                    Card(Ace(), Suit.HEART),
                    Card(Number(4), Suit.DIAMOND),
                    Card(Character.JACK, Suit.CLOVER),
                ),
            )
        assertThat(cards.getScore()).isEqualTo(16)
    }
}
