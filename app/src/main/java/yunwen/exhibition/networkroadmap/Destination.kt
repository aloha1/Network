package yunwen.exhibition.networkroadmap

interface Destination {
    val route: String
}

object HomeDestination : Destination {
    override val route: String = "HomeDestination"
}

object OkHttpDestination : Destination {
    override val route: String = "OkHttpDestination"
}