package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CardTest {

    @Test
    fun `카드의 총 합을 구하는 기능`() {
        val jack = Card(CardNumber.JACK, Suit.HEART)
        val two = Card(CardNumber.TWO, Suit.HEART)
        val participant = Participant()
        participant.addCard(jack)
        participant.addCard(two)
        val cardList = listOf(jack, two)
        val actual = participant.getCardSum(cardList)
        val expected = 12
        assertThat(actual).isEqualTo(expected)
    }



    @ParameterizedTest
    @ValueSource(ints = [17, 21])
    fun `카드 총 합이 기준 점수 이상이면 true`(treshold: Int) {
        val sum = 21
        val actual = checkScore(treshold,sum)
        assertThat(actual).isEqualTo(true)
    }

    fun checkScore(threshold: Int, sum: Int): Boolean = threshold <= sum
}

class Participant {
    private val cardList: MutableList<Card> = mutableListOf()
    fun addCard(card: Card) {
        cardList.add(card)
    }
    fun getCardSum(cardList: List<Card>): Int {
        return cardList.map { it.cardNumber.score }.sum()
    }
}
