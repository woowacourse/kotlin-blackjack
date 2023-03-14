package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.card.Cards
import blackjack.domain.card.TestCardsGenerator
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Test

class BlackjackManagerTest {
    private val softAssertions = SoftAssertions()

    @Test
    fun `게임을 시작하기 전에 모든 플레이어에게 카드를 2장씩 나눠준다`() {

        // when
        val blackjackManager = BlackjackManager(TestCardsGenerator(cards), listOf("aaa", "bbb"))
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
            BlackjackManager(TestCardsGenerator(cards), listOf("aaa", "bbb")) { listOf(10000, 20000)[betAmountIndex++] }

        // when
        var moreCardIndex = 0
        val gameResult = blackjackManager.playGame({ _: String -> listOf(true, false, false)[moreCardIndex++] }, {}, {})
        val dealerProfit = gameResult.getDealerResult().profit
        val participantsProfit = gameResult.getParticipantsResult().participantsResults

        // then
        softAssertions.assertThat(dealerProfit).isEqualTo(30000)
        softAssertions.assertThat(participantsProfit[0].getProfit()).isEqualTo(-10000)
        softAssertions.assertThat(participantsProfit[1].getProfit()).isEqualTo(-20000)
        softAssertions.assertAll()
    }

    companion object {
        val cards = Cards(
            listOf(
                // dealer
                Card(CardNumber.FOUR, CardShape.HEART),
                Card(CardNumber.FIVE, CardShape.HEART),

                // player1
                Card(CardNumber.SIX, CardShape.DIAMOND),
                Card(CardNumber.EIGHT, CardShape.DIAMOND),

                // player2
                Card(CardNumber.THREE, CardShape.CLOVER),
                Card(CardNumber.FOUR, CardShape.CLOVER),

                // 추가 발급
                Card(CardNumber.FOUR, CardShape.SPADE),
                Card(CardNumber.FIVE, CardShape.SPADE),
                Card(CardNumber.SIX, CardShape.SPADE)
            )
        )
    }
}
