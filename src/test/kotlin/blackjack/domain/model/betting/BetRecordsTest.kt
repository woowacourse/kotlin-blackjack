package blackjack.domain.model.betting

import blackjack.domain.model.card.CLUB_KING
import blackjack.domain.model.card.CLUB_SEVEN
import blackjack.domain.model.card.DIAMOND_ACE
import blackjack.domain.model.card.DIAMOND_QUEEN
import blackjack.domain.model.card.DIAMOND_SEVEN
import blackjack.domain.model.card.HEART_KING
import blackjack.domain.model.card.Hand
import blackjack.domain.model.card.SPADE_JACK
import blackjack.domain.model.card.SPADE_KING
import blackjack.domain.model.card.SPADE_QUEEN
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetRecordsTest {
    private val blackJack =
        Player(
            name = "블랙잭의크롱",
            hand = Hand.of(DIAMOND_ACE, SPADE_JACK),
            betAmount = BetAmount(1000.0),
        )
    private val twenty =
        Player(
            name = "20점의메다",
            hand = Hand.of(SPADE_QUEEN, DIAMOND_QUEEN),
            betAmount = BetAmount(1000.0),
        )
    private val seventeen =
        Player(
            name = "17점의환노",
            hand = Hand.of(CLUB_KING, DIAMOND_SEVEN),
            betAmount = BetAmount(1000.0),
        )
    private val ten =
        Player(
            name = "10점의크림",
            hand = Hand.of(HEART_KING),
            betAmount = BetAmount(1000.0),
        )
    private val dealer =
        Dealer(
            name = "17점의딜러",
            hand = Hand.of(SPADE_KING, CLUB_SEVEN),
        )
    private val players: List<Player> =
        listOf(
            blackJack,
            twenty,
            seventeen,
            ten,
        )

    @Test
    fun `모든 플레이어의 베팅 수익 결과를 확인할 수 있다`() {
        val betRecords = BetRecords(dealer, players)
        val actualPlayersProfit = betRecords.playersProfit()

        val expectedPlayersProfit =
            mapOf(
                blackJack to 1500.0,
                twenty to 1000.0,
                seventeen to 0.0,
                ten to -1000.0,
            )

        assertThat(actualPlayersProfit).isEqualTo(expectedPlayersProfit)
    }

    @Test
    fun `딜러의 베팅 수익 결과를 확인할 수 있다`() {
        val betRecords = BetRecords(dealer, players)
        val actualDealerProfit = betRecords.dealerProfit()

        val expectedDealerProfit = -1500.0

        assertThat(actualDealerProfit).isEqualTo(expectedDealerProfit)
    }
}
