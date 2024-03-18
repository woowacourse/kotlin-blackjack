package blackjack.model

import blackjack.model.TrumpCards.ACE_CARD
import blackjack.model.TrumpCards.EIGHT_CARD
import blackjack.model.TrumpCards.FOUR_CARD
import blackjack.model.TrumpCards.JACK_CARD
import blackjack.model.TrumpCards.SEVEN_CARD
import blackjack.model.TrumpCards.SIX_CARD
import blackjack.model.TrumpCards.TEN_CARD
import blackjack.model.TrumpCards.THREE_CARD
import blackjack.model.TrumpCards.TWO_CARD
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class DealerTest {
    @Nested
    @DisplayName("hitOrStay 메서드 테스트")
    inner class HitOrStayTest {
        private fun printDealerHitMessage() {
            println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        }

        @Test
        fun `카드의 합이 16 이하일 때만 hit할 수 있다`() {
            val dealer = Dealer()
            val dealingShoe =
                DealingShoe(listOf(EIGHT_CARD, EIGHT_CARD, TWO_CARD))

            dealer.hitOrStay(
                dealingShoe,
                ::printDealerHitMessage,
            )

            val result = dealer.showCard()
            assertThat(result).isEqualTo(listOf(EIGHT_CARD, EIGHT_CARD, TWO_CARD))
        }

        @Test
        fun `카드의 합이 17이면 hit을 멈춘다`() {
            val dealer = Dealer()
            val dealingShoe =
                DealingShoe(listOf(TEN_CARD, SEVEN_CARD, TWO_CARD, TWO_CARD))

            dealer.hitOrStay(
                dealingShoe,
                ::printDealerHitMessage,
            )

            val result = dealer.showCard()
            assertThat(result).isEqualTo(listOf(TEN_CARD, SEVEN_CARD))
        }
    }

    @DisplayName("isHitable 테스트")
    @Test
    fun `딜러의 카드 총 합이 16 이하면 true를 반환한다`() {
        val dealer = creatDealer(JACK_CARD, SIX_CARD)
        val actual = dealer.isHittable()
        assertThat(actual).isEqualTo(true)
    }

    @Test
    fun `딜러의 카드 총 합이 17 이상이면 false를 반환한다`() {
        val dealer = creatDealer(JACK_CARD, SEVEN_CARD)
        val actual = dealer.isHittable()
        assertThat(actual).isEqualTo(false)
    }

    @Nested
    @DisplayName("딜러를 기준으로 승패를 판정하는 기능을 테스트")
    inner class DealerJudgeTest {
        private lateinit var dealer: Dealer
        private lateinit var player: Player

        @Test
        fun `플레이어만 버스트되면 WIN을 반환한다`() {
            player = creatPlayer(TEN_CARD, TEN_CARD, TEN_CARD)
            dealer = creatDealer(TEN_CARD)
            val actual = dealer.judge(player)
            assertThat(actual).isEqualTo(GameResult.WIN)
        }

        @Test
        fun `플레이어와 딜러 모두 버스트되면 WIN을 반환한다`() {
            player = creatPlayer(TEN_CARD, TEN_CARD, TEN_CARD)
            dealer = creatDealer(TEN_CARD, TEN_CARD, TEN_CARD)
            val actual = dealer.judge(player)
            assertThat(actual).isEqualTo(GameResult.WIN)
        }

        @Test
        fun `딜러만 버스트되면 LOSE를 반환한다`() {
            player = creatPlayer(TWO_CARD)
            dealer = creatDealer(TEN_CARD, TEN_CARD, TEN_CARD)
            val actual = dealer.judge(player)
            assertThat(actual).isEqualTo(GameResult.LOSE)
        }

        @Test
        fun `플레이어만 블랙잭이면 LOSE_BLACKJACK을 반환한다`() {
            player = creatPlayer(TEN_CARD, ACE_CARD)
            dealer = creatDealer(TEN_CARD, SEVEN_CARD)
            val actual = dealer.judge(player)
            assertThat(actual).isEqualTo(GameResult.LOSE_BLACKJACK)
        }

        @Test
        fun `플레이어와 딜러 모두 블랙잭이면 DRAW를 반환한다`() {
            player = creatPlayer(TEN_CARD, ACE_CARD)
            dealer = creatDealer(TEN_CARD, ACE_CARD)
            val actual = dealer.judge(player)
            assertThat(actual).isEqualTo(GameResult.DRAW)
        }

        @Test
        fun `딜러만 블랙잭이면 WIN을 반환한다`() {
            player = creatPlayer(TEN_CARD, SEVEN_CARD)
            dealer = creatDealer(TEN_CARD, ACE_CARD)
            val actual = dealer.judge(player)
            assertThat(actual).isEqualTo(GameResult.WIN)
        }

        @Test
        fun `모두 버스트 되지 않았을 때, 딜러의 총 합이 플레이어의 총 합보다 크면 WIN을 반환한다`() {
            player = creatPlayer(TWO_CARD)
            dealer = creatDealer(FOUR_CARD)
            val actual = dealer.judge(player)
            assertThat(actual).isEqualTo(GameResult.WIN)
        }

        @Test
        fun `모두 버스트 되지 않았을 때, 딜러의 총 합이 플레이어의 총 합과 같으면 DRAW를 반환한다`() {
            player = creatPlayer(THREE_CARD)
            dealer = creatDealer(THREE_CARD)
            val actual = dealer.judge(player)
            assertThat(actual).isEqualTo(GameResult.DRAW)
        }

        @Test
        fun `모두 버스트 되지 않았을 때, 딜러의 총 합이 플레이어의 총 합보다 작으면 LOSE를 반환한다`() {
            player = creatPlayer(FOUR_CARD)
            dealer = creatDealer(TWO_CARD)
            val actual = dealer.judge(player)
            assertThat(actual).isEqualTo(GameResult.LOSE)
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
