package com.example.bmicare.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmicare.model.BmiCategory
import com.example.bmicare.viewmodel.BmiViewModel

@Composable
fun BmiResultScreen(
    viewModel: BmiViewModel,
    onRecalculate: () -> Unit
) {
    val bmiResult by viewModel.bmiResult
    val bmiCategory by viewModel.bmiCategory

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "BMI Result & Categories",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Your BMI is",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = bmiResult.toString(),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 48.sp, 
                        fontWeight = FontWeight.Bold,
                        color = bmiCategory.color
                    )
                )
                Text(
                    text = bmiCategory.title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = bmiCategory.color,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier.padding(top = 8.dp)
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = bmiCategory.recommendation,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }

        // Category List
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                BmiCategory.values().forEach { category ->
                    CategoryRow(
                        category = category,
                        isCurrent = category == bmiCategory
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                viewModel.reset()
                onRecalculate()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Recalculate", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun CategoryRow(category: BmiCategory, isCurrent: Boolean) {
    val bgColor = if (isCurrent) category.color.copy(alpha = 0.15f) else Color.Transparent
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(bgColor, RoundedCornerShape(8.dp))
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .background(category.color, RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = category.title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Normal,
                    color = if (isCurrent) category.color else MaterialTheme.colorScheme.onSurface
                )
            )
        }
        Text(
            text = category.range,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Normal
            )
        )
    }
}
