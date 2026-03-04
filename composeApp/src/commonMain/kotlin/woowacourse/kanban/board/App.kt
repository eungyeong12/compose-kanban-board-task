package woowacourse.kanban.board

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.task_planet
import org.jetbrains.compose.resources.painterResource

@Composable
@Preview(showBackground = true)
fun App() {
    val inputWindow = InputWindow()
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        var showInputWindow = AddButton(modifier = Modifier.align(Alignment.Center))
        if (showInputWindow) inputWindow.OpenInputWindow()
    }

}

@Composable
fun AddButton(modifier: Modifier): Boolean {
    var showInputWindow by remember { mutableStateOf(false) }
    Button(
        onClick = { showInputWindow = !showInputWindow },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF5CFFD1),
            contentColor = Color.Black,
        ),
        modifier = modifier.size(height = 150.dp, width = 250.dp),
    ) {
        Row {
            Image(
                painter = painterResource(Res.drawable.task_planet),
                contentDescription = "",
                modifier = Modifier.size(50.dp),
            )
            Text(
                text = "추가",
                fontSize = 50.sp
            )
        }
    }
    return showInputWindow
}