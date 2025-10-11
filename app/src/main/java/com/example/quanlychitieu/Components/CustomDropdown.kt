package com.example.quanlychitieu.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.quanlychitieu.ui.theme.Dimens.PaddingBody
import com.example.quanlychitieu.ui.theme.Dimens.RadiusFull
import com.example.quanlychitieu.ui.theme.Dimens.RadiusLarge
import com.example.quanlychitieu.ui.theme.Dimens.RadiusMedium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> CustomDropdown(
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable (() -> Unit))? = null,
    items: List<T>,
    selectedItem: T?,
    itemLabel: (T) -> String,
    onSelect: (T) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
            .padding(horizontal = PaddingBody)
    ) {
        TextField(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .shadow(
                    elevation = 11.dp,
                    shape = RoundedCornerShape(RadiusLarge),
                    clip = false
                )
                .clip(RoundedCornerShape(RadiusLarge)),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color.Black
            ),
            leadingIcon = leadingIcon,
            value = selectedItem?.let { itemLabel(it) } ?: "",
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) }
        )
        Spacer(modifier = Modifier.height(40.dp))
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = Color.White,
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(itemLabel(item),color = Color.Black) },
                    onClick = {
                        onSelect(item)
                        expanded = false
                    }
                )
            }
        }
    }
}
