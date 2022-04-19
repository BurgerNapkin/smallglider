package com.zcdorman.smallglider.ui.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.zcdorman.smallglider.ui.navigation.SmallGliderNavHost
import com.zcdorman.smallglider.ui.activity.base.BaseActivity
import com.zcdorman.smallglider.ui.theme.SmallGliderTheme

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            SmallGliderApp(navController)
        }
    }
}

@Composable
fun SmallGliderApp(navController: NavHostController) {
    SmallGliderTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            SmallGliderNavHost(navController = navController)
        }
    }
}