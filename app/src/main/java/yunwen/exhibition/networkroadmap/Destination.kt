package yunwen.exhibition.networkroadmap

interface Destination {
    val route: String
}

object RetrofitDestination : Destination {
    override val route: String = "RetrofitDestination"
}

object OkHttpDestination : Destination {
    override val route: String = "OkHttpDestination"
}

object GraphQLDestination: Destination {
    override val route: String
        get() = "GraphQLDestination"
}