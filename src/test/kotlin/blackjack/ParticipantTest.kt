package blackjack

import blackjack.domain.Participant
import blackjack.domain.card.Card
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantTest {
    private fun setPlayerCard(
        player: Participant,
        cards: List<Card>,
    ) {
        cards.forEach { card -> player.addCard(card) }
    }

    @Test
    fun `덱에서 한 장의 카드를 추가할 수 있다`() {
        val participant = FakeParticipant()
        val card = Card.of(Rank.ACE, Suit.SPADE)

        participant.addCard(card)
        assertThat(participant.cards.getCards()).contains(card)
    }

    @Test
    fun `카드의 총합을 계산할 수 있다`() {
        val player = FakeParticipant()

        setPlayerCard(
            player,
            listOf(
                Card.of(Rank.TWO, Suit.SPADE),
                Card.of(Rank.THREE, Suit.SPADE),
            ),
        )

        assertThat(player.totalSum).isEqualTo(5)
    }

    @Test
    fun `플레이어는 가지고 있는 카드의 합을 계산할 수 있다(ACE 1장)`() {
        val player = FakeParticipant()

        setPlayerCard(
            player,
            listOf(
                Card.of(Rank.TWO, Suit.SPADE),
                Card.of(Rank.ACE, Suit.SPADE),
            ),
        )

        assertThat(player.totalSum).isEqualTo(13)
    }

    @Test
    fun `플레이어는 가지고 있는 카드의 합을 계산할 수 있다(ACE 2장)`() {
        val player = FakeParticipant()
        setPlayerCard(
            player,
            listOf(
                Card.of(Rank.ACE, Suit.SPADE),
                Card.of(Rank.NINE, Suit.SPADE),
                Card.of(Rank.ACE, Suit.HEART),
            ),
        )

        assertThat(player.totalSum).isEqualTo(21)
    }

    @Test
    fun `카드의 총합이 21이 넘으면 버스트가 된다`() {
        val player = FakeParticipant()

        setPlayerCard(
            player,
            listOf(
                Card.of(Rank.TEN, Suit.SPADE),
                Card.of(Rank.NINE, Suit.SPADE),
                Card.of(Rank.KING, Suit.HEART),
            ),
        )
        assertThat(player.isBust()).isEqualTo(true)
    }
}
