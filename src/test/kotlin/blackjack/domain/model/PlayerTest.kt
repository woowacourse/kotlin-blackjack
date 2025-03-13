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
    private val aceHeart = Card(Shape.Heart, CardNumber.Ace)
    private val sixSpade = Card(Shape.Spade, CardNumber.Six)
    private val kingHeart = Card(Shape.Heart, CardNumber.King)
    private val queenDiamond = Card(Shape.Diamond, CardNumber.Queen)

    @BeforeEach
    fun setup() {
        // given
        player = Player("hwannow")
        dealer = Dealer()
    }

    @Test
    fun `받은 카드의 목록을 반환한다`() {
        // given
        player.receiveCard(listOf(aceHeart, sixSpade))
        // when
        val actual = player.cardDeck
        val expected = listOf(Card(Shape.Heart, CardNumber.Ace), Card(Shape.Spade, CardNumber.Six))
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `플레이어가 버스트라면 항상 패배한다`() {
        // given
        player.receiveCard(listOf(kingHeart, queenDiamond, sixSpade))

        dealer.receiveCard(listOf(kingHeart, aceHeart))
        // when
        val actual = player.compareScores(dealer)
        val expected = GameResult.Lose
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `플레이어가 버스트가 아니고, 딜러가 버스트라면 승리한다`() {
        // given
        player.receiveCard(listOf(kingHeart, aceHeart))

        dealer.receiveCard(listOf(queenDiamond, kingHeart, sixSpade))
        // when
        val actual = player.compareScores(dealer)
        val expected = GameResult.Win
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `플레이어와 딜러 모두 버스트가 아니라면 점수가 높은 참가자가 승리한다`() {
        // given
        player.receiveCard(listOf(sixSpade, queenDiamond))

        dealer.receiveCard(listOf(kingHeart, aceHeart))
        // when
        val actual = player.compareScores(dealer)
        val expected = GameResult.Lose
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `플레이어와 딜러 모두 버스트가 아니고, 점수가 같다면 무승부한다`() {
        // given
        player.receiveCard(listOf(sixSpade, queenDiamond))

        dealer.receiveCard(listOf(sixSpade, queenDiamond))
        // when
        val actual = player.compareScores(dealer)
        val expected = GameResult.Draw
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `플레이어의 점수 합계가 21점 미만이라면 더 뽑을 수 있다`() {
        // given
        player.receiveCard(listOf(sixSpade))
        // when
        val actual = player.canHit()
        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `플레이어의 점수 합계가 21점 이상이라면 더 뽑을 수 없다`() {
        // given
        player.receiveCard(listOf(kingHeart, queenDiamond, sixSpade))
        // when
        val actual = player.canHit()
        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun `플레이어는 초기 카드로 2장을 보여 준다`() {
        // given
        player.receiveCard(listOf(aceHeart, sixSpade, kingHeart))
        // when
        val actual = player.getInitCard().size
        val expected = 2
        // then
        assertThat(actual).isEqualTo(expected)
    }
}
