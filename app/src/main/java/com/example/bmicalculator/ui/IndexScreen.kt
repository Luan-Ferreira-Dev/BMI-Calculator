package com.example.bmicalculator.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmicalculator.R
import com.example.bmicalculator.data.IndexUiState
import com.example.bmicalculator.ui.theme.BMICalculatorTheme

@Composable
fun IndexScreen(modifier: Modifier = Modifier, uiState: IndexUiState) {
    val fontSize = 12.sp
    val fontColor = MaterialTheme.colorScheme.secondary
    val painter = when (uiState.bodyMassIndex) {
        in 0.0..18.5 -> R.drawable.underweight
        in 18.5 ..25.0 -> R.drawable.healthy
        in 25.0 .. 30.0 -> R.drawable.overweight
        else -> R.drawable.obese
    }
    val colorBMI = when (uiState.bodyMassIndex) {
        in 0.0..18.5 -> Color(0xff689cef)
        in 18.5..25.0 -> Color(0xff62b867)
        in 25.0..30.0 -> Color(0xffe49465)
        else -> Color(0xffd86161)
    }


    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)){
        Text(text = stringResource(id = R.string.index), fontWeight = FontWeight.Bold)
        Divider(thickness = 2.dp)

        Box(contentAlignment = Alignment.BottomCenter) {
            Image(painter = painterResource(id = painter), contentDescription = null)
            Text(text = uiState.bodyMassIndex.toString(), modifier = Modifier.padding(bottom = 10.dp), color = colorBMI)
        }
        Column {
            Text(stringResource(id = R.string.analysis), fontSize = fontSize, color = fontColor)
            Row (modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween){
                Text(text = stringResource(id = R.string.height_label), fontSize = fontSize, color = fontColor)
                Text(text = (uiState.height * 100).toString(), fontSize = fontSize, color = fontColor)
            }
            Row (modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween){
                Text(text = stringResource(id = R.string.suggested), fontSize = fontSize, color = fontColor)
                Text(text = uiState.suggestedWeight, fontSize = fontSize, color = fontColor)
            }
        }




    }
}

@Preview
@Composable
fun IndexScreenPreview() {
    BMICalculatorTheme {
        Surface (
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background){
            IndexScreen(modifier = Modifier.padding(16.dp), uiState = IndexUiState(20, true, 1.6, 40.0))
        }
    }

}

