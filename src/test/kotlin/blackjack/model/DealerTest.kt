package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `Dealer의 총 합이 Participant의 총 합보다 크면 LOSE를 반환한다`() {
        val dealer = Dealer()
        dealer.addCard(Card(CardNumber.THREE, Suit.HEART))
        val participant = Participant()
        participant.addCard(Card(CardNumber.TWO, Suit.HEART))
        val actual = dealer.judge(participant)
        assertThat(actual).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `Dealer의 총 합이 Participant의 총 합과 같으면 DRAW를 반환한다`() {
        val dealer = Dealer()
        dealer.addCard(Card(CardNumber.THREE, Suit.HEART))
        val participant = Participant()
        participant.addCard(Card(CardNumber.THREE, Suit.HEART))
        val actual = dealer.judge(participant)
        assertThat(actual).isEqualTo(GameResult.DRAW)
    }

    @Test
    fun `Dealer의 총 합이 Participant의 총 합보다 작으면 WIN을 반환한다`() {
        val dealer = Dealer()
        dealer.addCard(Card(CardNumber.THREE, Suit.HEART))
        val participant = Participant()
        participant.addCard(Card(CardNumber.FOUR, Suit.HEART))
        val actual = dealer.judge(participant)
        assertThat(actual).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `카드 총 합이 16 이하면 true를 반환한다`() {
        val dealer = Dealer()
        dealer.addCard(Card(CardNumber.THREE, Suit.HEART))
        val actual = dealer.isHitable()
        assertThat(actual).isEqualTo(true)
    }

    @Test
    fun `카드 총 합이 17 이상이면 false를 반환한다`() {
        val dealer = Dealer()
        dealer.addCard(Card(CardNumber.TEN, Suit.HEART))
        dealer.addCard(Card(CardNumber.SEVEN, Suit.HEART))
        val actual = dealer.isHitable()
        assertThat(actual).isEqualTo(false)
    }
}
