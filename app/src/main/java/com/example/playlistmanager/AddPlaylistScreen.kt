package com.example.playlistmanager

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddPlaylistScreen(
    onSongAdded: (String, String, Int, String) -> Unit,
    onBack: () -> Unit
) {
    var songTitle by remember { mutableStateOf("") }
    var artistName by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf("") }
    var comments by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Add New Song",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        //shows error
        if (showError) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Red.copy(alpha = 0.1f))
            ) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }

        // song title input
        OutlinedTextField(
            value = songTitle,
            onValueChange = { songTitle = it },
            label = { Text("Song Title") },
            isError = showError && songTitle.isEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )

        // artist name input
        OutlinedTextField(
            value = artistName,
            onValueChange = { artistName = it },
            label = { Text("Artist Name") },
            isError = showError && artistName.isEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )

        // rating input
        OutlinedTextField(
            value = rating,
            onValueChange = { rating = it },
            label = { Text("Rating (1-5)") },
            //shows error if rating is empty || not valid || too big or small
            isError = showError && (rating.isEmpty() || rating.toIntOrNull() == null
                    || rating.toIntOrNull() !in 1..5),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )

        // comments input
        OutlinedTextField(
            value = comments,
            onValueChange = { comments = it },
            label = { Text("Comments") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // add song button
        Button(
            onClick = {
                when {
                    songTitle.isEmpty() -> {
                        errorMessage = "Please enter a song title"
                        showError = true
                    }
                    artistName.isEmpty() -> {
                        errorMessage = "Please enter an artist name"
                        showError = true
                    }
                    rating.isEmpty() -> {
                        errorMessage = "Please enter a rating"
                        showError = true
                    }
                    rating.toIntOrNull() == null -> {
                        errorMessage = "Rating must be a number"
                        showError = true
                    }
                    rating.toIntOrNull() !in 1..5 -> {
                        errorMessage = "Rating must be between 1 and 5"
                        showError = true
                    }
                    else -> {
                        // success woohoo!
                        val ratingInt = rating.toInt()
                        onSongAdded(songTitle, artistName, ratingInt, comments)
                        showError = false
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Song")
        }

        // back button
        Button(
            onClick = onBack,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text("Back to Main")
        }
    }
}

@Preview(showBackground = true, name = "Add Playlist Screen Preview")
@Composable
fun AddPlaylistScreenPreview() {
    AddPlaylistScreen(
        onSongAdded = { title, artist, rating, comments ->
            println("Song Added: $title by $artist with rating $rating and comments: $comments")
        },
        onBack = {}
    )
}