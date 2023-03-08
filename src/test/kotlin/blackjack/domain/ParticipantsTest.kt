package blackjack.domain

import blackjack.Shape.HEART
import blackjack.domain.carddeck.CardDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantsTest {

    @Test
    fun `유저두명에게 각각 카드를 한장씩 추가로 분배한다`() {
        val stubInput = StubInput()
        val participants = Participants(
            listOf("krong", "dogpig"),
            StubCardDeck(
                mutableListOf(
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
                ),
            ),
        )
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
        val participants = Participants(
            listOf("krong", "dogpig"),
            StubCardDeck(
                mutableListOf(
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
                ),
            ),
        )
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
        val participants = Participants(
            listOf("krong", "dogpig"),
            StubCardDeck(
                mutableListOf(
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
                ),
            ),
        )
        participants.judgmentDealerAddCard()
        assertThat(participants.dealer.cardBunch.cards).isEqualTo(
            listOf(
                Card(HEART, CardNumber.JACK),
                Card(HEART, CardNumber.SEVEN),
            ),
        )
    }

    @Test
    fun `딜러의 총점이 17점 이라면 유저의 점수가 6일때는 패배하고 유저의 점수가 19라면 승리한다`() {
        val participants = Participants(
            listOf("krong", "dogpig"),
            StubCardDeck(
                mutableListOf(
                    // 딜러 초기화
                    Card(HEART, CardNumber.JACK),
                    Card(HEART, CardNumber.SEVEN),
                    // 플레이어 두명 초기화
                    // 첫번째 유저
                    Card(HEART, CardNumber.TWO),
                    Card(HEART, CardNumber.FOUR),
                    // 두번째 유저
                    Card(HEART, CardNumber.JACK),
                    Card(HEART, CardNumber.NINE),
                ),
            ),
        )
        assertThat(participants.getConsequence(participants.players[0])).isEqualTo(Consequence.LOSE)
        assertThat(participants.getConsequence(participants.players[1])).isEqualTo(Consequence.WIN)
    }
}

class StubCardDeck(private val cardDeck: MutableList<Card>) : CardDeck {

    override fun drawCard(): Card = cardDeck.removeFirst()
}

class StubInput() {
    private val decisions = mutableListOf(true, false, true, false)

    fun stubGetDecision(): Boolean = decisions.removeFirst()
}
