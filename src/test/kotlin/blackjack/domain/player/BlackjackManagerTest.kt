package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.card.CardsGenerator
import blackjack.domain.card.RandomCardsGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackManagerTest {

    @Test
    fun `입력받은 이름의 참가자를 생성한다`() {

        // given
        val blackjackManager = BlackjackManager(RandomCardsGenerator())
        blackjackManager.generateParticipants { listOf("aaa", "bbb") }

        // when
        val actual = blackjackManager.participants.values.map { it.name }

        // then
        assertThat(actual).isEqualTo(listOf("aaa", "bbb"))
    }

    @Test
    fun `초기에 모든 플레이어에게 카드 두장을 나누어준다`() {
        // given
        val blackjackManager = BlackjackManager(RandomCardsGenerator())
        val dealer = Dealer()
        val participant1 = Participant("aaa")
        val participant2 = Participant("bbb")

        // when
        blackjackManager.settingPlayersCards(dealer, Participants(listOf(participant1, participant2)))
        val actual = listOf(dealer.cards.values.size, participant1.cards.values.size, participant2.cards.values.size)

        // then
        assertThat(actual).isEqualTo(listOf(2, 2, 2))
    }

    @Test
    fun `추가 발행 여부에 따라 플레이어에게 카드를 나누어준다`() {
        // given
        val blackjackManager = BlackjackManager(TestCardsGenerator())
        val dealer = Dealer()
        val participant1 = Participant("aaa")
        val participant2 = Participant("bbb")
        blackjackManager.settingPlayersCards(dealer, Participants(listOf(participant1, participant2)))

        // when
        blackjackManager.provideParticipantMoreCard(participant1) { true }
        blackjackManager.provideParticipantMoreCard(participant2) { false }

        // then
        assertThat(participant1.cards.values.size).isEqualTo(3)
        assertThat(participant2.cards.values.size).isEqualTo(2)
    }

    class TestCardsGenerator : CardsGenerator {
        override fun generate(): List<Card> =
            listOf(
                Card(CardNumber.FOUR, CardShape.HEART),
                Card(CardNumber.FIVE, CardShape.HEART),

                Card(CardNumber.FOUR, CardShape.DIAMOND),
                Card(CardNumber.FIVE, CardShape.DIAMOND),

                Card(CardNumber.FOUR, CardShape.CLOVER),
                Card(CardNumber.FIVE, CardShape.CLOVER),

                Card(CardNumber.FOUR, CardShape.SPADE),
                Card(CardNumber.FIVE, CardShape.SPADE),
                Card(CardNumber.SIX, CardShape.SPADE),
            )
    }
}
