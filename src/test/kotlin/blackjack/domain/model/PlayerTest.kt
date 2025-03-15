package blackjack.domain.model

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Rank
import blackjack.domain.model.card.Suit
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Player
import blackjack.domain.model.result.GameResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerTest {
    @ParameterizedTest
    @ValueSource(strings = ["", " ", "\t", "\n"])
    fun `플레이어의 이름이 공백일 시 오류가 발생한다`(value: String) {
        assertThrows<IllegalArgumentException> { Player(value) }
    }

    @Test
    fun `플레이어는 모든 카드를 공개한다`() {
        val player = Player("A", Card(Suit.HEART, Rank.TWO), Card(Suit.HEART, Rank.THREE))
        assertThat(player.showHand()).isEqualTo(listOf(Card(Suit.HEART, Rank.TWO), Card(Suit.HEART, Rank.THREE)))
    }

    @Test
    fun `플레이어가 버스트되지 않았으면 히트할 수 있다`() {
        val player = Player("A", Card(Suit.HEART, Rank.TWO), Card(Suit.HEART, Rank.THREE)) // 5점
        assertThat(player.canHit()).isTrue()
    }

    @Test
    fun `플레이어가 버스트됐으면 히트할 수 없다`() {
        val player = Player("A", Card(Suit.HEART, Rank.JACK), Card(Suit.HEART, Rank.QUEEN), Card(Suit.HEART, Rank.KING)) // 30점
        assertThat(player.canHit()).isFalse()
    }

    @Test
    fun `플레이어가 버스트되지 않았고 점수가 딜러보다 높을 시 플레이어가 승리한다`() {
        val player = Player("A", Card(Suit.HEART, Rank.QUEEN), Card(Suit.HEART, Rank.KING)) // 20점
        val dealer = Dealer(Card(Suit.SPADE, Rank.TWO), Card(Suit.SPADE, Rank.THREE)) // 5점
        assertThat(player.compareAgainst(dealer)).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `플레이어는 버스트되지 않고 딜러는 버스트됐을 시 플레이어가 승리한다`() {
        val player = Player("A", Card(Suit.HEART, Rank.TWO), Card(Suit.HEART, Rank.THREE)) // 5점
        val dealer = Dealer(Card(Suit.SPADE, Rank.JACK), Card(Suit.SPADE, Rank.QUEEN), Card(Suit.SPADE, Rank.KING)) // 30점
        assertThat(player.compareAgainst(dealer)).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `딜러가 버스트되지 않았고 플레이어의 점수가 딜러보다 낮을 시 플레이어가 패배한다`() {
        val player = Player("A", Card(Suit.HEART, Rank.TWO), Card(Suit.HEART, Rank.THREE)) // 5점
        val dealer = Dealer(Card(Suit.SPADE, Rank.QUEEN), Card(Suit.SPADE, Rank.KING)) // 20점
        assertThat(player.compareAgainst(dealer)).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `플레이어는 버스트되고 딜러는 버스트되지 않았을 시 플레이어가 패배한다`() {
        val player = Player("A", Card(Suit.HEART, Rank.JACK), Card(Suit.HEART, Rank.QUEEN), Card(Suit.HEART, Rank.KING)) // 30점
        val dealer = Dealer(Card(Suit.SPADE, Rank.TWO), Card(Suit.SPADE, Rank.THREE)) // 5점
        assertThat(player.compareAgainst(dealer)).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `플레이어와 딜러가 모두 버스트됐을 시 플레이어가 패배한다`() {
        val player = Player("A", Card(Suit.HEART, Rank.JACK), Card(Suit.HEART, Rank.QUEEN), Card(Suit.HEART, Rank.KING)) // 30점
        val dealer = Dealer(Card(Suit.SPADE, Rank.JACK), Card(Suit.SPADE, Rank.QUEEN), Card(Suit.SPADE, Rank.KING)) // 30점
        assertThat(player.compareAgainst(dealer)).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `플레이어와 딜러가 모두 버스트되지 않고 점수가 같을 시 비긴다`() {
        val player = Player("A", Card(Suit.HEART, Rank.TWO), Card(Suit.HEART, Rank.THREE)) // 5점
        val dealer = Dealer(Card(Suit.SPADE, Rank.TWO), Card(Suit.SPADE, Rank.THREE)) // 5점
        assertThat(player.compareAgainst(dealer)).isEqualTo(GameResult.PUSH)
    }

    @Test
    fun `플레이어가 블랙잭이고 딜러는 블랙잭이 아닐 시 플레이어가 블랙잭이 된다`() {
        val player = Player("A", Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.KING)) // 21점
        val dealer = Dealer(Card(Suit.SPADE, Rank.TWO), Card(Suit.SPADE, Rank.THREE)) // 5점
        assertThat(player.compareAgainst(dealer)).isEqualTo(GameResult.BLACKJACK)
    }

    @Test
    fun `플레이어와 딜러가 동시에 블랙잭일 시 비긴다`() {
        val player = Player("A", Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.KING)) // 21점
        val dealer = Dealer(Card(Suit.SPADE, Rank.ACE), Card(Suit.SPADE, Rank.KING)) // 21점
        assertThat(player.compareAgainst(dealer)).isEqualTo(GameResult.PUSH)
    }

    @Test
    fun `플레이어의 첫 패가 블랙잭이고 히트하지 않았을 시 블랙잭이다`() {
        val player = Player("A", Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.KING)) // 21점
        assertThat(player.isBlackJack()).isTrue()
    }

    @Test
    fun `플레이어의 첫 패가 블랙잭이었어도 히트했을 시 블랙잭이 아니다`() {
        val player = Player("A", Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.KING), Card(Suit.HEART, Rank.TWO)) // 13점
        assertThat(player.isBlackJack()).isFalse()
    }
}
