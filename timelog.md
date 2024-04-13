# Project Timelog

| Date | Jaxen | Vansh | Kevin | Frank | Eric | Will | Task |
| - | - | - | - | - | - | - | - |
| `26-01-2024` | 1 | | | | | | Created project repo with `README.md` and `timelog.md` files |
| `28-02-2024` | | | | 1 | | | Created Android project, dependencies, and create project directory structure |
| `06-03-2024` | | | | 5 | 6 | | Implement custom tabs for UI mockup |
| `06-03-2024` | | | | 4 | | | Create custom search bar for UI mockup, tie different screens together |
| `08-03-2024` | 6 | | | | | | Researched UI/UX styles best suited with initiation of Figma experimentation |
| `10-03-2024` | 4 | | | | | | Conducted 4 user interviews based on wireframes and user intuition |
| `11-03-2024` | 4 | | | | | | Conducted 4 more user interviews based on wireframes and user intuition |
| `11-03-2024` | | 10 | | | | | Implemented the screens and incorporated frontend functionality for the initial prototype
| `11-03-2024` | | 4 | | | | | Polished the app prototype for the demo
| `13-03-2024` | 5 | | | | | | Started building a High Fidelity Figma Prototype |
| `14-03-2024` | 4 | | | | | | Finished building a High Fidelity Figma Prototype |
| `16-03-2024` | 5 | | | | | | Conducted 5 user interviews of hi-fi prototypes |
| `17-03-2024` | 3 | | | | | | Conducted 3 more user interviews of hi-fi prototypes |
| `20-03-2024` | | | | 10 | | | Investigate setting up stable diffusion |
| `21-03-2024` | | | | 10 | | | Investigate setting up stable diffusion - Conclusion: ROCm / PyTorch issues |
| `22-03-2024` | | | | 5 | | | Setting up stable diffusion for AI image gen - Solution: Relocate GPU to dedicated machine |
| `23-03-2024` | | | | 3 | | | Set up Stable Diffusion - Get NovelAI model working |
| `23-03-2024` | | 8 | | | | | Investigated Renpy, a library to display visual novels
| `24-03-2024` | 8 | | | | | | Started examining existing UI placeholder set-up and started replacing and modifying components |
| `25-03-2024` | 7 | | | | | | Continued attempt at fixing existing front-end structure |
| `26-03-2024` | 10 | | | | | | Scrapped previous UI to implement new polished version, starting with the home screen |
| `27-03-2024` | 11 | | | | | | Finalized home screen with tabs and switchable views |
| `28-03-2024` | 10 | | | | | | Structured the home screen book view to have a bookshelf-like view for the sake of neumorphism; started to fine tune the component design choices like search bars, logos, colours, and typography |
| `29-03-2024` | 8 | | | | | | Continued working on ways to clean up messy book and user lists, and started standardizing app colours |
| `29-03-2024` | | 9 | | | | | Tested sample visual novels, and tried to render them as standalone Android applications
| `30-03-2024` | 7 | | | | | | Created data types for users and made the front end for how books and users show up on lists |
| `30-03-2024` | | | | 6 | | | Research and implement code for OpenAI chat completion API |
| `30-03-2024` | | | | 8 | | | Research and implement OpenAI "tool calls/function calling" for predictable ChatGPT outputs |
| `30-03-2024` | | | | | 6 | | Created basic backend framework, Created database|
| `31-03-2024` | 10 | | | | | | Removed unneeded test assets and performed major restructuring to the UI; UI is split into pages and views; researched how pagination works in android and used Pager3 to allow user search and book search results to be displayed; finished hooking up most of the pages; added getUserDataAll API call |
| `31-03-2024` | | 5 | | | | | Learned how web-views work on frontend
| `31-04-2024` | | | | 4 | | | Implement visual novel story generation |
| `31-03-2024` | | | | 4 | | | Implement visual novel character generation |
| `31-03-2024` | | | | 6 | | | Script generation for each scene |
| `31-03-2024` | | | | | 6 | | Created basic backend framework, Created database |
| `01-04-2024` | 10 | | | | | | Added context menu tap and press pop up; refined the book cards on book lists; added icon extension support; fixed and rescaled profile headers; added settings button and removed follow button for own profile; made log in page; context menu view profile clickable action added; added some API endpoints |
| `01-04-2024` | | | | 5 | | | Figure out how to generate Stable Diffusion images and decode results into a file |
| `01-04-2024` | | | | | 8 | | Connected backend to frontend |
| `01-04-2024` | | 5 | | | | | Tried to integrate the visual novels as web elements using the Android web-view using Renpy Web (beta)
| `02-04-2024` | | 7 | | | | | Tried to integrate the visual novels as web elements using the Android web-view using Renpy Web (beta)
| `02-04-2024` | 7 | | | | | | Created the new book button; new book button pop up menu with prompt, genre, and any tags; long press on books brings up context menu now; app icon and splash screen updated; sign up page created that toggles with login page with lazy password validation |
| `02-04-2024` | | | | 8 | | | Generate prompts and images for backgrounds/character assets |
| `03-04-2024` | 3 | | | | | | Added novel details button; collaborated with backend team members to work on integrating the front- and the back-end |
| `04-04-2024` | | | | | 6 | | Connected backend to frontend |
| `04-04-2024` | | 5 | | | | | Continued working on the web-view integration (unsuccessful due to limited Web support from Renpy)
| `05-04-2024` | 6 | | | | | | Worked on polishing the novel details pop-up; restructured how followers, followings, bookmarks, and creations get stored and how the book views would reflect the same  |
| `05-04-2024` | | | | | 6 | | Connected backend to frontend |
| `06-04-2024` | 6 | | | | | | Fixed HttpFablerService dependencies / syntax errors; made IFablerService into an interface; fixed book long press; fixed book details pop up  |
| `06-04-2024` | | | | | 8 | | Finalized search function |
| `06-04-2024` | | 5 | | | | | Worked on installing visual novels locally as standalone applications (after major design change from web rendering)
| `07-04-2024` | 6 | | | | | | Added coroutine version of okHTTP3, added Followers and following page; amended FablerRepo accordingly; new components made, like FormTextField and BackAndTextHeader  |
| `07-04-2024` | | 5 | | | | | Worked on improving local VN management and compressing APK size
| `07-04-2024` | | | | | 10 | | Created login system |
| `08-04-2024` | | | | | 10 | | Completed the rest of the backend routes |
| `11-04-2024` | | | | | 10 | | Connected backend routes with frontend |
