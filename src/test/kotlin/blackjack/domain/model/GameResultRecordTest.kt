package blackjack.domain.model

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.CardNumber
import blackjack.domain.model.card.Suit
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameResultRecordTest {
    @Test
    fun `딜러와 플레이어를 비교해 딜러의 게임 결과를 가져올 수 있다`() {
        val dealer = Dealer() // 20
        val firstPlayer = Player() // BLACKJACK
        val secondPlayer = Player() // 20
        dealer.handCards.add(Card(CardNumber.KING, Suit.HEART))
        dealer.handCards.add(Card(CardNumber.JACK, Suit.CLUB))
        firstPlayer.handCards.add(Card(CardNumber.ACE, Suit.CLUB))
        firstPlayer.handCards.add(Card(CardNumber.QUEEN, Suit.DIAMOND))
        secondPlayer.handCards.add(Card(CardNumber.TEN, Suit.SPADE))
        secondPlayer.handCards.add(Card(CardNumber.QUEEN, Suit.HEART))

        val result = GameResultRecord(dealer, listOf(firstPlayer, secondPlayer)).getDealerResult()

        assertThat(result[GameResult.WIN]).isEqualTo(0)
        assertThat(result[GameResult.DRAW]).isEqualTo(1)
        assertThat(result[GameResult.LOSE]).isEqualTo(1)
    }
}
