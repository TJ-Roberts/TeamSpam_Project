$(document).ready(function () {
    editEvent();
});

function editEvent()
{
    $('#editevent').click(function (event) {
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
           'dataType': 'json',
           success: function() { //TODO fix success/error
               alert('Event updated');
           },
           error: function () {
               alert('Unable to edit event');
           }
        })
    });
}