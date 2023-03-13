package blackjack.domain.player

import blackjack.domain.card.TestCardsGenerator
import blackjack.domain.result.GameResult
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Test

class BlackjackManagerTest {
    private val softAssertions = SoftAssertions()

    @Test
    fun `게임을 시작하기 전에 모든 플레이어에게 카드를 2장씩 나눠준다`() {

        // when
        val blackjackManager = BlackjackManager(TestCardsGenerator(), listOf("aaa", "bbb"))
        lateinit var dealer: Dealer
        lateinit var participants: Participants
        blackjackManager.setupCards { actualDealer, actualParticipants ->
            dealer = actualDealer
            participants = actualParticipants
        }

        // then
        softAssertions.assertThat(dealer.cards.getSize()).isEqualTo(2)
        softAssertions.assertThat(participants.values[0].cards.getSize()).isEqualTo(2)
        softAssertions.assertThat(participants.values[1].cards.getSize()).isEqualTo(2)
        softAssertions.assertAll()
    }

    @Test
    fun `카드 게임 결과를 테스트한다`() {
        // given
        var betAmountIndex = 0
        val blackjackManager =
            BlackjackManager(TestCardsGenerator(), listOf("aaa", "bbb")) { listOf(10000, 20000)[betAmountIndex++] }

        // when
        var moreCardIndex = 0
        blackjackManager.playGame({ _: String -> listOf(true, false, false)[moreCardIndex++] }, {}, {})
        lateinit var gameResult: GameResult
        blackjackManager.calculatePlayersResult({ _, _ -> }, { actualGameResult -> gameResult = actualGameResult })
        val dealerProfit = gameResult.dealerProfit
        val participantsProfit = gameResult.participantsProfit

        // then
        softAssertions.assertThat(dealerProfit).isEqualTo(30000)
        softAssertions.assertThat(participantsProfit[0].profit).isEqualTo(-10000)
        softAssertions.assertThat(participantsProfit[1].profit).isEqualTo(-20000)
        softAssertions.assertAll()
    }
}
