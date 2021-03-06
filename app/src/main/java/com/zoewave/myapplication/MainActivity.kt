package com.zoewave.myapplication

//import androidx.ui.livedata.observeAsState
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.animation.Crossfade
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import com.zoewave.myapplication.ui.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var addWordComposeUI: AddWordComposeUI

    @Inject
    lateinit var mainComposeUI: MainComposeUI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                AppTheme {
                    Text("--> Place Holder - Nav is below here <--")
                    AppContent(mainComposeUI, addWordComposeUI)
                }
            }
        }
    }

}

@Composable
private fun AppContent(
    mainComposeUI: MainComposeUI,
    addWordComposeUI: AddWordComposeUI
) {
    Crossfade(AppScreen.currentScreen) { screen ->
        Surface(color = MaterialTheme.colors.background) {
            backButtonHandler(onBackPressed = {
                navigateTo(
                    NavScreen.Home
                )
            })
            when (screen) {
                is NavScreen.Home -> mainComposeUI.HomeScreen()
                is NavScreen.AddWord -> addWordComposeUI.ScaffoldWithBottomBar()
            }
        }
    }
}