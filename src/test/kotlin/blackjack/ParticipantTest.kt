package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ParticipantTest {
    private lateinit var participant: Participant

    @BeforeEach
    fun setUp() {
        participant = Dealer()
    }

    @Test
    fun `참가자가 카드를 추가하면 핸드에 카드가 포함된다`() {
        val card = Card.of(rank = Rank.ACE, suit = Suit.SPADE)

        participant.addCard(card)

        assertThat(participant.hand.cards()).contains(card)
    }

    @Test
    fun `카드의 총합이 21이 넘으면 버스트가 된다`() {
        val card1 = Card.of(rank = Rank.TEN, suit = Suit.SPADE)
        val card2 = Card.of(rank = Rank.NINE, suit = Suit.SPADE)
        val card3 = Card.of(rank = Rank.KING, suit = Suit.HEART)

        participant.addCard(card1)
        participant.addCard(card2)
        participant.addCard(card3)

        assertThat(participant.hand.isBust()).isEqualTo(true)
    }
}
