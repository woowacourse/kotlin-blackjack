package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

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
}
