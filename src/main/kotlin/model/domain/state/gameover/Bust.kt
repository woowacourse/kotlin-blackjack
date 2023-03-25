package model.domain.state.gameover

import model.domain.card.Hand

class Bust(override val hand: Hand) : GameOverState()