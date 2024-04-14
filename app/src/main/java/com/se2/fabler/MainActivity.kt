package com.se2.fabler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.se2.fabler.ui.pages.FollowPage
import com.se2.fabler.ui.pages.HomePage
import com.se2.fabler.ui.pages.ProfilePage
import com.se2.fabler.ui.pages.SearchPage
import com.se2.fabler.ui.pages.SignInPage
import com.se2.fabler.ui.pages.SignUpPage
import com.se2.fabler.ui.theme.FablerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: This is a placeholder for the actual app model
        val appModel = getTestAppModel()
        //val appModel = AppModel(getTestAppModel().loggedInUserData, HttpFablerService())
        appModel.pushView("SignInPage")
        setContent {
            FablerTheme {
                Column {
                    when (appModel.currentView) {
                        "SignInPage" -> SignInPage(appModel)
                        "SignUpPage" -> SignUpPage(appModel)
                        "HomePage" -> HomePage(appModel)
                        "SearchPage" -> SearchPage(appModel)
                        "ProfilePage" -> ProfilePage(appModel)
                        "FollowPage" -> FollowPage(appModel)
                    }
                }
            }
        }
        WindowCompat.setDecorFitsSystemWindows(window, true)
        val controller = WindowCompat.getInsetsController(window, window.decorView)
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}
