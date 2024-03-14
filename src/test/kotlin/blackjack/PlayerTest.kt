package blackjack

import blackjack.model.BettingAmount
import blackjack.model.Card
import blackjack.model.CardDeck
import blackjack.model.CardNumber
import blackjack.model.CardSymbol
import blackjack.model.Participant.Player
import blackjack.model.ParticipantName
import blackjack.model.UserInformation
import blackjack.view.OutputView
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var player: Player
    private lateinit var cardDeck: CardDeck

    @BeforeEach
    fun setUp() {
        player = Player(UserInformation(ParticipantName("채드"), BettingAmount(1000)))
        cardDeck = CardDeck()
    }

    @Test
    fun `카드 한장 뽑기`() {
        player.draw(cardDeck.pickCard())

        assertThat(player.gameInformation.cards.size).isEqualTo(1)
    }

    @Test
    fun `추가 드로우 의사가 있을때 카드 추가로 뽑기`() {
        player.draw(Card(CardNumber.TWO, CardSymbol.SPADE))
        player.draw(Card(CardNumber.THREE, CardSymbol.SPADE))

        fun readDecision(): Boolean {
            return player.gameInformation.cards.size == 2
        }
        player.judgeDrawOrNot(cardDeck, { readDecision() }) { OutputView.outputParticipantCard(player) }

        assertThat(player.gameInformation.cards.size == 3).isTrue
    }

    @Test
    fun `추가 드로우 의사가 없을때`() {
        player.draw(Card(CardNumber.TWO, CardSymbol.SPADE))
        player.draw(Card(CardNumber.THREE, CardSymbol.SPADE))

        fun readDecision(): Boolean {
            return false
        }
        player.judgeDrawOrNot(cardDeck, { readDecision() }) { OutputView.outputParticipantCard(player) }

        assertThat(player.gameInformation.cards.size == 2).isTrue
    }
}
