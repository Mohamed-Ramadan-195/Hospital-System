# Hospital System 
Hospital System is an Android application designed using clean architecture with Kotlin. 
The application allows 6 users to use it, and each user has specific features. 
This makes it easy for the hospital management to facilitate the tasks of their staff. 
Advanced concepts and topics in Android components have been relied upon in building this application.

# Features 
* using it for this
  - Clean Architecture : Clean Architecture ensures that changes to one part of the system do not ripple through the entire codebase, making maintenance more efficient and reducing the risk of unintended side effects.
  - Navigation Component : the interactions that let users navigate across, into, and back out from the different screens of content within app.
  - MVVM : improve the performance of the application by caching data in the ViewModel.
           makes it easier to test the UI and the data separately.
  - Dagger Hilt : reduces the boilerplate of doing manual dependency injection in project.
  - Kotlin Coroutine : simplify operations that executes asynchronously.
  - Retrofit : for seamless API interactions.
  - Shared Preference : file containing key-value pairs for store and retrieve simple values ( info for users ) 
* others
  - Base Fragment : for implement abstract functions and handle view binding.
  - Certain screens have been used for design more than once to improve performance.
  - Use many dialogs : ( Bottom sheet dialog - Date Picker Dialog )

# Important Functions
 - Search by ( name or date )
 - Use fingerprint to take attendance ( Biometric )

# Users vs Operations
1)  **HR** : Adds hospital employees to the system by adding specific information.
2)  **Manager** : He can see all the cases in the hospital as well as assign staff to specific tasks and view and create reports.
3)  **Receptionst** : He can create a call to a specific doctor and give him the patient information.
4)  **Doctor** : He can respond with approval or rejection to the receptionist’s calls, and in case of acceptance, 
  he follows up on the case details and sends the medical record and medical measurements to the analysis employee or nurse.
5)  **Nurse** : She receives calls from the manager and is also responsible for receiving all measurements of patients' cases that have been assigned to her by a specific doctor.
6)  **Analysis** : Receives calls from manager, answers medical record and sends details to doctor.

There are some **common operations** between users such as:
- Displaying all employees is shared between HR and the manager.
- Tasks are assigned to all employees except the manager, who only creates them.
- Reports are assigned to all employees and can create it.
- Attendance is available to all employees without exception.
- All users except HR and Receptionst show case details for patient.

# Dependencies
 - Navigation Component
 - MVVM Architecture Pattern
 - Live Data & View Model
 - Dagger Hilt
 - Retrofit
 - Kotlin Coroutine
 - Shared Preference Database
 - Material Design & Lottie
 - Biometric

# How to install this app ?
 Just copy this url ``` https://github.com/Mohamed-Ramadan-195/HospitalSystem.git ``` and clone it in your android studio. 
