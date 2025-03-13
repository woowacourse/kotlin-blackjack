package blackjack.domain

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.card.CardRank
import blackjack.model.card.CardSuit
import blackjack.model.game.GameManager
import blackjack.model.game.WinningManager
import blackjack.model.participant.Name
import blackjack.model.participant.Participant.Companion.INITIAL_DRAW_COUNT
import blackjack.model.rule.WinningResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameManagerTest {
    private lateinit var gameManager: GameManager
    private lateinit var cardDeck: CardDeck

    @BeforeEach
    fun setup() {
        gameManager = GameManager()
        cardDeck = CardDeck()
    }

    @Test
    fun `딜러가 정상적으로 2장을 가진 채로 준비된다`() {
        // given
        val dealerName = "딜러"

        // when
        val dealer = gameManager.prepareDealer(dealerName, cardDeck)

        // then
        assertThat(dealer.name.toString()).isEqualTo(dealerName)
        assertThat(dealer.cards.size).isEqualTo(INITIAL_DRAW_COUNT)
    }

    @Test
    fun `플레이어들이 정상적으로 각각 2장을 가진 채로 준비된다`() {
        // given
        val playerNames = listOf("Alice", "Bob")

        // when
        val players = gameManager.preparePlayers(playerNames, cardDeck)

        // then
        assertThat(players.value.map { it.name.toString() }).containsExactlyElementsOf(playerNames)
        players.value.forEach { player ->
            assertThat(player.cards.size).isEqualTo(INITIAL_DRAW_COUNT)
        }
    }

    @Test
    fun `딜러가 16점 이하일 때 추가로 카드를 뽑는다`() {
        // given
        val dealer = gameManager.prepareDealer("딜러", cardDeck)
        dealer.addAll(listOf(Card(CardRank.TWO, CardSuit.HEART)))

        // when
        gameManager.progressDealerDraw(dealer, cardDeck::draw)

        // then
        assertThat(dealer.score()).isGreaterThan(16)
    }

    @Test
    fun `딜러와 플레이어의 게임 결과가 정상적으로 생성된다`() {
        // given
        val dealer = gameManager.prepareDealer("딜러", cardDeck)
        val players = gameManager.preparePlayers(listOf("Alice"), cardDeck)
        val winningManager = WinningManager(dealer, players)

        // when
        val gameResult = gameManager.getResult(winningManager)

        // then
        assertThat(gameResult.dealerResult.keys).containsExactlyInAnyOrder(*WinningResult.entries.toTypedArray())
        assertThat(gameResult.playerResults.keys).containsExactly(Name("Alice"))
    }
}
