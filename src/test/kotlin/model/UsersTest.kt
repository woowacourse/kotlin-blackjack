package model

import entity.Card
import entity.CardNumber
import entity.CardType
import entity.Dealer
import entity.Money
import entity.Name
import entity.Player
import entity.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UsersTest {

    private fun Players(): Players {
        return Players(listOf(Player(Name("test"), Money(0))))
    }

    @Test
    fun `딜러와 모든 플레이어에게 카드 2장씩 분배한다`() {
        // given
        val cardFactory = ManualCardFactory(
            listOf(
                CardType.CLUB to CardNumber.THREE,
                CardType.SPADE to CardNumber.QUEEN,
                CardType.DIAMOND to CardNumber.TWO,
                CardType.CLUB to CardNumber.FOUR
            )
        )
        val players = Players()
        val dealer = Dealer()
        val users = Users(players, dealer)

        // when
        users.distribute(cardFactory)

        // then
        val dealerExcept = listOf(
            Card(CardType.CLUB, CardNumber.THREE),
            Card(CardType.SPADE, CardNumber.QUEEN),
        )
        val playerExcept = listOf(
            Card(CardType.DIAMOND, CardNumber.TWO),
            Card(CardType.CLUB, CardNumber.FOUR),
        )
        assertThat(users.dealer.cards.value).isEqualTo(dealerExcept)
        assertThat(users.players.value[0].cards.value).isEqualTo(playerExcept)
    }
}
