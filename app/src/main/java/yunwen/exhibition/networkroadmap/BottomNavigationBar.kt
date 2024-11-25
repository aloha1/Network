package yunwen.exhibition.networkroadmap

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val curRoute = navBackStackEntry?.destination?.route

        NavigationBarItem(
            icon = { },
            label = { Text(text = HomeDestination.route) },
            selected = curRoute == HomeDestination.route,
            onClick = { navController.navigate(HomeDestination.route) }
        )
        NavigationBarItem(
            icon = { },
            label = { Text(text = OkHttpDestination.route) },
            selected = curRoute == OkHttpDestination.route,
            onClick = { navController.navigate(OkHttpDestination.route) }
        )
    }
}