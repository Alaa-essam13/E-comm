package com.example.e_comm.presentaion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.e_comm.Components.BottmItem
import com.example.e_comm.Components.CustomBottomNavBar
import com.example.e_comm.R
import com.example.e_comm.presentaion.Pages.Cart.Cart
import com.example.e_comm.presentaion.Pages.Home.HomeScreen
import com.example.e_comm.presentaion.Pages.Home.dataViewModel
import com.example.e_comm.presentaion.Pages.Search.SearchPage
import com.example.e_comm.presentaion.Pages.itemPage.ItemPage
import com.example.e_comm.presentaion.Pages.itemspage.itemslist
import com.example.e_comm.presentaion.Pages.ListAllProducts.ListProducts
import com.example.e_comm.presentaion.Pages.itemPage.ItemViewModel
import com.example.e_comm.presentaion.Pages.onBoarding.OnboardingUtils
import com.example.e_comm.presentaion.Pages.onBoarding.Onboardingcreen
import com.example.e_comm.utile.filterLoved
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val onboardingUtils by lazy { OnboardingUtils(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        if(onboardingUtils.isOnboardingCompleted()){
            val splashScreen = installSplashScreen()
            splashScreen.setKeepOnScreenCondition{true}
            CoroutineScope(Dispatchers.Main).launch{
                delay(2000L)
                splashScreen.setKeepOnScreenCondition{false}
            }
        }
        setContent {
            if(onboardingUtils.isOnboardingCompleted())
                BasePoint()
            else
                ShowOnBoarding()
        }
    }
    @Composable
    fun ShowOnBoarding() {
        val scope = rememberCoroutineScope()
        Onboardingcreen {
            onboardingUtils.setOnboardingCompleted()
            scope.launch {
                setContent {
                    BasePoint()
                }
            }
        }
    }

}




@Composable
fun BasePoint() {
    val navController = rememberNavController()
    var showBottomBar by rememberSaveable {
        mutableStateOf(true)
    }
    navController.addOnDestinationChangedListener { _, destination, _ ->
        showBottomBar = destination.route != "Item/{itemId}"
    }
    val itms = listOf(
        BottmItem("Home", R.drawable.home,R.drawable.fillhome,"Home"),
        BottmItem("Search", R.drawable.search,R.drawable.fillsearch,"Search"),
        BottmItem("ListProducts", R.drawable.shop,R.drawable.fillshop,"ListProducts"),
        BottmItem("Cart", R.drawable.cart,R.drawable.fillcart,"Cart"),
        BottmItem("", R.drawable.btn_5,R.drawable.filluser,""),
    )
    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                CustomBottomNavBar(navController,itms)
            }
        }
    ) { padding ->
        NavigationPoint( navController = navController, padding = padding)
    }
}

@Composable
fun NavigationPoint(
    navController: NavHostController,
    padding: PaddingValues,
) {

    val sharedViewModel:dataViewModel= hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = "Home",
        modifier = Modifier.padding(padding)
    ) {
        composable("Home") {
            HomeScreen( {
                navController.navigate("ListLovedProducts")
            }) { itemId ->
                navController.navigate("Item/$itemId")
            }
        }

        composable("Search") {
            SearchPage {
                navController.navigate("Item/${it.id}")
            }
        }

        composable(
            route = "Item/{itemId}",
            arguments = listOf(navArgument("itemId") {
                type = NavType.IntType
            })
        ) {
            ItemPage {
                navController.popBackStack()
                sharedViewModel.fetchData()
            }
        }
        composable(
            route = "ListProducts"
        ) {
            ListProducts { itemId ->
                navController.navigate("Item/$itemId")
            }
        }
        composable(
            route = "Cart"
        ) {
            Cart()
        }
        composable(route = "ListLovedProducts") {
            itemslist(sharedViewModel){
                navController.navigate("Item/$it")
            }
        }

    }
}





