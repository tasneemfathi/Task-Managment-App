package com.example.taskmanagmentapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeadlineSelector(
    deadline: Long,
    onDeadLineChanged: (Long) -> Unit,
){

    var isDatePickerShown by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = deadline,selectableDates = object: SelectableDates{
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            return utcTimeMillis >=  System.currentTimeMillis()
        }
    })
    // Update DatePickerState when the deadline changes
    LaunchedEffect(deadline) {
        datePickerState.updateSelection(deadline)
    }
    Box(contentAlignment = Alignment.Center){
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Select Task Deadline")
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {
                isDatePickerShown = true
            }) {
                Text(text = "Pick a Deadline")
            }
        }


        if(isDatePickerShown){
            DatePickerDialog(
                onDismissRequest = { isDatePickerShown = false },
                confirmButton = {
                    onDeadLineChanged(
                        datePickerState.selectedDateMillis ?: deadline
                    )
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }


}
// Extension function to update DatePickerState
@OptIn(ExperimentalMaterial3Api::class)
fun DatePickerState.updateSelection(deadline: Long) {
    // Check if the deadline is valid and set it
    if (deadline >= System.currentTimeMillis()) {
        selectedDateMillis = deadline
    }
}