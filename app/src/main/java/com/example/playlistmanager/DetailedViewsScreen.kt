package com.example.playlistmanager

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetailedViewScreen(
    songTitles: List<String>,
    artistNames: List<String>,
    songRatings: List<Int>,
    songComments: List<String>,
    onBack: () -> Unit
) {
    // state to track if average rating popup should be shown or not
    var showAverageDialog by remember { mutableStateOf(false) }
    // state to track if songs should be shown or no
    var showSongsList by remember { mutableStateOf(false) }

    // Calculate average rating
    val averageRating = if (songRatings.isNotEmpty()) {
        var sum = 0
        for (rating in songRatings) {
            sum += rating
        }
        sum.toDouble() / songRatings.size
    } else 0.0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Playlist Details",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Display songs button
        Button(
            onClick = { showSongsList = !showSongsList },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Text(if (showSongsList) "Hide Song List" else "Display Song List (${songTitles.size} songs)")
        }

        // display average rating button
        Button(
            onClick = { showAverageDialog = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Text("Show Average Rating")
        }

        // sverage rating popup dialog
        if (showAverageDialog) {
            AlertDialog(
                onDismissRequest = { showAverageDialog = false },
                title = {
                    Text(
                        text = "Average Rating",
                        fontWeight = FontWeight.Bold
                    )
                },
                text = {
                    Text(
                        text = "The average song rating for your playlist is %.1f out of 5.0".format(averageRating),
                        fontSize = 16.sp
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = { showAverageDialog = false }
                    ) {
                        Text("OK")
                    }
                }
            )
        }

        // songs list - only show if showSongsList is true
        if (showSongsList) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 8.dp)
            ) {
                items(songTitles.size) { index ->
                    SongCard(
                        title = songTitles[index],
                        artist = artistNames[index],
                        rating = songRatings[index],
                        comments = songComments[index]
                    )
                }
            }
        } else {
            // this is to keep back button at the bottom
            Spacer(modifier = Modifier.weight(1f))
        }

        // back to main button
        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back to Main Screen")
        }
    }
}

@Preview(showBackground = true, name = "Detailed View Screen Preview")
@Composable
fun DetailedViewScreenPreview() {
    val sampleTitles = listOf("tA", "tB", "tC")
    val sampleArtists = listOf("aA", "a2u", "aC")
    val sampleRatings = listOf(1, 4, 5)
    val sampleComments = listOf("wack", "nice", "wowzers")

    DetailedViewScreen(
        songTitles = sampleTitles,
        artistNames = sampleArtists,
        songRatings = sampleRatings,
        songComments = sampleComments,
        onBack = {}
    )
}

@Composable
fun SongCard(
    title: String,
    artist: String,
    rating: Int,
    comments: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Artist: $artist",
                fontSize = 14.sp
            )
            Text(
                text = "Rating: $rating/5",
                fontSize = 14.sp
            )
            if (comments.isNotEmpty()) {
                Text(
                    text = "Comments: $comments",
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Song Card Preview")
@Composable
fun SongCardPreview() {
    SongCard(
        title = "Blessed",
        artist = "Me",
        rating = 5,
        comments = "PLEASE!"
    )
}