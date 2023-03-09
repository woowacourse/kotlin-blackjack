package blackjack.domain

import domain.CardGame
import domain.CardPackGenerator
import model.InputState
import model.cards.Card
import model.cards.Hand
import model.cards.Rank
import model.cards.Suit
import model.participants.Dealer
import model.participants.Name
import model.participants.Player
import model.participants.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import view.OutputView

class CardGameTest {
    private lateinit var game: CardGame

    @BeforeEach
    fun setUp() {
        val outputView = OutputView()
        game = CardGame(CardPackGenerator().createCardPack(), outputView::printError)
    }

    @Test
    fun `게임을 시작하면 이름을 받고 각각 카드를 두 장씩 세팅한다`() {
        val participants = game.setUp { InputState.Success(listOf("jason", "pobi")) }
        assertThat(participants.dealer.name.value).isEqualTo("딜러")
        assertThat(participants.dealer.hand.size).isEqualTo(2)
        assertThat(participants.players.toList()[0].name.value).isEqualTo("jason")
        assertThat(participants.players.toList()[0].hand.size).isEqualTo(2)
        assertThat(participants.players.toList()[1].name.value).isEqualTo("pobi")
        assertThat(participants.players.toList()[1].hand.size).isEqualTo(2)
    }

    @Test
    fun `각 플레이어의 배팅 금액을 세팅할 수 있다`() {
        val getBet = { InputState.Success(1000) }
        val participants = game.setUp { InputState.Success(listOf("jason", "pobi")) }
        val betInfos = game.setBets(participants.players, {}, getBet)
        participants.players.toList().forEach {
            if (betInfos[it] != null) assertThat(betInfos[it]!!.amount).isEqualTo(1000)
        }
    }

    @Test
    fun `Hit인 플레이어가 y를 입력하면 카드를 더 뽑을 수 있다`() {
        val getAnswer = { InputState.Success(true) }
        val players = Players(listOf(player(Name("jason"), Rank.JACK, Rank.NINE), player(Name("pobi"), Rank.JACK, Rank.NINE)))
        game.askGetMorePlayersCard(players, {}, getAnswer, {})
        players.toList().forEach {
            assertThat(it.hand.size).isGreaterThan(2)
        }
    }

    @Test
    fun `Hit인 플레이어가 n을 입력하면 카드를 더 뽑지 않는다`() {
        val getAnswer = { InputState.Success(false) }
        val players = Players(listOf(player(Name("jason"), Rank.JACK, Rank.NINE), player(Name("pobi"), Rank.JACK, Rank.NINE)))
        game.askGetMorePlayersCard(players, {}, getAnswer, {})
        players.toList().forEach {
            assertThat(it.hand.size).isEqualTo(2)
        }
    }

    @Test
    fun `딜러가 Hit이면 Hit이 아닐 때까지 카드를 더 뽑는다`() {
        val dealer = dealer(Rank.JACK, Rank.DEUCE)
        game.getMoreDealerCard(dealer) {}
        assertThat(dealer.hand.size).isGreaterThan(2)
        assertThat(dealer.isHit()).isFalse
    }

    @Test
    fun `딜러가 Hit이 아니면 카드를 더 뽑지 않는다`() {
        val dealer = dealer(Rank.JACK, Rank.NINE)
        game.getMoreDealerCard(dealer) {}
        assertThat(dealer.hand.size).isEqualTo(2)
        assertThat(dealer.isHit()).isFalse
    }

    private fun player(name: Name, vararg ranks: Rank): Player = Player(
        Hand(ranks.map { Card(it, Suit.DIAMOND) }), name
    )

    private fun dealer(vararg ranks: Rank): Dealer = Dealer(
        Hand(ranks.map { Card(it, Suit.HEART) })
    )
}
