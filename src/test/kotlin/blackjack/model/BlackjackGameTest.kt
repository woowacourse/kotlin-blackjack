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
    return Player(name, state = Hit(hand))
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
    }
}
