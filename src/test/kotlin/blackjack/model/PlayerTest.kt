package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var player: Player

    @Test
    fun `플레이어가 초기 카드를 2장 가져온다`() {
        val dealingShoe = DealingShoe(listOf(DealerTest.FOUR_CARD, DealerTest.TWO_CARD))
        player = creatPlayer()
        player.pickCard(dealingShoe, 2)
        val actual = player.showCard()

        assertThat(actual.size).isEqualTo(2)
        assertThat(actual).isEqualTo(listOf(DealerTest.FOUR_CARD, DealerTest.TWO_CARD))
    }

    @Test
    fun `플레이어가 딜링슈에서 맨 앞의 2하트 카드를 한 개 뽑아 가져온다`() {
        val dealingShoe = DealingShoe(listOf(DealerTest.FOUR_CARD, DealerTest.TWO_CARD))
        player = creatPlayer()
        player.pickCard(dealingShoe)
        val actual = player.showCard()

        assertThat(actual).isEqualTo(listOf(FOUR_CARD))
    }

    @Test
    fun `JACK과 TWO 카드의 총 합으로 12를 반환한다`() {
        val player = creatPlayer(JACK_CARD, TWO_CARD)
        val actual = player.getCardSum()
        val expected = 12
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `에이스가 11일 때 총 합에 10을 더해 21을 반환한다`() {
        val player = creatPlayer(ACE_CARD, JACK_CARD)
        val actual = player.getCardSum()
        val expected = 21
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `에이스가 1일 때 총 합 16을 반환한다`() {
        val player = creatPlayer(ACE_CARD, JACK_CARD, TWO_CARD)
        val actual = player.getCardSum()
        val expected = 13
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `카드 총 합이 21 이상이면 true를 반환한다`() {
        val player = creatPlayer(TEN_CARD, TEN_CARD, TWO_CARD)
        val actual = player.isBusted()
        assertThat(actual).isEqualTo(true)
    }

    @Test
    fun `카드 총 합이 21과 같으면 true를 반환한다`() {
        val player = creatPlayer(TEN_CARD, ACE_CARD)
        val actual = player.isMaxScore()
        assertThat(actual).isEqualTo(true)
    }

    @Nested
    @DisplayName("플레이어를 기준으로 승패를 판정하는 기능을 테스트한다")
    inner class PlayerJudgeTest {
        private lateinit var dealer: Dealer
        private lateinit var player: Player

        @Test
        fun `플레이어만 버스트되면 LOSE를 반환한다`() {
            player = creatPlayer(TEN_CARD, TEN_CARD, TEN_CARD)
            dealer = creatDealer(THREE_CARD)
            val actual = player.judge(dealer)
            assertThat(actual).isEqualTo(GameResult.LOSE)
        }

        @Test
        fun `모두 버스트되면 LOSE를 반환한다`() {
            player = creatPlayer(TEN_CARD, TEN_CARD, TEN_CARD)
            dealer = creatDealer(TEN_CARD, TEN_CARD, TEN_CARD)

            val actual = player.judge(dealer)
            assertThat(actual).isEqualTo(GameResult.LOSE)
        }

        @Test
        fun `딜러만 버스트되면 WIN을 반환한다`() {
            player = creatPlayer(TWO_CARD)
            dealer = creatDealer(TEN_CARD, TEN_CARD, TEN_CARD)

            val actual = player.judge(dealer)
            assertThat(actual).isEqualTo(GameResult.WIN)
        }

        @Test
        fun `모두 버스트 되지 않았을 때, 딜러의 총 합이 플레이어의 총 합보다 크면 LOSE를 반환한다`() {
            player = creatPlayer(TWO_CARD)
            dealer = creatDealer(FOUR_CARD)
            val actual = player.judge(dealer)
            assertThat(actual).isEqualTo(GameResult.LOSE)
        }

        @Test
        fun `모두 버스트 되지 않았을 때, 딜러의 총 합이 플레이어의 총 합과 같으면 DRAW를 반환한다`() {
            player = creatPlayer(THREE_CARD)
            dealer = creatDealer(THREE_CARD)
            val actual = player.judge(dealer)
            assertThat(actual).isEqualTo(GameResult.DRAW)
        }

        @Test
        fun `모두 버스트 되지 않았을 때, 딜러의 총 합이 플레이어의 총 합보다 작으면 WIN을 반환한다`() {
            player = creatPlayer(FOUR_CARD)
            dealer = creatDealer(TWO_CARD)
            val actual = player.judge(dealer)
            assertThat(actual).isEqualTo(GameResult.WIN)
        }
    }

    private fun creatPlayer(vararg cards: Card): Player {
        val player = Player("빙티")
        cards.forEach {
            player.addCard(it)
        }
        return player
    }

    private fun creatDealer(vararg cards: Card): Dealer {
        val dealer = Dealer()
        cards.forEach {
            dealer.addCard(it)
        }
        return dealer
    }

    companion object {
        val TWO_CARD = Card(CardNumber.TWO, Suit.HEART)
        val THREE_CARD = Card(CardNumber.THREE, Suit.HEART)
        val FOUR_CARD = Card(CardNumber.FOUR, Suit.HEART)
        val TEN_CARD = Card(CardNumber.TEN, Suit.HEART)
        val ACE_CARD = Card(CardNumber.ACE, Suit.HEART)
        val JACK_CARD = Card(CardNumber.JACK, Suit.HEART)
    }
}
