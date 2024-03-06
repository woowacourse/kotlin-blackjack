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
    @ValueSource(ints = [22, 23, 24])
    fun `카드 총 합이 21 이상이면 Busted`(sum: Int) {
        val actual = isBusted(sum)
        assertThat(actual).isEqualTo(true)
    }

    fun checkScore(threshold: Int, sum: Int): Boolean = threshold <= sum

    fun isBusted(sum: Int): Boolean{
        val threshold = 21
        return checkScore(threshold, sum)
    }
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

