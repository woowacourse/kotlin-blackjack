package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.result.Result
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `카드를 더 받아야 하는 상태인지 확인한다`() {
        val dealer = Dealer("aaa")
        dealer.addCard(Card(CardNumber.EIGHT, CardShape.CLOVER))
        val actual = dealer.canHit()
        assertThat(actual).isEqualTo(true)
    }

    @Test
    fun `참가자들을 받아 각각의 승패를 결정하도록 전달한다`() {
        val participant1 = Participant("aa")
        val participant2 = Participant("bb")
        participant1.addCard(Card(CardNumber.FIVE, CardShape.HEART))
        participant2.addCard(Card(CardNumber.QUEEN, CardShape.DIAMOND))
        val participants = Participants(listOf(participant1, participant2))
        val dealer = Dealer("dealer")
        dealer.addCard(Card(CardNumber.SEVEN, CardShape.DIAMOND))
        dealer.decideParticipantsResult(participants)
        assertThat(participant1.result).isEqualTo(Result.LOSE)
        assertThat(participant2.result).isEqualTo(Result.WIN)
    }

    @Test
    fun `딜러는 각 참가자들의 승패와 상반되는 승패 결과를 모두 더한다`() {
        val participant1 = Participant("aa")
        val participant2 = Participant("bb")
        participant1.addCard(Card(CardNumber.FIVE, CardShape.HEART))
        participant2.addCard(Card(CardNumber.QUEEN, CardShape.DIAMOND))
        val participants = Participants(listOf(participant1, participant2))
        val dealer = Dealer("dealer")
        dealer.addCard(Card(CardNumber.SEVEN, CardShape.DIAMOND))
        dealer.decideParticipantsResult(participants)
        dealer.decideDealerResult(participants)
        assertThat(dealer.results).isEqualTo(
            mapOf(Result.WIN to 1, Result.DRAW to 0, Result.LOSE to 1)
        )
    }
}
