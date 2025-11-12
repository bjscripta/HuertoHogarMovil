package com.example.huertohogar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.conedb.ViewModel.FormularioViewModel
import com.example.conedb.data.UsuarioDataBase
import com.example.huertohogar.ui.Screen.Catalogo
import com.example.huertohogar.ui.Screen.Home
import com.example.huertohogar.ui.Screen.Login
import com.example.huertohogar.ui.Screen.PerfilUsuario
import com.example.huertohogar.ui.Screen.PostScreen
import com.example.huertohogar.ui.Screen.Registro
import com.example.huertohogar.ui.theme.HuertoHogarTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        setContent {
            HuertoHogarTheme {
                val postViewModel: com.example.huertohogar.ViewModel.PostViewModel = viewModel()

                PostScreen(viewModel = postViewModel)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HuertoHogarTheme {
        Home()
    }
}

@Composable
fun FormularioApp(){
    val context = LocalContext.current

    val database = remember {
        Room.databaseBuilder(
            context,
            UsuarioDataBase::class.java,
            "usuario.db"
        ).build()
    }
    val viewModel = remember {
        FormularioViewModel(database.usuarioDAO())
    }
    Registro(viewModel = viewModel)
}

