package com.example.composenavigationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composenavigationapp.ui.theme.ComposeNavigationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNavigationAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.tertiary                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "screen_a") {
        composable("screen_a") {
            ScreenA(navController = navController)
        }
        composable("screen_b?message={message}") {
            val message = it.arguments?.getString("message")
            ScreenB(navController = navController, message = message)
        }
    }
}

@Composable
fun ScreenA(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(67.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(text = "voce está na tela A, a de primeiro lugar, a tela palmeirense")
        Button(onClick = { navController.navigate("screen_b") }) {
            Text("ir para a tela do real madrid, a tela do time de segundo lugar")
        }
    }
}

@Composable
fun ScreenB(navController: NavController, message: String?) {
    Column(
        modifier = Modifier.fillMaxSize().padding(76.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Esta é a tela do real madrid")
        message?.let { Text(text = "Mensagem da Tela A: $it") }
        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar para a tela do maior do brasil")
        }
    }
}

// Pré-visualizações (opcional)
@Preview(showBackground = true)
@Composable
fun ScreenAPreview() {
    ComposeNavigationAppTheme {
        ScreenA(rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenBPreview() {
    ComposeNavigationAppTheme {
        ScreenB(rememberNavController(), "Olá da Tela A")
    }
}
