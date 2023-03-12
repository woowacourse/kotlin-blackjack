package blackjack.domain.player

import blackjack.domain.card.Cards
import blackjack.domain.card.TestCardsGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackManagerTest {

    @Test
    fun `BlackjackManager를 생성할 때 이름을 넘겨주면, 객체를 생성할 때, 해당 이름을 가진 참가자를 생성한다`() {

        // when
        val blackjackManager = BlackjackManager(TestCardsGenerator(), listOf("aaa", "bbb"))
        val participants: Participants = blackjackManager.participants

        // then
        var index = 0
        participants.values.forEach { participant ->
            assertThat(participant.name).isEqualTo(listOf("aaa", "bbb")[index++])
        }
    }

    @Test
    fun `setup 함수를 호출하면 모든 플레이어에게 카드 두장을 발행한다`() {
        // given
        val blackjackManager = BlackjackManager(TestCardsGenerator(), listOf("aaa", "bbb"))

        // when
        blackjackManager.setupCards()
        val dealerCards: Cards = blackjackManager.dealer.cards
        val participantsCards: List<Cards> = blackjackManager.participants.values.map { it.cards }

        // then
        assertThat(dealerCards.getSize()).isEqualTo(2)
        var index = 0
        participantsCards.forEach { cards ->
            assertThat(cards.getSize()).isEqualTo(listOf(2, 2)[index++])
        }
    }

//    @Test
//    fun `플레이어1은 카드 추가 발급을 한번 원하고, 플레이어2는 카드 추가 발급을 원하지 않을 때, 플레이어1 에게만 카드를 추가로 한장만 나눠준다`() {
//        // given
//        val blackjackManager = BlackjackManager(TestCardsGenerator(), listOf("aaa", "bbb"))
//        blackjackManager.setupCards()
//
//        // when
//        var index = 0
//        blackjackManager.playParticipantsTurns({ _: String -> listOf(true, false, false)[index++] }) {}
//
//        val participant1Cards: Cards = blackjackManager.participants.values[0].cards
//        val participant2Cards: Cards = blackjackManager.participants.values[1].cards
//
//        // then
//        assertThat(participant1Cards.getSize()).isEqualTo(3)
//        assertThat(participant2Cards.getSize()).isEqualTo(2)
//    }

//    @Test
//    fun `카드 숫자 합이 16이 넘지 않는동안 딜러에게 계속 카드를 발행한다`() {
//        // given
//        val blackjackManager = BlackjackManager(TestCardsGenerator(), listOf("aaa", "bbb"))
//        blackjackManager.setupCards()
//
//        // when
//        blackjackManager.playDealerTurn {}
//        val dealerCards: Cards = blackjackManager.dealer.cards
//
//        // then
//        assertThat(dealerCards.getSize()).isEqualTo(4)
//    }

//    @Test
//    fun `플레이어1 스코어는 14 플레이어2 스코어는 7 딜러 스코어는 9일때, 참가자들의 수익률을 계산한다`() {
//        // given
//        val blackjackManager = BlackjackManager(TestCardsGenerator(), listOf("aaa", "bbb"))
//        blackjackManager.setupCards()
//
//        // when
//        val playersEarningRate: List<Double> =
//            blackjackManager.calculateParticipantsEarningRate().values.map { it.earningRate.rate }
//
//        // then
//        assertThat(playersEarningRate).isEqualTo(
//            listOf(1.0, -1.0)
//        )
//    }

//    @Test
//    fun `플레이어1 스코어는 14 플레이어2 스코어는 블랙잭 딜러 스코어는 14일때, 참가자들의 수익률을 계산한다`() {
//        // given
//        val blackjackManager = BlackjackManager(TestCardsGenerator2(), listOf("aaa", "bbb"))
//        blackjackManager.setupCards()
//
//        // when
//        val playersEarningRate: List<Double> =
//            blackjackManager.calculateParticipantsEarningRate().values.map { it.earningRate.rate }
//
//        // then
//        assertThat(playersEarningRate).isEqualTo(
//            listOf(0.0, 1.5)
//        )
//    }
}
