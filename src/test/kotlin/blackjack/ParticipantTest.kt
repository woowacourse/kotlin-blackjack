package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import blackjack.domain.participant.Participant
import blackjack.fakeParticipant.FakeParticipant
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
    fun `카드를 추가하면 참가자가 가지는 카드에 추가한 카드를 찾을 수 있다`() {
        val participant = FakeParticipant()
        val card = Card.of(Rank.ACE, Suit.SPADE)

        participant.addCard(card)
        assertThat(participant.cards.toList()).contains(card)
    }

    @Test
    fun `참가자에게 카드를 추가하면 카드의 총합을 계산한 점수의 정보를 가져올 수 있다`() {
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
    fun `참가자에게 카드를 추가하면 카드의 총합을 계산한 점수의 정보를 가져올 수 있다(ACE 1장)`() {
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
    fun `참가자에게 카드를 추가하면 카드의 총합을 계산한 점수의 정보를 가져올 수 있다(ACE 2장)`() {
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
    fun `총합이 21이 넘는 카드를 전달하면 버스트라는 사실을 알 수 있다`() {
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

    @Test
    fun `A한장과 10, J,Q,K 중 한 장의 카드를 가지고 있지 않으면 블랙잭으로 판단하지 않는다`() {
        val player = FakeParticipant()

        setPlayerCard(
            player,
            listOf(
                Card.of(Rank.TEN, Suit.SPADE),
                Card.of(Rank.NINE, Suit.SPADE),
                Card.of(Rank.KING, Suit.HEART),
            ),
        )
        assertThat(player.isBlackJack()).isFalse()
    }

    @Test
    fun `A한장과 10, J,Q,K 중 한 장의 카드를 가지고 있으면 블랙잭으로 판단한다`() {
        val player = FakeParticipant()

        setPlayerCard(
            player,
            listOf(
                Card.of(Rank.ACE, Suit.SPADE),
                Card.of(Rank.TEN, Suit.SPADE),
            ),
        )
        assertThat(player.isBlackJack()).isTrue()
    }
}
