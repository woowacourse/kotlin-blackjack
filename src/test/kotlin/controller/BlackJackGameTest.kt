package controller

import domain.Card
import domain.CardCategory
import domain.CardNumber
import domain.CardPicker
import domain.Cards
import domain.Name
import domain.Names
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackGameTest {

    fun Names(vararg names: String): Names {
        return Names(names.map(::Name))
    }

    class TestCardPicker() : CardPicker {
        override fun draw(): Card {
            return Card.of(cardCategory = CardCategory.CLOVER, cardNumber = CardNumber.FIVE)
        }

        override fun drawInitCards(): Cards {
            return Cards(List(2) { draw() })
        }
    }

    @Test
    fun `숫자가 5인 카드만 뽑을 수 있고, 계속 카드를 받는다고 하면 모든 플레이어의 최종 스코어는 25이다`() {
        // given
        val blackJackGame = BlackJackGame(Names("scott", "woogi", "mendel"), TestCardPicker())
        // when
        blackJackGame.playersSelectAddPhase({ true }, {})
        // then
        blackJackGame.participants.players.list.forEach { player ->
            assertThat(player.getScore().getValue()).isEqualTo(25)
        }
    }

    @Test
    fun `숫자가 5인 카드만 뽑을 수 있고, 카드를 받지 않는다고 하면 모든 플레이어의 최종 스코어는 10이다`() {
        // given
        val blackJackGame = BlackJackGame(Names("scott", "woogi", "mendel"), TestCardPicker())
        // when
        blackJackGame.playersSelectAddPhase({ false }, {})
        // then
        blackJackGame.participants.players.list.forEach { player ->
            assertThat(player.getScore().getValue()).isEqualTo(10)
        }
    }

    @Test
    fun `숫자가 5인 카드만 뽑을 수 있을 때, 딜러의 최종 스코어는 15 이다`() {
        // given
        val blackJackGame = BlackJackGame(Names("scott", "woogi", "mendel"), TestCardPicker())
        // when
        blackJackGame.dealerSelectPhase { }
        // then
        assertThat(blackJackGame.participants.dealer.getScore().getValue()).isEqualTo(15)
    }

    @Test
    fun `참가자들의 승패 결과를 반환한다`() {
    }
}
