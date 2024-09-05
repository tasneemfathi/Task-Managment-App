package com.example.taskmanagmentapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PrioritySelector(
    priority: Int,
    onPriorityChanged: (Int) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Priority")
        Spacer(modifier = Modifier.width(25.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "Low")
            RadioButton(
                selected = priority == 1,
                onClick = { onPriorityChanged(1) },
                colors = RadioButtonDefaults.colors(selectedColor = 1.getColorByPriority())
            )
            Text(text = "Medium")
            RadioButton(
                selected = priority == 2,
                onClick = { onPriorityChanged(2) },
                colors = RadioButtonDefaults.colors(selectedColor = 2.getColorByPriority())
            )
            Text(text = "High")
            RadioButton(
                selected = priority == 3,
                onClick = { onPriorityChanged(3) },
                colors = RadioButtonDefaults.colors(selectedColor = 3.getColorByPriority())
            )
        }

    }
}