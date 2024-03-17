package com.example.bmicalculator.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Man
import androidx.compose.material.icons.filled.Woman
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bmicalculator.R
import com.example.bmicalculator.ui.theme.BMICalculatorTheme
import com.example.bmicalculator.ui.theme.Shapes

@Composable
fun StartScreen(modifier: Modifier = Modifier, indexViewModel: IndexViewModel) {
    val fontSize = 12.sp
    val fontColor = MaterialTheme.colorScheme.secondary
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)){
        Text(text = "BMI", fontWeight = FontWeight.Bold)
        Divider(thickness = 2.dp)
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)){
            InputInfo(
                modifier = Modifier.weight(1f),
                label = R.string.age_label,
                placeholder = R.string.age_placeholder,
                value = indexViewModel.age,
                onValueChange = {indexViewModel.updateAge(it)})

            Column (horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center){
                Text(
                    text = "${stringResource(id = R.string.gender_label)} " +
                            if (indexViewModel.isMale) stringResource(
                                id = R.string.male
                            )
                            else stringResource(
                                id = R.string.female
                            ),
                    fontSize = fontSize,
                    color = fontColor)
                Row (modifier = Modifier.padding(end = 8.dp)){
                    GenreButton(
                        imageVector = Icons.Filled.Man,
                        man = true,
                        isMale = indexViewModel.isMale,
                        onClick = {indexViewModel.updateGender(true)})
                    GenreButton(imageVector = Icons.Filled.Woman,
                        man = false,
                        isMale = indexViewModel.isMale,
                        onClick = {indexViewModel.updateGender(false)})
                }
            }

        }
        InputInfo(
            label = R.string.height_label,
            placeholder = R.string.height_placeholder,
            value = indexViewModel.height,
            onValueChange = {indexViewModel.updateHeight(it)})
        InputInfo(
            label = R.string.weight_label,
            placeholder = R.string.weight_placeholder,
            value = indexViewModel.weight,
            onValueChange = {indexViewModel.updateWeight(it)})

        Column {
            Text(text = stringResource(
                id = R.string.about),
                fontSize = fontSize,
                fontWeight = FontWeight.Bold,
                color = fontColor)
            Text(text = stringResource(
                id = R.string.description),
                fontSize = fontSize,
                color = fontColor,
                textAlign = TextAlign.Justify,
                lineHeight = 12.sp)
            Text(text = stringResource(
                id = R.string.explanation),
                fontSize = fontSize,
                color = fontColor,
                textAlign = TextAlign.Justify,
                lineHeight = 12.sp)
        }



        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = {  }, shape = Shapes.medium, modifier = Modifier.fillMaxWidth(0.7f)) {
            Text("Calculate")
        }
    }
}

@Composable
fun GenreButton(modifier: Modifier = Modifier, isMale: Boolean, man: Boolean, imageVector: ImageVector, onClick: () -> Unit) {
    var color = MaterialTheme.colorScheme.secondary
    if (isMale == man) {
        color = MaterialTheme.colorScheme.primary
    }
    Surface ( shadowElevation = 2.dp, shape = Shapes.small, modifier = modifier.clickable { onClick() }
        .padding(2.dp)){
        Icon(
            imageVector = imageVector,
            tint = color,
            contentDescription = null,
            modifier = Modifier.size(width = 40.dp, height = 45.dp))
    }
}

@Composable
fun InputInfo(
    modifier: Modifier = Modifier,
    @StringRes label: Int,
    @StringRes placeholder: Int,
    value: String,
    onValueChange: (String) -> Unit) {

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        placeholder = {Text(stringResource(id = placeholder))},
        label = { Text(stringResource(id = label)) }
    )
}

@Preview
@Composable
fun StartScreenPreview() {
    BMICalculatorTheme {
        Surface (
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background){
            StartScreen(modifier = Modifier.padding(16.dp), indexViewModel = viewModel())
        }
    }
}