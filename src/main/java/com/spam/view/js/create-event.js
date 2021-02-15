$(document).ready(function () {
    addEvent();
});

function addEvent()
{

    var attendeesArray = [];

    $('#addevent').click(function(event) {
        $.ajax({
           type: 'POST',
           url: 'http://localhost:8080/api/create/event/' + 1, //1 is default user (organizer) id
           data: JSON.stringify({
                attendees: attendeesArray,
                organization: $('#org').val(),
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
           'dataType': 'json',
           success: function() {
               alert('Your event has been created');
               $('#orgid').val(''),
               $('#org').val('');
               $('#title').val('');
               $('#location').val('');
               $('#date').val('');
               $('#time').val('');
               $('#description').val('');
               $('#food').val('');
           },
           error: function () {
               alert('Unable to add new event');
           }
        })
    });
}