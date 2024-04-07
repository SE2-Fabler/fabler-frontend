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
                followerList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21,
                    22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45,
                    46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69,
                    70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93,
                    94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114,
                    115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134,
                    135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154,
                    155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174,
                    175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185),
                followingList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21),
                bookmarkPrivacy = true,
                about = "I am a writer\n" +
                        "sometimes\n" +
                        "haha",
                location = "New York",
                joined = "2021-10-10",
                following = true,
                isFollowing = false,
                creationList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20),
                bookmarkList = listOf(1, 2, 3, 4, 5, 6, 7, 14, 15, 16, 17, 18, 19, 20)
            )
        }
    val otheruser: UserData
        get() {
            return UserData (
                id = 2,
                name = "Bob Joe Alice",
                email = "bobjoealice",
                username = "bja",
                profileImageResId = R.drawable.sample_pfp,
                followerList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
                    26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49,
                    50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73,
                    74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97,
                    98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117,
                    118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137,
                    138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157,
                    158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177),
                followingList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21),
                bookmarkPrivacy = true,
                about = "DO NOT talk to me!!! >:((",
                location = "New Hampshire",
                joined = "2021-20-10",
                following = true,
                isFollowing = false,
                creationList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20),
                bookmarkList = listOf(1, 2, 3, 4, 5, 6, 7, 14, 15, 16, 17, 18, 19, 20)
            )
        }
}

fun getTestAppModel(): AppModel {
    val tds = TestDataSource()
    return AppModel(UserDataAll(tds.userdata, tds.books, tds.books), TestFablerService())
}
