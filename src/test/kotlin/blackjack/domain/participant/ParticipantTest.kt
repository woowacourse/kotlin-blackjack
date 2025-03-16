package blackjack.domain.participant

import blackjack.domain.SIX_HEART
import blackjack.domain.TEN_HEART
import blackjack.model.card.Card
import blackjack.model.card.CardCount
import blackjack.model.card.CardDeck
import blackjack.model.hand.Hand
import blackjack.model.participant.Money
import blackjack.model.participant.Name
import blackjack.model.participant.Participant
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ParticipantTest {
    private lateinit var participant: DummyParticipant

    @BeforeEach
    fun setup() {
        participant = DummyParticipant("공백", 10_000.0)
    }

    @Test
    fun `"공백"이라는 이름, 10000원, 빈 패를 가진 Participant가 정상적으로 생성된다`() {
        // then
        assertEquals("공백", participant.name.value)
        assertEquals(Money(10_000.0), participant.money)
        assertTrue(participant.cards.isEmpty())
    }

    @Test
    fun `receiveMoney 호출 시 돈이 증가한다`() {
        // when
        participant.receiveMoney(Money(10_000.0))

        // then
        assertEquals(Money(20_000.0), participant.money)
    }

    @Test
    fun `payMoney 호출 시 돈이 차감된다`() {
        // when
        participant.payMoney(Money(3000.0))

        // then
        assertEquals(Money(7000.0), participant.money)
    }

    @Test
    fun `receiveCards 호출 시 처음에는 2장, 이후에는 1장을 받는다`() {
        // given
        val cardDeck = CardDeck()
        val drawCards: (CardCount) -> List<Card> = { cardDeck.draw(it) }

        // when
        participant.receiveCards(drawCards)
        val firstDrawSize = participant.cards.size

        participant.receiveCards(drawCards)
        val secondDrawSize = participant.cards.size

        // then
        assertEquals(2, firstDrawSize)
        assertEquals(3, secondDrawSize)
    }

    @Test
    fun `addAll 호출 시 카드가 정상적으로 추가된다`() {
        // given
        val cards = listOf(SIX_HEART, TEN_HEART)

        // when
        participant.addAll(cards)

        // then
        assertEquals(2, participant.cards.size)
        assertTrue(participant.cards.containsAll(cards))
    }
}

class DummyParticipant(
    name: String,
    money: Double,
    hand: Hand = Hand(),
) : Participant(Name(name), Money(money), hand) {
    override val isDrawable: Boolean = true

    override fun showInitialCards(): List<Card> = cards.take(2)
}
