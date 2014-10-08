# Preview-URL test

A small demo using OG to view Preview Elements.(Title/Description/Image)


After playing through Yahoo Mail Android App, and testing out the paste Preview Link.
A few points were determined:

* It seems like it does not intercept the Paste value, as you can see the original URL first, and then quickly changes to the Preview Link
* Typing in a URL does not change it to a Preview Link
* After viewing the HTML for popular websites like Engadget, the Verge - they seem to all have og:title/og:description. OG is Open Graph protocol.

---------------------------------
How this app works:

* Uses TextWatcher to determine if the CharSequence s, onTextChange is a URL, if so - create a PreviewURL.
* PreviewURL object, downloads the HTML in an AsyncTask, and using JSoup, each of the OG tags are parsed.
* Update the corresponding fields with the correct Data

A small preview:


![alt text](https://raw.githubusercontent.com/nealmanaktola/preview-url/master/preview-url.gif "Logo Title Text 1")
