package model

import entity.GameResults

interface GameResultDeterminer {
    fun determine(user: User, users: List<User>): GameResults
}
