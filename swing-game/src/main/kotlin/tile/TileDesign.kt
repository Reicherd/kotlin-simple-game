package arw.simple.game.tile

enum class TileDesign(val value: Int) {
    GRASS(0),
    WALL(1),
    WATER(2);

    companion object {
        fun fromValue(value: Int): TileDesign = when (value) {
            0 -> GRASS
            1 -> WALL
            2 -> WATER
            else -> {
                throw IllegalArgumentException("Illegal value $value")
            }
        }
    }
}