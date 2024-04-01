package com.se2.fabler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import com.se2.fabler.ui.pages.HomePage
import com.se2.fabler.ui.pages.ProfilePage
import com.se2.fabler.ui.pages.SearchPage
import com.se2.fabler.ui.theme.FablerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: This is a placeholder for the actual app model
        val appModel = getTestAppModel()
        appModel.pushView("HomePage")
        setContent {
            FablerTheme {
                Column {
                    when (appModel.currentView) {
                        "HomePage" -> HomePage(appModel)
                        "SearchPage" -> SearchPage(appModel)
                        "ProfilePage" -> ProfilePage(appModel)
                    }
                }
            }
        }
    }
}
