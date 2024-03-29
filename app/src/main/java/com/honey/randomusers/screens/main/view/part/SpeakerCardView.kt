package com.honey.randomusers.screens.main.view.part

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.honey.randomusers.R
import com.honey.randomusers.screens.main.model.SpeakerItemModel


@OptIn(ExperimentalCoilApi::class)
@Composable
internal fun SpeakerCardView(
    model: SpeakerItemModel,
    onCardClicked: ((cardModel: SpeakerItemModel) -> Unit)? = null,
    onFavClicked: ((itemId: Int, newValue: Boolean) -> Unit)? = null
) {
    val isChecked = remember { mutableStateOf(model.inFav) }

    Card(modifier = Modifier
        .clickable { onCardClicked?.invoke(model) }
        .padding(
            vertical = 8.dp,
            horizontal = 16.dp
        )
        .fillMaxWidth(),
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.background,
        shape = MaterialTheme.shapes.medium
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Log.d("MyLog", "${model.imageId}")

            Image(
                modifier = Modifier
                    .size(64.dp)
                    .clip(shape = RoundedCornerShape(100)),
                painter = rememberImagePainter(data = model.imageId),
                contentDescription = "Speaker",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                val name = if (model.speaker.length > 23){
                    model.speaker.substring(0..20) + "..."
                } else {
                    model.speaker
                }
                Text(text = name, fontWeight = FontWeight.Bold)
                Text(text = model.timeZone, fontWeight = FontWeight.Bold)
                val text = if (model.text.length > 50){
                    model.text.substring(0..50) + "..."
                } else {
                    model.text
                }
                Text(text = text)
            }
            IconToggleButton(
                checked = model.inFav,
                onCheckedChange = {newValue->
//                    isChecked.value = newValue
                    onFavClicked?.invoke(model.id, newValue)
                },
            ) {
                if (model.inFav) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_favorite),
                        contentDescription = "Checked"
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_favorite_border),
                        contentDescription = "Unchecked"
                    )
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun PreviewSpeakerCard(){
//    SpeakerCardView(
//        model = SpeakerItemModel(
//            imageId = R.drawable.img_man_one,
//            timeZone = "10:00-11:00",
//            speaker = "Аналий Жопанов",
//            text = "Доклад: А тут текста я не придумал так что пусть будет просто текст",
//            inFav = true
//        )
//    )
//}