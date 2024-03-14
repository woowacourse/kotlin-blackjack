package blackjack.model.playing.participants.player

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.card.CardNumber
import blackjack.model.card.CardShape
import blackjack.model.card.generator.ExplicitCardGenerator
import blackjack.model.playing.cardhand.CardHand
import blackjack.model.playing.cardhand.CardHandState
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    private val cardHand = CardDeck()

    @Test
    fun `카드 패에 카드가 2장 있고, 카드 숫자의 합이 21이면 카드 패의 상태는 BLACKJACK 이다`() {
        val cardHand =
            CardHand(
                Card(CardShape.HEART, CardNumber.TEN),
                Card(CardShape.SPADE, CardNumber.ACE),
            )

        val player = Player(PlayerName("해나"), cardHand)

        assertThat(player.getState()).isEqualTo(CardHandState.BLACKJACK)
    }

    @Test
    fun `카드 패의 합이 20 이하이면 카드 패의 상태는 DRAW_POSSIBILITY 이다`() {
        val cardHand =
            CardHand(
                Card(CardShape.HEART, CardNumber.SEVEN),
                Card(CardShape.SPADE, CardNumber.SIX),
            )

        val player = Player(PlayerName("해나"), cardHand)

        assertThat(player.getState()).isEqualTo(CardHandState.DRAW_POSSIBILITY)
    }

    @Test
    fun `카드 패에 카드가 3장 이상 있고, 카드 숫자의 합이 21이면 카드 패의 상태는 DRAW_POSSIBILITY 이다`() {
        val cardHand =
            CardHand(
                Card(CardShape.HEART, CardNumber.FIVE),
                Card(CardShape.SPADE, CardNumber.SIX),
                Card(CardShape.DIAMOND, CardNumber.TEN),
            )

        val player = Player(PlayerName("해나"), cardHand)

        assertThat(player.getState()).isEqualTo(CardHandState.DRAW_POSSIBILITY)
    }

    @Test
    fun `카드 패 숫자의 합이 21 초과이면 카드 패의 상태는 BUST 이다`() {
        val cardHand =
            CardHand(
                Card(CardShape.HEART, CardNumber.FIVE),
                Card(CardShape.SPADE, CardNumber.SIX),
                Card(CardShape.DIAMOND, CardNumber.SEVEN),
                Card(CardShape.DIAMOND, CardNumber.EIGHT),
            )

        val player = Player(PlayerName("해나"), cardHand)

        assertThat(player.getState()).isEqualTo(CardHandState.BUST)
    }

    @Test
    fun `상태가 HIT 이면 카드 한 장을 더 뽑는다`() {
        val player =
            Player(
                PlayerName("해나"),
                CardHand(
                    Card(CardShape.SPADE, CardNumber.ACE),
                    Card(CardShape.SPADE, CardNumber.TWO),
                ),
            )

        if (player.getState() == CardHandState.HIT) {
            player.draw(cardHand)
        }
        assertThat(player.cardHand.hand.size).isEqualTo(2)
    }

    @Test
    fun `상태가 HIT 이 아니면 카드 한 장을 더 뽑는다`() {
        val player =
            Player(
                PlayerName("해나"),
                CardHand(
                    Card(CardShape.SPADE, CardNumber.ACE),
                    Card(CardShape.SPADE, CardNumber.TWO),
                ),
            )

        if (player.getState() == CardHandState.HIT) {
            player.draw(CardDeck(ExplicitCardGenerator()))
        }

        assertThat(player.cardHand.hand.size).isEqualTo(2)
    }
}
