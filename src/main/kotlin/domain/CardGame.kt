package domain

import model.cards.CardPack
import model.cards.Cards
import model.cards.Hand
import model.participants.Bet
import model.participants.BetInfo
import model.participants.Dealer
import model.participants.Name
import model.participants.Names
import model.participants.Participants
import model.participants.Player
import model.participants.Players

class CardGame(private val cardPack: CardPack) {
    fun setUp(names: Names): Participants {
        val dealer = setDealer()
        val players = setPlayers(names)
        return Participants(dealer, players)
    }

    private fun setDealer(): Dealer = Dealer(Hand(listOf(cardPack.pop(), cardPack.pop())))

    private fun setPlayers(names: Names): Players = Players(bulidPlayers(names))

    private fun bulidPlayers(names: Names) = buildList {
        names.forEach {
            add(setPlayer(it))
        }
    }

    private fun setPlayer(name: Name): Player = Player(Hand(pickTwice()), name)

    private fun pickTwice(): Cards = Cards(listOf(cardPack.pop(), cardPack.pop()))

    fun setBets(players: Players, onGetBet: (Name) -> Unit, getBet: () -> Bet): BetInfo =
        BetInfo(buildBetInfo(players, onGetBet, getBet))

    private fun buildBetInfo(players: Players, onGetBet: (Name) -> Unit, getBet: () -> Bet): Map<Player, Bet> =
        buildMap {
            players.toList().forEach {
                onGetBet(it.name)
                put(it, getBet())
            }
        }

    fun askGetMorePlayersCard(
        players: Players,
        onHit: (Name) -> Unit,
        getAnswer: () -> Boolean,
        onGetPlayerCard: (Player) -> Unit
    ) {
        players.toList().filter { it.isHit() }.forEach {
            onHit(it.name)
            askGetMorePlayerCard(it, getAnswer, onGetPlayerCard, onHit)
        }
    }

    private fun askGetMorePlayerCard(
        player: Player,
        getAnswer: () -> Boolean,
        onGetPlayerCard: (Player) -> Unit,
        onHit: (Name) -> Unit
    ) {
        while (player.isHit() && getAnswer()) {
            player.pick(cardPack)
            onGetPlayerCard(player)
            if (player.isHit()) onHit(player.name)
        }
    }

    fun getMoreDealerCard(dealer: Dealer, onHitDealer: () -> Unit) {
        while (dealer.isHit()) {
            onHitDealer()
            dealer.pick(cardPack)
        }
    }
}
