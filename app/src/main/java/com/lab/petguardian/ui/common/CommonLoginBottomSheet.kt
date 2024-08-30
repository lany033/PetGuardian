package com.lab.petguardian.ui.common

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lab.petguardian.R
import com.lab.petguardian.data.AuthManager
import com.lab.petguardian.ui.theme.Geraldine

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonLoginBottomSheet(
    onDismiss: () -> Unit,
    onClickSignUp: () -> Unit,
    onClickForgotPassword: () -> Unit,
    onClickHome: () -> Unit,
    onGoogleSignIn: () -> Unit,
    email: String,
    password: String,
    onValueEmailChange: (String) -> Unit,
    onValuePasswordChange: (String) -> Unit
) {

    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { DragHandleCustom() }) {
        PetGuardianLogin(
            onClickSignUp = { onClickSignUp() },
            onClickForgotPassword = { onClickForgotPassword() },
            onClickHome = { onClickHome() },
            onGoogleSignIn = { onGoogleSignIn() },
            email = email,
            password = password,
            onValueEmailChange = { onValueEmailChange(it) },
            onValuePasswordChange = { onValuePasswordChange(it) }
        )
    }
}

@Composable
fun DragHandleCustom() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .height(4.dp)
                .width(100.dp)
                .background(Geraldine, RoundedCornerShape(4.dp))
        ) {}
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.TopStart),
                fontSize = 22.sp,
                text = "Continue with",
                fontWeight = FontWeight.Bold,
                color = Geraldine
            )
            Text(
                modifier = Modifier.align(Alignment.TopEnd),
                fontSize = 18.sp,
                text = "Cancel",
                fontWeight = FontWeight.Bold,
                color = Geraldine
            )
        }
        Divider()
    }

}

@Composable
fun PetGuardianLogin(
    onClickSignUp: () -> Unit,
    onClickForgotPassword: () -> Unit,
    onClickHome: () -> Unit,
    onGoogleSignIn: () -> Unit,
    email: String,
    password: String,
    onValueEmailChange: (String) -> Unit,
    onValuePasswordChange: (String) -> Unit
) {

    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {

        CommonTextFieldWithTextAbove(
            textAbove = "E-MAIL ADDRESS",
            placeholderText = "E-mail address",
            value = email,
            onValueChange = { onValueEmailChange(it) }
        )
        CommonTextFieldWithTextAbove(
            textAbove = "PASSWORD",
            placeholderText = "Password",
            value = password,
            onValueChange = { onValuePasswordChange(it) }
        )

        CommonButton(onClick = { onClickHome() }, text = "Continue", modifier = Modifier)

        CommonForgotPasswordText(onClickForgotPassword = { onClickForgotPassword() })
        CommonSignUpText(onClickSignUp = { onClickSignUp() })
        LoginDivider()
        CommonLoginSocialButton(
            onClick = { onGoogleSignIn() },
            text = "Continue with Google",
            icon = R.drawable.ic_google
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

