package blackjack

import blackjack.domain.GameResult
import blackjack.domain.ParticipantCards
import blackjack.domain.card.CardTier
import blackjack.domain.card.Shape
import blackjack.domain.card.TrumpCard
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player
import blackjack.fixture.trumpCardFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어가 게임을 시작하면 카드를 2장 지급받는다`() {
        val player = Player("페토", ParticipantCards())
        val fixture = trumpCardFixture()
        fixture.forEach {
            player.receiveCard(it)
        }
        assertThat(player.getAllCards()).containsExactly(*fixture.toTypedArray())
    }

    @Test
    fun `플레이어 카드의 총합이 21을 초과하면 버스트된다`() {
        val player = Player("peto", ParticipantCards())
        repeat(3) {
            player.receiveCard(TrumpCard(CardTier.KING, Shape.DIA))
        }
        assertThat(player.isBust()).isEqualTo(true)
    }

    @Test
    fun `플레이어가 버스트되지 않고 딜러의 카드 총합보다 카드의 총합이 크면 승리한다`() {
        val participants = Participants(Dealer(ParticipantCards()), listOf(Player("bibi", ParticipantCards())))
        participants.dealer.receiveCard(TrumpCard(CardTier.KING, Shape.DIA))
        participants.dealer.receiveCard(TrumpCard(CardTier.NINE, Shape.DIA))
        repeat(2) {
            participants.players.first().receiveCard(TrumpCard(CardTier.KING, Shape.DIA))
        }
        assertThat(participants.players.first().getResult(participants.dealer)).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `플레이어가 버스트되지 않고 딜러의 카드 총합과 카드의 총합이 크면 무승부이다`() {
        val participants = Participants(Dealer(ParticipantCards()), listOf(Player("bibi", ParticipantCards())))
        repeat(2) {
            participants.dealer.receiveCard(TrumpCard(CardTier.KING, Shape.DIA))
        }
        repeat(2) {
            participants.players.first().receiveCard(TrumpCard(CardTier.KING, Shape.DIA))
        }
        assertThat(participants.players.first().getResult(participants.dealer)).isEqualTo(GameResult.DRAW)
    }
}
