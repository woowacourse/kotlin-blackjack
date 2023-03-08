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

    @Test
    fun `딜러의 첫카드 2장의 합이 16 이하라면 카드한장을 더뽑는다`() {
        val participants = Participants(listOf("krong", "dogpig"), StubAddDealerCardDeck())
        participants.judgmentDealerAddCard()
        assertThat(participants.dealer.cardBunch.cards).isEqualTo(
            listOf(
                Card(HEART, CardNumber.TWO),
                Card(HEART, CardNumber.THREE),
                Card(HEART, CardNumber.FIVE),
            ),
        )
    }

    @Test
    fun `딜러의 첫카드 2장의 합이 16 이상이라면 카드한장를 뽑지 않는다`() {
        val participants = Participants(listOf("krong", "dogpig"), StubAddDealerCardDeck2())
        participants.judgmentDealerAddCard()
        assertThat(participants.dealer.cardBunch.cards).isEqualTo(
            listOf(
                Card(HEART, CardNumber.JACK),
                Card(HEART, CardNumber.SEVEN)
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
        // 첫번째 유저
        Card(HEART, CardNumber.TWO),
        Card(HEART, CardNumber.FOUR),
        // 두번째 유저
        Card(HEART, CardNumber.FIVE),
        Card(HEART, CardNumber.SIX),
        // 플레이어 별로 한장씩 추가
        Card(HEART, CardNumber.FIVE),
        Card(HEART, CardNumber.SIX),
    )

    override fun drawCard(): Card = cardDeck.removeFirst()
}

class StubAddDealerCardDeck : CardDeck {
    private val cardDeck = mutableListOf(
        // 딜러 초기화
        Card(HEART, CardNumber.TWO),
        Card(HEART, CardNumber.THREE),
        // 플레이어 두명 초기화
        // 첫번째 유저
        Card(HEART, CardNumber.TWO),
        Card(HEART, CardNumber.FOUR),
        // 두번째 유저
        Card(HEART, CardNumber.FIVE),
        Card(HEART, CardNumber.SIX),
        // 추가 딜러가 받는카드
        Card(HEART, CardNumber.FIVE),
    )

    override fun drawCard(): Card = cardDeck.removeFirst()
}

class StubAddDealerCardDeck2 : CardDeck {
    private val cardDeck = mutableListOf(
        // 딜러 초기화
        Card(HEART, CardNumber.JACK),
        Card(HEART, CardNumber.SEVEN),
        // 플레이어 두명 초기화
        // 첫번째 유저
        Card(HEART, CardNumber.TWO),
        Card(HEART, CardNumber.FOUR),
        // 두번째 유저
        Card(HEART, CardNumber.FIVE),
        Card(HEART, CardNumber.SIX),
        // 추가 딜러가 받는카드
        Card(HEART, CardNumber.FIVE),
    )

    override fun drawCard(): Card = cardDeck.removeFirst()
}

class StubInput() {
    private val decisions = mutableListOf(true, false, true, false)

    fun stubGetDecision(): Boolean = decisions.removeFirst()
}
