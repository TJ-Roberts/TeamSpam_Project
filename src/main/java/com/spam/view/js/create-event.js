$(document).ready(function () {
    addEvent();
});

function addEvent()
{
    $('#addEvent').click(function (event) {
        $.ajax({
           type: 'POST',
           url: 'http://localhost:8080/api/create/event',
           data: JSON.stringify({
                organizerId: $('#orgId').val(),
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
               $('#orgId').val(''),
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