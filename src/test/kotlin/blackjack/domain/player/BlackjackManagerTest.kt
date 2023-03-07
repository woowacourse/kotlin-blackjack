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
        blackjackManager.generateParticipants { listOf("aaa", "bbb") }

        // when
        blackjackManager.settingPlayersCards()
        val actual1 = blackjackManager.dealer.cards.values.size
        val actual2 = blackjackManager.participants.values.map { it.cards.values.size }

        // then
        assertThat(actual1).isEqualTo(2)
        assertThat(actual2).isEqualTo(listOf(2, 2))
    }

    @Test
    fun `카드 발행 가능 상태이고 추가 발행을 원한다면 참가자에게 카드를 나눠준다`() {
        // given
        val blackjackManager = BlackjackManager(TestCardsGenerator())
        blackjackManager.generateParticipants { listOf("aaa", "bbb") }
        blackjackManager.settingPlayersCards()

        // when
        val participant1 = blackjackManager.participants.values[0]
        val participant2 = blackjackManager.participants.values[1]
        blackjackManager.provideParticipantMoreCard(participant1) { true }
        blackjackManager.provideParticipantMoreCard(participant2) { false }
        val actual1 = participant1.cards.values.size
        val actual2 = participant2.cards.values.size

        // then
        assertThat(actual1).isEqualTo(3)
        assertThat(actual2).isEqualTo(2)
    }

    @Test
    fun `카드 발행 가능 상태인 동안 딜러에게 계속 카드를 발행한다`() {
        // given
        val blackjackManager = BlackjackManager(TestCardsGenerator())
        blackjackManager.generateParticipants { listOf("aaa", "bbb") }
        blackjackManager.settingPlayersCards()

        // when
        blackjackManager.provideDealerMoreCard()
        val actual = blackjackManager.dealer.cards.values.size

        // then
        assertThat(actual).isEqualTo(4)
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
                Card(CardNumber.SIX, CardShape.SPADE)
            )
    }
}
