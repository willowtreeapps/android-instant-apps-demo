# instantapps-demo

This is a companion app called bumblebee to the blog post [An Introduction to Android Instant Apps](https://willowtreeapps.com/ideas/an-introduction-to-android-instant-apps)
by James Sun and Nish Tahir.

# Setup

Please see the [Set up your development environment](https://developer.android.com/topic/instant-apps/getting-started/setup.html) of the Android Instant apps documentation to set up your
development environment.

This project uses Firebase for the cloud database and file storage.
In order to build this project, you will need to create your own
Firebase account and set it up:

1. Once you have Firebase set up, go to the Firebase console and download
the `google-services.json` configuration file and copy it into the
2 empty `google-services.json` files in this project.
There is one in the base feature module (`instantapps-demo-base`), and one in the apk module (`instantapps-demo-apk`).
They can be identical.
2. Setup the database on Firebase by importing the
`bumblebee-11789-export.json` file in this repo.
3. You will need to upload your own images to the Firebase storage
under a folder name `item_images` and then update your catalog
in the database accordingly.
