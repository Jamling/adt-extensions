## adt-extensions
extend Android ADT, such as Quickly new/edit Activity/Service/BroadcastReceiver/ContentProvider in Android Project with a wizard and configure in AndroidManifest.xml, it's also eclipse plugin for Android-ORM
Quickly new Activity/Service/BroadcastReceiver/ContentProvider in Android Project with a wizard and configurate in AndroidManifest.xml.

Main function list:
  - New Activity/Service/BroadcastReceiver.
  - Customize action and category for intent-filter.
  - New (ORM) ContentProvider.
  - Customize authorities for ContentProvider.
  - Add/Remove ORM capability

## Install
### Marketplace client
  - Click "Help->Marketplace..." in Eclipse
  - Search "ADT extensions" as keyword to find "Android ADT extensions" plugin in result.
  - Click "Install"

## Tutorial
 ### New Activity
  -  Choose a package right click and select "New->Others" in popups menu.
  -  Select New Activity under Android category.(If you work in java perspective, New Activity would be visible popups menu.) to open wizard page.
  -  Check "with super suffix" to add super suffix to type name.(such with a Activity suffix)
  -  Select methos stubs which you want to create.
  -  Click "Add..." to add System action/category
  -  Click "Remove..." to remove selected action/category
  -  Click "Add custom" in popups to add custom action/category
  -  Click "Up/Down" to sort selected action/category
  -  Double click to edit seleted action/category 

### New Provider
  -  See New Activity steps to open New Provider wizard page
  -  Input authorities for provider

### New ORM Provoder
  - Select mapping aorm .java files or packages 
  - ADT extensions ->New ORM Provider
  - Input table name and authorities for provider

## Screenshot

see ![screen shot](https://Jamling.github.com/adt-extensions/wiki/Screenshot>).

## Reference

http://ieclipse.cn/p/adt-extensions
