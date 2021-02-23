# StackEX

Simple demo application that demonstrates searching users of StackOverflow by their display name using Stack Exchange API.

# App Screenshot

<table>
  <tr>
    <td>Search Screen</td>
    <td>Search Result</td>
    <td>User Detail Screen</td>
  </tr>
  <tr>
    <td><img src="Screenshots/Screenshot 2021-02-23 at 18.28.48.png" width=280 height=480></td>
    <td><img src="Screenshots/Screenshot 2021-02-23 at 18.35.47.png" width=280 height=480></td>
        <td><img src="Screenshots/Screenshot 2021-02-23 at 18.35.25.png" width=280 height=480></td>
  </tr>
</table>
 
# Project Description
 
### Design Pattern - MVVM
The application follows MVVM design pattern. AndroidViewModel class is used for the same. When ViewModel finishes the work, it updates the View using LiveData.
 


### Data Binding - LiveData
Whenever there is a new status or new set of data, those information are observed in the views using LiveData



### Coroutines
Coroutines is used in for downloading the data in IO thread and update the UI in Main thread seamlessly



### Retrofit
Retrofit is used for writing web service client
