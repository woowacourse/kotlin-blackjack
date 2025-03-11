package blackjack

import blackjack.domain.Card
import blackjack.domain.Participant
import blackjack.domain.Rank
import blackjack.domain.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FakeParticipant : Participant() {
    override val hitThreshold: Int
        get() = 21
}

class ParticipantTest {

    private lateinit var participant: Participant

    @BeforeEach
    fun setUp() {
        participant = FakeParticipant()
    }

    @Test
    fun `덱에서 한 장의 카드를 가져올 수 있다`() {
        val card = Card.of(rank = Rank.ACE, suit = Suit.SPADE)

        participant.addCard(card)
        assertThat(participant.cards).contains(card)
    }

    @Test
    fun `카드의 총합을 계산할 수 있다`() {
        val card1 = Card.of(rank = Rank.TWO, suit = Suit.SPADE)
        val card2 = Card.of(rank = Rank.THREE, suit = Suit.SPADE)

        participant.addCard(card1)
        participant.addCard(card2)

        assertThat(participant.totalSum).isEqualTo(5)
    }

    @Test
    fun `플레이어는 가지고 있는 카드의 합을 계산할 수 있다(ACE 1장)`() {
        val card1 = Card.of(rank = Rank.TWO, suit = Suit.SPADE)
        val card2 = Card.of(rank = Rank.ACE, suit = Suit.SPADE)

        participant.addCard(card1)
        participant.addCard(card2)

        assertThat(participant.totalSum).isEqualTo(13)
    }

    @Test
    fun `플레이어는 가지고 있는 카드의 합을 계산할 수 있다(ACE 2장)`() {
        val card1 = Card.of(rank = Rank.ACE, suit = Suit.SPADE)
        val card2 = Card.of(rank = Rank.NINE, suit = Suit.SPADE)
        val card3 = Card.of(rank = Rank.ACE, suit = Suit.HEART)

        participant.addCard(card1)
        participant.addCard(card2)
        participant.addCard(card3)

        assertThat(participant.totalSum).isEqualTo(21)
    }

    @Test
    fun `카드의 총합이 21이 넘으면 버스트가 된다`() {
        val card1 = Card.of(rank = Rank.TEN, suit = Suit.SPADE)
        val card2 = Card.of(rank = Rank.NINE, suit = Suit.SPADE)
        val card3 = Card.of(rank = Rank.KING, suit = Suit.HEART)

        participant.addCard(card1)
        participant.addCard(card2)
        participant.addCard(card3)

        assertThat(participant.isBust()).isEqualTo(true)
    }
}
