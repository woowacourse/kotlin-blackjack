package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private fun createCardDeckFrom(vararg numbers: Int): CardDeck = CardDeck(numbers.map { Card(it) })

private fun createDealer(vararg numbers: Int): Dealer {
    val hand = Hand(numbers.map { Card(it) }.toList())
    return Dealer(state = Hit(hand))
}

private fun createPlayer(
    name: String,
    vararg numbers: Int,
): Player {
    val hand = Hand(numbers.map { Card(it) })
    return Player(ParticipantName(name), state = Hit(hand))
}

private fun Card(value: Int): Card {
    return Card(CardNumber.entries.find { it.value == value }!!, CardShape.HEART)
}

class BlackjackGameTest {
    @Test
    fun `게임 참가자 카드 뽑기 테스트`() {
        val deck = createCardDeckFrom(6, 7, 11, 11, 10, 5)
        val dealer = createDealer(8, 9)
        val players = createPlayer("leo", 2, 2)

        val gameParticipants = Participants(dealer, listOf(players))
        val game = BlackjackGame(deck, gameParticipants)
        game.playRound({ true }, { })

        val playerAfterRound = game.participants.players.first()
        assertThat(playerAfterRound.state.hand().calculateSum()).isEqualTo(21)

        val dealerAfterRound = game.participants.dealer
        assertThat(dealerAfterRound.state.hand().calculateSum()).isEqualTo(17)
    }

    @Test
    fun `최종 승패 계산 테스트`() {
        val deck = createCardDeckFrom(6, 7, 11, 11, 10, 5)
        val dealer = createDealer(8, 9)
        val players =
            listOf(
                createPlayer("leo", 10, 8),
                createPlayer("yenni", 11, 10),
            )

        val gameParticipants = Participants(dealer, players)
        val game = BlackjackGame(deck, gameParticipants)
        val result = game.calculateResult()

        assertThat(result[dealer]).isEqualTo(WinningState(wins = 0, losses = 2))
        assertThat(result[players[0]]).isEqualTo(WinningState(wins = 1, losses = 0))
        assertThat(result[players[1]]).isEqualTo(WinningState(wins = 1, losses = 0))
    }
}
