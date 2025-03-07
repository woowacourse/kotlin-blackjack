package blackjack

import blackjack.domain.Card
import blackjack.domain.Rank
import blackjack.domain.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantTest {
    @Test
    fun `덱에서 한 장의 카드를 가져올 수 있다`() {
        val participant = FakeParticipant()
        val card = Card.of(Rank.ACE, Suit.SPADE)

        participant.addCard(card)
        assertThat(participant.cards).contains(card)
    }

    @Test
    fun `카드의 총합을 계산할 수 있다`() {
        val player = FakeParticipant()

        val card1 = Card.of(Rank.TWO, Suit.SPADE)
        val card2 = Card.of(Rank.THREE, Suit.SPADE)

        player.addCard(card1)
        player.addCard(card2)

        assertThat(player.totalSum).isEqualTo(5)
    }

    @Test
    fun `플레이어는 가지고 있는 카드의 합을 계산할 수 있다(ACE 1장)`() {
        val player = FakeParticipant()

        val card1 = Card.of(Rank.TWO, Suit.SPADE)
        val card2 = Card.of(Rank.ACE, Suit.SPADE)

        player.addCard(card1)
        player.addCard(card2)

        assertThat(player.totalSum).isEqualTo(13)
    }

    @Test
    fun `플레이어는 가지고 있는 카드의 합을 계산할 수 있다(ACE 2장)`() {
        val player = FakeParticipant()

        val card1 = Card.of(Rank.ACE, Suit.SPADE)
        val card2 = Card.of(Rank.NINE, Suit.SPADE)
        val card3 = Card.of(Rank.ACE, Suit.HEART)

        player.addCard(card1)
        player.addCard(card2)
        player.addCard(card3)

        assertThat(player.totalSum).isEqualTo(21)
    }

    @Test
    fun `카드의 총합이 21이 넘으면 버스트가 된다`() {
        val player = FakeParticipant()

        val card1 = Card.of(Rank.TEN, Suit.SPADE)
        val card2 = Card.of(Rank.NINE, Suit.SPADE)
        val card3 = Card.of(Rank.KING, Suit.HEART)

        player.addCard(card1)
        player.addCard(card2)
        player.addCard(card3)

        assertThat(player.isBust()).isEqualTo(true)
    }
}
