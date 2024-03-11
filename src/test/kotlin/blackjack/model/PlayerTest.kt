package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `에이스가 없을 때 카드의 총 합을 구하는 기능`() {
        val jack = Card(CardNumber.Jack, Suit.Heart)
        val two = Card(CardNumber.Two, Suit.Heart)
        val participant = Player("홍길동")
        participant.addCard(jack)
        participant.addCard(two)
        val actual = participant.getCardSum()
        val expected = 12
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `에이스가 11인 것이 유리할 때 카드의 총 합을 구하는 기능`() {
        val ace = Card(CardNumber.Ace, Suit.Heart)
        val jack = Card(CardNumber.Jack, Suit.Heart)
        val participant = Player("홍길동")
        participant.addCard(ace)
        participant.addCard(jack)
        val actual = participant.getCardSum()
        val expected = 21
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `에이스가 1인 것이 유리할 때 카드의 총 합을 구하는 기능`() {
        val ace = Card(CardNumber.Ace, Suit.Heart)
        val jack = Card(CardNumber.Jack, Suit.Heart)
        val two = Card(CardNumber.Two, Suit.Heart)
        val three = Card(CardNumber.Three, Suit.Heart)
        val participant = Player("홍길동")
        participant.addCard(ace)
        participant.addCard(jack)
        participant.addCard(two)
        participant.addCard(three)
        val actual = participant.getCardSum()
        val expected = 16
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `카드 총 합이 21 이상이면 true를 반환한다`() {
        val participant = Player("홍길동")
        participant.addCard(Card(CardNumber.Ten, Suit.Heart))
        participant.addCard(Card(CardNumber.Ten, Suit.Heart))
        participant.addCard(Card(CardNumber.Two, Suit.Heart))
        val actual = participant.isBusted()
        assertThat(actual).isEqualTo(true)
    }

    @Test
    fun `카드 총 합이 21이면 true를 반환한다`() {
        val participant = Player("홍길동")
        participant.addCard(Card(CardNumber.Ten, Suit.Heart))
        participant.addCard(Card(CardNumber.Ace, Suit.Heart))
        val actual = participant.isMaxScore()
        assertThat(actual).isEqualTo(true)
    }
}
