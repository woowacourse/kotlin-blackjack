package blackjack.model

import blackjack.model.TrumpCards.ACE_CARD
import blackjack.model.TrumpCards.FOUR_CARD
import blackjack.model.TrumpCards.TEN_CARD
import blackjack.model.TrumpCards.THREE_CARD
import blackjack.model.TrumpCards.TWO_CARD
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class PlayerTest {
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
            assertThat(actual).isEqualTo(Return.LOSE)
        }

        @Test
        fun `플레이어와 딜러 모두 버스트되면 LOSE를 반환한다`() {
            player = creatPlayer(TEN_CARD, TEN_CARD, TEN_CARD)
            dealer = creatDealer(TEN_CARD, TEN_CARD, TEN_CARD)

            val actual = player.judge(dealer)
            assertThat(actual).isEqualTo(Return.LOSE)
        }

        @Test
        fun `딜러만 버스트되면 WIN을 반환한다`() {
            player = creatPlayer(TWO_CARD)
            dealer = creatDealer(TEN_CARD, TEN_CARD, TEN_CARD)

            val actual = player.judge(dealer)
            assertThat(actual).isEqualTo(Return.WIN)
        }

        @Test
        fun `플레이어만 블랙잭이면 BLACKJACK을 반환한다`() {
            player = creatPlayer(TEN_CARD, ACE_CARD)
            dealer = creatDealer(TEN_CARD, TEN_CARD)

            val actual = player.judge(dealer)
            assertThat(actual).isEqualTo(Return.BLACKJACK)
        }

        @Test
        fun `플레이어와 딜러 모두 블랙잭이면 DRAW를 반환한다`() {
            player = creatPlayer(TEN_CARD, ACE_CARD)
            dealer = creatDealer(TEN_CARD, ACE_CARD)
            val actual = player.judge(dealer)
            assertThat(actual).isEqualTo(Return.DRAW)
        }

        @Test
        fun `딜러만 블랙잭이면 LOSE를 반환한다`() {
            player = creatPlayer(TEN_CARD, FOUR_CARD)
            dealer = creatDealer(TEN_CARD, ACE_CARD)
            val actual = player.judge(dealer)
            assertThat(actual).isEqualTo(Return.LOSE)
        }

        @Test
        fun `모두 버스트 되지 않았을 때, 딜러의 총 합이 플레이어의 총 합보다 크면 LOSE를 반환한다`() {
            player = creatPlayer(TWO_CARD)
            dealer = creatDealer(FOUR_CARD)
            val actual = player.judge(dealer)
            assertThat(actual).isEqualTo(Return.LOSE)
        }

        @Test
        fun `모두 버스트 되지 않았을 때, 딜러의 총 합이 플레이어의 총 합과 같으면 DRAW를 반환한다`() {
            player = creatPlayer(THREE_CARD)
            dealer = creatDealer(THREE_CARD)
            val actual = player.judge(dealer)
            assertThat(actual).isEqualTo(Return.DRAW)
        }

        @Test
        fun `모두 버스트 되지 않았을 때, 딜러의 총 합이 플레이어의 총 합보다 작으면 WIN을 반환한다`() {
            player = creatPlayer(FOUR_CARD)
            dealer = creatDealer(TWO_CARD)
            val actual = player.judge(dealer)
            assertThat(actual).isEqualTo(Return.WIN)
        }
    }

    @Nested
    @DisplayName("calculateBetAmount 테스트")
    inner class CalculateBetAmountTest {
        @Test
        fun `플레이어가 10000원을 배팅하고 이기면 10000원을 반환한다`() {
            val dealer = creatDealer(THREE_CARD)
            val player1 = creatPlayer(FOUR_CARD)

            val actual = player1.calculateBetAmount(dealer)
            val expected = 10000L
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `플레이어가 10000원을 배팅하고 지면 10000원을 반환한다`() {
            val dealer = creatDealer(FOUR_CARD)
            val player1 = creatPlayer(THREE_CARD)

            val actual = player1.calculateBetAmount(dealer)
            val expected = -10000L
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `플레이어가 10000원을 배팅하고 비기면 0원을 반환한다`() {
            val dealer = creatDealer(FOUR_CARD)
            val player1 = creatPlayer(FOUR_CARD)

            val actual = player1.calculateBetAmount(dealer)
            val expected = 0L
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `플레이어가 10000원을 배팅하고 블랙잭이면 15000원을 반환한다`() {
            val dealer = creatDealer(FOUR_CARD)
            val player1 = creatPlayer(TEN_CARD, ACE_CARD)

            val actual = player1.calculateBetAmount(dealer)
            val expected = 15000L
            assertThat(actual).isEqualTo(expected)
        }
    }

    private fun creatPlayer(vararg cards: Card): Player {
        val player = Player("빙티", 10000)
        val dealingShoe = DealingShoe(cards.toList())
        player.pickCard(dealingShoe, cards.size)
        return player
    }

    private fun creatDealer(vararg cards: Card): Dealer {
        val dealer = Dealer()
        val dealingShoe = DealingShoe(cards.toList())
        dealer.pickCard(dealingShoe, cards.size)
        return dealer
    }
}
