package blackjack

import blackjack.domain.GameResult
import blackjack.domain.Money
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
        val player = Player("페토", ParticipantCards(), Money(1000))
        val fixture = trumpCardFixture()
        fixture.forEach {
            player.receiveCard(it)
        }
        assertThat(player.getAllCards()).containsExactly(*fixture.toTypedArray())
    }

    @Test
    fun `플레이어 카드의 총합이 21을 초과하면 버스트된다`() {
        val player = Player("peto", ParticipantCards(), Money(1000))
        repeat(3) {
            player.receiveCard(TrumpCard(CardTier.KING, Shape.DIA))
        }
        assertThat(player.cards.isBust()).isEqualTo(true)
    }

    @Test
    fun `플레이어가 버스트되지 않고 딜러의 카드 총합보다 카드의 총합이 크면 승리한다`() {
        val participants = Participants(Dealer(ParticipantCards()), listOf(Player("bibi", ParticipantCards(), Money(1000))))
        participants.dealer.receiveCard(TrumpCard(CardTier.KING, Shape.DIA))
        participants.dealer.receiveCard(TrumpCard(CardTier.NINE, Shape.DIA))
        repeat(2) {
            participants.players.first().receiveCard(TrumpCard(CardTier.KING, Shape.DIA))
        }
        assertThat(participants.players.first().getResult(participants.dealer)).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `플레이어가 버스트되지 않고 딜러의 카드 총합과 카드의 총합이 크면 무승부이다`() {
        val participants = Participants(Dealer(ParticipantCards()), listOf(Player("bibi", ParticipantCards(), Money(1000))))
        repeat(2) {
            participants.dealer.receiveCard(TrumpCard(CardTier.KING, Shape.DIA))
        }
        repeat(2) {
            participants.players.first().receiveCard(TrumpCard(CardTier.KING, Shape.DIA))
        }
        assertThat(participants.players.first().getResult(participants.dealer)).isEqualTo(GameResult.DRAW)
    }

    @Test
    fun `플레이어만 블랙잭이면 1․5배의 수익률을 얻는다`() {
        val player = Player("peto", ParticipantCards(), Money(10000))

        val expected = 15000.00

        assertThat(player.getProfit(GameResult.BLACKJACK)).isEqualTo(expected)
    }

    @Test
    fun `플레이어와 딜러 둘 다 블랙잭이면 무승부이다`() {
        val player = Player("bibi", ParticipantCards(), Money(10000))
        val dealer = Dealer(ParticipantCards())
        player.receiveCard(TrumpCard(CardTier.KING, Shape.DIA))
        player.receiveCard(TrumpCard(CardTier.ACE, Shape.DIA))
        dealer.receiveCard(TrumpCard(CardTier.KING, Shape.DIA))
        dealer.receiveCard(TrumpCard(CardTier.ACE, Shape.DIA))

        val expected = GameResult.DRAW

        assertThat(player.getResult(dealer)).isEqualTo(expected)
    }

    @Test
    fun `플레이어와 딜러 둘 다 블랙잭이면 베팅 금액을 돌려받는다`() {
        val player = Player("bibi", ParticipantCards(), Money(10000))

        val expected = 0.00

        assertThat(player.getProfit(GameResult.DRAW)).isEqualTo(expected)
    }
}
