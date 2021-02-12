$(document).ready(function () {
    loadEvents();
    viewEvent();
});

var selectedEvent;

function loadEvents()
{
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/events',
        success: function(eventArray)
        {
            var allEvents = $('#allevents');
            $.each(eventArray, function(index, event)
            {
                //stringify to be able to display
                var eventId = JSON.stringify(event.eventId); //for testing

                var org = JSON.stringify(event.organization);
                var title = JSON.stringify(event.eventTitle);
                var location = JSON.stringify(event.location);
                var date = JSON.stringify(event.eventDate);
                var time = JSON.stringify(event.eventTime);

                var box = '<div class="card-body">'

                var event = '<p id="' + eventId + '">'; //is the element id
                event += 'Event Id: ' + eventId + '<br>'; //for testing

                event += 'Organization: ' + org + '<br>';
                event += 'Title: ' + title + '<br>';
                event += 'Location: ' + location + '<br>';
                event += 'Date: ' + date + '<br>';
                event += 'Time: ' + time + '<br>';
                event += '</p>';
                box += event;
                box += '</div>';

                allEvents.append(box);

                $('#allevents').click(function(event) {
                    var clickedEvent = JSON.stringify(event.target.id);
                    var noQuotes = clickedEvent.replace(/"/g,"");
                    var chosenEventId = parseInt(noQuotes);

                    selectedEvent = chosenEventId;
                 })
            })
        },
        error: function()
        {
            alert('Failed to GET events from API');
        }
    })
}

function viewEvent() //TODO create box for that specific event in its own page
{
   $('#allevents').click(function(event){
        $.ajax({
        type: "GET",
        url: 'http://localhost:8080/api/events/' + selectedEvent,
        success: function() {
            alert('Retrieved individual event');













            window.location.href = "specific-event.html";
        },
        dataType: 'json',
        error: function() //have to double-click to avoid this
        {
            alert('Double-click to view a specific event');
        }
        });
   })
}

















