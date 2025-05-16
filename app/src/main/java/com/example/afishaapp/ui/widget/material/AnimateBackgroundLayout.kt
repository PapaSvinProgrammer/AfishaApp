package com.example.afishaapp.ui.widget.material

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.example.afishaapp.ui.theme.ColorBottom1
import com.example.afishaapp.ui.theme.ColorBottom2
import com.example.afishaapp.ui.theme.ColorBottom3
import com.example.afishaapp.ui.theme.ColorMiddle1
import com.example.afishaapp.ui.theme.ColorMiddle2
import com.example.afishaapp.ui.theme.ColorMiddle3
import com.example.afishaapp.ui.theme.ColorTop1
import com.example.afishaapp.ui.theme.ColorTop2
import com.example.afishaapp.ui.theme.ColorTop3
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun AnimateBackgroundLayout(
    currentPage: Int,
    content: @Composable BoxScope.() -> Unit
) {
    val topColor = remember { Animatable(initialValue = ColorTop1) }
    val middleColor = remember { Animatable(initialValue = ColorMiddle1) }
    val bottomColor = remember { Animatable(initialValue = ColorBottom1) }
    val animatedPoint = remember { Animatable(.8f) }

    LaunchedEffect(currentPage) {
        val colors1 = listOf(ColorTop1, ColorMiddle1, ColorBottom1)
        val colors2 = listOf(ColorTop2, ColorMiddle2, ColorBottom2)
        val colors3 = listOf(ColorTop3, ColorMiddle3, ColorBottom3)

        var colors = colors1

        when (currentPage) {
            1 -> colors = colors2
            2 -> colors = colors3
        }

        fun animate(color: Animatable<Color, AnimationVector4D>, index: Int) {
            launch {
                color.animateTo(
                    targetValue = colors[index],
                    animationSpec = tween(
                        durationMillis = Random.nextInt(300, 500)
                    )
                )
            }
        }

        listOf(topColor, middleColor, bottomColor).forEachIndexed { index, animatable ->
            animate(animatable, index)
        }
    }

    LaunchedEffect(currentPage) {
        if (currentPage % 2 == 1) {
            animatedPoint.animateTo(
                targetValue = .1f,
                animationSpec = spring(stiffness = Spring.StiffnessVeryLow)
            )
        }
        else {
            animatedPoint.animateTo(
                targetValue = .9f,
                animationSpec = spring(stiffness = Spring.StiffnessVeryLow)
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .meshGradient(
                points = listOf(
                    listOf(
                        Offset(0f, 0f) to topColor.value,
                        Offset(.5f, 0f) to topColor.value,
                        Offset(1f, 0f) to topColor.value
                    ),
                    listOf(
                        Offset(0f, .5f) to middleColor.value,
                        Offset(.5f, animatedPoint.value) to middleColor.value,
                        Offset(1f, .5f) to middleColor.value
                    ),
                    listOf(
                        Offset(0f, 1f) to bottomColor.value,
                        Offset(.5f, 1f) to bottomColor.value,
                        Offset(1f, 1f) to bottomColor.value
                    )
                ),
                resolutionX = 32
            )
    ) {
        content()
    }
}