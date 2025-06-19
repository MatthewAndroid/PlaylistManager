package com.example.playlistmanager

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlin.system.exitProcess

@Composable
fun MusicPlaylistApp() {
    // this state manages the screen shown
    var currentScreen by remember { mutableStateOf("main") }

    // arrays to manage data added already
    var songTitles by remember { mutableStateOf(
        mutableListOf("Song A", "Song B", "Song C", "Song D")
    )}
    var artistNames by remember { mutableStateOf(
        mutableListOf("Artist A", "Artist B", "Artist C", "Artist D")
    )}
    var songRatings by remember { mutableStateOf(
        mutableListOf(4, 1, 2, 3)
    )}
    var songComments by remember { mutableStateOf(
        mutableListOf("Rap", "Dance song", "Best love song", "Memories")
    )}

    when (currentScreen) {
        "main" -> MainScreen(
            onAddPlaylist = { currentScreen = "add" },
            onNavigateToDetails = { currentScreen = "details" },
            onExit = { exitProcess(0)  }
        )
        "add" -> AddPlaylistScreen(
            onSongAdded = { title, artist, rating, comments ->
                songTitles.add(title)
                artistNames.add(artist)
                songRatings.add(rating)
                songComments.add(comments)
                currentScreen = "main"
            },
            onBack = { currentScreen = "main" }
        )
        "details" -> DetailedViewScreen(
            songTitles = songTitles,
            artistNames = artistNames,
            songRatings = songRatings,
            songComments = songComments,
            onBack = { currentScreen = "main" }
        )
    }
}