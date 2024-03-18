package model

import model.card.Deck
import model.human.Dealer
import model.human.Human
import model.human.Player
import model.human.Players

class BlackjackGame(private val deck: Deck) {
    fun initGame(
        dealer: Dealer,
        players: Players,
        showGameInit: (Dealer, Players) -> Unit,
    ) {
        initDealer(dealer)
        initPlayers(players)
        showGameInit(dealer, players)
    }

    private fun initDealer(dealer: Dealer) {
        initPlayer(dealer)
    }

    private fun initPlayers(players: Players) {
        players.players.forEach { player ->
            initPlayer(player)
        }
    }

    private fun initPlayer(human: Human) {
        repeat(2) {
            human.hand.add(deck.pop())
        }
    }

    fun play(
        dealer: Dealer,
        players: Players,
        playOfOnePlayer: (Player) -> Unit,
    ) {
        players.players.forEach { player ->
            playOfOnePlayer(player)
        }
        dealer.play(deck)
    }

    fun playByAnswer(
        answer: Answer,
        player: Player,
        onDone: (Human) -> Unit,
    ): Boolean {
        val isBusted =
            when (answer) {
                Answer.YES -> {
                    player.hand.add(deck.pop())
                    player.isHittable()
                }
                Answer.NO -> false
            }
        onDone(player)
        return isBusted
    }

    fun judgeWinningResult(
        dealer: Dealer,
        players: Players,
    ): PlayersResult  {
        val playersResult = PlayersResult()
        players.players.forEach { player ->
            playersResult.add(dealer.judge(player))
        }
        return playersResult
    }
}
