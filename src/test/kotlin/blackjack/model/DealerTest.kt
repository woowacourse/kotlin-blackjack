package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var dealer: Dealer
    private lateinit var player: Player

    @BeforeEach
    fun `모든 테스트에 딜러와 플레이어 한 명이 참가하며, 딜러는 3하트 카드를 갖도록 세팅한다`() {
        player = Player("빙티")
        dealer = Dealer()
        dealer.addCard(THREE_CARD)
    }

    @Test
    fun `딜러의 총 합이 플레이어의 총 합보다 크면 LOSE를 반환한다`() {
        player.addCard(TWO_CARD)
        val actual = dealer.judge(player)
        assertThat(actual).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `딜러의 총 합이 플레이어의 총 합과 같으면 DRAW를 반환한다`() {
        player.addCard(Card(CardNumber.THREE, Suit.HEART))
        val actual = dealer.judge(player)
        assertThat(actual).isEqualTo(GameResult.DRAW)
    }

    @Test
    fun `딜러의 총 합이 플레이어의 총 합보다 작으면 WIN을 반환한다`() {
        player.addCard(FOUR_CARD)
        val actual = dealer.judge(player)
        assertThat(actual).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `딜러의 카드 총 합이 16 이하면 true를 반환한다`() {
        val actual = dealer.isHitable()
        assertThat(actual).isEqualTo(true)
    }

    @Test
    fun `딜러의 카드 총 합이 17 이상이면 false를 반환한다`() {
        dealer.addCard(TEN_CARD)
        dealer.addCard(SEVEN_CARD)
        val actual = dealer.isHitable()
        assertThat(actual).isEqualTo(false)
    }

    companion object {
        val TWO_CARD = Card(CardNumber.TWO, Suit.HEART)
        val THREE_CARD = Card(CardNumber.THREE, Suit.HEART)
        val FOUR_CARD = Card(CardNumber.FOUR, Suit.HEART)
        val SEVEN_CARD = Card(CardNumber.SEVEN, Suit.HEART)
        val TEN_CARD = Card(CardNumber.TEN, Suit.HEART)
    }
}
