$(document).ready(function () {
    getIndividualData();
    editEvent();
    deleteEvent();
});

var specificEventId;

function getIndividualData() //get individual event data from other page
{
    var eventHeading = $('#eventheading');
    var specificEvent = $('#specificeventinfo');

    var anEvent = localStorage.getItem("indivInfo");
    localStorage.clear(); //to clean the localStorage

    var eventId = JSON.parse(anEvent).eventId;
    var org = JSON.parse(anEvent).organization;
    var userId = JSON.parse(anEvent).user.userId; //id of who organized event
    var title = JSON.parse(anEvent).eventTitle;
    var location = JSON.parse(anEvent).location;
    var date = JSON.parse(anEvent).eventDate;
    var time = JSON.parse(anEvent).eventTime;
    var description = JSON.parse(anEvent).description;
    var theFood = JSON.parse(anEvent).foodType;

    var header = '<div id="header">';
    header += org + ': ' + title + ' ' + '(event #' + eventId + ')';
    header += '</div>';

    eventHeading.append(header); //to here

    var square = '<div id="currentdetails">'

    var squareHeading = '<p id="heading">';
    squareHeading += 'Current Details' + '<br>';
    squareHeading += '</p>'

    var user = '<p>';
    user += 'User Id: ' + userId + '<br>';
    user += '</p>';

    var theLocation = '<p id="thelocation">';
    theLocation += 'Location: ' + location + '<br>';
    theLocation += '</p>';

    var theDate = '<p id="thedate">';
    theDate += 'Date: ' + date + '<br>';
    theDate += '</p>';

    var theTime = '<p id="thetime">';
    theTime += 'Time: ' + time + '<br>';
    theTime += '</p>';

    var theDescription = '<p id="thedescription">';
    theDescription += 'Description: ' + description + '<br>';
    theDescription += '</p>';

    var foodAv = '<p id="foodav">';
    foodAv += 'Available Food: ' + theFood + '<br>';
    foodAv += '</p>';

    square += squareHeading;
    square += user;

    square += theLocation;
    square += theDate;
    square += theTime;
    square += theDescription;
    square += foodAv;

    square += '</div>';
    specificEvent.append(square); //create box with info

    specificEventId = eventId; //to use in edit function
}

function editEvent()
{
    $('#edit').click(function(event) {
        $.ajax({
           type: 'PUT',
           url: 'http://localhost:8080/api/edit/event/details',
           data: JSON.stringify({
                userId: 1, //default
                organization: $('#org').val(),
                eventId: specificEventId,
                eventTitle: $('#title').val(),
                location: $('#location').val(),
                eventDate: $('#date').val(),
                eventTime: $('#time').val(),
                description: $('#description').val(),
                foodType: $('#food').val()
           }),
           headers: {
               'Accept': 'application/json',
               'Content-Type': 'application/json'
           },
           'dataType': 'text',
           success: function() {
                document.getElementById('eventheading').innerHTML = $('#org').val() + ': ' + $('#title').val() + ' ' + '(event #' + specificEventId + ')';
                document.getElementById('thelocation').innerHTML = 'Location: ' + $('#location').val();
                document.getElementById('thedate').innerHTML = 'Date: ' + $('#date').val();
                document.getElementById('thetime').innerHTML = 'Time: ' + $('#time').val();
                document.getElementById('thedescription').innerHTML = 'Description: ' + $('#description').val();
                document.getElementById('foodav').innerHTML = 'Food Available: ' + $('#food').val();
                alert('Event updated');

                //clear form input
                $('#org').val('');
                $('#title').val('');
                $('#location').val('');
                $('#date').val('');
                $('#time').val('');
                $('#description').val('');
                $('#food').val('');
           },
           error: function () {
               alert('Unable to edit event');
           }
        })
    });
}

function deleteEvent()
{
    $('#delete').click(function(event){
        $.ajax({
            type: 'DELETE',
            url: 'http://localhost:8080/api/delete/event/' + specificEventId,
            success: function() {
                alert('Event ' + specificEventId + ' deleted');
                window.location.href = "home.html"; //go back to home after event deleted
            },
            error: function () {
                alert('Delete unsuccessful');
            }
        });
    })
}