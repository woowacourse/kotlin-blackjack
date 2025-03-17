package blackjack.domain

import blackjack.domain.card.Deck
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BlackjackGameTest {
    private lateinit var player: Player
    private lateinit var dealer: Dealer
    private lateinit var participants: Participants
    private lateinit var deck: Deck

    @BeforeEach
    fun setUp() {
        player = Player("포르")
        dealer = Dealer()
        participants = Participants(dealer, listOf(player))
        deck = Deck.create()
    }

    @Test
    fun `초기 카드를 나누어 주면 딜러의 카드는 2장이다`() {
        val game = BlackjackGame(deck, participants)
        game.dealInitialCards()
        assertThat(dealer.hand.cards.size).isEqualTo(2)
    }

    @Test
    fun `참가자가 카드 뽑기를 한 번 하면 플레이어의 카드는 한 장이다`() {
        // given
        val game = BlackjackGame(deck, participants)

        var firstCall = true
        val shouldContinue: (Participant) -> Boolean = {
            if (firstCall) {
                firstCall = false
                true
            } else {
                false
            }
        }

        // when
        game.playTurns(
            shouldContinue = shouldContinue,
            onDraw = {},
        )

        // then
        assertThat(player.hand.cards.size).isEqualTo(1)
    }
}
