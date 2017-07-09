# BumbleBracket
A database to rank and store your dates be it Tinder, Bumble, Happn, or etc.

## Reason
Say you have a lot of boys or girls you are dating at the moment and you wanted to keep track of all of them and rank them so that you can feel like you are on the bachelorette or bachelor. More specifically you want to know who so far seems like the best fit for you. This is the database storing app to do that for you.

## Setup
The setup for this right now only has a local instance running, that is namely a couchbase instance you can find and download via https://www.couchbase.com/ and install for your appropriate machine. Turn on that server, if you have an actual server feel free to fork this and have your url replaced in the database configuration file.

Upsert is now possible with Firebase, no configuration needed unless you want to configure to your own firebase application, than input your own service-account.json file via generating a new private key and map the path to that file as well as change the Database URL to your own.

## Usage
The usage is via the CLI and is pretty intuitive, if you want to see the list of commands, just pass in the "-help" command to get a list, feel free to modify the cli usage to your own discretion. It has your typical database commands so you can use it accordingly

## Future implementation
The future implementation will be the UI aspect of actually building a bracket based off the rating, that is having seeding as well as an aesthetically pleasing database table. Furthermore, firebase useage will be implemented so that you and your friends can all use and look at it via the cloud.
