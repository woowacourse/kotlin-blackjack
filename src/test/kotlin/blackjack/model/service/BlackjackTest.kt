package blackjack.model.service

import blackjack.model.domain.GameResult
import blackjack.model.domain.card.Card
import blackjack.model.domain.card.PlayingCard
import blackjack.model.domain.participant.Dealer
import blackjack.model.domain.participant.Player
import blackjack.model.domain.participant.PlayerGroup
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BlackjackTest {
    private lateinit var player1: Player
    private lateinit var player2: Player
    private lateinit var player3: Player
    private lateinit var dealer: Dealer
    private lateinit var card: List<Card>
    private lateinit var deck: PlayingCard
    private lateinit var game: Blackjack
    private lateinit var playerGroup: PlayerGroup

    // given
    @BeforeEach
    fun setup() {
        player1 = Player("제리")
        player2 = Player("환노")
        player3 = Player("포르")
        dealer = Dealer()
        card = Card.CARDDECK.values.toList()
        deck = PlayingCard(ArrayDeque(card))
        playerGroup = PlayerGroup(listOf(player1, player2, player3), dealer)
        game = Blackjack(deck, playerGroup)
    }

    @Test
    fun `게임 시작시 카드를 2장을 나눈다`() {
        // when
        game.initGame()
        // then
        assertThat(dealer.cardDeck.size).isEqualTo(2)
        assertThat(player1.cardDeck.size).isEqualTo(2)
        assertThat(player2.cardDeck.size).isEqualTo(2)
    }

    @Test
    fun `게임이 끝난 후 승패를 가린다`() {
        // given
        player1.receiveCard(listOf(Card.from("AceSpade")))
        player2.receiveCard(listOf(Card.from("SixSpade")))
        player3.receiveCard(listOf(Card.from("SevenHeart")))
        dealer.receiveCard(listOf(Card.from("SevenSpade")))
        // when
        val actual = game.endGame()
        val expected =
            mapOf(
                player1 to GameResult.Win,
                player2 to GameResult.Lose,
                player3 to GameResult.Draw,
            )

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `딜러는 처음에 받은 2장의 합계가 16이하이면 카드를 추가로 받는다`() {
        // given
        game.initGame()
        // when
        game.drawUntilThreshold(dealer)
        // then
        assertThat(dealer.cardDeck.size).isGreaterThan(2)
    }

    @Test
    fun `플레이어가 hit을 외치면 카드 한장을 뽑습니다`() {
        // when
        val size = player1.cardDeck.size
        game.hitAction(player1)
        // then
        assertThat(player1.cardDeck.size).isEqualTo(size + 1)
    }

    @Test
    fun `플레이어가 hit을 외쳤는데 카드 숫자의 합이 21이 넘으면 bust된다`() {
        // given
        player1.receiveCard(
            listOf(
                Card.from("KingSpade"),
                Card.from("KingHeart"),
                Card.from("AceHeart"),
            ),
        )
        val size = player1.cardDeck.size
        // when
        game.hitAction(player1)
        // then
        assertThat(player1.cardDeck.size).isEqualTo(size + 1)
    }
}
