$(document).ready(function () {
    loadEvents();
    //viewEvent();
});

var selectedEvent; //TODO assign a value to this to use for url later

function loadEvents()
{
 $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/events',
        success: function(eventArray) //array of JSON items
        {
            var allevents = $('#allevents');
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

                allevents.append(box);

                //TODO change id string into int
                $('#allevents').click(function(event) {
                    var clickedEvent = JSON.stringify(event.target.id);
                    var chosenEventId = parseInt(clickedEvent); //to change into an int to put into url later
                    //document.getElementById("test").innerHTML = chosenEventId;
                    document.getElementById("test").innerHTML = clickedEvent;
                 })
            })
        },
        error: function()
        {
            alert('Failed to GET events from API');
        }
    })
}

/*function viewEvent() //click on an event and go to specific event page
{
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/events/' + chosenEventId,
        success: function(eventArray)
        {
            alert('selectedEvent id: ' + chosenEventId);


        },
        error: function()
        {
            alert('Failed to GET an event from API');
        }
    })
} */