package blackjack.domain.model

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.CardNumber
import blackjack.domain.model.card.Shape
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var player: Player
    private lateinit var dealer: Dealer
    private val aceSpade = Card(Shape.Spade, CardNumber.Ace)
    private val twoSpade = Card(Shape.Spade, CardNumber.Two)
    private val aceHeart = Card(Shape.Heart, CardNumber.Ace)
    private val sixSpade = Card(Shape.Spade, CardNumber.Six)

    @BeforeEach
    fun setup() {
        // given
        player = Player("hwannow")
        dealer = Dealer()
    }

    @Test
    fun `받은 카드의 목록을 반환한다`() {
        // given
        player.receiveCard(aceHeart)
        player.receiveCard(sixSpade)
        // when
        val actual = player.cardDeck
        val expected = listOf(Card(Shape.Heart, CardNumber.Ace), Card(Shape.Spade, CardNumber.Six))
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `타켓값이 임계값보다 작으면 Lose상태를 반환한다`() {
        // given
        player.receiveCard(twoSpade)
        dealer.receiveCard(aceSpade)
        // when
        val actual = player.compareScores(dealer)
        val expected = GameResult.Lose
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `타켓값이 임계값보다 크면 Win상태를 반환한다`() {
        // given
        player.receiveCard(aceSpade)
        dealer.receiveCard(twoSpade)
        // when
        val actual = player.compareScores(dealer)
        val expected = GameResult.Win
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `타켓값이 임계값과 같으면 Draw상태를 반환한다`() {
        // given
        player.receiveCard(aceSpade)
        dealer.receiveCard(aceHeart)
        // when
        val actual = player.compareScores(dealer)
        val expected = GameResult.Draw
        // then
        assertThat(actual).isEqualTo(expected)
    }
}
