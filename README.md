# instantapps-demo

This is an early demo of Android Instant Apps.
Please see your blog post here to learn what you need to get set up.

This project uses Firebase for the cloud database and file storage.
In order to build this project, you will need to create your own
Firebase account and set it up:

1. Once you have Firebase set up, go to the Firebase console and download
the `google-services.json` configuration file and copy it into the
2 empty `google-services.json` files in this project.
There is one in the base feature module, and one in the apk module.
They can be identical.
2. Setup the database on Firebase by importing the
`bumblebee-11789-export.json` file in this repo.
3. You will need to upload your own images to the Firebase storage
under a folder name `item_images` and then update your catalog
in the database accordingly.