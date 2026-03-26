package com.example.h2h.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.h2h.R

@Composable
fun Navigationsbar(
    goMap: () -> Unit,
    goCommunity: () -> Unit,
    goProfile: () -> Unit
    ) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .border(1.dp, Color.LightGray)
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 16.dp)
    ) {
        NavItem("Home", R.drawable.home) { /* Allerede på home */ }
        NavItem("Map", R.drawable.map) { goMap() }
        NavItem("Community", R.drawable.community) { goCommunity() }
        NavItem("Profile", R.drawable.profile) { goProfile() }
    }

}

//her har vi composavle til størelse navbar detalier
@Composable
fun NavItem(label: String, iconResId: Int, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = label,
            modifier = Modifier.size(32.dp) // Du kan justere størrelsen her
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = label,
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            // Valgfrit: Gør teksten pink hvis det er Home (ligesom dit ikon)
            color = if (label == "Home") Color(0xFFE91E63) else Color.Gray
        )
    }
}