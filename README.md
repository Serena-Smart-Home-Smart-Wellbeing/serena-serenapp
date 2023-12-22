# Serena Android App

- [Serena Android App](#serena-android-app)
  - [Background](#background)
  - [Features](#features)
    - [User Accounts](#user-accounts)
    - [SerenBox Usage](#serenbox-usage)
      - [SerenBox Sessions](#serenbox-sessions)
    - [Mood Tracking](#mood-tracking)
    - [Spotify Mood-based Music Recommender](#spotify-mood-based-music-recommender)
    - [SerenPlace](#serenplace)
  - [How to set up and run](#how-to-set-up-and-run)
  - [UI Design](#ui-design)
  - [Members Contributions](#members-contributions)

## Background

SerenApp is the companion app for SerenBox. It provides necessary features for users to use their SerenBox and track their mood.
SerenApp works in tandem with [Serena Emotion Detector](https://github.com/Serena-Smart-Home-Smart-Wellbeing/serena-emotion-detector) and [SerenBox](https://github.com/Serena-Smart-Home-Smart-Wellbeing/serena-backend),
but it provides other features that can be used without a SerenBox.

## Features

### User Accounts

| ![Login Register Page](<demo/images/Login Register Page.png>) | ![Login Page](<demo/images/Login Page.png>) | ![User Profile](<demo/images/User Profile.png>)
| :-------------------------------------------------------------: | :-----------------------------------------: | :-------------------------------------------: |

The user can create accounts and login to our app using email & password. They can also
delete their account and all of their data.

### SerenBox Usage

| ![Home Menu](<demo/images/Home Menu.png>) | ![Device Credentials](<demo/images/Device Credentials.png>)| ![SerenBox Details](<demo/images/SerenBox Details.png>) |
| :----------------------------------------: |  :----------------------------------------: |:-----------------------------------------------------: |

In the `Home` menu, users can pair their SerenBox using its MAC address. Once paired, the user can
see a list of all thier SerenBox and see their details. Users can turn on their SerenBox
by running configuring and running sessions from the SerenBox details menu. This feature
is the only feature that relies on a SerenBox to work, the other features can be used
without a SerenBox.

#### SerenBox Sessions

| ![SerenBox Configuration](<demo/images/SerenBox Configuration.png>)  | ![SerenBox Session](<demo/images/SerenBox Session.png>) |
| :-----------------------------------------------------: |:-----------------------------------------------------: |

SerenApp allows the user to run SerenBox sessions where SerenBox will activate to diffuse the essential oils based on the users emotions.
Before running a session, the user can configure their session's duration, detection mode, and diffusion option.
A session only runs for the duration or until the user stops it.

The flow of SerenBox session logic can be drawn as follows:
![SerenBox Logic Flow](<./demo/images/SerenBox Logic Flow.png>)

When running a diffusion session, SerenApp functions as the controller for the diffusion of the user's SerenBox. SerenApp will periodically take and send
the user's photo to be analyzed by the model. From that emotion analysis results, SerenApp will use our APIs to toggle the correct slot in the user's SerenBox based on their emotions and session configuration.
The emotion analysis results sent by the model will contain the 7 emotions categorized into 2 groups below (for explanation regarding
how they are grouped, refer to our [Serena Emotion Detector](https://github.com/Serena-Smart-Home-Smart-Wellbeing/serena-emotion-detector) documentations):

```json
{
    "energetic": {
        "anger": 0.3,
        "fear": 0.2,
        "surprise": 0.2,
        "total": 0.7
    },
    "relax": {
        "disgust": 0.075,
        "joy": 0.075,
        "neutral": 0.075,
        "sadness": 0.075,
        "total": 0.3
    }
}
```

SerenApp will check the `total` field of each group and consider the current session's configurations:

1. Detection Mode  
   This controls how often SerenApp will adjust the SerenBox. If `Interval`, SerenApp will analyze the user's emotions and adjust
   SerenBox every 2-5 seconds. If `Once`, SerenApp will only analyze the user's emotions and adjust the SerenBox once at the start of the session.
2. Diffusion Option  
   This controls how to toggle each SerenBox slot. If `Match mood`, SerenApp will toggle the slot that best matches the user's
   current dominant emotions. Since SerenBox has 2 slots: slot A for energetic oils and slot B for relaxing oils; if the user
   is energetic, SerenApp will toggle slot A, and vice versa. If `Opposite of mood`, SerenApp will toggle the slot that best
   match the user's current passive emotions. For example, if the user is mainly energetic, SerenApp will toggle slot B, and vice versa.

### Mood Tracking

| ![Mood Menu](<demo/images/Mood Menu.png>) | ![Detect Emotion](<demo/images/Detect Emotion.png>) | ![Mood Details](<demo/images/Mood Details.png>) |
| :----------------------------------------------: |:---------------------------------------------------: | :----------------------------------------------: |

SerenApp records a history of all the user's emotion analysis results which can be seen
in the `Mood` menu. They can also detect their emotions using the `Detect Emotion` button
where the app will take a photo of them and send it to the model to be analyzed. The emotion
analysis records are taken from SerenBox sessions or the `Detect Emotion` feature.

### Spotify Mood-based Music Recommender

![Music Recommender](<demo/images/Music Recommender.png>)

We use Spotify API to recommend music based on the user's mood.
In the `Music` menu, they can click the `Recommend` button to get a playlist
based on their latest emotions analysis.

### SerenPlace

| ![SerenPlace](<demo/images/SerenPlace.png>) | ![SerenPlace Product details](<demo/images/SerenPlace Product details.png>) |
| :------------------------------------------: | :--------------------------------------------------------------------------: |

This is our in-app marketplace where users can purchase oil refills and products such as
the SerenBox.

## How to set up and run

These are the steps for using the application on a smartphone.

1. Download the apk file from [this link](https://drive.google.com/drive/folders/1Jr4M0aGxm2_mq6haQafRH213LsgLEl6M?usp=sharing).
2. Install application.
3. If you get a warning pop up from Google, you can press "More Details" then click "Install Anyway" and wait a moment.
4. Open application.
5. Finally, you can try out the application.

These are how to develop files in Android Studio. Here are the steps:

1. Download the zip file from this repository.
2. Extract the contents of the zip folder.
3. Open Android Studio, click on "Open," and select the extracted file.
4. Wait for a moment, then click on "Run App."
5. Wait for a moment again, and the app will appear on the Android Studio emulator.
6. Finally, you can try out the application.

## UI Design

![Figma UI](<demo/images/Figma UI.png>)

You can find our SerenApp UI design in [our Figma](https://www.figma.com/file/7eJ2fFyhpNZMCJ2XAalyCZ/SerenApp-UI?type=design&node-id=4%3A15&mode=design&t=co9Pr4qD6Svtu1Vv-1). You can
also checkout our [user flows in Figma](https://www.figma.com/file/xAC1bh7b1L6UA2HKhs9GCs/User-Flow-Serena?type=whiteboard&t=nloHfc7m7CqoyQLy-1).

## Members Contributions

| Name                    | Student ID       | Contribution                                                                                                                                                                                                                                       |
| ----------------------- | ---------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Muhammad Najib Izzulhaq | (MD) A548BKY4498 | Created Serena's UI using Figma and brought the design to life in Android Studio. This smooth integration guarantees a seamless shift from design to Serena app development, maintaining consistency and efficiency throughout the entire process. |
|      Muhammad Reyhan Ardiya Putra Wijaya      | (CC) C200BSY3485 | Created user flows. Created UI wireframe and prototype. |
