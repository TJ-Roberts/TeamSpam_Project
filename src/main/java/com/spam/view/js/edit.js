$(document).ready(function () {
    //displayEventToEdit();
    editEvent();
});

var specificEventId;

/*function displayEventToEdit() //get individual event data from other page
{
    var selectedEvent = $('#selectedevent');

    var anEvent = localStorage.getItem("indivInfo");
    localStorage.clear(); //to clean the localStorage

    var eventId = JSON.parse(anEvent).eventId;
    var org = JSON.parse(anEvent).organization;
    var title = JSON.parse(anEvent).eventTitle;
    var location = JSON.parse(anEvent).location;
    var date = JSON.parse(anEvent).eventDate;
    var time = JSON.parse(anEvent).eventTime;
    var description = JSON.parse(anEvent).description;
    var theFood = JSON.parse(anEvent).foodType;

    var box = '<div class="card-body">'

    var info = '<p>';
    info += 'Organization: ' + org + '<br>';
    info += 'Title: ' + title + '<br>';
    info += 'Event Id: ' + eventId + '<br>';
    info += 'Location: ' + location + '<br>';
    info += 'Date: ' + date + '<br>';
    info += 'Time: ' + time + '<br>';
    info += 'Details: ' + description + '<br>';
    info += 'Available Food: ' + theFood + '<br>';

    info += 'test append';

    info += '</p>';

    box += info;
    box += '</div>';
    selectedEvent.append(box); //create box with info

    alert(specificEventId);

    specificEventId = eventId; //to use in url
} */

function editEvent()
{
    $('#editevent').click(function(event) {
        $.ajax({
           type: 'PUT',
           url: 'http://localhost:8080/api/edit/event',
           data: JSON.stringify({
                organizerId: $('#orgid').val(),
                organization: $('#org').val(),
                eventId: $('#eventid').val(), //TODO eventId is one from current page
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