package com.example.e_comm.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.e_comm.R
import com.example.e_comm.ui.theme.Purple40

@Composable
fun CustomBottomNavBar(
    navController: NavHostController,
    itms: List<BottmItem>,
    selected: Int = 0,
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(Color.Black),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        itms.forEachIndexed { index, itm ->
            val isSelected = currentRoute == itm.route
            if (index == 2) {
                Box(
                    modifier = Modifier
                        .offset(y = (-20).dp)
                        .size(70.dp)
                        .background(Color.Black, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(Purple40, shape = CircleShape)
                            .clickable {
                                navController.navigate(itm.route) {
                                    launchSingleTop = true
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = if (!isSelected) itm.selectedIcon else itm.unselectedIcon),
                            contentDescription = itm.title,
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            } else {
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {
                        navController.navigate(itm.route) {
                            launchSingleTop = true
                        }

                    }
                ) {
                    Image(
                        painter = painterResource(id = if (!isSelected) itm.selectedIcon else itm.unselectedIcon),
                        contentDescription = itm.title,
                        modifier = Modifier.size(30.dp)
                    )
                    if (isSelected) {
                        Text(
                            text = itm.title,
                            color = Color(0xFF386BF6),
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
        }
    }
}


//@Preview(showSystemUi = true)
//@Composable
//private fun prevy() {
//    var selected by remember {
//        mutableStateOf(0)
//    }
//    Scaffold(
//        bottomBar = {
//
//            CustomBottomNavBar(selected = selected) { selected = it }
//        }
//    ) {
//        Box(Modifier.padding(it)) {
//
//        }
//    }
//}