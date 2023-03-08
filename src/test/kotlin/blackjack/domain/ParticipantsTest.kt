package blackjack.domain

import blackjack.Shape.HEART
import blackjack.domain.carddeck.CardDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantsTest {

    @Test
    fun `유저두명에게 각각 카드를 한장씩 추가로 분배한다`() {
        val stubInput = StubInput()
        val participants = Participants(listOf("krong", "dogpig"), StubAddPlayerCardDeck())
        participants.progressPlayersAddCard({ stubInput.stubGetDecision() }, {})
        assertThat(participants.players[0].cardBunch.cards).isEqualTo(
            listOf(
                Card(HEART, CardNumber.TWO),
                Card(HEART, CardNumber.FOUR),
                Card(HEART, CardNumber.FIVE),
            ),
        )
        assertThat(participants.players[1].cardBunch.cards).isEqualTo(
            listOf(
                Card(HEART, CardNumber.FIVE),
                Card(HEART, CardNumber.SIX),
                Card(HEART, CardNumber.SIX),
            ),
        )
    }
}

class StubAddPlayerCardDeck : CardDeck {
    private val cardDeck = mutableListOf(
        // 딜러 초기화
        Card(HEART, CardNumber.TWO),
        Card(HEART, CardNumber.THREE),
        // 플레이어 두명 초기화
        Card(HEART, CardNumber.TWO),
        Card(HEART, CardNumber.FOUR),
        Card(HEART, CardNumber.FIVE),
        Card(HEART, CardNumber.SIX),
        // 플레이어 별로 한장씩 추가
        Card(HEART, CardNumber.FIVE),
        Card(HEART, CardNumber.SIX),
    )

    override fun drawCard(): Card = cardDeck.removeFirst()
}

class StubInput() {
    private val decisions = mutableListOf(true, false, true, false)

    fun stubGetDecision(): Boolean = decisions.removeFirst()
}
