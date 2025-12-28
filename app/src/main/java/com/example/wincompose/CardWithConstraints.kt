package com.example.wincompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.wincompose.ui.theme.WINComposeTheme

@Composable
fun CardWithConstraints() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(2.dp)
    ) {
        val (flag, countryName, stateName) = createRefs()

        val imageResId = R.drawable.ic_launcher_foreground
        val painter: Painter = painterResource(imageResId)

        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize(0.3f)
                .height(70.dp)
                .padding(2.dp)
                .constrainAs(flag) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = "India",
            modifier = Modifier
                .fillMaxWidth(1.0f)
                .padding(2.dp)
                .constrainAs(countryName) {
                    top.linkTo(flag.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(flag.end)
                },
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Delhi",
            modifier = Modifier
                .fillMaxWidth(1.0f)
                .padding(2.dp)
                .constrainAs(stateName) {
                    top.linkTo(countryName.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(flag.end)
                },
            fontSize = 15.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CardWithConstraintsPreview() {
    WINComposeTheme {
        CardWithConstraints()
    }
}