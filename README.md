# SmartCampus App

SmartCampus is an Android mobile application developed to help students access the latest campus news and identify reported issues around the campus through an interactive map.

## Main Features

- Displays the latest campus news from Firebase Firestore
- Shows campus issues using dynamic Google Maps markers
- Displays issue details such as location, description, reporter and date
- Provides an About page with project and team information
- Includes a clickable link to the GitHub repository

## Technologies Used

- Android Studio
- Java
- XML
- Firebase Firestore
- Google Maps SDK for Android
- Git and GitHub

## Firestore Collections

### campus_news

Stores campus news information:

- title
- description
- category
- timestamp

### campus_issues

Stores reported campus issues:

- hazardType
- locationName
- description
- latitude
- longitude
- reporter
- timestamp

## Project Team

- Nur Fasihah Athilah binti Nordin
- Farah Nurliyana binti Mat Sabri
- Nabilah binti Nordin
- Siti Nur Hazirah binti Jamil
- Nur Atiqah Bashasha binti Rusli

## Programme

Bachelor of Information Technology (Hons.)  
UiTM Cawangan Kelantan, Kampus Machang

## Security Note

The Google Maps API key is stored locally and is not included in this public repository.

## Copyright

Copyright © 2026 SmartCampus App Team.
