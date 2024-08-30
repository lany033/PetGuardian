package com.lab.petguardian.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog


@Composable
fun CommonLogoutDialog(
    onConfirmLogout: () -> Unit,
    onDismiss: () -> Unit,
    onClickProfile: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismiss() }
    ) {
        Card {
            Column(
                modifier = Modifier.padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextButton(onClick = { onClickProfile() }) {
                    Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "account")
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "My Profile")
                }
                Divider()
                TextButton(onClick = { onConfirmLogout() }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.Logout, contentDescription = "logout")
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "Logout")
                }
            }
        }
    }
}
