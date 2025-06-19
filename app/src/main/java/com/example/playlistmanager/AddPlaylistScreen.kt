package com.example.playlistmanager

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.playlistmanager.data.Song

@Composable
fun AddPlaylistScreen(
    onSongAdded: (Song) -> Unit,
    onBack: () -> Unit
) {
    var songTitle by remember { mutableStateOf("") }
    var artistName by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf("") }
    var comments by remember { mutableStateOf("") }

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

        // Song Title input
        OutlinedTextField(
            value = songTitle,
            onValueChange = { songTitle = it },
            label = { Text("Song Title") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )

        // Artist Name input
        OutlinedTextField(
            value = artistName,
            onValueChange = { artistName = it },
            label = { Text("Artist Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )

        // Rating input
        OutlinedTextField(
            value = rating,
            onValueChange = { rating = it },
            label = { Text("Rating (1-5)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )

        // Comments input
        OutlinedTextField(
            value = comments,
            onValueChange = { comments = it },
            label = { Text("Comments") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Add Song button
        Button(
            onClick = {
                if (songTitle.isNotEmpty() && artistName.isNotEmpty() && rating.isNotEmpty()) {
                    val ratingInt = rating.toIntOrNull() ?: 1
                    val validRating = if (ratingInt in 1..5) ratingInt else 1
                    onSongAdded(Song(songTitle, artistName, validRating, comments))
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Song")
        }

        // Back button
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
        onSongAdded = { song: Song ->
            println(song)
        },
        onBack = {}
    )
}