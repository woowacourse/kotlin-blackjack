package model.domain.state.gameover

import model.domain.card.Hand

class Stay(override val hand: Hand) : GameOverState()
