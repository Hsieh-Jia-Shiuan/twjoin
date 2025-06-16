package com.example.twjoin.presentation.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.twjoin.data.entities.ListEntity
import com.example.twjoin.ui.theme.ProjectColor
import com.example.twjoin.ui.theme.ProjectTextStyle

@Composable
fun ListItemWidget(
    modifier: Modifier = Modifier,
    data: ListEntity,
    onEdit: () -> Unit = {},
    onDelete: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                ProjectColor.White,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 16.dp)
            .height(100.dp)
            .border(
                border = BorderStroke(width = 0.dp, color = Color.Transparent),
                shape = RoundedCornerShape(8.dp),
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(
                        ProjectColor.PersonBG,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .size(48.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = null,
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = data.name,
                style = ProjectTextStyle.H8,
                color = ProjectColor.Black
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .width(24.dp)
                    .height(24.dp)
                    .clickable {
                        onEdit()
                    },
                tint = ProjectColor.Edit
            )

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .width(24.dp)
                    .height(24.dp)
                    .clickable {
                        onDelete()
                    },
                tint = ProjectColor.Red
            )
        }
    }
}

@Preview
@Composable
fun ListItemWidgetPreview() {
    ListItemWidget(
        data = ListEntity(
            id = 0,
            name = "預覽項目 A",
            description = "預覽描述"
        ),
        onEdit = {},
        onDelete = {}
    )
}