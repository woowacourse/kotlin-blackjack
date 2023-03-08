package domain

import domain.card.Card
import domain.card.CardValue
import domain.card.Shape
import domain.deck.Deck
import domain.participants.Names
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackGameTest {

    val deck = Deck(
        listOf(
            Card(Shape.SPADE, CardValue.KING),
            Card(Shape.DIAMOND, CardValue.QUEEN),
            Card(Shape.CLOVER, CardValue.JACK),
            Card(Shape.DIAMOND, CardValue.TEN),
            Card(Shape.HEART, CardValue.THREE),
            Card(Shape.SPADE, CardValue.TWO)
        )
    )
    val blackjackGame = BlackjackGame(Names(listOf("pingu")), deck)

    @Test
    fun `입력값이 false라면 카드를 뽑지않는다`() {
        blackjackGame.playsTurn({ false }, {})
        val expect = mutableListOf(
            Card(Shape.DIAMOND, CardValue.TEN),
            Card(Shape.CLOVER, CardValue.JACK)
        )

        assertThat(blackjackGame.players.first().ownCards.cards).isEqualTo(expect)
    }

    @Test
    fun `입력값이 true라면 플레이어가 burst가 될 때까지 카드를 뽑는다`() {
        blackjackGame.playsTurn({ true }, {})
        val expect = mutableListOf(
            Card(Shape.DIAMOND, CardValue.TEN),
            Card(Shape.CLOVER, CardValue.JACK),
            Card(Shape.DIAMOND, CardValue.QUEEN)
        )

        assertThat(blackjackGame.players.first().ownCards.cards).isEqualTo(expect)
    }

    @Test
    fun `딜러가 16이하의 값을 가졌다면 16을 넘을 때까지 계속 카드를 뽑는다`() {
        blackjackGame.dealerPickCard({})
        assertThat(blackjackGame.dealer.ownCards.calculateCardSum()).isEqualTo(25)
    }

    // @Test
    // fun `카드값 20을 가진 플레이어와 5를 가진 딜러를 비교하여 승을 반환한다`() {
    //     blackjackGame.dealer.judgePlayersResult(blackjackGame.players)
    //
    //     assertThat(blackjackGame.players.first().result).isEqualTo(Result.WIN)
    // }
}
