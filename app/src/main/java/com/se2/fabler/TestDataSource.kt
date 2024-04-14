package com.se2.fabler

import com.se2.fabler.api.TestFablerService
import com.se2.fabler.models.BookData
import com.se2.fabler.models.UserData
import com.se2.fabler.models.UserDataAll

class TestDataSource {
    val books: List<BookData>
        get() {
            return listOf(
                BookData(
                    0, "Fortune Killer", R.drawable.bg1, "bja", 2, "Crime",
                    "Nobody paid much attention to Jaxen – just a normal teenager at a high school where the social elite happen to possess unthinkable powers and abilities. But Jaxen’s got a secret past that threatens to bring down the school’s whole social order – and much more. Fulfilling his destiny won’t be easy though, because there are battles, frenemies and deadly conspiracies around every corner.",
                    true, 4, true
                ),
                BookData(
                    1,
                    "Spiders in the Night",
                    R.drawable.bg2,
                    "bja",
                    2,
                    "Horror",
                    "Description 1",
                    true,
                    4,
                    true
                ),
                BookData(
                    2,
                    "Don't Look Back Ever",
                    R.drawable.bg3,
                    "jaxdutta",
                    1,
                    "Romance, Comedy",
                    "Description 2",
                    false,
                    4,
                    false
                ),
                BookData(
                    3,
                    "Title 3",
                    R.drawable.bg4,
                    "Author 3",
                    3,
                    "Genre 3",
                    "Description 3",
                    true,
                    4,
                    true
                ),
                BookData(
                    4,
                    "Title 4",
                    R.drawable.bg1,
                    "Author 4",
                    4,
                    "Genre 4",
                    "Description 4",
                    false,
                    4,
                    false
                ),
                BookData(
                    5,
                    "Title 5",
                    R.drawable.bg3,
                    "Author 5",
                    5,
                    "Genre 5",
                    "Description 5",
                    true,
                    4,
                    true
                ),
                BookData(
                    6,
                    "Title 6",
                    R.drawable.bg4,
                    "Author 6",
                    6,
                    "Genre 6",
                    "Description 6",
                    false,
                    4,
                    false
                ),
                BookData(
                    7,
                    "Title 7",
                    R.drawable.bg2,
                    "Author 7",
                    7,
                    "Genre 7",
                    "Description 7",
                    true,
                    4,
                    true
                ),
                BookData(
                    8,
                    "Title 8",
                    R.drawable.bg3,
                    "Author 8",
                    8,
                    "Genre 8",
                    "Description 8",
                    false,
                    4,
                    false
                ),
                BookData(
                    9,
                    "Title 9",
                    R.drawable.bg4,
                    "Author 9",
                    9,
                    "Genre 9",
                    "Description 9",
                    true,
                    4,
                    true
                ),
                BookData(
                    10,
                    "Title 10",
                    R.drawable.bg1,
                    "Author 10",
                    10,
                    "Genre 10",
                    "Description 10",
                    false,
                    4,
                    false
                ),
                BookData(
                    11,
                    "Title 11",
                    R.drawable.bg2,
                    "Author 11",
                    11,
                    "Genre 11",
                    "Description 11",
                    true,
                    4,
                    true
                ),
                BookData(
                    12,
                    "Title 12",
                    R.drawable.bg3,
                    "Author 12",
                    12,
                    "Genre 12",
                    "Description 12",
                    false,
                    4,
                    false
                ),
                BookData(
                    13,
                    "Title 13",
                    R.drawable.bg4,
                    "Author 13",
                    13,
                    "Genre 13",
                    "Description 13",
                    true,
                    4,
                    true
                ),
                BookData(
                    14,
                    "Title 14",
                    R.drawable.bg2,
                    "Author 14",
                    14,
                    "Genre 14",
                    "Description 14",
                    false,
                    4,
                    false
                ),
                BookData(
                    15,
                    "Title 15",
                    R.drawable.bg3,
                    "Author 15",
                    15,
                    "Genre 15",
                    "Description 15",
                    true,
                    4,
                    true
                ),
                BookData(
                    16,
                    "Title 16",
                    R.drawable.bg1,
                    "Author 16",
                    16,
                    "Genre 16",
                    "Description 16",
                    false,
                    4,
                    false
                ),
                BookData(
                    17,
                    "Title 17",
                    R.drawable.bg2,
                    "Author 17",
                    17,
                    "Genre 17",
                    "Description 17",
                    true,
                    4,
                    true
                ),
                BookData(
                    18,
                    "Title 18",
                    R.drawable.bg4,
                    "Author 18",
                    18,
                    "Genre 18",
                    "Description 18",
                    false,
                    4,
                    false
                ),
                BookData(
                    19,
                    "Title 19",
                    R.drawable.bg1,
                    "Author 19",
                    19,
                    "Genre 19",
                    "Description 19",
                    true,
                    4,
                    true
                ),
                BookData(
                    20,
                    "Title 20",
                    R.drawable.bg4,
                    "Author 20",
                    20,
                    "Genre 20",
                    "Description 20",
                    false,
                    4,
                    false
                ),
            )
        }
    val userdata: UserData
        get() {
            return UserData(
                id = 1,
                name = "Jaxen",
                email = "jaxdutta@gmail.com",
                username = "jaxdutta",
                profileImageResId = R.drawable.sample_pfp,
                followers = 86759,
                following = 6534,
                bookmarkPrivacy = true,
                about = "I am a writer\n" +
                        "sometimes\n" +
                        "haha",
                location = "New York",
                joined = "2021-10-10",
                imFollowing = true,
                isFollowing = false
            )
        }
    val otheruser: UserData
        get() {
            return UserData (
                id = 2,
                name = "Priscila",
                email = "poohbear@gmail.com",
                username = "poohbear",
                profileImageResId = R.drawable.sample_pfp_2,
                followers = 7658,
                following = 654,
                bookmarkPrivacy = true,
                about = "DO NOT talk to me!!! >:((",
                location = "New Hampshire",
                joined = "2021-20-10",
                imFollowing = true,
                isFollowing = false
            )
        }
}

fun getTestAppModel(): AppModel {
    val tds = TestDataSource()
    return AppModel(UserDataAll(tds.userdata, tds.books, tds.books), TestFablerService())
}
