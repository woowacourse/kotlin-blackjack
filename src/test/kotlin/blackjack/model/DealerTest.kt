package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `Dealer의 총 합이 Player의 총 합보다 크면 LOSE를 반환한다`() {
        val dealer = Dealer()
        dealer.addCard(Card(CardNumber.`3`, Suit.`하트`))
        val player = Player("철수")
        player.addCard(Card(CardNumber.`2`, Suit.`하트`))
        val actual = dealer.judge(player)
        assertThat(actual).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `Dealer의 총 합이 Player의 총 합과 같으면 DRAW를 반환한다`() {
        val dealer = Dealer()
        dealer.addCard(Card(CardNumber.`3`, Suit.`하트`))
        val player = Player("철수")
        player.addCard(Card(CardNumber.`3`, Suit.`하트`))
        val actual = dealer.judge(player)
        assertThat(actual).isEqualTo(GameResult.DRAW)
    }

    @Test
    fun `Dealer의 총 합이 Player의 총 합보다 작으면 WIN을 반환한다`() {
        val dealer = Dealer()
        dealer.addCard(Card(CardNumber.`3`, Suit.`하트`))
        val player = Player("철수")
        player.addCard(Card(CardNumber.`4`, Suit.`하트`))
        val actual = dealer.judge(player)
        assertThat(actual).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `카드 총 합이 16 이하면 true를 반환한다`() {
        val dealer = Dealer()
        dealer.addCard(Card(CardNumber.`3`, Suit.`하트`))
        val actual = dealer.isHitable()
        assertThat(actual).isEqualTo(true)
    }

    @Test
    fun `카드 총 합이 17 이상이면 false를 반환한다`() {
        val dealer = Dealer()
        dealer.addCard(Card(CardNumber.`10`, Suit.`하트`))
        dealer.addCard(Card(CardNumber.`7`, Suit.`하트`))
        val actual = dealer.isHitable()
        assertThat(actual).isEqualTo(false)
    }
}
