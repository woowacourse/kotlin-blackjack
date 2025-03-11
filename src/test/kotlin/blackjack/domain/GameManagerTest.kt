package blackjack.domain

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.card.CardRank
import blackjack.model.card.CardSuit
import blackjack.model.game.GameManager
import blackjack.model.game.ResultManager
import blackjack.model.participant.Participant.Companion.INITIAL_DRAW_COUNT
import blackjack.model.rule.ScoreCalculator
import blackjack.model.rule.WinningResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameManagerTest {
    private lateinit var gameManager: GameManager
    private lateinit var scoreCalculator: ScoreCalculator
    private lateinit var cardDeck: CardDeck

    @BeforeEach
    fun setup() {
        gameManager = GameManager()
        scoreCalculator = ScoreCalculator()
        cardDeck = CardDeck()
    }

    @Test
    fun `딜러가 정상적으로 2장을 가진 채로 준비된다`() {
        // given
        val dealerName = "딜러"

        // when
        val dealer = gameManager.prepareDealer(dealerName, cardDeck, scoreCalculator)

        // then
        assertThat(dealer.name).isEqualTo(dealerName)
        assertThat(dealer.cards.size).isEqualTo(INITIAL_DRAW_COUNT)
    }

    @Test
    fun `플레이어들이 정상적으로 각각 2장을 가진 채로 준비된다`() {
        // given
        val playerNames = listOf("Alice", "Bob")

        // when
        val players = gameManager.preparePlayers(playerNames, cardDeck, scoreCalculator)

        // then
        assertThat(players.value.map { it.name }).containsExactlyElementsOf(playerNames)
        players.value.forEach { player ->
            assertThat(player.cards.size).isEqualTo(INITIAL_DRAW_COUNT)
        }
    }

    @Test
    fun `딜러가 16점 이하일 때 추가로 카드를 뽑는다`() {
        // given
        val dealer = gameManager.prepareDealer("딜러", cardDeck, scoreCalculator)
        dealer.addAll(listOf(Card(CardRank.TWO, CardSuit.HEART)))

        // when
        gameManager.progressDealerDraw(dealer, cardDeck::draw)

        // then
        assertThat(dealer.score()).isGreaterThan(16)
    }

    @Test
    fun `딜러와 플레이어의 게임 결과가 정상적으로 생성된다`() {
        // given
        val dealer = gameManager.prepareDealer("딜러", cardDeck, scoreCalculator)
        val players = gameManager.preparePlayers(listOf("Alice"), cardDeck, scoreCalculator)
        val resultManager = ResultManager(dealer, players)

        // when
        val gameResult = gameManager.getResult(resultManager)

        // then
        assertThat(gameResult.dealerResult.keys).containsExactlyInAnyOrder(*WinningResult.entries.toTypedArray())
        assertThat(gameResult.playerResults.keys).containsExactly("Alice")
    }
}
