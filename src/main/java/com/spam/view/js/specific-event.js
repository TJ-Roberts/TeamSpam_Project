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

    var header = '<h1>';
    header += org + ': ';
    header += title + ' ';
    header += '(event #' + eventId + ')';
    header += '<h1>'

    eventHeading.append(header);

    var square = '<div id="currentdetails">'

    var squareHeading = '<p id="heading">';
    squareHeading += 'Current Details' + '<br>';
    squareHeading += '</p>'

    var info = '<p>';
    info += 'User Id: ' + userId + '<br>';
    info += 'Location: ' + location + '<br>';
    info += 'Date: ' + date + '<br>';
    info += 'Time: ' + time + '<br>';
    info += 'Description: ' + description + '<br>';
    info += 'Available Food: ' + theFood + '<br>';
    info += '</p>';

    square += squareHeading;
    square += info;
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
               alert('Event updated');
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