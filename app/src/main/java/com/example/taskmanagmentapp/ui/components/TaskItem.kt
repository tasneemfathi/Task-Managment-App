package com.example.taskmanagmentapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.taskmanagmentapp.model.Task

@Composable
fun TaskItem(task: Task,
             onTaskCheckedChange:(Boolean)-> Unit,
             onTaskEdited:()-> Unit,
             onTaskDeleted:()-> Unit,
             ){
    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable { onTaskEdited() }
        ) {
        Row(modifier = Modifier
            .padding(vertical = 10.dp,horizontal = 16.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            // Column
            Column(modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = task.description,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = task.priority.asPlainTextPriority(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = task.priority.getColorByPriority()
                )
            }


            //CheckBox
                Checkbox(
                    checked = task.isCompleted,
                    onCheckedChange = onTaskCheckedChange
                )
            //IconButton
                IconButton(onClick = onTaskDeleted) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete This Task")
                }

        }


    }
}




fun Int.asPlainTextPriority()=  when(this){
    2->"Medium"
    3->"High"
    else->"Low"
}

fun Int.getColorByPriority() =  when(this){
    2->Color.Yellow
    3->Color.Red
    else->Color.Green
}