/*
 * Copyright (C) 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.affirmations
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.affirmations.data.Datasource
import com.example.affirmations.model.Affirmation
import com.example.affirmations.ui.theme.AffirmationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AffirmationApp()
        }
    }
}

@Composable
fun AffirmationApp() {
    AffirmationsTheme {
        AffirmationHorizontalList(affirmations = Datasource().loadAffirmations())
    }
}


@Composable
fun AffirmationHorizontalList(affirmations: List<Affirmation>, modifier: Modifier = Modifier){
   LazyColumn {
       items(affirmations) { affirmation ->
           AffirmationCard(affirmation = affirmation)
       }
   }
}

//Note: It is a best practice to pass a modifier to every composable and set it to a default value.
@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier){
  Card(modifier = modifier.padding(8.dp), elevation = 4.dp) {
      Column {
          Image(painter = painterResource(id = affirmation.imageResourceId),
              contentDescription = stringResource(
              id = affirmation.stringResourceId
          ), modifier = modifier
                  .fillMaxWidth()
                  .height(194.dp),
              contentScale = ContentScale.Crop
          )

          Text(text = stringResource(id = affirmation.stringResourceId),
              modifier = modifier.padding(16.dp),
              style = MaterialTheme.typography.h6
          )


//          Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
//              Text(text = stringResource(id = affirmation.stringResourceId),
//                  modifier = modifier.padding(16.dp),
//                  style = MaterialTheme.typography.h6
//              )
//
//
//              Icon(painter = painterResource(id = R.drawable.ic_baseline_delete_24), contentDescription = "Delete Icon")
//          }

      }
  }
}


@Composable
fun AffirmationGridList(affirmations: List<Affirmation>, modifier: Modifier = Modifier){
   LazyVerticalGrid(columns = GridCells.Fixed(2), verticalArrangement = Arrangement.spacedBy(8.dp),
       horizontalArrangement = Arrangement.spacedBy(8.dp),
       modifier = modifier.padding(8.dp)){
      items(Datasource().loadAffirmations()) { affirmation ->
          AffirmationGridCard(affirmation)
      }
   }
}

@Composable
fun AffirmationGridCard(affirmation: Affirmation, modifier: Modifier = Modifier){
    Card(elevation = 8.dp) {
        Row {
            Box {
                Image(painter = painterResource(id = affirmation.imageResourceId), contentDescription = stringResource(
                    id = affirmation.stringResourceId
                ), contentScale = ContentScale.Crop, modifier = modifier
                    .size(68.dp, height = 68.dp)
                    .aspectRatio(1f))
            }


            Column {
                Text(text = stringResource(id = affirmation.stringResourceId), style = MaterialTheme.typography.body2, modifier = modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(painter = painterResource(id = R.drawable.ic_baseline_delete_24), contentDescription = "delete", modifier = modifier
                        .padding(start = 16.dp)
                        .size(12.dp))

                    Text(text = "321", style = MaterialTheme.typography.caption, modifier = modifier.padding(start = 8.dp))
                }
            }
        }
    }
}


@Preview
@Composable
private fun AffirmationCardPreview() {
//    AffirmationHorizontalList(affirmations = Datasource().loadAffirmations())
//   AffirmationCard(affirmation = Affirmation(R.string.affirmation1, R.drawable.image1))
//    AffirmationGridCard(affirmation = Affirmation(R.string.affirmation1, R.drawable.image1))
    AffirmationGridList(affirmations = Datasource().loadAffirmations())
}

//Stopped At Build Beautiful Apps.
