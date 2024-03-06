package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CardTest {

    @Test
    fun `에이스가 없을 때 카드의 총 합을 구하는 기능`() {
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

    @Test
    fun `에이스가 11인 것이 유리할 때 카드의 총 합을 구하는 기능`() {
        val ace = Card(CardNumber.ACE, Suit.HEART)
        val jack = Card(CardNumber.JACK, Suit.HEART)
        val participant = Participant()
        participant.addCard(ace)
        participant.addCard(jack)
        val cardList = listOf(ace, jack)
        val actual = participant.getCardSum(cardList)
        val expected = 21
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `에이스가 1인 것이 유리할 때 카드의 총 합을 구하는 기능`() {
        val ace = Card(CardNumber.ACE, Suit.HEART)
        val jack = Card(CardNumber.JACK, Suit.HEART)
        val two = Card(CardNumber.TWO, Suit.HEART)
        val three = Card(CardNumber.THREE, Suit.HEART)
        val participant = Participant()
        participant.addCard(ace)
        participant.addCard(jack)
        participant.addCard(two)
        participant.addCard(three)
        val cardList = listOf(ace, jack, two, three)
        val actual = participant.getCardSum(cardList)
        val expected = 16
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @ValueSource(ints = [22, 23, 24])
    fun `카드 총 합이 21 이상이면 true를 반환한다`(sum: Int) {
        val actual = isBusted(sum)
        assertThat(actual).isEqualTo(true)
    }

    @ParameterizedTest
    @ValueSource(ints = [14, 15, 16])
    fun `카드 총 합이 16 이하면 true를 반환한다`(sum: Int) {
        val actual = isHitable(sum)
        assertThat(actual).isEqualTo(true)
    }

    fun isHitable(score: Int): Boolean {
        val threshold = 17
        return score < threshold
    }

    fun isBusted(score: Int): Boolean {
        val threshold = 21
        return threshold < score
    }
}

class Participant {
    private val cardList: MutableList<Card> = mutableListOf()
    fun addCard(card: Card) {
        cardList.add(card)
    }

    fun getCardSum(cardList: List<Card>): Int {
        val sum = cardList.map { it.cardNumber.score }.sum()
        val additionalScore = if (hasAce() && sum <= 11) 10 else 0
        return sum + additionalScore
    }

    private fun hasAce() = cardList.filter { it.cardNumber == CardNumber.ACE }.isNotEmpty()
}

