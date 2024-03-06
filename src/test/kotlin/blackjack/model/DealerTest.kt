package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {

    private val dealer = Dealer()

    @Test
    fun `Dealer의 총 합이 Participant의 총 합보다 크면 LOSE를 반환한다`() {
        val participant = Participant()
        participant.addCard(Card(CardNumber.TWO, Suit.HEART))
        dealer.addCard(Card(CardNumber.THREE, Suit.HEART))
        val actual = dealer.judge(participant)
        assertThat(actual).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `Dealer의 총 합이 Participant의 총 합과 같으면 DRAW를 반환한다`() {
        val participant = Participant()
        participant.addCard(Card(CardNumber.TWO, Suit.HEART))
        dealer.addCard(Card(CardNumber.TWO, Suit.HEART))
        val actual = dealer.judge(participant)
        assertThat(actual).isEqualTo(GameResult.DRAW)
    }

    @Test
    fun `Dealer의 총 합이 Participant의 총 합보다 작으면 WIN을 반환한다`() {
        val participant = Participant()
        participant.addCard(Card(CardNumber.THREE, Suit.HEART))
        dealer.addCard(Card(CardNumber.TWO, Suit.HEART))
        val actual = dealer.judge(participant)
        assertThat(actual).isEqualTo(GameResult.WIN)
    }
}
