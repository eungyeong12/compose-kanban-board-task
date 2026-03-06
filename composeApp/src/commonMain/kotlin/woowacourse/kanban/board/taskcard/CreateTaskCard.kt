package woowacourse.kanban.board.taskcard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.profile_image
import org.jetbrains.compose.resources.painterResource

class TaskCardPreviewParameterProvider : PreviewParameterProvider<TaskCard> {
    override val values = sequenceOf(
        TaskCard(
            title = "LazyColumn 컴포넌트 구현",
            content = "세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다.",
            tags = listOf("컴포넌트", "성능"),
            author = "다이노"
        ),
        TaskCard(
            title = "LazyColumn 컴포넌트 구현",
            content = "",
            tags = listOf("컴포넌트", "성능"),
            author = "다이노"
        ),
        TaskCard(
            title = "LazyColumn 컴포넌트 구현",
            content = "세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다.",
            tags = listOf(),
            author = "다이노"
        ),
        TaskCard(
            title = "LazyColumn 컴포넌트 구현",
            content = "",
            tags = listOf(),
            author = "다이노"
        )
    )
}

@Composable
@Preview(showBackground = true)
fun CreateTaskCard(
    @PreviewParameter(TaskCardPreviewParameterProvider::class) taskCard: TaskCard
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color(0xFFE5E7EB)),
        modifier = Modifier.width(286.dp)
    ) {
        Column(
            modifier = Modifier.padding(17.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Title(title = taskCard.title)
            if (taskCard.content.isNotEmpty()) Content(content = taskCard.content)
            if (taskCard.tags.isNotEmpty()) Tags(tags = taskCard.tags)
            HorizontalDivider(color = Color(0xFFE5E7EB))
            Profile(author = taskCard.author)
        }
    }
}

@Composable
fun Title(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        color = Color(0xFF101828),
        fontSize = 16.sp,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun Content(content: String) {
    Text(
        text = content,
        style = MaterialTheme.typography.bodyMedium,
        color = Color(0xFF4A5565),
        fontSize = 14.sp,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun Tags(tags: List<String>) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        tags.forEach { tag ->
            SuggestionChip(
                onClick = {},
                label = { Text(
                    text = tag,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 12.sp
                )},
                colors = SuggestionChipDefaults.suggestionChipColors(
                    containerColor = Color(0xFFF3F4F6),
                    labelColor = Color(0xFF364153),
                ),
                border = BorderStroke(0.dp, Color.Transparent),
                shape = MaterialTheme.shapes.large
            )
        }
    }
}

@Composable
fun Profile(author: String) {
    Row {
        Image(
            painter = painterResource(Res.drawable.profile_image),
            contentDescription = "Profile Image",
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = author,
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF364153),
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}